package pl.sda.urlopy.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column
    private String nameOfDepartment;

    @Column
    private String headOfDepartment;

    @Column
    private String location;


}
