package cz.cvut.fel.ear.semestralka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "editors")
public class Editor extends BaseUserEntity {

    @JsonIgnore
    @OrderBy("manuscriptStatus")
    @OneToMany(mappedBy = "editor", cascade = CascadeType.PERSIST)
    private List<Manuscript> manuscripts;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    protected Editor() {
        super();
        this.setUsersRole(Role.EDITOR);
    }

    public void addManuscript(Manuscript man) {
        manuscripts.add(man);
    }

    public static class EditorBuilder {
        private List<Manuscript> manuscripts1 = new ArrayList<>();
        private Category category;
        private String email;
        private String firstName;
        private String lastName;


        public EditorBuilder() {
        }

        public EditorBuilder withManuscripts(List<Manuscript> manuscripts) {
            this.manuscripts1 = manuscripts;
            return this;
        }

        public EditorBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public EditorBuilder withEmail(String email) {
            this.email = email;
            return this;
        }


        public EditorBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EditorBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Editor build() {
            Editor ed = new Editor();
            ed.setCategory(category);
            ed.setManuscripts(manuscripts1);
            ed.setEmail(email);
            ed.setFirstName(firstName);
            ed.setLastName(lastName);
            return ed;
        }

    }
}
