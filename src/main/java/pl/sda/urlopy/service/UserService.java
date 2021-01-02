package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.UserAssembler;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.RoleType;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.model.UserRole;
import pl.sda.urlopy.repository.RoleRepository;
import pl.sda.urlopy.repository.UserRepository;


import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class   UserService {
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
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return (UserDetails) userRepository.findByUsername(s);
//    }
}
