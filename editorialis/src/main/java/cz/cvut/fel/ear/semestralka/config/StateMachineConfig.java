package cz.cvut.fel.ear.semestralka.config;

import cz.cvut.fel.ear.semestralka.config.actions.AssignToEditor;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvent;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.*;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<ManuscriptState, ManuscriptEvent> {

    private final Logger log = LoggerFactory.getLogger(StateMachineConfig.class);
    private final Action<ManuscriptState, ManuscriptEvent> assigToEditor;

//    @Bean
//    public StateMachine<ManuscriptState, ManuscriptEvent> buildMachine() throws Exception{
//        StateMachineBuilder.Builder<ManuscriptState, ManuscriptEvent> builder = StateMachineBuilder.builder();
//
//        builder.configureStates()
//                .withStates()
//                .initial(ManuscriptState.NEW)
//                .end(ManuscriptState.ACCEPTED)
//                .end(ManuscriptState.REJECTED)
//                .states(EnumSet.allOf(ManuscriptState.class));
//
//        builder.configureTransitions()
//                .withExternal().source(ManuscriptState.NEW)
//                .target(ManuscriptState.PENDING)
//                .event(ManuscriptEvent.manuscriptAssignedToEditor)
//                .and()
//                .withExternal()
//                .source(ManuscriptState.PENDING)
//                .target(ManuscriptState.PEER_REVIEW)
//                .event(ManuscriptEvent.manuscriptAssignedToReviewer)
//                .and()
//                .withExternal()
//                .source(ManuscriptState.PEER_REVIEW)
//                .target(ManuscriptState.PRINCIPAL_REVIEW)
//                .event(ManuscriptEvent.completedReview)
//                .and()
//                .withExternal()
//                .source(ManuscriptState.PRINCIPAL_REVIEW)
//                .target(ManuscriptState.ACCEPTED)
//                .event(ManuscriptEvent.manuscriptAccepted)
//                .and()
//                .withExternal()
//                .source(ManuscriptState.PENDING)
//                .target(ManuscriptState.REJECTED)
//                .event(ManuscriptEvent.manuscriptRejected)
//                .and()
//                .withExternal()
//                .source(ManuscriptState.PRINCIPAL_REVIEW)
//                .target(ManuscriptState.REJECTED)
//                .event(ManuscriptEvent.manuscriptRejected);
//
//
//        builder.configureConfiguration()
//                .withConfiguration()
//                .autoStartup(false)
//                .beanFactory(null)
//                .taskExecutor(null)
//                .taskScheduler(null)
//                .listener(listener());
//        return builder.build();
//    }
//
    @Override
    public void configure(StateMachineStateConfigurer<ManuscriptState, ManuscriptEvent> states) throws Exception {
        states
                .withStates()
                .initial(ManuscriptState.NEW)
                .end(ManuscriptState.ACCEPTED)
                .end(ManuscriptState.REJECTED)
                .states(EnumSet.allOf(ManuscriptState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ManuscriptState, ManuscriptEvent> transitions) throws Exception {
        transitions
//                .withExternal().source(ManuscriptStates.START)
//                .target(ManuscriptStates.NEW)
//                .event(ManuscriptEvents.manuscriptUploaded)
//                .and()
                .withExternal().source(ManuscriptState.NEW)
                .target(ManuscriptState.PENDING)
                .event(ManuscriptEvent.manuscriptAssignedToEditor)
                .and()
                .withExternal()
                .source(ManuscriptState.PENDING)
                .target(ManuscriptState.PEER_REVIEW)
                .event(ManuscriptEvent.manuscriptAssignedToReviewer)
                .and()
                .withExternal()
                .source(ManuscriptState.PEER_REVIEW)
                .target(ManuscriptState.PRINCIPAL_REVIEW)
                .event(ManuscriptEvent.completedReview)
                .and()
                .withExternal()
                .source(ManuscriptState.PRINCIPAL_REVIEW)
                .target(ManuscriptState.ACCEPTED)
                .event(ManuscriptEvent.manuscriptAccepted)
                .and()
                .withExternal()
                .source(ManuscriptState.PENDING)
                .target(ManuscriptState.REJECTED)
                .event(ManuscriptEvent.manuscriptRejected)
                .and()
                .withExternal()
                .source(ManuscriptState.PRINCIPAL_REVIEW)
                .target(ManuscriptState.REJECTED)
                .event(ManuscriptEvent.manuscriptRejected);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ManuscriptState, ManuscriptEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

//    private static final class StateMachineListener extends StateMachineListenerAdapter<ManuscriptStates, ManuscriptEvents> {
//        @Override
//        public void stateChanged(State<ManuscriptStates, ManuscriptEvents> from, State<ManuscriptStates, ManuscriptEvents> to) {
//            System.out.println("state changed from " + from.toString() + "        to" + to.toString());
//        }
//    }


//    @Bean
//    public StateMachineListener<ManuscriptState, ManuscriptEvent> listener() {
//        return new StateMachineListenerAdapter<>() {
//            @Override
//            public void stateChanged(State<ManuscriptState, ManuscriptEvent> from, State<ManuscriptState, ManuscriptEvent> to) {
//                System.out.println("State change to " + to.getId());
//            }
//        };
//    }

    @Bean
    StateMachineListener<ManuscriptState, ManuscriptEvent> listener() {
    return new StateMachineListenerAdapter<ManuscriptState, ManuscriptEvent>(){
        @Override
        public void transition(Transition<ManuscriptState, ManuscriptEvent> transition) {
            log.warn("move from:{} to:{}",
                    ofNullableState(transition.getSource()),
                    ofNullableState(transition.getTarget()));
        }

        @Override
        public void eventNotAccepted(Message<ManuscriptEvent> event) {
            log.error("event not accepted: {}", event);
        }

        private Object ofNullableState(State s) {
            return Optional.ofNullable(s)
                    .map(State::getId)
                    .orElse(null);
        }
    };
}
}
