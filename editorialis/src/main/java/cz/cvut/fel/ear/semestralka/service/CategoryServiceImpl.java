package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.dao.CategoryRepository;
import cz.cvut.fel.ear.semestralka.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository catRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public Category findById(Long id) {
        return catRepo.findById(id).orElseThrow(
                () -> {
                    log.error("trying to find empty category");
                    throw new IllegalArgumentException("Could not find category with id: " + id);
                }
        );
    }

    @Override
    public Category save(Category category) {
        return catRepo.save(category);
    }

    @Override
    public Iterable<Category> findAll() {
        return catRepo.findAll();
    }

    @Override
    public void delete(Category category) {
        if (catRepo.findById(category.getCategoryId()).isPresent()) {
            catRepo.delete(category);
        } else {
            log.error("trying to delete empty category");
            throw new IllegalArgumentException("could not delete category");
        }

    }
}
