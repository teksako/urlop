package pl.sda.urlopy.model;
import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENTS")
public class Departments {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENTS_ID")
    private Long id;

    @Column
    private Long headOfDepartment_id;

    @Column
    private String name;
}
