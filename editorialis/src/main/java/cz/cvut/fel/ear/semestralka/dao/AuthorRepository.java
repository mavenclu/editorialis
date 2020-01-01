package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Iterable<Author> findAll(Sort sort);
    Page<Author> findAll(Pageable pageable);

    List<Author> findAll();
    List<Author> findByLastName(String lastName);
    List<Author> findByLastNameContaining(String containing);
    List<Author> findByEmail(String email);
    Author findByUserId(Long authorId);



}
