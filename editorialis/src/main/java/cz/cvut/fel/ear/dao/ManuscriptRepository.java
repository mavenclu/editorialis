package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Manuscript;
import org.springframework.data.repository.CrudRepository;

public interface ManuscriptRepository extends CrudRepository<Manuscript, Long> {
}
