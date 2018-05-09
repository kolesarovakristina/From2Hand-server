package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Category;
import sk.bazos.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
@Api(value = "category", description = "Basic crud over category entity.")
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public Long addCategory(@RequestBody(required = true) Category category) {
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
        //if(categoryDetails.getPhotos()!=null){
            //categoryToupdate.setPhotos(categoryDetails.getPhotos());
       // }
        return  categoryRepository.save(categoryToupdate);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        Category category = categoryRepository.getOne(id);
        categoryRepository.delete(category);


    }

}
