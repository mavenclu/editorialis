package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewerServiceImpl implements ReviewerService{
    @Autowired
    private ReviewerRepository revRepository;

    @Override
    public Reviewer getReviwerWithinCategoryWithLeastReviewsMade(Long cateogryId) {
//        revRepository.findReviewersByCategory_CategoryId(cateogryId)
        return null;
    }
}
