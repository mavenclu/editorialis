package cz.cvut.fel.ear.semestralka.config;

import cz.cvut.fel.ear.semestralka.core.ManuscriptEvents;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class ManuscriptFlowStateMachineConfig extends
        EnumStateMachineConfigurerAdapter<ManuscriptStates, ManuscriptEvents> {


    @Override
    public void configure(StateMachineStateConfigurer<ManuscriptStates, ManuscriptEvents> states) throws Exception {
        states
                .withStates()
                .initial(ManuscriptStates.START)
                .end(ManuscriptStates.ACCEPTED)
                .end(ManuscriptStates.REJECTED)
                .states(EnumSet.allOf(ManuscriptStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ManuscriptStates, ManuscriptEvents> transitions) throws Exception {
        transitions
                .withExternal().source(ManuscriptStates.START)
                .target(ManuscriptStates.NEW)
                .event(ManuscriptEvents.manuscriptUploaded)
                .and()
                .withExternal().source(ManuscriptStates.NEW)
                .target(ManuscriptStates.PENDING)
                .event(ManuscriptEvents.manuscriptAssignedToEditor)
                .and()
                .withExternal()
                .source(ManuscriptStates.PENDING)
                .target(ManuscriptStates.IN_REVISION_BY_REVIEWER)
                .event(ManuscriptEvents.manuscriptAssignedToReviewer)
                .and()
                .withExternal()
                .source(ManuscriptStates.IN_REVISION_BY_REVIEWER)
                .target(ManuscriptStates.IN_REVISION_BY_EDITOR)
                .event(ManuscriptEvents.reviewUploaded)
                .and()
                .withExternal()
                .source(ManuscriptStates.IN_REVISION_BY_EDITOR)
                .target(ManuscriptStates.ACCEPTED)
                .event(ManuscriptEvents.manuscriptAccepted)
                .and()
                .withExternal()
                .source(ManuscriptStates.PENDING)
                .target(ManuscriptStates.REJECTED)
                .event(ManuscriptEvents.manuscriptRejected)
                .and()
                .withExternal()
                .source(ManuscriptStates.IN_REVISION_BY_EDITOR)
                .target(ManuscriptStates.REJECTED)
                .event(ManuscriptEvents.manuscriptRejected);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ManuscriptStates, ManuscriptEvents> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(new StateMachineListener());
    }

    private static final class StateMachineListener extends StateMachineListenerAdapter<ManuscriptStates, ManuscriptEvents> {
        @Override
        public void stateChanged(State<ManuscriptStates, ManuscriptEvents> from, State<ManuscriptStates, ManuscriptEvents> to) {
            System.out.println("state changed from " + from.toString() + "        to" + to.toString());
        }
    }
}
