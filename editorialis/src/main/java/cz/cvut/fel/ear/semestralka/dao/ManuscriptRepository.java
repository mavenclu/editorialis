package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ManuscriptRepository extends PagingAndSortingRepository<Manuscript, Long> {

    List<Manuscript> findAll();
    Manuscript findByManuscriptId(@Param("id") Long id);
    List<Manuscript> findByTitle(@Param("title") String title);
    List<Manuscript> findBySender_UserId(@Param("senderId") Long senderId);
    List<Manuscript> findByEditor_UserId(@Param("id") Long id);
    List<Manuscript> findByCategory_Name(@Param("categoryName") String categoryName);
    List<Manuscript> findByCategory_NameAndManuscriptStatus(@Param("category") String category, @Param("status") ManuscriptState status);
    List<Manuscript> findByManuscriptStatus(@Param("status") ManuscriptState status);
    List<Manuscript> findByClosedFalse();
    List<Manuscript> findByClosedTrue();




}
