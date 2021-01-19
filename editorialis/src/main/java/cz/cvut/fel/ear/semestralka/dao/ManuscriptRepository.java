package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins="*")
//@PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'EDITOR', 'REVIEWER')")
@RepositoryRestResource
public interface ManuscriptRepository extends CrudRepository<Manuscript, Long> {

    @Override
    Iterable<Manuscript> findAll();

    @Override
    Manuscript save(@Param("manuscript") Manuscript manuscript);


    @Override
    Optional<Manuscript> findById(@Param("id") Long id);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("manuscript") Manuscript manuscript);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(@Param("id") Long id);

    @Override
    boolean existsById(@Param("id") Long id);

    Optional<Manuscript> findByManuscriptId(@Param("id") Long id);

    Iterable<Manuscript> findAllByCategory(Category category);

    Iterable<Manuscript> findAllByEditor(Editor editor);

    Iterable<Manuscript> findAllByReviewer(Reviewer reviewer);

    Iterable<Manuscript> findAllByAuthorsIn(Collection<List<Author>> authors);

    Iterable<Manuscript> findAllByManuscriptStatus(ManuscriptState status);

    Iterable<Manuscript> findAllByClosedIsTrue();


}
