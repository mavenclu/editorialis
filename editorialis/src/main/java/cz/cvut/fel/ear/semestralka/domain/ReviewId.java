package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private Manuscript manuscript;
    private Reviewer reviewer;

    public ReviewId(ReviewId reviewId){};

    public ReviewId(Manuscript manuscript, Reviewer reviewer) {
        this.manuscript = manuscript;
        this.reviewer = reviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewId)) return false;
        ReviewId reviewId = (ReviewId) o;
        return manuscript.equals(reviewId.manuscript) &&
                reviewer.equals(reviewId.reviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manuscript, reviewer);
    }
}
