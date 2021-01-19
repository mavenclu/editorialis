package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.EditorRepository;
import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManuscriptServiceImpl implements ManuscriptService {
    private final Logger log = LoggerFactory.getLogger(ManuscriptServiceImpl.class);


    private final ManuscriptRepository manRepo;
    private final EditorRepository editorRepo;

    @Autowired
    public ManuscriptServiceImpl(ManuscriptRepository manRepo, EditorRepository editorRepo) {
        this.manRepo = manRepo;
        this.editorRepo = editorRepo;
    }

    @Override
    public Manuscript findById(long id) {
        return manRepo.findById(id).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Could not find manuscript wiht id: " + id);
                }
        );
    }

    @Override
    public Iterable<Manuscript> findAll() {
        return manRepo.findAll();
    }

    @Override
    public Manuscript save(Manuscript manuscript) {
        return manRepo.save(manuscript);
    }

    @Override
    public void delete(Manuscript manuscript) {
        if (manRepo.findById(manuscript.getManuscriptId()).isPresent()) {
            manRepo.delete(manuscript);
        } else {
            throw new IllegalArgumentException("Could not find manuscript");
        }
    }

    @Override
    public void deleteById(long id) {
        Manuscript manuscript = findById(id);
        manRepo.delete(manuscript);
    }

    /**
     * gets
     *
     * @param manuscript category and
     * @return Editor responsible for that category
     */
    @Override
    public Editor getManuscriptEditor(Manuscript manuscript) {
        if (manRepo.findById(manuscript.getManuscriptId()).isPresent()) {
            return editorRepo.findEditorByCategory_CategoryId(manuscript.getCategory().getCategoryId()).orElseThrow(
                    () -> {
                        throw new IllegalArgumentException("Could not get an editor for a manuscirpt with an ID: " + manuscript.getManuscriptId());
                    }
            );
        } else {
            log.error("Trying to get editor of the empty manuscript.");
            throw new IllegalArgumentException("Could not get an editor for a manuscript with an ID: " + manuscript.getManuscriptId());
        }
    }

    @Override
    public Category getManuscriptCategory(Category category) {
        return null;
    }

    @Override
    public List<Reviewer> getManuscriptReviewers(Manuscript manuscript) {
        return null;
    }

    @Override
    public List<Review> getManuscriptReviews(Manuscript manuscript) {
        return null;
    }

    @Override
    public Iterable<Manuscript> findALlByCategory(Category category) {
        return manRepo.findAllByCategory(category);
    }
}
