package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
@PreAuthorize("hasRole('ADMIN')")
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Override
    @PreAuthorize("isAuthenticated()")
    Optional<Category> findById(@Param("id") Long id);

    @Override
    @PreAuthorize("isAuthenticated()")
    Iterable<Category> findAll();

    @Override
    Category save(@Param("category") Category category);

    @Override
    void delete(@Param("category") Category category);


}
