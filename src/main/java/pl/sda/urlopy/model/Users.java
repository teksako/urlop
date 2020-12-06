package pl.sda.urlopy.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String firstname;
    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Long department_id;

}
