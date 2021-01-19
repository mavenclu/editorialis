package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
//@PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN', 'EDITOR')")
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Override
//    @PreAuthorize("hasRole('ADMIN') or #author?.email == authentication.name")
    Author save(@Param("author") Author author);

    @Override
    Optional<Author> findById(@Param("id") Long id);

    @Override
    Iterable<Author> findAll();

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("author") Author author);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll(Iterable<? extends Author> entities);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll();

    Optional<Author> findByEmail(@Param("email") String email);
}
