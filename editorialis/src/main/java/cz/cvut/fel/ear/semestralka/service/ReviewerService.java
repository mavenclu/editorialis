package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Reviewer;

public interface ReviewerService {
    Reviewer getReviwerWithinCategoryWithLeastReviewsMade(Long cateogryId);
}
