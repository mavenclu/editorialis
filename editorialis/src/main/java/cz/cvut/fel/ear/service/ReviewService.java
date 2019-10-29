package cz.cvut.fel.ear.service;

import cz.cvut.fel.ear.dao.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public int getNumberOfReviewsInDelay(){
        return reviewRepository.findByDateTimeAssignedBeforeAndDateTimeUploaded(LocalDateTime.now().minusDays(10), null).size();
    }
}
