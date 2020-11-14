package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptStates {
    START("start"),
    NEW("new"),
    PENDING("pending"),
    PEER_REVIEW("underRevisionByReviewers"),
    PRINCIPAL_REVIEW("underRevisionByEditor"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    public final String label;
    ManuscriptStates(String label){
        this.label = label;
    }
}
