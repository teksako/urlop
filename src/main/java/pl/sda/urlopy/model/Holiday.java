package pl.sda.urlopy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HOLIDAY")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOLIDAY_ID")
    private Long id;

//    @Column
//    private Long userId;

    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    private Long countOfDays;

    @Column
    private String accepted;

    @Column
    private String replacement;

    @Column
    private String actualLoggedUser;


}
