package cz.cvut.fel.ear.semestralka.config.actions;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvent;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AssignToEditor implements Action<ManuscriptState, ManuscriptEvent> {
    @Override
    public void execute(StateContext<ManuscriptState, ManuscriptEvent> context) {
        System.out.println("context message headers: " + context.getMessageHeaders());
        Long manId = (Long) context.getMessageHeader(context.getStateMachine());
        System.out.println("id from context message header: " + manId);
    }
}
