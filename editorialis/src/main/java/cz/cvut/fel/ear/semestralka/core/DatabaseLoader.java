//package cz.cvut.fel.nss.core;
//
//import cz.cvut.fel.nss.category.Category;
//import cz.cvut.fel.nss.category.CategoryRepository;
//import cz.cvut.fel.nss.manuscript.Manuscript;
//import cz.cvut.fel.nss.manuscript.ManuscriptRepository;
//import cz.cvut.fel.nss.review.Review;
//import cz.cvut.fel.nss.review.ReviewRepository;
//import cz.cvut.fel.nss.user.PublishingUser;
//import cz.cvut.fel.nss.user.PublishingUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Component
//public class DatabaseLoader implements ApplicationRunner {
//    private final ManuscriptRepository manuscriptRepository;
//    private final ReviewRepository reviewRepository;
//    private final PublishingUserRepository publishingUserRepository;
//    private final CategoryRepository categoryRepository;
//
//    @Autowired
//    public DatabaseLoader(ManuscriptRepository manuscriptRepository, ReviewRepository reviewRepository, PublishingUserRepository publishingUserRepository, CategoryRepository categoryRepository) {
//        this.manuscriptRepository = manuscriptRepository;
//        this.reviewRepository = reviewRepository;
//        this.publishingUserRepository = publishingUserRepository;
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception{
//        Category cat = new Category("Astrophysics");
//        categoryRepository.save(cat);
//        List<PublishingUser> authors = Arrays.asList(new PublishingUser(cat);
//        author.setEmail("john.doe@example.com");
//        author.setFirstName("John");
//        author.setLastName("Doe");
//        publishingUserRepository.save(author);
//        Manuscript man1 = new Manuscript("Cerne diry",cat, Arrays.asList(new PublishingUser(cat)), false );
//        man1.setAuthors(author);
//        man1.setDateTimeUploaded(LocalDateTime.now());
//        manuscriptRepository.save(man1);
//        Review review = new Review(author);
//        review.setDateTimeAssigned(LocalDateTime.now().minusDays(7));
//        review.setDateTimeUploaded(LocalDateTime.now());
//        reviewRepository.save(review);
//        man1.addReview(review);
//    }
//}
