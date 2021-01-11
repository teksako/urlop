package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.urlopy.model.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAll();
}
