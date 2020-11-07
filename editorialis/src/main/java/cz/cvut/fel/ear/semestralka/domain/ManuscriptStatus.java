package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptStatus {
    NEW("new"),
    PENDING("pending"),
    IN_REVISION_BY_REVIEWER("inRevisionByReviewers"),
    IN_REVISION_BY_AUTHORS_TO_MATCH_STYLE("inRevisionByAuthorsToMatchStyle"),
    IN_REVISION_BY_AUTHOR("inRevisionByAuthors"),
    IN_REVISION_BY_EDITOR("inRevisionByEditor"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    public final String label;
    ManuscriptStatus(String label){
        this.label = label;
    }
}
