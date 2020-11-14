package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStates;
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
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.NEW).size();
    }
    public int getNumberOfPendingManuscripts(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.PENDING).size();
    }
    public int getNumberOfManuscriptsInReviewByReviewer(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.PEER_REVIEW).size();
    }
    public int getNumberOfManuscriptsWithCompleteReviews(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.PRINCIPAL_REVIEW).size();
    }
    public int getNumberOfManuscriptsRejected(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.REJECTED).size();
    }
    public int getNumberOfManuscriptsAccepted(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptStates.ACCEPTED).size();
    }
    public int getNumberOfManuscriptsInRevision(){
        return getNumberOfManuscriptsInReviewByReviewer();
    }

}
