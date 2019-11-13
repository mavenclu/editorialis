package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> findAll();
    List<Author> findByLastName(String lastName);
    List<Author> findByLastNameContaining(String containing);
    List<Author> findByEmail(String email);
    Author findByUserId(Long authorId);



}
