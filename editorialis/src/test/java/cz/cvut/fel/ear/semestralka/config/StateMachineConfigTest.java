package cz.cvut.fel.ear.semestralka.config;

import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvent;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

//@SpringJUnitConfig(StateMachineConfigTest.Config.class)
@SpringBootTest
class StateMachineConfigTest {

    private StateMachine<ManuscriptState, ManuscriptEvent> stateMachine;
    @Autowired
    private StateMachineFactory<ManuscriptState, ManuscriptEvent> factory;

    @BeforeEach
    void setUp() throws Exception {
        stateMachine = factory.getStateMachine(UUID.randomUUID());
        stateMachine.start();
    }

    @Test
    void intTest() {
        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptState.NEW);

        Assertions.assertThat(stateMachine).isNotNull();
    }

    @Test
    void fromStartToAcceptedTest() {
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToEditor);
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToReviewer);
        stateMachine.sendEvent(ManuscriptEvent.completedReview);
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAccepted);

        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptState.ACCEPTED);
    }

    @Test
    void fromStartToRejectedTest() {
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToEditor);
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToReviewer);
        stateMachine.sendEvent(ManuscriptEvent.completedReview);
        stateMachine.sendEvent(ManuscriptEvent.manuscriptRejected);

        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptState.REJECTED);
    }

    @Test
    void fromStartToRejectedRightAwayTest() {
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToEditor);
        stateMachine.sendEvent(ManuscriptEvent.manuscriptRejected);

        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptState.REJECTED);
    }

    @Test
    void eventOutOfOrderWontChangeStateTest() {
        stateMachine.sendEvent(ManuscriptEvent.manuscriptAssignedToReviewer);
        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptState.NEW);
    }


}