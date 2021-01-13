package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reviewers")
public class Reviewer extends BaseUserEntity {

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.PERSIST)
    private List<Review> reviews ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.PERSIST)
    private List<Manuscript> reviewedManuscripts;

    private boolean onReview;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime whenAssigned;

    protected Reviewer() {
        super();
        this.setUsersRole(Role.REVIEWER);
        reviews = new ArrayList<>();
    }


    public void addReviews(Review review) {
        reviews.add(review);
    }

    public static class ReviewerBuilder{
        private List<Review> reviews = new ArrayList<>();
        private Category category;
        private boolean onReview;
        private String email;
        private String firstName;
        private String lastName;

        public ReviewerBuilder(){
        }

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

        public ReviewerBuilder withFirstName(@NotNull String name){
            this.firstName = name;
            return this;
        }

        public ReviewerBuilder withLastName(@NotNull String name){
            this.lastName = name;
            return this;
        }

        public ReviewerBuilder isOnReview(boolean val){
            this.onReview = val;
            return this;
        }

        public Reviewer build(){
            Reviewer rev = new Reviewer();
            rev.setReviews(reviews);
            rev.setCategory(category);
            rev.setOnReview(onReview);
            rev.setEmail(email);
            rev.setFirstName(firstName);
            rev.setLastName(lastName);
            return rev;
        }
    }
}
