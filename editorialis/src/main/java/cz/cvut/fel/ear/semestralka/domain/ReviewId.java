package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ReviewId implements Serializable {
    private Long manuscript;
    private Long reviewer;

    public ReviewId() {
    }


    public ReviewId(Long manuscript_id, Long reviewer_id) {
        this.manuscript = manuscript_id;
        this.reviewer = reviewer_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewId)) return false;
        ReviewId reviewId = (ReviewId) o;
        return getManuscript().equals(reviewId.getManuscript()) && getReviewer().equals(reviewId.getReviewer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManuscript(), getReviewer());
    }
}
