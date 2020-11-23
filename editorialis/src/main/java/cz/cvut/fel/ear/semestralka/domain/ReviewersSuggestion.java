package cz.cvut.fel.ear.semestralka.domain;

public enum ReviewersSuggestion {
    APPROVE("success"),
    MINOR_REVISIONS("success"),
    MAJOR_REVISIONS("danger"),
    REJECT("danger");


    public final String label;

    private ReviewersSuggestion(String label) {
        this.label = label;
    }
}
