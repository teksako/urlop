package pl.sda.urlopy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import pl.sda.urlopy.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
