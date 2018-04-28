package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Advert;

import sk.bazos.repository.AdvertRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/advert")
@Api(value = "advert", description = "advert")
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @PostMapping(value="/postAdvert")
        public Long createAdvert(@RequestParam Long idu, @RequestParam String name, @RequestParam String descr, @RequestParam String category, @RequestParam String subcategory, @RequestParam Long price, @RequestParam String city) {
        Advert advertForSave = new Advert();
        advertForSave.setIdu(idu);
        advertForSave.setName(name);
        advertForSave.setDescr(descr);
        advertForSave.setCategory(category);
        advertForSave.setSubcategory(subcategory);
        advertForSave.setPrice(price);
        advertForSave.setCity(city);
        return advertRepository.save(advertForSave).getId();
    }

    @GetMapping(value="/allAdvert",produces = "application/json")
    public List<Advert> getAllAdvert() {
        return advertRepository.findAll();
    }

    @GetMapping("/oneAdvert/{id}")
    public Optional<Advert> getAdvertById(@PathVariable(value = "id") Long id) {
        Optional<Advert> advertToupdate = advertRepository.findById(id);
        return advertToupdate;
    }

    @DeleteMapping("/deleteAdveert/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        Advert advert = advertRepository.getOne(id);

        advertRepository.delete(advert);


    }

}
