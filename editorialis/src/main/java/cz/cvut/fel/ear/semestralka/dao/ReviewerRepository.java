package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RepositoryRestResource
public interface ReviewerRepository extends CrudRepository<Reviewer, Long> {

    @Override
    Reviewer save(Reviewer entity);

    @Override
    Optional<Reviewer> findById(Long aLong);

    Optional<Reviewer> findByEmail(String email);

    @Override
    Iterable<Reviewer> findAll();

    @Override
    void delete(Reviewer entity);

    @Override
    boolean existsById(Long id);

    Iterable<Reviewer> findAllByOnReviewFalse();
    Iterable<Reviewer> findAllByOnReviewTrue();
}
