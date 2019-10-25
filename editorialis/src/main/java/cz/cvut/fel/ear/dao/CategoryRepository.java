package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Author;
import cz.cvut.fel.ear.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    List<Category> findAll();
    Category findCategoryByName(String name);
    Category findCategoryByCategoryId(Long categoryId);

    @RestResource(rel = "name-contains", path = "containsName")
    Page<Category> findCategoriesByNameContaining(@Param("name") String containing, Pageable pageable);

}
