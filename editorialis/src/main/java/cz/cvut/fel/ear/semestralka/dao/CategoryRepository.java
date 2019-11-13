package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    List<Category> findAll();
    Category findCategoryByName(String name);
    Category findCategoryByCategoryId(Long categoryId);

    @RestResource(rel = "name-contains", path = "containsName")
    Page<Category> findCategoriesByNameContaining(@Param("name") String containing, Pageable pageable);

}
