package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.urlopy.model.RoleType;
import pl.sda.urlopy.model.UserRole;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    //Optional<UserRole> findByType(RoleType type);
}

