package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
