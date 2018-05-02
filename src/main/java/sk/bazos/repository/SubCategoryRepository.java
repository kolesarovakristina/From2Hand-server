package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.SubCategoryItem;

public interface SubCategoryRepository extends JpaRepository<SubCategoryItem,Long> {
}
