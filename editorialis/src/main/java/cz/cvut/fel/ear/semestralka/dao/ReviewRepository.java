package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewId;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import java.util.Optional;
@CrossOrigin(origins="*")
@RepositoryRestResource
public interface ReviewRepository extends CrudRepository<Review, ReviewId> {

    @Override
    Iterable<Review> findAll();

    @Override
    Review save(Review review);

    @Override
    void delete(Review entity);

    Iterable<Review> findAllByManuscript(Manuscript manuscript);
    Iterable<Review> findAllByReviewer(Reviewer reviewer);
    Optional<Review> findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);
    boolean existsByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);
}
