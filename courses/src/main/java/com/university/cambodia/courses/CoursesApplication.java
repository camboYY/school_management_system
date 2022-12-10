package com.university.cambodia.courses;

import com.university.cambodia.courses.entity.Role;
import com.university.cambodia.courses.model.ERoleModel;
import com.university.cambodia.courses.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CoursesApplication {

	private static Logger LOG = LoggerFactory
			.getLogger(CoursesApplication.class);


	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(CoursesApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Bean
	public CommandLineRunner addNewRole(RoleRepository roleRepository) {
		return (args) -> {
			LOG.info("EXECUTING : command line runner");
//			Role roleUser = new Role(null, ERoleModel.ROLE_USER);
//			Role roleAdmin = new Role(null, ERoleModel.ROLE_ADMIN);
//			Role roleSupperAdmin = new Role(null, ERoleModel.ROLE_SUPPER_ADMIN);
//			Role roleDriver = new Role(null, ERoleModel.ROLE_DRIVER);
//			Role roleCleaner = new Role(null, ERoleModel.ROLE_CLEANER);
//			roleRepository.saveAll(List.of(roleAdmin,roleCleaner, roleDriver, roleSupperAdmin, roleUser));
		};
	}

}
