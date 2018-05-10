package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;
import sk.bazos.repository.AdvertRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/advert")
@Api
public class AdvertService {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AdvertRepository advertRepository;

    @PostMapping
    public Long createAdvert(@RequestBody Advert advert) {
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
    public Advert updateAdvert(@PathVariable(value = "id") Long id, @RequestBody Advert advert) {

        Advert advertItemToupdate = advertRepository.getOne(id);
        advertItemToupdate.setName(advert.getName());
        advertItemToupdate.setDescr(advert.getDescr());
        if (advert.getCategory() != null && advert.getCategory().getId() != null) {
            final Category category = entityManager.getReference(Category.class, advert.getCategory().getId());
            advertItemToupdate.setCategory(category);
        }
        advertItemToupdate.setPrice(advert.getPrice());
        advertItemToupdate.setCity(advert.getCity());

        return advertRepository.save(advertItemToupdate);
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        Advert advert = advertRepository.getOne(id);

        advertRepository.delete(advert);


    }

}
