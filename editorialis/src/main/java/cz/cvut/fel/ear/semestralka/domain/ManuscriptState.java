package cz.cvut.fel.ear.semestralka.domain;

public enum ManuscriptState {
    //    START("start"),
    NEW("new"),
    PENDING("pending"),
    PEER_REVIEW("underRevisionByReviewers"),
    PRINCIPAL_REVIEW("underRevisionByEditor"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    public final String label;

    ManuscriptState(String label) {
        this.label = label;
    }
}
