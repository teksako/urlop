package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.DepartmentAssembler;
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;


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

    public List<Department> findAll(){ return departmentRepository.findAll();}


//      AKTUALIZACJA DZIAŁÓW
    public void departmentUpdate(DepartmentDto departmentDto) {
        Optional<Department> department = departmentRepository.findById(departmentDto.getId());
        department.get().setHeadOfDepartment(departmentDto.getHeadOfDepartment());
        departmentRepository.save(department.get());
    }

}
