package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;
import sk.bazos.model.User;
import sk.bazos.service.util.AdvertUtil;
import sk.bazos.to.AdvertFindDto;

import javax.persistence.NamedQuery;
import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert>findAdvertsByCategory_Id(@Param("categoryId") Long categoryId);

    List<Advert>findAdvertsByUser_Id(@Param("id") User id);

    List<Advert>findAdvertsByCityDistrictLike(@Param("cityDistrict")String cityDistrict);

    List<Advert>findAdvertsByNameContaining(@Param("text")String text);

    List<Advert> findAdvertsByUser_Id(Long id);

    List<Advert> findAdvertsByCategorySubcategoriesOrderById(Long id);

    //List<Advert>findAdvertsByNameContainingAndCityIsLikeAndAndCategory_Id(@Param(AdvertFindDto));



}