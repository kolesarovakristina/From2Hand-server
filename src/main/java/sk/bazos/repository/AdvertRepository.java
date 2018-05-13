package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;

import javax.persistence.NamedQuery;
import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> findByCategoryId(@Param("categoryId") Long categoryId);
}
