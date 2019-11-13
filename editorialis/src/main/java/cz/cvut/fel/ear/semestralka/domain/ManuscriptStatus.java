package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptStatus {
    NEW,
    PENDING,
    IN_REVISION_BY_REVIEWER,
    IN_REVISION_BY_AUTHOR,
    IN_REVISION_BY_EDITOR,
    ACCEPTED,
    REJECTED
}
