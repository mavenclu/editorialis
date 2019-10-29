package cz.cvut.fel.ear.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends BaseUserEntity {

    @OneToMany(mappedBy = "sender")
    private List<Manuscript> submittedManuscripts;

    @ManyToMany(mappedBy = "authors")
    private List<Manuscript> authoredManuscripts;

    protected Author() {
        super();
    }

    public static class AuthorBuilder{
        private List<Manuscript> submittedManuscripts;
        private List<Manuscript> authoredManuscripts;
        private String email;
        private String phoneNumber;
        private String firstName;
        private String lastName;



        public AuthorBuilder(){ }

        public AuthorBuilder withSubmittedManuscripts(List<Manuscript> submitted){
            this.submittedManuscripts = submitted;
            return this;
        }


        public AuthorBuilder withAuthoredManuscripts(List<Manuscript> authored){
            this.authoredManuscripts = authored;
            return this;
        }

        public AuthorBuilder withEmail(@Email String email){
            this.email = email;
            return this;
        }

        public AuthorBuilder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public AuthorBuilder withFirstName(@NotNull String name){
            this.firstName = name;
            return this;
        }

        public AuthorBuilder withLastName(@NotNull String name){
            this.lastName = name;
            return this;
        }

        public Author build(){
            Author author = new Author();
            author.setSubmittedManuscripts(submittedManuscripts);
            author.setAuthoredManuscripts(authoredManuscripts);
            author.setEmail(email);
            author.setPhoneNumber(phoneNumber);
            author.setFirstName(firstName);
            author.setLastName(lastName);
            return author;
        }
    }
}
