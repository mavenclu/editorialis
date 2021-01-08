package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Author;

interface AuthorService {
    Author findById(Long id);
    Author save(Author author);
    Iterable<Author> findAll();
    void delete(Author author);
    Author findByEmail(String email);
}
