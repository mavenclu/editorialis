package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ManuscriptStateMachineServiceImplTest {
    private final ManuscriptStateMachineService machineServ;
    private final EditorService editorServ;
    private final CategoryService catServ;
    private final AuthorService authorServ;
    private final ReviewerService reviewerServ;
    private final ManuscriptService manServ;
    private final ReviewService reviewServ;


    private Editor editor;
    private Category category;
    private Manuscript manuscript;
    private Reviewer reviewer;
    private Author author;
    private Review review;

    @Autowired
    ManuscriptStateMachineServiceImplTest(ManuscriptStateMachineService machineServ, EditorService editorServ, CategoryService catServ, AuthorService authorServ, ReviewerService reviewerServ, ManuscriptService manServ, ReviewService reviewServ){
        this.machineServ = machineServ;
        this.editorServ = editorServ;
        this.catServ = catServ;
        this.authorServ = authorServ;
        this.reviewerServ = reviewerServ;
        this.manServ = manServ;
        this.reviewServ = reviewServ;
    }

    @Transactional
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
                .withEmail("reviewer.anne@test.com")
                .withCategory(category)
                .build();

        manuscript = new Manuscript.ManuscriptBuilder()
                .withTitle("My fancy new manuscript.")
                .withAuthors(List.of(author))
                .withCategory(category)
                .build();
        review = new Review(manuscript, reviewer);

        catServ.save(category);
        reviewerServ.save(reviewer);
        editorServ.save(editor);

        authorServ.save(author);
//        manServ.save(manuscript);
    }
    @Transactional
    @AfterEach
    void tearDown(){
//        editor.setEmail(null);
//        reviewer.setEmail(null);
//        sender.setEmail(null);
//        category.setName(null);
        manServ.delete(manuscript);
//        reviewServ.delete(review);
        authorServ.delete(author);
        editorServ.delete(editor);
        reviewerServ.delete(reviewer);
        catServ.delete(category);
    }

    @Transactional
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
    @Transactional
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

    @Transactional
    @Test
    void completeReviewShouldChangeStateToPrincipalReview() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        machineServ.assignToReviewer(savedMan.getManuscriptId(), reviewer.getUserId());
        reviewServ.save(review);
        machineServ.completeReview(savedMan.getManuscriptId(), reviewer.getUserId());

        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.completeReview(savedMan.getManuscriptId(), reviewer.getUserId());

        Assertions.assertThat(machine.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.PRINCIPAL_REVIEW);
        Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.PRINCIPAL_REVIEW);
    }

    @Transactional
    @Test
    void rejectAfterCompletedReviewsShouldChangeStateToRejectedAndClosedToTrueTest() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.rejectManuscript(manuscript.getManuscriptId());
        Assertions.assertThat(machine.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.REJECTED);
        Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.REJECTED);

    }

    @Transactional
    @Test
    void acceptManuscript() throws Exception {
        Manuscript savedMan = machineServ.newManuscript(manuscript);
        machineServ.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        machineServ.assignToReviewer(savedMan.getManuscriptId(), reviewer.getUserId());
        reviewServ.save(review);
        machineServ.completeReview(savedMan.getManuscriptId(), reviewer.getUserId());
        StateMachine<ManuscriptState, ManuscriptEvent> machine = machineServ.acceptManuscript(savedMan.getManuscriptId());
        Assertions.assertThat(machine.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.ACCEPTED);
        Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(ManuscriptState.ACCEPTED);
    }



}