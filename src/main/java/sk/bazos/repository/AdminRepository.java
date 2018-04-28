package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.Admin;

public interface AdminRepository extends JpaRepository <Admin, Long> {
}
