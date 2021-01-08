package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewId;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

interface ReviewService {

    Review findById(ReviewId id);
    Iterable<Review> findAll();
    Review save(Review review);
    void delete(Review review);
    Iterable<Review> findAllByManuscript(Manuscript manuscript);
    Iterable<Review> findAllByReviewer(Reviewer reviewer);
    Review findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);

}
