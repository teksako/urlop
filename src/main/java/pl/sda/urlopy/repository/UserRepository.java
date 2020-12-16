package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.urlopy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
