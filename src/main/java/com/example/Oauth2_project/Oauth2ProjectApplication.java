package com.example.Oauth2_project;

import com.example.Oauth2_project.config.RSAKeyRecord;
import com.example.Oauth2_project.entity.UserInfoEntity;
import com.example.Oauth2_project.repo.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyRecord.class)
public class Oauth2ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2ProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserInfoRepo userInfoRepo, PasswordEncoder passwordEncoder) {
		{

			return args -> {

				UserInfoEntity manager = new UserInfoEntity();
				manager.setUserName("Manager");
				manager.setPassword(passwordEncoder.encode("password"));
				manager.setRoles("ROLE_MANAGER");
				manager.setEmailId("manager@manager.com");


				UserInfoEntity admin = new UserInfoEntity();
				admin.setUserName("Admin");
				admin.setPassword(passwordEncoder.encode("password"));
				admin.setRoles("ROLE_ADMIN");
				admin.setEmailId("admin@admin.com");

				UserInfoEntity user = new UserInfoEntity();
				user.setUserName("User");
				user.setPassword(passwordEncoder.encode("password"));
				user.setRoles("ROLE_USER");
				user.setEmailId("user@user.com");

				userInfoRepo.saveAll(List.of(manager, admin, user));


			};
		}


	}
}
