package pl.sda.urlopy.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.urlopy.model.AccountStatus;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull

    private String firstname;
    @NotNull

    private String lastname;
    @NotNull

    private String username;
    @NotNull

    private String password;
    @NotNull

    private String matchingPassword;
//    private Long departmentId;
//    private Data createData;
//    private AccountStatus status;

}
