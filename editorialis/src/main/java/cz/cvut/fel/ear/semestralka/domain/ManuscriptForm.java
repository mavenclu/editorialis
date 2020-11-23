package cz.cvut.fel.ear.semestralka.domain;

import lombok.Data;

import java.util.Set;
@Data
public class ManuscriptForm {
    private String title;
    private Category category;
    private Author sender;
}
