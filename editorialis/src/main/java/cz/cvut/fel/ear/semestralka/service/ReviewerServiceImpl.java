package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewerServiceImpl implements ReviewerService{
    private final Logger log = LoggerFactory.getLogger(ReviewerServiceImpl.class);

    private final ReviewerRepository reviewerRepo;
    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewRepoer) {
        this.reviewerRepo = reviewRepoer;
    }


    @Override
    public Reviewer findById(Long id) {
        return reviewerRepo.findById(id).orElseThrow(
                () -> {
                    log.error("searching for empty reviewer");
                    throw new IllegalArgumentException("Could not find reviewer with id: " + id);
                }
        );
    }

    @Override
    public Iterable<Reviewer> findAll() {
        return reviewerRepo.findAll();
    }

    @Override
    public Reviewer save(Reviewer reviewer) {
        return reviewerRepo.save(reviewer);
    }

    @Override
    public void delete(Reviewer reviewer) {
        if (reviewerRepo.findById(reviewer.getUserId()).isPresent()){
            reviewerRepo.delete(reviewer);
        }else {
            log.error("trying to delete empty reviewer");
            throw new IllegalArgumentException("Could not delete reviewer with id: " + reviewer.getUserId());
        }
    }

    @Override
    public Reviewer findByEmail(String email) {
        return reviewerRepo.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("could not find reviewer with email: " + email)
        );
    }


}
