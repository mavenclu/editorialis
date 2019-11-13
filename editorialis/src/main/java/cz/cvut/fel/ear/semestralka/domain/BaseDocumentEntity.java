package cz.cvut.fel.ear.semestralka.domain;

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


    @Lob
    private MultipartFile file;

    private String url;

    private String viewerUrl;

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

    public String getViewerUrl(){
        StringBuilder temp = new StringBuilder();
        temp.append("https://docs.google.com/gview?url=");
        temp.append(url);
//        temp.append("&embedded=true");
        return temp.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
