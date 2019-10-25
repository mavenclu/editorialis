package cz.cvut.fel.ear.presentation.controller;

import cz.cvut.fel.ear.domain.Author;
import cz.cvut.fel.ear.domain.Manuscript;
import cz.cvut.fel.ear.domain.Review;
import cz.cvut.fel.ear.domain.ReviewSuggestion;
import cz.cvut.fel.ear.dao.AuthorRepository;
import cz.cvut.fel.ear.dao.CategoryRepository;
import cz.cvut.fel.ear.dao.ManuscriptRepository;
import cz.cvut.fel.ear.dao.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final ManuscriptRepository manuscriptRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, ManuscriptRepository manuscriptRepository, AuthorRepository authorRepository, ReviewRepository reviewRepository) {
        this.categoryRepository = categoryRepository;
        this.manuscriptRepository = manuscriptRepository;
        this.authorRepository = authorRepository;
        this.reviewRepository = reviewRepository;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "album";
    }

    @RequestMapping("/editor")
    public String editorDahsboard(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        List<Manuscript> manuscripts = (List<Manuscript>) manuscriptRepository.findAll();
        for (Manuscript manuscript : manuscripts
        ) {
            manuscript.setAuthors((List<Author>) authorRepository.findAll());
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

}
