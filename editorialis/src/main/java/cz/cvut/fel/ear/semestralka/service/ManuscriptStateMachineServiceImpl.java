package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.EditorRepository;
import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManuscriptStateMachineServiceImpl implements ManuscriptStateMachineService {
    public static final String MANUSCRIPT_ID_HEADER = "manuscript_id";
    private final ManuscriptStateChangeInterceptor manuscriptStateChangeInterceptor;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ManuscriptRepository manuscriptRepository;
    private EditorRepository editorRepository;
    private StateMachineFactory<ManuscriptState, ManuscriptEvent> factory;
    private ReviewerRepository reviewerRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public ManuscriptStateMachineServiceImpl(ManuscriptStateChangeInterceptor manuscriptStateChangeInterceptor,
                                             ManuscriptRepository manuscriptRepository, EditorRepository editorRepository,
                                             StateMachineFactory<ManuscriptState, ManuscriptEvent> factory,
                                             ReviewerRepository reviewerRepository, ReviewRepository reviewRepository) {
        this.manuscriptStateChangeInterceptor = manuscriptStateChangeInterceptor;
        this.manuscriptRepository = manuscriptRepository;
        this.editorRepository = editorRepository;
        this.factory = factory;
        this.reviewerRepository = reviewerRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Manuscript newManuscript(Manuscript manuscript) throws IllegalArgumentException {
        try {
            manuscript.setManuscriptStatus(ManuscriptState.NEW);
            manuscriptRepository.save(manuscript);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return manuscript;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> assignManuscriptToEditor(Long manuscriptId, Long editorId)
            throws IllegalArgumentException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscriptId);
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
            man.setEditor(editorRepository.findByUserId(editorId));
//            man.setManuscriptStatus(ManuscriptState.PENDING);
            sendEvent(manuscriptId, sm, ManuscriptEvent.manuscriptAssignedToEditor);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> assignManuscriptToEditor(Manuscript manuscript, Editor editor)
            throws NullPointerException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscript.getManuscriptId());
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscript.getManuscriptId());
            man.setEditor(editor);
//            man.setManuscriptStatus(ManuscriptState.PENDING);
            sendEvent(man.getManuscriptId(), sm, ManuscriptEvent.manuscriptAssignedToEditor);
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> rejectManuscript(Long manuscriptId) throws IllegalArgumentException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscriptId);
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
//            man.setManuscriptStatus(ManuscriptState.REJECTED);
            man.setClosed(true);
            sendEvent(manuscriptId, sm, ManuscriptEvent.manuscriptRejected);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> assignToReviewer(Long manuscriptId, Long reviewerId) throws IllegalArgumentException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscriptId);
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
            Reviewer reviewer = reviewerRepository.findByUserId(reviewerId);
            reviewer.setOnReview(true);
            man.setReviewer(reviewer);
            man.setManuscriptStatus(ManuscriptState.PEER_REVIEW);
            sendEvent(manuscriptId, sm, ManuscriptEvent.manuscriptAssignedToReviewer);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> completeReview(Long manuscriptId, Long reviewerId) throws IllegalArgumentException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscriptId);
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
            man.setManuscriptStatus(ManuscriptState.PRINCIPAL_REVIEW);
            Review review = reviewRepository.findByManuscriptAndReviewer(manuscriptRepository.findByManuscriptId(manuscriptId), reviewerRepository.findByUserId(reviewerId));
            List<Review> reviews = new ArrayList<>();
            reviews.add(review);
            man.setReviews(reviews);
            sendEvent(manuscriptId, sm, ManuscriptEvent.completedReview);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<ManuscriptState, ManuscriptEvent> acceptManuscript(Long manuscriptId) throws IllegalArgumentException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            sm = build(manuscriptId);
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
            man.setManuscriptStatus(ManuscriptState.ACCEPTED);
            man.setClosed(true);
            sendEvent(manuscriptId, sm, ManuscriptEvent.manuscriptAccepted);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;
    }

    private void sendEvent(Long manuscriptID, StateMachine<ManuscriptState, ManuscriptEvent> sm, ManuscriptEvent event) throws IllegalArgumentException {
        try {
            Message<ManuscriptEvent> msg = MessageBuilder.withPayload(event)
                    .setHeader(MANUSCRIPT_ID_HEADER, manuscriptID)
                    .build();

            sm.sendEvent(msg);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    private StateMachine<ManuscriptState, ManuscriptEvent> build(Long manuscriptId) throws NullPointerException {
        StateMachine<ManuscriptState, ManuscriptEvent> sm = null;
        try {
            Manuscript man = manuscriptRepository.findByManuscriptId(manuscriptId);
            sm = factory.getStateMachine(Long.toString(manuscriptId));
            sm.stop();
            sm.getStateMachineAccessor()
                    .doWithAllRegions(sma -> {
                        sma.addStateMachineInterceptor(manuscriptStateChangeInterceptor);
                        sma.resetStateMachine(new DefaultStateMachineContext<>(man.getManuscriptStatus(), null, null, null));
                    });
            sm.start();
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return sm;

    }
}
