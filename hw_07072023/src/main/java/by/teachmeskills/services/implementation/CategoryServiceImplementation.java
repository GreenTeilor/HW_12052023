package by.teachmeskills.services.implementation;

import by.teachmeskills.entities.Category;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.repositories.implementation.CategoryRepositoryImplementation;
import by.teachmeskills.services.CategoryService;

import java.util.List;

public class CategoryServiceImplementation implements CategoryService {
    private CategoryRepositoryImplementation categoryRepository = new CategoryRepositoryImplementation();
    @Override
    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public List<Category> read() throws BadConnectionException {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }
}
