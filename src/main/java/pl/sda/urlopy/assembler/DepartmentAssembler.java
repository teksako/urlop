package pl.sda.urlopy.assembler;

import org.springframework.stereotype.Component;
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.Holiday;

@Component
public class DepartmentAssembler {
    public Department toEntity(DepartmentDto departmentDto) {
        Department entity = new Department();
        entity.setNameOfDepartment(departmentDto.getNameOfDepartment());
        entity.setLocation(departmentDto.getLocation());
        entity.setHeadOfDepartment(departmentDto.getHeadOfDepartment());
        return entity;
    }
}
