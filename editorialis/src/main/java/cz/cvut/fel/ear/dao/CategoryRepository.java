package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
