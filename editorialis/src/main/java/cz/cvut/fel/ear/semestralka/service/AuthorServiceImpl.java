package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.AuthorRepository;
import cz.cvut.fel.ear.semestralka.domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);


    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(
                () -> {
                    log.error("trying to find empty author with id: " + id);
                    throw new IllegalArgumentException("Could not find author with id: " + id);
                }
        );
    }

    @Override
    public Author save(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepo.findAll();
    }

    @Override
    public void delete(Author author) {
        if (authorRepo.findById(author.getUserId()).isPresent()) {
            authorRepo.delete(author);
        } else {
            log.error("Trying to delete empty author");
            throw new IllegalArgumentException("Could not delete author");
        }

    }

    @Override
    public Author findByEmail(String email) {
        return authorRepo.findByEmail(email).orElseThrow(
                () -> {
                    log.error("trying to find empty author");
                    throw new IllegalArgumentException("could not find author with email: " + email);
                });
    }
}
