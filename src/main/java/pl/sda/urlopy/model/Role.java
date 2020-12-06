package pl.sda.urlopy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name= "ROLE")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType type;

}
