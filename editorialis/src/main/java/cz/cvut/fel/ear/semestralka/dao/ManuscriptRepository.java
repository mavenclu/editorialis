package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Author;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManuscriptRepository extends PagingAndSortingRepository<Manuscript, Long> {

    List<Manuscript> findAll();
    Manuscript findByDocumentId(Long id);
    List<Manuscript> findByTitle(String title);
    List<Manuscript> findBySender_UserId(Long senderId);
    List<Manuscript> findByAuthors(List<Author> authors);
    List<Manuscript> findByEditor_UserId(Long id);
    List<Manuscript> findByCategory_Name(String categoryName);
    List<Manuscript> findByCategory_NameAndManuscriptStatus(String category, ManuscriptStatus status);
    List<Manuscript> findByManuscriptStatus(ManuscriptStatus status);
    List<Manuscript> findByClosedFalse();
    List<Manuscript> findByClosedTrue();



}
