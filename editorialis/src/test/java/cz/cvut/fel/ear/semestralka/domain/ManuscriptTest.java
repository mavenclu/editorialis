package cz.cvut.fel.ear.semestralka.domain;

import cz.cvut.fel.ear.semestralka.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class ManuscriptTest {

    @Autowired
    ManuscriptService manscrRepo;
    @Autowired
    private AuthorService authorRepo;
    @Autowired
    private CategoryService categoryRepo;
    private Author author;
    private Manuscript man;
    private Reviewer reviewer;
    private Category category;
    @Autowired
    private ReviewerService reviewerRepo;
    @Autowired
    private ReviewService reviewRepo;

    @BeforeEach
    void setUp() {
        category = new Category("Test category");
        author = new Author.AuthorBuilder()
                .withEmail("auth@test.com")
                .withFirstName("First")
                .withLastName("Last")
                .build();
        man = new Manuscript.ManuscriptBuilder()
                .withAuthors(List.of(author))
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
    void shortTitleOnManuscriptShouldThrowExceeptionOnSaveTest() {

        Manuscript man = new Manuscript("Aj", category);
        assertThatThrownBy(() -> manscrRepo.save(man)).isInstanceOf(ConstraintViolationException.class);
    }

    @Transactional
    @Test
    void newReviewWithManscrAndReviewerTest() {
        manscrRepo.save(man);
        reviewerRepo.save(reviewer);
        Review review = new Review(man, reviewer);
        reviewRepo.save(review);
        assertThat(review.getManuscript()).isEqualTo(man);
        assertThat(review.getReviewer()).isEqualTo(reviewer);


    }

    @Transactional
    @Test
    void manFromReviewSideShouldMakeManAwareOfTheRelationTest() {
        authorRepo.save(author);
        categoryRepo.save(category);
        manscrRepo.save(man);
        reviewerRepo.save(reviewer);
        Review review = new Review(man, reviewer);
        reviewRepo.save(review);
        man.addReview(review);
        assertThat(man.getReviews()).isNotEmpty();
        assertThat(review.getManuscript()).as("check relationship from review side").isNotNull();
        man.setReviewer(null);
        reviewerRepo.delete(reviewer);
        manscrRepo.delete(man);
        authorRepo.delete(author);

    }
}