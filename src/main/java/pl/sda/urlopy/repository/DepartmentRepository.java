package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.urlopy.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
