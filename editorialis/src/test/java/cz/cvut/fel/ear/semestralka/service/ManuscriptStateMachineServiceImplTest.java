package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.*;
import cz.cvut.fel.ear.semestralka.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ManuscriptStateMachineServiceImplTest {
    @Autowired
    private final ManuscriptStateMachineService machineServ;

    @Autowired
    private final EditorService editorServ;
    @Autowired
    private final CategoryService catServ;
    @Autowired
    private final AuthorRepository authorRepo;
    @Autowired
    private final ReviewerRepository reviewerRepo;
    @Autowired
    private final ManuscriptService manServ;


    private Editor editor;
    private Category category;
    private Manuscript manuscript;
    private Reviewer reviewer;
    private Author author;

    @Autowired
    ManuscriptStateMachineServiceImplTest(ManuscriptStateMachineService machineServ, EditorService editorServ, CategoryService catServ, AuthorRepository authorRepo, ReviewerRepository reviewerRepo, ManuscriptService manServ) {
        this.machineServ = machineServ;
        this.editorServ = editorServ;
        this.catServ = catServ;
        this.authorRepo = authorRepo;
        this.reviewerRepo = reviewerRepo;
        this.manServ = manServ;
    }


    @BeforeEach
    void setUp(){
        category = new Category("Test Category");
        author = new Author.AuthorBuilder()
                .withEmail("author@test.com")
                .withFirstName("Petr")
                .withLastName("Novotny")
                .build();

        editor  = new Editor.EditorBuilder()
                .withFirstName("Jan")
                .withLastName("Novak")
                .withEmail("editor@test.com")
                .withCategory(category)
                .build();

        reviewer = new Reviewer.ReviewerBuilder()
                .withFirstName("Anne")
                .withLastName("Shirley")
                .withEmail("reviewer@test.com")
                .withCategory(category)
                .build();

        manuscript = new Manuscript.ManuscriptBuilder()
                .withTitle("My fancy new manuscript.")
                .withAuthors(List.of(author))
                .withCategory(category)
                .build();

        catServ.save(category);
        reviewerRepo.save(reviewer);
        editorServ.save(editor);
        authorRepo.save(author);
        manServ.save(manuscript);
    }

    @AfterEach
    void tearDown(){
//        editor.setEmail(null);
//        reviewer.setEmail(null);
//        sender.setEmail(null);
//        category.setName(null);
        manServ.delete(manuscript);
        editorServ.delete(editor);
        reviewerRepo.delete(reviewer);
        catServ.delete(category);
    }


    @Test
    void newManuscriptShouldSetStatusToNew() {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        org.assertj.core.api.Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check that service changed state of manuscript")
                .isEqualTo(ManuscriptState.NEW);
    }

    @Transactional
    @Test
    void assignManuscriptToEditorViaIDs() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manServ.findById(savedMan.getManuscriptId());
        org.assertj.core.api.Assertions.assertThat(machine.getState().getId())
                .as("check  machine state")
                .isEqualTo(ManuscriptState.PENDING);
        Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check manuscript status")
                .isEqualTo(ManuscriptState.PENDING);

    }
    @Transactional
    @Test
    void assignManuscriptToEditorViaObjects() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.assignManuscriptToEditor(savedMan, editor);
        Manuscript assignedMan = manServ.findById(savedMan.getManuscriptId());
        org.assertj.core.api.Assertions.assertThat(assignedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.PENDING);
        Assertions.assertThat(machine.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.PENDING);
    }

    @Transactional
    @Test
    void testAssignManuscriptToReviewerShouldChangeStateToPeerReviewTest() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machineEdit = machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manServ.findById(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> machineRev = machineServ.assignToReviewer(assignedMan.getManuscriptId(), reviewer.getUserId());
        Assertions.assertThat(machineRev.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.PEER_REVIEW);
        Assertions.assertThat(assignedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(assignedMan.getManuscriptStatus());


    }

    @Test
    void rejectManuscriptShouldChangeStateToRejectedAndClosedToTrueTest() throws Exception{
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> assignedMachine = machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manServ.findById(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> rejectedMachine = machineServ.rejectManuscript(assignedMan.getManuscriptId());
        Assertions.assertThat(rejectedMachine.getState().getId())
               .as("check machine state")
                .isEqualTo(ManuscriptState.REJECTED);
        Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.REJECTED);
        Assertions.assertThat(assignedMan.isClosed()).isTrue().as("check manuscript is closed");

    }

    @Test
    void completeReviewShouldChangeStateToPrincipalReview() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machineEdit = machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manServ.findById(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> machineRev = machineServ.assignToReviewer(assignedMan.getManuscriptId(), reviewer.getUserId());
        Manuscript reviewedMan = manServ.findById(assignedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> reviewedMachine = machineServ.completeReview(reviewedMan.getManuscriptId(), reviewer.getUserId());
        Assertions.assertThat(reviewedMachine.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.PEER_REVIEW);
        Assertions.assertThat(reviewedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.PEER_REVIEW);
    }

    @Transactional
    @Test
    void rejectAfterCompletedReviewsShouldChangeStateToRejectedAndClosedToTrueTest(){

    }


    @Test
    void acceptManuscript() {
    }

    @Test
    void assigneToEditorActionTest() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.assignManuscriptToEditor(savedMan, editor);


    }
}