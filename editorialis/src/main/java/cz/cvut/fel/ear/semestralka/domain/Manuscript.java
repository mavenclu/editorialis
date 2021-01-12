package cz.cvut.fel.ear.semestralka.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "manuscripts")
public class Manuscript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuscriptId;

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[A-Za-z0-9 _.,!\"'/$]*", message = "Title should contain only letetrs, numbers and punctuation.")
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "manuscript_authors",
            joinColumns = @JoinColumn(name = "manuscript_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

    @OneToMany(mappedBy = "manuscript", cascade = CascadeType.PERSIST)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private ManuscriptState manuscriptStatus;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "whenUploaded", column = @Column(name = "when_uploaded")),
            @AttributeOverride(name = "whenAssignedToEditor", column = @Column(name = "when_assigned_to_editor")),
            @AttributeOverride(name = "whenAssignedToReviewer", column = @Column(name = "when_assigned_to_reviewer")),
            @AttributeOverride(name = "whenCompletedReviews", column = @Column(name = "when_completed_reviews")),
            @AttributeOverride(name = "whenAccepted", column = @Column(name = "when_accepted")),
            @AttributeOverride(name = "whenRejected", column = @Column(name = "when_rejected"))})
    private EventsSequence eventsSequence;

    @Column
    private boolean closed;

    @Column
    private boolean reviewed;

    public Manuscript (){
        this.closed = false;
        this.reviewed = false;
        this.reviews = new ArrayList<>();
    }

    public Manuscript(@NotNull @Size(min = 3, max = 100) @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message = "pro popis jsou dovolene pouze znaky pismena a cislice") String title,
                      @NotNull List<Author> authors,
                      Category category) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.category = category;
        this.closed = false;
        this.reviewed = false;
        this.reviews = new ArrayList<>();
        this.eventsSequence = new EventsSequence();
    }

    public  Manuscript(String tilte, List<Author> author) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.closed = false;
        this.reviewed = false;
        this.reviews = new ArrayList<>();
        this.eventsSequence = new EventsSequence();
    }

    public ManuscriptState getManuscriptStatus() {
        return manuscriptStatus;
    }

    public EventsSequence getEventsSequence() {
        if(eventsSequence == null)
            return null;
            EventsSequence copy = new EventsSequence();
            copy.setWhenAccepted(eventsSequence.getWhenAccepted());
            copy.setWhenAssignedToEditor(eventsSequence.getWhenAssignedToEditor());
            copy.setWhenAssignedToReviewer(eventsSequence.getWhenAssignedToReviewer());
            copy.setWhenCompletedReviews(eventsSequence.getWhenCompletedReviews());
            copy.setWhenUploaded(eventsSequence.getWhenUploaded());
            copy.setWhenRejected(eventsSequence.getWhenRejected());
        return copy;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setManuscript(this);
    }


    public Long getManuscriptId() {
        return manuscriptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min=3) String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setManuscriptStatus(ManuscriptState manuscriptStatus) {
        this.manuscriptStatus = manuscriptStatus;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public void setEventsSequence(EventsSequence eventsSequence) {
        this.eventsSequence = eventsSequence;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reiviewed) {
        this.reviewed = reiviewed;
    }



    public static class ManuscriptBuilder{
        private String title;
        private List<Author> authors;
        private Editor editor;
        private List<Review> reviews;
        private Category category;
        private ManuscriptState manuscriptStatus;
        private boolean closed;
        private EventsSequence eventsSequence;

        public ManuscriptBuilder(){}

        public ManuscriptBuilder withTitle(String title){
            this.title = title;
            return this;
        }


        public ManuscriptBuilder withAuthors(List<Author> authors){
            this.authors = new ArrayList<>(authors);
            return this;
        }

        public ManuscriptBuilder withEditor(Editor ed){
            this.editor = ed;
            return this;
        }

        public ManuscriptBuilder withReviews(List<Review> rev){
            this.reviews = new ArrayList<>(rev);
            return this;
        }

        public ManuscriptBuilder withCategory(Category cat){
            this.category = cat;
            return this;
        }

        public ManuscriptBuilder withManuscriptStatus(ManuscriptState status){
            this.manuscriptStatus = status;
            return this;
        }

        public ManuscriptBuilder isClosed(boolean bool){
            this.closed = bool;
            return this;
        }

        public ManuscriptBuilder withEventsSequence(EventsSequence sequence){
            this.eventsSequence = sequence;
            return this;
        }

        public Manuscript build(){
            Manuscript man = new Manuscript();
            man.setTitle(title);
            man.setAuthors(authors);
            man.setEditor(editor);
            man.setReviews(reviews);
            man.setCategory(category);
            man.setManuscriptStatus(manuscriptStatus);
            man.setClosed(closed);
            man.setEventsSequence(eventsSequence);
            return man;
        }

    }
}