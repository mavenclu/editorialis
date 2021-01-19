package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);


    private final ReviewRepository rewRepo;

    @Autowired
    public ReviewServiceImpl(ReviewRepository rewRepo) {
        this.rewRepo = rewRepo;
    }


    @Override
    public Iterable<Review> findAll() {
        return rewRepo.findAll();
    }

    @Override
    public Review save(Review review) {
        Review rev = new Review();
        rev.setReviewer(review.getReviewer());
        rev.setManuscript(review.getManuscript());
        return rewRepo.save(rev);
    }

    @Override
    public void delete(Review review) {
        if (rewRepo.existsByManuscriptAndReviewer(review.getManuscript(), review.getReviewer())) {
            rewRepo.delete(review);
        } else {
            log.error("trying to delete empty review.");
            throw new IllegalArgumentException("could not delete review with manuscript id: " +
                    review.getManuscript().getManuscriptId() + " and review id: " +
                    review.getReviewer().getUserId());
        }
    }

    @Override
    public Iterable<Review> findAllByManuscript(Manuscript manuscript) {
        return rewRepo.findAllByManuscript(manuscript);
    }

    @Override
    public Iterable<Review> findAllByReviewer(Reviewer reviewer) {
        return rewRepo.findAllByReviewer(reviewer);
    }

    @Override
    public Review findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer) {
        return rewRepo.findByManuscriptAndReviewer(manuscript, reviewer).orElseThrow(
                () -> {
                    log.error("trying to find review by empty manuscript or reviewer");
                    throw new IllegalArgumentException("Could not get reviewer for provided manuscript and reviewer");
                }
        );
    }
}
