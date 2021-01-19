package cz.cvut.fel.ear.semestralka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Version
    private Long version;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Editor> editors;

    @JsonIgnore
    @OrderBy("onReview")
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Reviewer> reviewers;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Manuscript> manuscripts;

    public Category() {
    }

    ;

    public Category(@NotNull @Size(min = 2, max = 30) String name) {
        this.name = name;
    }

    public Category(@NotNull @Size(min = 2, max = 30) String name, Set<Editor> editors, Set<Reviewer> reviewers, Set<Manuscript> manuscripts) {
        this.name = name;
        this.editors = editors;
        this.reviewers = reviewers;
        this.manuscripts = manuscripts;
    }
}