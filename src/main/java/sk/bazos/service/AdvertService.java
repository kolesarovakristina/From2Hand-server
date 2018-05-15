package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;
import sk.bazos.repository.AdvertRepository;
import sk.bazos.service.util.AdvertUtil;
import sk.bazos.to.AdvertCreateDto;

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
    public Long createAdvert(@RequestBody AdvertCreateDto advertCreateDto) {
        Advert advert = AdvertUtil.fromCreate(advertCreateDto);
        if (advertCreateDto.getCategoryId() != null) {
            Category category = entityManager.getReference(Category.class, advertCreateDto.getCategoryId());
            advert.setCategory(category);
        }
        //todo user by security context
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
    public void deleteAdvert(@PathVariable(value = "id") Long id) {
        Advert advert = advertRepository.getOne(id);
        advertRepository.delete(advert);
    }

    @GetMapping("/category/{id}")
    public List<Advert> getByCategory(@PathVariable(value = "id") Long categoryId) {
        return advertRepository.findByCategoryId(categoryId);
    }

    @GetMapping("/user/{id}")
    public List<Advert> getByUser(@PathVariable(value = "id") Long userId) {
        return advertRepository.findByCategoryId(userId);
    }

}
