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
@Api
public class CategoryItemService {

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @PostMapping

        public Long createCategory( @RequestBody CategoryItem categoryItem ) {
        return categoryItemRepository.save(categoryItem).getCategoryId();
        }


    // Get All Categories
    @GetMapping
    public List<CategoryItem> getAllCategories() {
        return categoryItemRepository.findAll();
    }

    // Get a Single Category
    @GetMapping("/{id}")
    public Optional<CategoryItem> getCategoryById(@PathVariable(value = "id") Long categoryId) {
        Optional<CategoryItem> categoryItemToupdate = categoryItemRepository.findById(categoryId);
        return categoryItemToupdate;
    }

    /// Update a Category
    @PutMapping("/{id}")
    public CategoryItem updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody CategoryItem categoryDetails) {

        CategoryItem categoryItemToupdate = categoryItemRepository.getOne(categoryId);


        categoryItemToupdate.setCategoryTitle(categoryDetails.getCategoryTitle());
        categoryItemToupdate.setSubcategoryTitle(categoryDetails.getSubcategoryTitle());
        if(categoryDetails.getPhotoData()!=null){
            categoryItemToupdate.setPhotoData(categoryDetails.getPhotoData());
        }
        return  categoryItemRepository.save(categoryItemToupdate);
    }
    // Delete a Category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long categoryId) {
        CategoryItem categoryItem = categoryItemRepository.getOne(categoryId);


        categoryItemRepository.delete(categoryItem);


    }
}

