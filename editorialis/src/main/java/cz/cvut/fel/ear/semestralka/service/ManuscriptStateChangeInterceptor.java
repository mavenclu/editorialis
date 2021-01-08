package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvent;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManuscriptStateChangeInterceptor extends StateMachineInterceptorAdapter<ManuscriptState, ManuscriptEvent> {

    private final ManuscriptService manService;

    @Autowired
    public ManuscriptStateChangeInterceptor(ManuscriptService manService) {
        this.manService = manService;
    }

    @Override
    public void preStateChange(State<ManuscriptState, ManuscriptEvent> state, Message<ManuscriptEvent> message,
                               Transition<ManuscriptState, ManuscriptEvent> transition, StateMachine<ManuscriptState, ManuscriptEvent> stateMachine) {

        if (message != null)
            Optional.ofNullable((Long) message.getHeaders().getOrDefault(ManuscriptStateMachineServiceImpl.MANUSCRIPT_ID_HEADER, -1L))
                    .ifPresent(manuscriptId -> {
                        Manuscript man = manService.findById(manuscriptId);
                        man.setManuscriptStatus(state.getId());
                        manService.save(man);
                    });
    }
}

