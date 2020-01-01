package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.domain.*;
import cz.cvut.fel.ear.semestralka.dao.AuthorRepository;
import cz.cvut.fel.ear.semestralka.dao.CategoryRepository;
import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewRepository;
import cz.cvut.fel.ear.semestralka.service.ManuscriptService;
import cz.cvut.fel.ear.semestralka.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final ManuscriptRepository manuscriptRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;
    private final ManuscriptService manuscriptService;
    private final ReviewService reviewService;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, ManuscriptRepository manuscriptRepository, AuthorRepository authorRepository, ReviewRepository reviewRepository, ManuscriptService manuscriptService, ReviewService reviewService) {
        this.categoryRepository = categoryRepository;
        this.manuscriptRepository = manuscriptRepository;
        this.authorRepository = authorRepository;
        this.reviewRepository = reviewRepository;
        this.manuscriptService = manuscriptService;
        this.reviewService = reviewService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "album";
    }

    @RequestMapping("/editor")
    public String editorDahsboard(Model model) {
        model.addAttribute("nmbNew", manuscriptService.getNumberOfNewManuscripts());
        model.addAttribute("nmbCompl", manuscriptService.getNumberOfManuscriptsWithCompleteReviews());
        model.addAttribute("nmbRev", manuscriptService.getNumberOfManuscriptsInRevision());
        model.addAttribute("nmbDel", reviewService.getNumberOfReviewsInDelay());
        model.addAttribute("categories", categoryRepository.findAll());
        Iterable<Manuscript> manuscripts = manuscriptRepository.findAll();
        Set<Author> authors = new HashSet<>();
        authors.add(
                new Author.AuthorBuilder().withFirstName("John")
                .withLastName("Smith")
                .withEmail("jsmith@example.com")
                .build());
        authors.add(
                new Author.AuthorBuilder().withFirstName("Jana")
                .withLastName("Novakoava")
                .withEmail("janovakova@example.com").build()
        );

        for (Manuscript manuscript : manuscripts
        ) {
            manuscript.setAuthors(authors);
        }
        List<Review> reviews = (List<Review>) reviewRepository.findAll();
        for (Review review : reviews
        ) {
            review.setReviewSuggestion(ReviewSuggestion.MINOR_REVISIONS);
        }

        model.addAttribute("manuscripts", manuscripts);
        model.addAttribute("title", "Dashboard");
        return "editor1";
    }

    @RequestMapping("/manuscripts/{id}")
    public String showManuscript(Model model, @PathVariable Long id){
        Manuscript manuscript = manuscriptRepository.findByDocumentId(id);
        model.addAttribute("manuscript",manuscript );
        return "manuscripts/manuscript";
    }
    @RequestMapping("/manuscripts")
    public String showManuscripts(Model model){
        model.addAttribute("manuscripts", manuscriptRepository.findByManuscriptStatus(ManuscriptStatus.NEW));
        model.addAttribute("heading", "New Manuscripts");
        model.addAttribute("firstCol", "Title");
        model.addAttribute("thirdCol", "Category");
        return "manuscripts1";
    }
    @ExceptionHandler(NotFoundException.class)
    public String notFount(Model model, Exception ex){
        model.addAttribute("exception", ex);
        return "error";
    }



}
