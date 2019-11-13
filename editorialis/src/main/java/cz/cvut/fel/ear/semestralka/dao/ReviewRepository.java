package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewSuggestion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    Review findReviewByDocumentId(Long reviewId);
    List<Review> findReviewsByManuscript_Title(String title);
    List<Review> findReviewsByManuscript_DocumentId(long manuscriptId);
    List<Review> findReviewsByReviewer_Name(String lastName);
    List<Review> findReviewsByReviewer_UserId(long reviewerId);
    List<Review> findReviewsByReviewSuggestion(ReviewSuggestion suggestion);
    List<Review> findByDateTimeAssigned(LocalDateTime localDateTime);
    List<Review> findByDateTimeAssignedBeforeAndDateTimeUploaded(LocalDateTime assigned, LocalDateTime uploaded);


}
