package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class EventsSequence {


    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime whenUploaded;
    private LocalDateTime whenAssignedToEditor;
    private LocalDateTime whenAssignedToReviewer;
    private LocalDateTime whenCompletedReviews;
    private LocalDateTime whenAccepted;
    private LocalDateTime whenRejected;
}
