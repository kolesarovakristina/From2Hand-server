package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.Advert;
import sk.bazos.model.Category;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> findAdvertsByCategory_Id(Long categoryId);
}
