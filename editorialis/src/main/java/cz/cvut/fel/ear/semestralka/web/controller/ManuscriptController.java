package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.config.ManuscriptNotFoundException;
import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ManuscriptController {
    @Autowired
    private ManuscriptRepository manuscriptRepo;

    @GetMapping("/manuscripts")
    public Iterable<Manuscript> getManuscripts() {
        return manuscriptRepo.findAll();
    }

    @GetMapping("/manuscripts/{id}")
    public Optional<Manuscript> getManuscript(@Param("id") Long id) {
        return manuscriptRepo.findById(id);
    }

    @PostMapping("/manuscripts")
    public Manuscript createNew(@RequestBody ManuscriptForm newManuscript) {
        Manuscript man = new Manuscript.ManuscriptBuilder()
                .withTitle(newManuscript.getTitle())
                .withAuthors(newManuscript.getAuthors())
                .withCategory(newManuscript.getCategory())
                .withEditor(newManuscript.getCategory().getEditor())
                .withManuscriptStatus(ManuscriptStatus.NEW)
                .isClosed(false)
                .build();
        return man;
    }

    @PutMapping("/manuscripts/{id}")
    public Manuscript updateManuscript(@RequestBody Manuscript newManuscript, @PathVariable("id") Long id) {
        return manuscriptRepo.findById(id)
                .map(manuscript -> {
                    manuscript.setTitle(newManuscript.getTitle());
                    manuscript.setCategory(newManuscript.getCategory());
                    manuscript.setAuthors(newManuscript.getAuthors());
                    manuscript.setEditor(newManuscript.getEditor());
                    manuscript.setClosed(newManuscript.isClosed());
                    manuscript.setReviews(newManuscript.getReviews());
                    manuscript.setManuscriptStatus(newManuscript.getManuscriptStatus());
                    manuscript.setFile(newManuscript.getFile());
                    return manuscriptRepo.save(manuscript);
                })
                .orElseGet(() -> {
                    return manuscriptRepo.save(newManuscript);
                });
    }

    @PatchMapping("/manuscripts/{id}/update-status")
    public Manuscript updateStatus(@PathVariable("id") Long id, @RequestBody ManuscriptStatus status) {
        return manuscriptRepo.findById(id)
                .map(manuscript -> {
                    manuscript.setManuscriptStatus(status);
                    return manuscriptRepo.save(manuscript);
                })
                .orElseThrow(() -> new ManuscriptNotFoundException(id));

    }

    @PatchMapping("/manuscripts/{id}/update-editor")
    public Manuscript updateEditor(@PathVariable Long id, @RequestBody Editor editor) {
        return manuscriptRepo.findById(id)
                .map(manuscript -> {
                    manuscript.setEditor(editor);
                    return manuscriptRepo.save(manuscript);
                })
                .orElseThrow(() -> new ManuscriptNotFoundException(id));
    }

    @PatchMapping("/manuscripts/{id}/update-category")
    public Manuscript updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return manuscriptRepo.findById(id)
                .map(manuscript -> {
                    manuscript.setCategory(category);
                    return manuscriptRepo.save(manuscript);
                })
                .orElseThrow(() -> new ManuscriptNotFoundException(id));
    }

    @ResponseBody
    @ExceptionHandler(ManuscriptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String manuscriptNotFoundHandler(ManuscriptNotFoundException ex) {
        return ex.getMessage();
    }

}
