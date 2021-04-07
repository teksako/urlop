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
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.RoleRepository;
import pl.sda.urlopy.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserAssembler userAssembler;



    private UserDetails userDetailsService() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String actualLoginUser() {
        return userDetailsService().getUsername();
    }

    public String actualLoginUserRole() {
        return userDetailsService().getAuthorities().toString();
    }

    public User findUserByUsername() {
        return userRepository.findUserByUsername(actualLoginUser());
    }

    public String userData(User user) {
        return user.getFirstname() + " " + user.getLastname();
    }

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

    public List<User> findAllByUserIsFalse() {
        return userRepository.findAllByUsernameIsNot(actualLoginUser());
    }

    public User changePassword(String password) {
        return userRepository.findUsersByPassword(password);
    }

    public void deleteUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        userRepository.delete(user.get());
    }

    public void updateUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        user.get().setDepartment(userDto.getDepartment());
        user.get().setRole(userDto.getRole());
        userRepository.save(user.get());
    }

    public void resetPasswordByAdmin(UserDto userDto) {
        Optional<User> user1 = userRepository.findById(userDto.getId());
        if (user1.isPresent()) {
            User user = user1.get();
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
        }
    }


}
