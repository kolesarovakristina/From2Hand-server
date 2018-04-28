package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.CategoryItem;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {

}
