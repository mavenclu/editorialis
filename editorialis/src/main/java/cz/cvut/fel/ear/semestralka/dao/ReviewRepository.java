package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewId;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
//@PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'EDITOR', 'REVIEWER')")
public interface ReviewRepository extends CrudRepository<Review, ReviewId> {

    @Override
    Iterable<Review> findAll();

    @Override
    Review save(@Param("review") Review review);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("review") Review review);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll();

    //    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECOTR', 'EDITOR')" +
//            "or #manuscript?.reviewer?.email == authentication.name")
    Iterable<Review> findAllByManuscript(Manuscript manuscript);

    //    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECOTR', 'EDITOR')" +
//            "or #reviewer?.email == authentication.name")
    Iterable<Review> findAllByReviewer(Reviewer reviewer);

    //    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECOTR', 'EDITOR')" +
//            "or #reviewer?.email == authentication.name")
    Optional<Review> findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);

    boolean existsByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);
}
