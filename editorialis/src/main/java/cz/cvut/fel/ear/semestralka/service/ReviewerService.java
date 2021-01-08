package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.ReviewId;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;

public interface ReviewerService {

    Reviewer findById(Long id);
    Iterable<Reviewer> findAll();
    Reviewer save(Reviewer reviewer);
    void delete(Reviewer reviewer);
}
