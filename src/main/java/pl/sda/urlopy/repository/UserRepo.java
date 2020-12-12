package pl.sda.urlopy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.urlopy.model.User;

public interface UserRepo  extends CrudRepository<User, Long> {

}
