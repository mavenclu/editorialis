package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
//@PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'EDITOR')")
public interface ReviewerRepository extends CrudRepository<Reviewer, Long> {

    @Override
//    @PreAuthorize("hasRole('ADMIN') or #reviewer?.username == authentication.name")
    Reviewer save(@Param("reviewer") Reviewer reviewer);

    @Override
    Optional<Reviewer> findById(@Param("id") Long id);

    Optional<Reviewer> findByEmail(@Param("email") String email);

    @Override
    Iterable<Reviewer> findAll();

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("reviewer") Reviewer reviewer);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll();

    @Override
    boolean existsById(Long id);

    Iterable<Reviewer> findAllByOnReviewFalse();

    Iterable<Reviewer> findAllByOnReviewTrue();
}
