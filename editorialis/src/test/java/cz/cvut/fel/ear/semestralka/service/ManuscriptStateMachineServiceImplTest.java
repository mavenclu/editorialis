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

@SpringBootTest
class ManuscriptStateMachineServiceImplTest {
    @Autowired
    private ManuscriptStateMachineService manscrService;
    @Autowired
    private ManuscriptRepository manscrRepo;
    @Autowired
    private EditorRepository editorRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private ReviewerRepository reviewerRepo;

    private Editor editor;
    private Category category;
    private Author sender;
    private Manuscript manuscript;
    private Reviewer reviewer;


    @BeforeEach
    void setUp(){
        category = new Category("Test Category");

        editor  = new Editor.EditorBuilder()
                .withFirstName("Jan")
                .withLastName("Novak")
                .withEmail("editor@test.com")
                .withCategory(category)
                .build();
        sender = new Author.AuthorBuilder()
                .withFirstName("Thomas")
                .withLastName("Klein")
                .withEmail("sender@test.com")
                .build();
        reviewer = new Reviewer.ReviewerBuilder()
                .withFirstName("Anne")
                .withLastName("Shirley")
                .withEmail("reviewer@test.com")
                .withCategory(category)
                .build();

        manuscript = new Manuscript.ManuscriptBuilder()
                .withTitle("My fancy new manuscript.")
                .withSender(sender)
                .withCategory(category)
                .build();

        categoryRepo.save(category);
        reviewerRepo.save(reviewer);
        authorRepo.save(sender);
        editorRepo.save(editor);
        manscrRepo.save(manuscript);
    }

    @AfterEach
    void tearDown(){
//        editor.setEmail(null);
//        reviewer.setEmail(null);
//        sender.setEmail(null);
//        category.setName(null);
        manscrRepo.delete(manuscript);
        editorRepo.delete(editor);
        authorRepo.delete(sender);
        reviewerRepo.delete(reviewer);
        categoryRepo.delete(category);
    }


    @Test
    void newManuscriptShouldSetStatusToNew() {
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        org.assertj.core.api.Assertions.assertThat(savedMan.getManuscriptStatus())
                .as("check that service changed state of manuscript")
                .isEqualTo(ManuscriptState.NEW);
    }

    @Transactional
    @Test
    void assignManuscriptToEditorViaIDs() throws Exception {
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = manscrService.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manscrRepo.findByManuscriptId(savedMan.getManuscriptId());
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
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = manscrService.assignManuscriptToEditor(savedMan, editor);
        Manuscript assignedMan = manscrRepo.findByManuscriptId(savedMan.getManuscriptId());
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
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machineEdit = manscrService.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manscrRepo.findByManuscriptId(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> machineRev = manscrService.assignToReviewer(assignedMan.getManuscriptId(), reviewer.getUserId());
        Assertions.assertThat(machineRev.getState().getId())
                .as("check machine state")
                .isEqualTo(ManuscriptState.PEER_REVIEW);
        Assertions.assertThat(assignedMan.getManuscriptStatus())
                .as("check manuscript state")
                .isEqualTo(assignedMan.getManuscriptStatus());


    }

    @Test
    void rejectManuscriptShouldChangeStateToRejectedAndClosedToTrueTest() throws Exception{
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> assignedMachine = manscrService.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manscrRepo.findByManuscriptId(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> rejectedMachine = manscrService.rejectManuscript(assignedMan.getManuscriptId());
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
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machineEdit = manscrService.assignManuscriptToEditor(savedMan.getManuscriptId(), editor.getUserId());
        Manuscript assignedMan = manscrRepo.findByManuscriptId(savedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> machineRev = manscrService.assignToReviewer(assignedMan.getManuscriptId(), reviewer.getUserId());
        Manuscript reviewedMan = manscrRepo.findByManuscriptId(assignedMan.getManuscriptId());
        StateMachine<ManuscriptState, ManuscriptEvent> reviewedMachine = manscrService.completeReview(reviewedMan.getManuscriptId(), reviewer.getUserId());
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
        Manuscript savedMan = manscrService.newManuscript(manuscript);
        StateMachine<ManuscriptState, ManuscriptEvent> machine = manscrService.assignManuscriptToEditor(savedMan, editor);


    }
}