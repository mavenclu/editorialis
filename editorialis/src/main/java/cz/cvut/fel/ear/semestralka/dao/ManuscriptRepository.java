package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface ManuscriptRepository extends CrudRepository<Manuscript, Long> {
    List<Manuscript> findAll();
    Manuscript findByDocumentId(@Param("id") Long id);
    List<Manuscript> findByTitle(@Param("title") String title);
    List<Manuscript> findBySender_UserId(@Param("senderId") Long senderId);
    List<Manuscript> findByEditor_UserId(@Param("id") Long id);
    List<Manuscript> findByCategory_Name(@Param("categoryName") String categoryName);
    List<Manuscript> findByCategory_NameAndManuscriptStatus(@Param("category") String category, @Param("status") ManuscriptStatus status);
    List<Manuscript> findByManuscriptStatus(@Param("status") ManuscriptStatus status);
    List<Manuscript> findByClosedFalse();
    List<Manuscript> findByClosedTrue();



}
