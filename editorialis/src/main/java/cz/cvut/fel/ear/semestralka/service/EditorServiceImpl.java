package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.EditorRepository;
import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.Category;
import cz.cvut.fel.ear.semestralka.domain.Editor;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EditorServiceImpl implements EditorService{

    private final EditorRepository editorRepo;
    private final ManuscriptRepository manRepo;

    private final Logger log = LoggerFactory.getLogger(EditorServiceImpl.class);

    @Autowired
    public EditorServiceImpl(EditorRepository editorRepo, ManuscriptRepository manRepo) {
        this.editorRepo = editorRepo;
        this.manRepo = manRepo;
    }


    public Editor findById(Long id){
        return  editorRepo.findById(id).orElseThrow(
                () -> {
                    log.error("trying to find empty editor.");
                    throw new IllegalArgumentException("Could not find editor with ID: " + id);
                });
    }
    public Iterable<Editor> findAll(){
        return editorRepo.findAll();
    }
    public Editor save(Editor editor){
        return editorRepo.save(editor);
    }
    public void delete(Editor editor){
        if (editorRepo.findById(editor.getUserId()).isPresent()){
            editorRepo.delete(editor);
        }else {
            log.error("trying to delete empty editor.");
            throw new IllegalArgumentException("Could not find an editor with ID: " + editor.getUserId() + " and name: " + editor.getName());
        }
    }
    public void deleteById(Long id){
        Editor editor = findById(id);
        editorRepo.delete(editor);
    }
    @Transactional
    public boolean addManuscript(Editor editor, Manuscript man) throws IllegalArgumentException {
        if (editorRepo.findById(editor.getUserId()).isPresent() && manRepo.findById(man.getManuscriptId()).isPresent()){
            editor.addManuscript(man);
            return true;
        }else {
            if (editorRepo.findById(editor.getUserId()).isEmpty()){
                log.error("trying to add manuscript to empty editor");
            }
            if (manRepo.findById(man.getManuscriptId()).isEmpty()){
                log.error("trying to add empty manuscript.");
            }
            throw new IllegalArgumentException("Could not find editor or manuscript");
        }
    }
    public Editor findByCategory(Category category) {
        return editorRepo.findEditorByCategory_CategoryId(category.getCategoryId()).orElseThrow(
                () -> {
                    log.error("trying to get editor for empty category");
                    throw new IllegalArgumentException("Could not find editor for category: " +category.getName());
                }
        );
    }

    public Iterable<Manuscript> getManuscripts(Editor editor){
        return manRepo.findAllByEditor(editor);
    }

    @Override
    public Editor findByEmail(String email) {
        return editorRepo.findByEmail(email).orElseThrow(
                () ->  new IllegalArgumentException("could not find editor with email: " + email)
        );
    }

}
