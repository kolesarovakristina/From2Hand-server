package sk.bazos.service;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Admin;
import sk.bazos.repository.AdminRepository;
import java.io.IOException;
import java.util.List;

    @RestController
    @RequestMapping ("admin")
    @Api(value = "admin", description = "Admin.")
    public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping(value="/createAdvert")
    public Long createAdvert(@RequestParam Long idu, @RequestParam String name, @RequestParam String descr, @RequestParam String category, @RequestParam String subcategory, @RequestParam Long price, @RequestParam String city) throws IOException {
        Admin adminCreateAdvert = new Admin();
        adminCreateAdvert.setIdu(idu);
        adminCreateAdvert.setName(name);
        adminCreateAdvert.setDescr(descr);
        adminCreateAdvert.setCategory(category);
        adminCreateAdvert.setSubcategory(subcategory);
        adminCreateAdvert.setPrice(price);
        adminCreateAdvert.setCity(city);
        return  adminRepository.save(adminCreateAdvert).getId();
    }
    @GetMapping
    public List<Admin> getAllAdverts(){
        return adminRepository.findAll();
    }

    @DeleteMapping("/deleteAdvert/{id}")
    public void deleteAdvertById(@PathVariable(value = "id")Long id){
        adminRepository.deleteById(id);
    }

    @PostMapping(value="/createCategory")
    public Long createCategory(@RequestParam String id, @RequestParam String idAdvert, @RequestParam String Category, @RequestParam String subcategory) throws IOException {
        Admin adminCreateCategory = new Admin();
        adminCreateCategory.setId( id);
        adminCreateCategory.setIdAdvert(idAdvert);
        adminCreateCategory.setCategory(Category);
        adminCreateCategory.setSubcategory(subcategory);
        return adminRepository.save(adminCreateCategory).getId();
    }
    @GetMapping("/showCategory/{id}")
    public List<Admin> getAllCategories(@PathVariable(value = "id")Long id) {
        return adminRepository.findAll();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deletecategoryById(@PathVariable(value = "id")Long id){adminRepository.deleteById(id);}
}
