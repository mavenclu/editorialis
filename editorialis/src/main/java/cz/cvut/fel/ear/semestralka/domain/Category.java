package cz.cvut.fel.ear.semestralka.domain;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Version
    private Long version;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @OneToOne(mappedBy = "category")
    private Editor editor;

    @OneToMany(mappedBy = "category")
    private List<Manuscript> manuscripts;

    @OneToMany(mappedBy = "category")
    private List<Reviewer> reviewers;

    public static class CategoryBuilder{
        private Long categoryId;
        private String name;
        private Editor editor;
        private List<Reviewer> reviewers;
        private List<Manuscript> manuscripts;


        public CategoryBuilder(long id){
            this.categoryId = id;
        }
        public CategoryBuilder(){}

        public CategoryBuilder withId(long categoryId){
            this.categoryId = categoryId;
            return this;
        }
        public CategoryBuilder withName(String name){
            this.name = name;
            return this;
        }

        public CategoryBuilder withEditor(Editor editor){
            this.editor = editor;
            return this;
        }
        public CategoryBuilder withReviewers(List<Reviewer> reviewers){
            this.reviewers = reviewers;
            return this;
        }
        public CategoryBuilder withManuscripts(List<Manuscript> manuscripts){
            this.manuscripts = manuscripts;
            return this;
        }
        public Category build(){
            Category cat = new Category();
            cat.setCategoryId(categoryId);
            cat.setName(name);
            cat.setEditor(editor);
            cat.setReviewers(reviewers);
            cat.setManuscripts(manuscripts);
            return cat;
        }
    }
}