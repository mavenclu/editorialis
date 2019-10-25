package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
