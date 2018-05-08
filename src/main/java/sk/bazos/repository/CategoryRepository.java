package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.Category;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByParentIsNull();
}
