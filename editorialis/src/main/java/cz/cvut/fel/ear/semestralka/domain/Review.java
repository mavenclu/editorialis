package cz.cvut.fel.ear.semestralka.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@IdClass(ReviewId.class)
@Table(name = "reviews")
public class Review {
    @Id
    @ManyToOne
    @JoinColumn(name = "manuscript_id")
    private Manuscript manuscript;

    @Id
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;


    @Enumerated(EnumType.STRING)
    private ReviewersSuggestion reviewersSuggestion;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime whenUploaded;

    protected Review() {
        super();
    }

    public static class ReviewBuilder{
        private Manuscript manuscript;
        private Reviewer reviewer;
        private ReviewersSuggestion reviewersSuggestion;

        public ReviewBuilder(){}

        public ReviewBuilder withManuscript(Manuscript man){
            this.manuscript = man;
            return this;
        }

        public ReviewBuilder withReviewer(Reviewer reviewer){
            this.reviewer = reviewer;
            return this;
        }
        public ReviewBuilder withReviewSuggestion(ReviewersSuggestion suggestion){
            this.reviewersSuggestion = suggestion;
            return this;
        }
        public Review build(){
            Review rew = new Review();
            rew.setManuscript(manuscript);
            rew.setReviewer(reviewer);
            rew.setReviewersSuggestion(reviewersSuggestion);
            return rew;
        }
    }
}
