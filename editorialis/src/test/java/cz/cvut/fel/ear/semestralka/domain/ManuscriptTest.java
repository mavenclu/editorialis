package cz.cvut.fel.ear.semestralka.domain;

import cz.cvut.fel.ear.semestralka.dao.*;
import  org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ManuscriptTest {

    @Autowired
    ManuscriptRepository manscrRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    private Author author;
    private Manuscript man;
    private Reviewer reviewer;
    private Category category;
    @Autowired
    private ReviewerRepository reviewerRepo;
    @Autowired
    private ReviewRepository reviewRepo;

    @BeforeEach
    void setUp() {
        category = new Category("Test category");
         author = new Author.AuthorBuilder()
                .withEmail("auth@test.com")
                .withFirstName("First")
                .withLastName("Last")
                .build();
          man = new Manuscript.ManuscriptBuilder()
                .withSender(author)
                .withTitle("My new fancy manuscript")
                .build();
          reviewer = new Reviewer.ReviewerBuilder()
                  .withFirstName("Jan")
                  .withLastName("Novak")
                  .withEmail("reviewer@test.com")
                  .withCategory(category)
                  .build();

    }


    @Test
    void shortTitleOnManuscriptShouldThrowExceeptionOnSaveTest(){
        authorRepo.save(author);
        Manuscript  man = new Manuscript("Aj", author);
        assertThatThrownBy(()->{
            manscrRepo.save(man);
        }).isInstanceOf(ConstraintViolationException.class);
        authorRepo.delete(author);
    }

   @Test
    void settingManFromReviewSideShouldMakeManAwareOfTheRelationTest(){
        authorRepo.save(author);
        manscrRepo.save(man);
        categoryRepo.save(category);
        reviewerRepo.save(reviewer);
        Review review = new Review.ReviewBuilder()
                .withManuscript(man)
                .withReviewer(reviewer)
                .build();
        reviewRepo.save(review);
       System.out.println(man.getReviews());
       assertThat(man.getReviews().isEmpty());
       reviewRepo.delete(review);
       reviewerRepo.delete(reviewer);
       manscrRepo.delete(man);
       authorRepo.delete(author);
       categoryRepo.delete(category);
   }
   @Test
    void manFromReviewSideShouldMakeManAwareOfTheRelationTest(){
        authorRepo.save(author);
        manscrRepo.save(man);
        categoryRepo.save(category);
        reviewerRepo.save(reviewer);
        Review review = new Review.ReviewBuilder()
                .withManuscript(man)
                .withReviewer(reviewer)
                .build();
        man.addReview(review);
        reviewRepo.save(review);
        assertThat(man.getReviews()).isNotEmpty();
        assertThat(review.getManuscript()).as("check relationship from review side").isNotNull();
        man.setSender(null);
        man.setReviewer(null);
        reviewerRepo.delete(reviewer);
       manscrRepo.delete(man);
       authorRepo.delete(author);

   }
}