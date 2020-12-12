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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
