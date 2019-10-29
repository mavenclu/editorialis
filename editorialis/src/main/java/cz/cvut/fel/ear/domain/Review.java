package cz.cvut.fel.ear.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends BaseDocumentEntity {

    @ManyToOne
    @JoinColumn(name = "manuscript_id")
    private Manuscript manuscript;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;
    @Enumerated(EnumType.STRING)
    private ReviewSuggestion reviewSuggestion;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateTimeAssigned;

    protected Review() {
        super();
    }

    public static class ReviewBuilder{
        private Manuscript manuscript;
        private Reviewer reviewer;
        private ReviewSuggestion reviewSuggestion;

        public ReviewBuilder(){}

        public ReviewBuilder withManuscript(Manuscript man){
            this.manuscript = man;
            return this;
        }

        public ReviewBuilder withReviewer(Reviewer reviewer){
            this.reviewer = reviewer;
            return this;
        }
        public ReviewBuilder withReviewSuggestion(ReviewSuggestion suggestion){
            this.reviewSuggestion = suggestion;
            return this;
        }
        public Review build(){
            Review rew = new Review();
            rew.setManuscript(manuscript);
            rew.setReviewer(reviewer);
            rew.setReviewSuggestion(reviewSuggestion);
            return rew;
        }
    }
}
