package cz.cvut.fel.ear.semestralka.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateContext.Stage;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import java.util.logging.Logger;
import org.springframework.statemachine.state.State;



public class StateMachineLogListener extends StateMachineListenerAdapter {

    private static final Logger LOGGER = Logger.getLogger(StateMachineListener.class.getName());

    @Override
    public void stateChanged(State from, State to) {
        LOGGER.info(() -> String.format("Transitioned from %s to %s%n", from == null ? "none" : from.getId(), to.getId()));
    }
//    private final LinkedList<String> messages = new LinkedList<String>();
//
//    public List<String> getMessages() {
//        return messages;
//    }
//
//    public void resetMessages() {
//        messages.clear();
//    }
//
//    @Override
//    public void stateContext(StateContext<String, String> stateContext) {
//        if (stateContext.getStage() == Stage.STATE_ENTRY) {
//            messages.addFirst(stateContext.getStateMachine().getId() + " enter " + stateContext.getTarget().getId());
//        } else if (stateContext.getStage() == Stage.STATE_EXIT) {
//            messages.addFirst(stateContext.getStateMachine().getId() + " exit " + stateContext.getSource().getId());
//        }
//    }
}
