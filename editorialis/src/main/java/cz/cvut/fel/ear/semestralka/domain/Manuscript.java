package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manuscripts")
public class Manuscript extends BaseDocumentEntity {

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author sender;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "manuscript_authors",
            joinColumns = @JoinColumn(name = "manuscript_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private Editor editor;

    @OneToMany(mappedBy = "manuscript")
    private List<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private ManuscriptStatus manuscriptStatus;

    @Column
    private boolean closed;

    protected Manuscript() {
        super();
    }

    public static class ManuscriptBuilder{
        private String title;
        private Author sender;
        private Set<Author> authors;
        private Editor editor;
        private List<Review> reviews;
        private Category category;
        private ManuscriptStatus manuscriptStatus;
        private boolean closed;
        private String url;

        public ManuscriptBuilder(){}

        public ManuscriptBuilder withTitle(String title){
            this.title = title;
            return this;
        }

        public ManuscriptBuilder withSender(Author sender){
            this.sender = sender;
            return this;
        }

        public ManuscriptBuilder withAuthors(Set<Author> authors){
            this.authors = authors;
            return this;
        }

        public ManuscriptBuilder withEditor(Editor ed){
            this.editor = ed;
            return this;
        }

        public ManuscriptBuilder withReviews(List<Review> rev){
            this.reviews = rev;
            return this;
        }

        public ManuscriptBuilder withCategory(Category cat){
            this.category = cat;
            return this;
        }

        public ManuscriptBuilder withManuscriptStatus(ManuscriptStatus status){
            this.manuscriptStatus = status;
            return this;
        }

        public ManuscriptBuilder isClosed(boolean bool){
            this.closed = bool;
            return this;
        }

        public ManuscriptBuilder withUrl(String url){
            this.url = url;
            return this;
        }

        public Manuscript build(){
            Manuscript man = new Manuscript();
            man.setTitle(title);
            man.setSender(sender);
            man.setAuthors(authors);
            man.setEditor(editor);
            man.setReviews(reviews);
            man.setCategory(category);
            man.setManuscriptStatus(manuscriptStatus);
            man.setClosed(closed);
            man.setUrl(url);
            return man;
        }
    }
}