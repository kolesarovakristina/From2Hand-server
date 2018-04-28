package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.Advert;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
