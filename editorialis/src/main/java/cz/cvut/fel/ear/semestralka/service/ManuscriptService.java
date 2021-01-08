package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Category;
import cz.cvut.fel.ear.semestralka.domain.Editor;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import cz.cvut.fel.ear.semestralka.domain.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ManuscriptService {
    Manuscript findById(long id);
    Iterable<Manuscript> findAll();
    Manuscript save(Manuscript manuscript);
    void delete(Manuscript manuscript);
    void deleteById(long id);

    Editor getManuscriptEditor(Manuscript manuscript);
    Category getManuscriptCategory(Category category);
    Iterable<Reviewer> getManuscriptReviewers(Manuscript manuscript);
    Iterable<Review> getManuscriptReviews(Manuscript manuscript);
    Iterable<Manuscript> findALlByCategory(Category category);

}
