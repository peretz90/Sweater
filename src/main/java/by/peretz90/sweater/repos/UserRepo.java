package by.peretz90.sweater.repos;

import by.peretz90.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findByActivationCode(String code);
}
