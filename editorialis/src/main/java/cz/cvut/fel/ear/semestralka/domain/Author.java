package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends BaseUserEntity {

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    private List<Manuscript> manuscripts;

    protected Author() {
        super();
    }

    public static class AuthorBuilder {
        private List<Manuscript> authoredManuscripts;
        private String email;
        private String firstName;
        private String lastName;


        public AuthorBuilder() {
        }

        public AuthorBuilder withManuscripts(List<Manuscript> authored) {
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
            author.setManuscripts(authoredManuscripts);
            author.setEmail(email);
            author.setFirstName(firstName);
            author.setLastName(lastName);
            return author;
        }
    }
}
