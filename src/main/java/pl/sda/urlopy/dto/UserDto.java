package pl.sda.urlopy.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.urlopy.model.AccountStatus;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    @Size(min = 5)
    private String username;
    @Size(min = 5)
    private String password;
    private String confirmedPassword;
    private Date createData;
    private AccountStatus status;
//    private Long departmentId;
}
