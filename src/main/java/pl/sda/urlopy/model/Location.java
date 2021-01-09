package pl.sda.urlopy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "LOCATION")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "LOCATION_ID")
    private Long id;

    @Column
    private String name;
}
