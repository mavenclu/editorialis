package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStatus;
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
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.NEW).size();
    }
    public int getNumberOfPendingManuscripts(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.PENDING).size();
    }
    public int getNumberOfManuscriptsInReviewByReviewer(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_REVIEWER).size();
    }
    public int getNumberOfManuscriptsInRevisionByAuthor(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_AUTHOR).size();
    }
    public int getNumberOfManuscriptsWithCompleteReviews(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.IN_REVISION_BY_EDITOR).size();
    }
    public int getNumberOfManuscriptsRejected(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.REJECTED).size();
    }
    public int getNumberOfManuscriptsAccepted(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.ACCEPTED).size();
    }
    public int getNumberOfManuscriptsInRevision(){
        return getNumberOfManuscriptsInRevisionByAuthor() + getNumberOfManuscriptsInReviewByReviewer();
    }

}
