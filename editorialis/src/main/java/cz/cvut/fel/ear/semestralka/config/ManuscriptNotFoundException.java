package cz.cvut.fel.ear.semestralka.config;

public class ManuscriptNotFoundException extends RuntimeException {
    public ManuscriptNotFoundException(Long id) {
        super("Could not find manuscript with id: " + id);

    }
}
