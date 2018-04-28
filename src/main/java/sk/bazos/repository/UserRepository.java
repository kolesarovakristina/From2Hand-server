package sk.bazos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bazos.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
