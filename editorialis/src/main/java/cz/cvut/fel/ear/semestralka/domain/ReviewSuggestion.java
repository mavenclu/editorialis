package cz.cvut.fel.ear.semestralka.domain;

public enum ReviewSuggestion {
    APPROVE("success"),
    MINOR_REVISIONS("success"),
    MAJOR_REVISIONS("danger"),
    REJECT("danger");


    public final String label;

    private ReviewSuggestion(String label) {
        this.label = label;
    }
}
