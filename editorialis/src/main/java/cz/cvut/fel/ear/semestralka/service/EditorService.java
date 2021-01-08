package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Category;
import cz.cvut.fel.ear.semestralka.domain.Editor;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;

public interface EditorService {

    Editor findById(Long id);
    Iterable<Editor> findAll();
    Editor save(Editor editor);
    void delete(Editor editor);
    void deleteById(Long id);
    boolean addManuscript(Editor editor, Manuscript manuscript);
    Editor findByCategory(Category category);
    Iterable<Manuscript> getManuscripts(Editor editor);

}
