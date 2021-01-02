package pl.sda.urlopy.model;
import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column
    private String name;

    @Column
    private Long headOfDepartmentId;

    @Column
    private String location;


}
