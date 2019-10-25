package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> findAll();
    List<Author> findAuthorsByLastName(String lastName);
    List<Author> findAuthorsByLastNameContaining(String containing);
    List<Author> findAuthorsByLastNameStartingWith(String starting);
    List<Author> findAuthorsByCategory_Name(String category);
    List<Author> findAuthorsByEmail(String email);
    Author findAuthorByUserId(Long authorId);


}
