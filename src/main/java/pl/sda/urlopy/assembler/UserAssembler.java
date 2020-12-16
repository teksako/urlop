package pl.sda.urlopy.assembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import java.util.Date;

@Component
public class UserAssembler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(UserDto userDto) {
        User entity = new User();
        entity.setFirstname(userDto.getFirstname());
        entity.setLastname(userDto.getLastname());
        entity.setCreateDate(new Date());
        entity.setUsername(userDto.getUsername());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return entity;
    }
}
