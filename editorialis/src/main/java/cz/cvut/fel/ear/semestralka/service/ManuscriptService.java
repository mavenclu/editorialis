package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.*;
import org.springframework.statemachine.StateMachine;

public interface ManuscriptService {

    Manuscript newManuscript(Manuscript manuscript) throws NullPointerException;
    StateMachine<ManuscriptState, ManuscriptEvent> assignManuscriptToEditor(Long manuscriptId, Long editorId) throws Exception;
    StateMachine<ManuscriptState, ManuscriptEvent> assignManuscriptToEditor(Manuscript manuscript, Editor editor) throws Exception;
    StateMachine<ManuscriptState, ManuscriptEvent> rejectManuscript(Long manuscriptId);
    StateMachine<ManuscriptState, ManuscriptEvent> assignToReviewer(Long manuscriptId, Long reviewerId);
    StateMachine<ManuscriptState, ManuscriptEvent> completeReview(Long manuscriptId, Long reviewId);
    StateMachine<ManuscriptState, ManuscriptEvent> acceptManuscript(Long manuscriptId);


}
