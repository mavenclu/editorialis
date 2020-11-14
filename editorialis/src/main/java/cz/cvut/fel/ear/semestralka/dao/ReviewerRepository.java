package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource
public interface ReviewerRepository extends PagingAndSortingRepository<Reviewer, Long> {

    List<Reviewer> findAll();
    List<Reviewer> findReviewersByCategory_Name(String categoryName);
    List<Reviewer> findReviewersByCategory_CategoryId(Long id);
    List<Reviewer> findReviewersByName(String name);
    List<Reviewer> findReviewersByLastName(String lastName);
    List<Reviewer> findByOnReview(boolean val);
    List<Reviewer> findByOnReviewFalse();
    Reviewer findReviewerByUserId(Long reviewerId);
    Reviewer findReviewerByEmail(String email);

}
