package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.Review;
import cz.cvut.fel.ear.semestralka.domain.ReviewId;
import cz.cvut.fel.ear.semestralka.domain.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ReviewRepository extends CrudRepository<Review, ReviewId> {

    @Override
    Optional<Review> findById(ReviewId reviewId);

    @Override
    Iterable<Review> findAll();

    @Override
    Review save(Review review);

    @Override
    void delete(Review entity);


    @RestResource(path = "mansucriptsreviews")
    Iterable<Review> findAllByManuscript(Manuscript manuscript);

    Iterable<Review> findAllByReviewer(Reviewer reviewer);

    Optional<Review> findByManuscriptAndReviewer(Manuscript manuscript, Reviewer reviewer);


}
