package pl.sda.urlopy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {

    private Long id;
    private String nameOfDepartment;
    private String location;

}
