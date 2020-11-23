package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewersSuggestion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;
@RepositoryRestResource
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {


    Review findByReviewId(Long reviewId);
    List<Review> findByManuscript_Title(String title);
    List<Review> findByManuscript_ManuscriptId(long manuscriptId);
    List<Review> findByReviewer_UserId(long reviewerId);
    List<Review> findByReviewersSuggestion(ReviewersSuggestion suggestion);
    List<Review> findAll();

}
