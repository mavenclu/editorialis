package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins="*")
@RepositoryRestResource
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Override
    Author save(Author entity);

    @Override
    Optional<Author> findById(Long aLong);

    @Override
    Iterable<Author> findAll();

    @Override
    void delete(Author entity);

    Optional<Author> findByEmail(String email);
}
