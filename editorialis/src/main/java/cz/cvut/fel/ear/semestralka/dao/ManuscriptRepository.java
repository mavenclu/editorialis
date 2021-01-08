package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ManuscriptRepository extends CrudRepository<Manuscript, Long> {

    @Override
    Iterable<Manuscript> findAll();

    @Override
    Manuscript save(Manuscript entity);

    @Override
    Optional<Manuscript> findById(Long aLong);

    @Override
    void delete(Manuscript entity);

    Iterable<Manuscript> findAllByCategory(Category category);
    Iterable<Manuscript> findAllByEditor(Editor editor);
    Iterable<Manuscript> findAllByReviewer(Reviewer reviewer);
    Iterable<Manuscript> findAllByAuthorsIn(Collection<List<Author>> authors);
    Iterable<Manuscript> findAllByManuscriptStatus(ManuscriptState status);
    Iterable<Manuscript> findAllByClosedIsTrue();





}
