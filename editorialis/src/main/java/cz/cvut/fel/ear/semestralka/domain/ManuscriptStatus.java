package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptStatus {
    NEW("new"),
    PENDING("pending"),
    IN_REVISION_BY_REVIEWER("inRevisionByReviewer"),
    IN_REVISION_BY_AUTHOR("inRevisionByAuthor"),
    IN_REVISION_BY_EDITOR("inRevisionByEditor"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String label;

    private ManuscriptStatus(String label){
        this.label = label;
    }
}
