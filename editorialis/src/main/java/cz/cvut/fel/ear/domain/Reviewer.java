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
@Table(name = "reviewers")
public class Reviewer extends BaseUserEntity {

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    protected Reviewer() {
        super();
    }

    public static class ReviewerBuilder{
        private List<Review> reviews;
        private Category category;
        private String email;
        private String phoneNumber;
        private String firstName;
        private String lastName;

        public ReviewerBuilder(){}

        public ReviewerBuilder withReviews(List<Review> reviews){
            this.reviews = reviews;
            return this;
        }

        public ReviewerBuilder withCategory(Category category){
            this.category = category;
            return this;
        }


        public ReviewerBuilder withEmail(@Email String email){
            this.email = email;
            return this;
        }

        public ReviewerBuilder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ReviewerBuilder withFirstName(@NotNull String name){
            this.firstName = firstName;
            return this;
        }

        public ReviewerBuilder withLastName(@NotNull String name){
            this.lastName = name;
            return this;
        }

        public Reviewer build(){
            Reviewer rev = new Reviewer();
            rev.setReviews(reviews);
            rev.setCategory(category);
            rev.setEmail(email);
            rev.setPhoneNumber(phoneNumber);
            rev.setFirstName(firstName);
            rev.setLastName(lastName);
            return rev;
        }
    }
}
