package pl.sda.urlopy.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.urlopy.model.RoleType;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.model.UserRole;
import pl.sda.urlopy.repository.RoleRepository;
import pl.sda.urlopy.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@ComponentScan("pl.sda")
@EntityScan("pl.sda.urlopy.model")
@EnableJpaRepositories("pl.sda.urlopy.repository")
@Import(SecurityConfig.class)
public class UrlopyApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(UrlopyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		if (userRepository.findAll().size() == 0){
			UserRole userRole = new UserRole();
			userRole.setType(RoleType.ADMIN);
			userRole = roleRepository.save(userRole);

			User admin = new User();
			admin.setUsername("administrator");
			admin.setPassword(encoder.encode("Qsz1oph2"));
			admin.setCreateDate(new Date());
			admin.setFirstname("Paweł");
			admin.setLastname("Kwapisiński");
			admin.setRoles(Arrays.asList(userRole));
			userRepository.save(admin);


		}
	}

}
