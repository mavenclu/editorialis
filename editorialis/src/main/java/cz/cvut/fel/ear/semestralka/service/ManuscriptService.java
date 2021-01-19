package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.*;

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
