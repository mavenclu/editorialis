package cz.cvut.fel.ear.semestralka.domain;

import lombok.Data;

@Data
public class ManuscriptForm {
    private String title;
    private Category category;
    private Author sender;
}
