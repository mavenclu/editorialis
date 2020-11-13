package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptStates {
    START("start"),
    NEW("new"),
    PENDING("pending"),
    IN_REVISION_BY_REVIEWER("inRevisionByReviewers"),
    IN_REVISION_BY_EDITOR("inRevisionByEditor"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    public final String label;
    ManuscriptStates(String label){
        this.label = label;
    }
}
