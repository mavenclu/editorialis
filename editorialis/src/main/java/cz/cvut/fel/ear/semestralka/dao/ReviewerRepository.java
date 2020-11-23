package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource
public interface ReviewerRepository extends PagingAndSortingRepository<Reviewer, Long> {

    List<Reviewer> findAll();
    List<Reviewer> findByCategory_Name(String categoryName);
    List<Reviewer> findByCategory_CategoryId(Long id);
    List<Reviewer> findByName(String name);
    List<Reviewer> findByLastName(String lastName);
    List<Reviewer> findByOnReview(boolean val);
    List<Reviewer> findByOnReviewFalse();
    Reviewer findByUserId(Long reviewerId);
    Reviewer findByEmail(String email);


}
