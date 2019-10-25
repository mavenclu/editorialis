package cz.cvut.fel.ear.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long documentId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateTimeUploaded;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateTimeAssigned;

    @Lob
    private MultipartFile file;

    BaseDocumentEntity() {
        this.documentId = null;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public LocalDateTime getDateTimeUploaded() {
        return dateTimeUploaded;
    }

    public void setDateTimeUploaded(LocalDateTime dateTimeUploaded) {
        this.dateTimeUploaded = dateTimeUploaded;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public LocalDateTime getDateTimeAssigned() {
        return dateTimeAssigned;
    }

    public void setDateTimeAssigned(LocalDateTime dateTimeAssigned) {
        this.dateTimeAssigned = dateTimeAssigned;
    }


}
