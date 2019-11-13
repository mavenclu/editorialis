package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Editor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EditorRepository extends PagingAndSortingRepository<Editor, Long> {

    List<Editor> findAll();
    Editor findEditorByUserId(Long editorId);
    List<Editor> findEditorsByLastName(String lastName);
    List<Editor> findEditorsByLastNameContaining(String containing);
    List<Editor> findEditorsByCategory_Name(String categoryName);
    List<Editor> findEditorsByCategory_CategoryId(Long id);

}
