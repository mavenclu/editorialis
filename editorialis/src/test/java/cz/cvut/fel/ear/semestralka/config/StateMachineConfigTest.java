package cz.cvut.fel.ear.semestralka.config;

import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvents;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStates;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//@SpringJUnitConfig(StateMachineConfigTest.Config.class)
@SpringBootTest
class StateMachineConfigTest {
    private StateMachine<ManuscriptStates, ManuscriptEvents> stateMachine;
    @Autowired
    private StateMachineFactory<ManuscriptStates, ManuscriptEvents> factory;

    @BeforeEach
    void setUp() throws Exception {
        stateMachine = factory.getStateMachine(UUID.randomUUID());
    }

    @Test
    void intTest(){
        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptStates.START);

        Assertions.assertThat(stateMachine).isNotNull();
    }

    @Test
    void fromStartToAcceptedTest(){
        stateMachine.sendEvent(ManuscriptEvents.manuscriptUploaded);
        stateMachine.sendEvent(ManuscriptEvents.manuscriptAssignedToEditor);
        stateMachine.sendEvent(ManuscriptEvents.manuscriptAssignedToReviewer);
        stateMachine.sendEvent(ManuscriptEvents.reviewUploaded);
        stateMachine.sendEvent(ManuscriptEvents.manuscriptAccepted);

        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptStates.ACCEPTED);
    }
    @Test
    void fromStartToRejectedTest(){
        stateMachine.sendEvent(ManuscriptEvents.manuscriptUploaded);
        stateMachine.sendEvent(ManuscriptEvents.manuscriptAssignedToEditor);
        stateMachine.sendEvent(ManuscriptEvents.manuscriptRejected);

        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptStates.REJECTED);
    }
    @Test
    void eventOutOfOrderWontChangeStateTest(){
        stateMachine.sendEvent(ManuscriptEvents.manuscriptAssignedToReviewer);
        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(ManuscriptStates.START);
    }
    @Configuration
    public  class Config {
    }
}