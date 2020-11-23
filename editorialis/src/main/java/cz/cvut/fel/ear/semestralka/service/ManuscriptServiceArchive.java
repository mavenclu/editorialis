package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManuscriptServiceArchive {
    private ManuscriptRepository manuscriptRepository;

    @Autowired
    public ManuscriptServiceArchive(ManuscriptRepository repository){
        manuscriptRepository= repository;
    }

    public int getNumberOfNewManuscripts(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.NEW).size();
    }
    public int getNumberOfPendingManuscripts(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.PENDING).size();
    }
    public int getNumberOfManuscriptsInReviewByReviewer(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.PEER_REVIEW).size();
    }
    public int getNumberOfManuscriptsWithCompleteReviews(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.PRINCIPAL_REVIEW).size();
    }
    public int getNumberOfManuscriptsRejected(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.REJECTED).size();
    }
    public int getNumberOfManuscriptsAccepted(){
        return manuscriptRepository.findByManuscriptStatus(ManuscriptState.ACCEPTED).size();
    }
    public int getNumberOfManuscriptsInRevision(){
        return getNumberOfManuscriptsInReviewByReviewer();
    }

}
