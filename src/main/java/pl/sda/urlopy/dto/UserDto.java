package pl.sda.urlopy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.urlopy.model.AccountStatus;

@Data
@NoArgsConstructor
public class UserDto {

    private String firstname;
    private String lastname;
    private String username;
    private String confirmedPassword;
    private String password;
    private Long departmentId;
    private Data createData;
    private AccountStatus status;

}
