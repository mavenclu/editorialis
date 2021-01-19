package cz.cvut.fel.ear.semestralka.events;

import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import cz.cvut.fel.ear.semestralka.service.AuthorService;
import cz.cvut.fel.ear.semestralka.service.EditorService;
import cz.cvut.fel.ear.semestralka.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryEventHandler
public class ManuscriptEventHandler {
    @Autowired
    private EditorService editorServ;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthorService authorService;


    @HandleBeforeCreate
    public void newManuscriptSetUp(Manuscript manuscript) {
        manuscript.setClosed(false);
        manuscript.setManuscriptStatus(ManuscriptState.NEW);
        manuscript.getEventsSequence().setWhenUploaded(LocalDateTime.now());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Author author = authorService.findByEmail(username);
//        manuscript.setAuthors(List.of(author));
        manuscript.setAuthors(List.of(authorService.findById(1L)));
//        Editor editor = editorServ.findByCategory(manuscript.getCategory());
        manuscript.setEditor(editorServ.findById(1L));
//        emailService.sendSimpleMessage(editor.getEmail(), "New Manuscript", "You have been assigned with a new manuscript: " + manuscript.getTitle());
        emailService.sendSimpleMessage("miyaquey@gmail.com", "TO EDITOR. New Manuscript", "You have been assigned with a new manuscript." + manuscript.getTitle());
        manuscript.setManuscriptStatus(ManuscriptState.PENDING);
        manuscript.getEventsSequence().setWhenAssignedToEditor(LocalDateTime.now());

    }
}
