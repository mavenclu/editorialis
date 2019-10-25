package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Review;
import cz.cvut.fel.ear.domain.Reviewer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewerRepository extends PagingAndSortingRepository<Reviewer, Long> {

    List<Reviewer> findAll();
    List<Reviewer> findReviewersByCategory_Name(String categoryName);
    List<Reviewer> findReviewersByCategory_CategoryId(Long id);
    List<Reviewer> findReviewersByName(String name);
    List<Reviewer> findReviewersByLastName(String lastName);
    Reviewer findReviewerByUserId(Long reviewerId);
    Reviewer findReviewerByEmail(String email);

}
