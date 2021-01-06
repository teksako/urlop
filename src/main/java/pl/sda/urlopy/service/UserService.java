package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.UserAssembler;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.RoleRepository;
import pl.sda.urlopy.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UserService  {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final RoleRepository roleRepository;

    public Long save(UserDto userDto) {
        User user = userAssembler.toEntity(userDto);
//        Optional<UserRole> userRole = roleRepository.findByType(RoleType.ADMIN);
//        user.setRoles(Arrays.asList(userRole.get()));
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
//
//   public String loadUsers(){
//       //User user = userAssembler.toEntity(userDto);
//        User loadUser = userRepository.findAllByUsername();
//        return loadUser.getUsername();
//   }
}
