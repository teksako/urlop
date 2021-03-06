package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.UserAssembler;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.RoleRepository;
import pl.sda.urlopy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByUserIsFalse(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String owner = principal.getUsername();
        return userRepository.findAllByUsernameIsNot(owner);
    }


    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) userRepository.findByUsername(username);
    }

    public User changePassword(String password){
                return userRepository.findUsersByPassword(password);
    }

    public void resetPasswordByAdmin(UserDto userDto) {

        Optional<User> user1 = userRepository.findById(userDto.getId());
        if (user1.isPresent()) {
            User user = user1.get();
            //user.setId(userDto.getId());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
        }
    }

}
