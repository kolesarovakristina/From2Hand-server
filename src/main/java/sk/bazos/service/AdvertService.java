package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;
import sk.bazos.model.User;
import sk.bazos.repository.AdvertRepository;
import sk.bazos.service.util.AdvertUtil;
import sk.bazos.to.AdvertCreateDto;
import sk.bazos.to.AdvertFindDto;

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

    @PostMapping(consumes = "multipart/*")
    @Secured("ROLE_USER")
    public Long createAdvert(@ModelAttribute AdvertCreateDto advertCreateDto) {
        Advert advert = AdvertUtil.fromCreate(advertCreateDto);
        if (advertCreateDto.getCategory() != null) {
            Category category = entityManager.getReference(Category.class, advertCreateDto.getCategory());
            advert.setCategory(category);
        }
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User reference = entityManager.getReference(User.class, user.getId());
        advert.setUser(reference);
                return advertRepository.save(advert).getId();
    }

    //@PostMapping
    //@Secured("ROLE_USER")
    //public List<Advert> findAdvert(@RequestBody AdvertFindDto advertFindDto) {
  //      return advertRepository.findAdvertsByCategorySubcategoriesOrderById(advertFindDto);
    //}

    @GetMapping("/all")
    public List<Advert> getAllAdvert() {
        return advertRepository.findAll();
    }


    @GetMapping
    public List<Advert> getAllUserAdvert() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User reference = entityManager.getReference(User.class, user.getId());
        return advertRepository.findAdvertsByUser_Id(reference.getId());
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
        advertItemToupdate.setCityDistrict(advert.getCityDistrict());

        return advertRepository.save(advertItemToupdate);
    }


    @DeleteMapping("/{id}")
    public void deleteAdvert(@PathVariable(value = "id") Long id) {
        Advert advert = advertRepository.getOne(id);
        advertRepository.delete(advert);
    }

    @GetMapping("/category/{id}")
    public List<Advert> getBySubCategory(@PathVariable(value = "id") Long SubcategoryId) {
        return advertRepository.findAdvertsByCategorySubcategoriesOrderById(SubcategoryId);
    }


    @GetMapping("/city/{city}")
    public List<Advert> getByCity(@PathVariable(value = "city") String cityDistrict) {
        return advertRepository.findAdvertsByCityDistrictLike(cityDistrict);
    }

    @GetMapping("/text/{text}")
    public List<Advert> getByText(@PathVariable(value = "text") String text) {
        return advertRepository.findAdvertsByNameContaining(text);
    }


}