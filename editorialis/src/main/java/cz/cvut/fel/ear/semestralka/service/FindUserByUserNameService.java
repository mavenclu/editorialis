package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.AuthorRepository;
import cz.cvut.fel.ear.semestralka.dao.EditorRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.BaseUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserByUserNameService {

    private final AuthorRepository authorRepo;
    private final AuthorService authorServ;
    private final EditorRepository editorRepo;
    private final EditorService editorServ;
    private final ReviewerRepository reviewerRepo;
    private final ReviewerService reviewerServ;

    @Autowired
    public FindUserByUserNameService(AuthorRepository authorRepo, AuthorService authorServ, EditorRepository editorRepo, EditorService editorServ, ReviewerRepository reviewerRepo, ReviewerService reviewerServ) {
        this.authorRepo = authorRepo;
        this.authorServ = authorServ;
        this.editorRepo = editorRepo;
        this.editorServ = editorServ;
        this.reviewerRepo = reviewerRepo;
        this.reviewerServ = reviewerServ;
    }

    public BaseUserEntity findByEmail(String email) {
        if (authorRepo.findByEmail(email).isPresent()) {
            return authorServ.findByEmail(email);
        } else if (editorRepo.findByEmail(email).isPresent()) {
            return editorServ.findByEmail(email);
        } else if (reviewerRepo.findByEmail(email).isPresent()) {
            return reviewerServ.findByEmail(email);
        } else {
            return null;
        }
    }

}
