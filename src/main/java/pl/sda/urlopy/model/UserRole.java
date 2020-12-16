package pl.sda.urlopy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Data
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name="ROLE_ID")
    private Long id;

    @Column(name="NAME")
    @Enumerated(EnumType.STRING)
    private RoleType type;
}
