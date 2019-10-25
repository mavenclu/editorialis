package cz.cvut.fel.ear.dao;

import cz.cvut.fel.ear.domain.Editor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EditorRepository extends PagingAndSortingRepository<Editor, Long> {

    List<Editor> findAll();
    Editor findEditorByUserId(Long editorId);
    List<Editor> findEditorsByLastName(String lastName);
    List<Editor> findEditorsByLastNameContaining(String containing);
    List<Editor> findEditorsByCategory_Name(String categoryName);
    List<Editor> findEditorsByCategory_CategoryId(Long id);

}
