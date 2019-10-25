package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Review;
import cz.cvut.fel.ear.domain.ReviewSuggestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    Review findReviewByDocumentId(Long reviewId);
    List<Review> findReviewsByManuscript_Title(String title);
    List<Review> findReviewsByManuscript_DocumentId(long manuscriptId);
    List<Review> findReviewsByReviewer_Name(String lastName);
    List<Review> findReviewsByReviewer_UserId(long reviewerId);
    List<Review> findReviewsByReviewSuggestion(ReviewSuggestion suggestion);


}
