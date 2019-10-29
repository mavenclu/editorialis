package cz.cvut.fel.ear.service;

import cz.cvut.fel.ear.dao.ManuscriptRepository;
import cz.cvut.fel.ear.domain.Manuscript;
import cz.cvut.fel.ear.domain.ManuscriptStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManuscriptService {
    private ManuscriptRepository manuscriptRepository;

    @Autowired
    public ManuscriptService(ManuscriptRepository repository){
        manuscriptRepository= repository;
    }

    public int getNumberOfNewManuscripts(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.NEW).size();
    }
    public int getNumberOfPendingManuscripts(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.PENDING).size();
    }
    public int getNumberOfManuscriptsInReviewByReviewer(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_REVIEWER).size();
    }
    public int getNumberOfManuscriptsInRevisionByAuthor(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_AUTHOR).size();
    }
    public int getNumberOfManuscriptsWithCompleteReviews(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_EDITOR).size();
    }
    public int getNumberOfManuscriptsRejected(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.REJECTED).size();
    }
    public int getNumberOfManuscriptsAccepted(){
        return manuscriptRepository.findManuscriptsByManuscriptStatus(ManuscriptStatus.ACCEPTED).size();
    }
    public int getNumberOfManuscriptsInRevision(){
        return getNumberOfManuscriptsInRevisionByAuthor() + getNumberOfManuscriptsInReviewByReviewer();
    }
    public void rejectManuscriptOnOneClick(Manuscript manuscript, int option){
        manuscript.setManuscriptStatus(ManuscriptStatus.REJECTED);
        manuscript.setClosed(true);
//        sendRejectEmail(sender, option);
    }
}
