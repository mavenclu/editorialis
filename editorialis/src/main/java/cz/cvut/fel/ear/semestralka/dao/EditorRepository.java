package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Editor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface EditorRepository extends CrudRepository<Editor, Long> {

    @Override
    Optional<Editor> findById(Long aLong);

    @Override
    Iterable<Editor> findAll();

    @Override
    Editor save(Editor editor);

    @Override
    void delete(Editor entity);

    Optional<Editor> findEditorByCategory_CategoryId(long id);

}
