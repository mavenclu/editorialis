package cz.cvut.fel.ear.semestralka.domain;

public enum Role {
    ADMIN("admin"),
    AUTHOR("author"),
    EDITOR("editor"),
    REVIEWER("reviewer"),
    HEAD_EDITOR("head editor"),
    DIRECTOR("direcotr");

    public final String label;

    private Role(String label) {
        this.label = label;
    }
}
