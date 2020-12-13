package pl.sda.urlopy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HOLIDAY")
public class Holiday {
    @Id
    @GeneratedValue
    @Column(name = "HOLIDAY_ID")
    private Long id;

    @Column
    private Long user_id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;


}
