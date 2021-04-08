package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    List<User> findAll();

    List<User> findAllByUsernameIsNot(String owner);
    List<User> findAllByUsernameIsNotAndDepartmentIs(String owner, String department);

    User findUsersByPassword(String password);

    User findUserByUsername(String username);

}
