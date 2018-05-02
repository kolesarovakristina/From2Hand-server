package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.SubCategoryItem;
import sk.bazos.repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;


    @RestController
    @RequestMapping("/subCategoryItem")
    @Api(value = "subCategoryItem", description = "Basic crud over subCategory item entity.")
    public class SubCategoryService {


        @Autowired
        private SubCategoryRepository subCategoryRepository;

        @PostMapping(value="/postSubCategory")

        public Long createSubCategory(@RequestBody SubCategoryItem subCategoryItem ) {
            return subCategoryRepository.save(subCategoryItem).getId();
        }


        // Get All SubCategories
        @GetMapping(value="/getAllSubCategories",produces = "application/json")
        public List<SubCategoryItem> getAllSubCategories() {
            return subCategoryRepository.findAll();
        }

        // Get a Single SubCategory
        @GetMapping("/oneSubCategory/{id}")
        public Optional<SubCategoryItem> getSubCategoryById(@PathVariable(value = "id") Long subCategoryId) {
            Optional<SubCategoryItem> subCategoryToupdate = subCategoryRepository.findById(subCategoryId);
            return subCategoryToupdate;
        }

        /// Update a SubCategory
        @PutMapping("/updateSubCategory/{id}")
        public SubCategoryItem updateSubCategory(@PathVariable(value = "id") Long id, @RequestBody SubCategoryItem subCategoryDetails) {
            SubCategoryItem subCategoryToupdate = subCategoryRepository.getOne(id);

            subCategoryToupdate.setSubCategoryTitle(subCategoryDetails.getSubCategoryTitle());


            if(subCategoryDetails.getPhotoData()!=null){
                subCategoryToupdate.setPhotoData(subCategoryDetails.getPhotoData());
            }
            return  subCategoryRepository.save(subCategoryToupdate);
        }
        // Delete a SubCategory
        @DeleteMapping("/deleteSubCategory/{id}")
        public void deleteSubCategory(@PathVariable(value = "id") Long subCategoryId) {
            SubCategoryItem subCategoryItem = subCategoryRepository.getOne(subCategoryId);
            subCategoryRepository.delete(subCategoryItem);


        }
    }



