package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Editor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource
public interface EditorRepository extends PagingAndSortingRepository<Editor, Long> {

    List<Editor> findAll();
    Editor findByUserId(Long editorId);
    List<Editor> findByLastName(String lastName);
    List<Editor> findByLastNameContaining(String containing);
    List<Editor> findByCategory_Name(String categoryName);
    List<Editor> findByCategory_CategoryId(Long id);

}
