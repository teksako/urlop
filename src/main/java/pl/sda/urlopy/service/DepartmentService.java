package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.DepartmentAssembler;
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.repository.DepartmentRepository;


@Service
@RequiredArgsConstructor
@Data
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentAssembler departmentAssembler;

    public Long save(DepartmentDto departmentDto) {
        Department department = departmentAssembler.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return savedDepartment.getId();
    }
}
