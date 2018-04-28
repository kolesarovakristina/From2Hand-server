package sk.bazos.repository;

import sk.bazos.model.Add;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddRepository extends JpaRepository<Add, Long> {
}
