package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends BaseUserEntity {

    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST)
    private List<Manuscript> submittedManuscripts = new ArrayList<>();

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    private List<Manuscript> authoredManuscripts = new ArrayList<>();

    protected Author() {
        super();
    }

    public void addSubmittedManuscript(Manuscript manuscript){
        submittedManuscripts.add(manuscript);
    }
    public void addAuthoredManuscript(Manuscript manuscript){
        authoredManuscripts.add(manuscript);
    }

    public static class AuthorBuilder {
        private List<Manuscript> submittedManuscripts = new ArrayList<>();
        private List<Manuscript> authoredManuscripts = new ArrayList<>();
        private String email;
        private String firstName;
        private String lastName;


        public AuthorBuilder() {
        }

        public AuthorBuilder withSubmittedManuscripts(List<Manuscript> submitted) {
            this.submittedManuscripts = submitted;
            return this;
        }


        public AuthorBuilder withAuthoredManuscripts(List<Manuscript> authored) {
            this.authoredManuscripts = authored;
            return this;
        }

        public AuthorBuilder withEmail(@Email String email) {
            this.email = email;
            return this;
        }


        public AuthorBuilder withFirstName(@NotNull String name) {
            this.firstName = name;
            return this;
        }

        public AuthorBuilder withLastName(@NotNull String name) {
            this.lastName = name;
            return this;
        }

        public Author build() {
            Author author = new Author();
            author.setSubmittedManuscripts(submittedManuscripts);
            author.setAuthoredManuscripts(authoredManuscripts);
            author.setEmail(email);
            author.setFirstName(firstName);
            author.setLastName(lastName);
            return author;
        }
    }
}
