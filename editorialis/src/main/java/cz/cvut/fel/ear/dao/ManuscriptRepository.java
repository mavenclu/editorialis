package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Author;
import cz.cvut.fel.ear.domain.Manuscript;
import cz.cvut.fel.ear.domain.ManuscriptStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManuscriptRepository extends PagingAndSortingRepository<Manuscript, Long> {

    List<Manuscript> findAll();
    List<Manuscript> findManuscriptsByTitle(String title);
    List<Manuscript> findManuscriptsBySender_UserId(Long senderId);
    List<Manuscript> findManuscriptsByAuthors(List<Author> authors);
    List<Manuscript> findManuscriptsByEditor_UserId(Long id);
    List<Manuscript> findManuscriptsByCategory_Name(String categoryName);
    List<Manuscript> findManuscriptsByManuscriptStatus(ManuscriptStatus status);
    List<Manuscript> findManuscriptsByClosedFalse();
    List<Manuscript> findManuscriptsByClosedTrue();



}
