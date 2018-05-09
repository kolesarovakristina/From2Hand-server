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
@Api
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @PostMapping
    public Long createAdvert( @RequestBody Advert advert) {
        return advertRepository.save(advert).getId();
    }

    @GetMapping
    public List<Advert> getAllAdvert() {
        return advertRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Advert> getAdvertById(@PathVariable(value = "id") Long id) {
        Optional<Advert> advertToupdate = advertRepository.findById(id);
        return advertToupdate;
    }

    /// Update a Category
    @PutMapping("/{id}")
    public Advert updateAdvert(@PathVariable(value = "id") Long id, @RequestBody Advert advertDetails) {

        Advert advertItemToupdate = advertRepository.getOne(id);


        advertItemToupdate.setName(advertDetails.getName());
        advertItemToupdate.setDescr(advertDetails.getDescr());
        advertItemToupdate.setCategoryId(advertDetails.getCategoryId());
        advertItemToupdate.setSubcategoryId(advertDetails.getSubcategoryId());
        advertItemToupdate.setPrice(advertDetails.getPrice());
        advertItemToupdate.setCity(advertDetails.getCity());

        return  advertRepository.save(advertItemToupdate);
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        Advert advert = advertRepository.getOne(id);

        advertRepository.delete(advert);


    }

}
