package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;

public interface ReviewService {


    Iterable<Review> findAll();

    Review save(Review review);

    void delete(Review review);

    Iterable<Review> findAllByManuscript(Manuscript manuscript);

    Iterable<Review> findAllByReviewer(Reviewer reviewer);

    Review findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);

}
