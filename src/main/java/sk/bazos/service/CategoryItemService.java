package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sk.bazos.model.CategoryItem;

import sk.bazos.repository.CategoryItemRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categoryItem")
@Api(value = "categoryItem", description = "Basic crud over category item entity.")
public class CategoryItemService {

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @PostMapping(value="/postCategory")

        public Long createCategory( @RequestBody CategoryItem categoryItem ) {
        return categoryItemRepository.save(categoryItem).getId();
        }


    // Get All Categories
    @GetMapping(value="/getAllCategories",produces = "application/json")
    public List<CategoryItem> getAllCategories() {
        return categoryItemRepository.findAll();
    }

    // Get a Single Category
    @GetMapping("/oneCategory/{id}")
    public Optional<CategoryItem> getCategoryById(@PathVariable(value = "id") Long categoryId) {
        Optional<CategoryItem> categoryItemToupdate = categoryItemRepository.findById(categoryId);
        return categoryItemToupdate;
    }

    /// Update a Category
    @PutMapping("/updateCategory/{id}")
    public CategoryItem updateCategory(@PathVariable(value = "id") Long id, @RequestBody CategoryItem categoryDetails) {

        CategoryItem categoryItemToupdate = categoryItemRepository.getOne(id);


        categoryItemToupdate.setCategoryTitle(categoryDetails.getCategoryTitle());
        categoryItemToupdate.setSubcategoryTitle(categoryDetails.getSubcategoryTitle());
        if(categoryDetails.getPhotoData()!=null){
            categoryItemToupdate.setPhotoData(categoryDetails.getPhotoData());
        }
        return  categoryItemRepository.save(categoryItemToupdate);
    }
    // Delete a Category
    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long categoryId) {
        CategoryItem categoryItem = categoryItemRepository.getOne(categoryId);


        categoryItemRepository.delete(categoryItem);


    }
}

