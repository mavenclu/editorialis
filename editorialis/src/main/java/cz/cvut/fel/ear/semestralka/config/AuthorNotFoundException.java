package cz.cvut.fel.ear.semestralka.config;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("Could not find author with id: " + id);

    }
}
