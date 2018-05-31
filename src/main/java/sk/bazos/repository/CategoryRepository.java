package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import sk.bazos.model.Category;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    //List<Category> (@Param("id") Category id);

    List<Category> findCategoriesByParentIsNull();
}
