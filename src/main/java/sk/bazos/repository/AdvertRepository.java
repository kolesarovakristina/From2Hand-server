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

    List<Advert>findByCategoryId(@Param("categoryId") Long categoryId);

    List<Advert>findAdvertsByUser_Id(@Param("userId")Long userId);

    List<Advert>findAdvertsByCityIsLike(@Param("city")String city);

    List<Advert>findAdvertsByNameContaining(@Param("text")String text);

    //List<Advert>findAdvertsByNameContainingAndCityIsLikeAndAndCategory_Id(@Param(AdvertFindDto));


}