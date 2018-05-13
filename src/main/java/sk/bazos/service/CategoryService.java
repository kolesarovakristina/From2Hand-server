package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.bazos.model.Category;
import sk.bazos.model.Photo;
import sk.bazos.repository.CategoryRepository;
import sk.bazos.service.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
@Api(value = "category", description = "Basic crud over category entity.")
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    public Long addCategory(@RequestParam(required = true) String title, @RequestParam(required = false) MultipartFile photo) {
        Category category = new Category();
        category.setTitle(title);

        if (photo != null) {
            try {
                Photo photoData = new Photo();
                photoData.setData(photo.getBytes());
                category.setPhoto(photoData);
            } catch (IOException e) {
                throw new ServiceException("Something terrible happens with photo data", e);
            }
        }
        return categoryRepository.save(category).getId();
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findCategoriesByParentIsNull();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElse(null);
    }

    @PostMapping("/{id}/subcategory")
    public void addSubcategory(@PathVariable("id") Long id, @RequestBody(required = true) Category category) {
        Optional<Category> byId = categoryRepository.findById(id);
        byId.ifPresent(category1 -> {
            category1.addSubcategory(category);
            categoryRepository.save(category);
        });
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long id, @RequestBody Category categoryDetails) {

        Category categoryToupdate = categoryRepository.getOne(id);

        categoryToupdate.setTitle(categoryDetails.getTitle());
        if (categoryDetails.getPhoto() != null && categoryDetails.getPhoto().getId() != null) {
            final Photo photo = entityManager.getReference(Photo.class, categoryDetails.getPhoto().getId());
            categoryToupdate.setPhoto(photo);
        }
        return categoryRepository.save(categoryToupdate);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        Category category = categoryRepository.getOne(id);
        categoryRepository.delete(category);


    }

}
