package cz.cvut.fel.ear.semestralka.service;

import cz.cvut.fel.ear.semestralka.domain.Category;

public interface CategoryService {

    Category findById(Long id);

    Category save(Category category);

    Iterable<Category> findAll();

    void delete(Category category);
}
