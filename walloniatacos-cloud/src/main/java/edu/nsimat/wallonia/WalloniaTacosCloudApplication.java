package edu.nsimat.wallonia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import edu.nsimat.wallonia.jparepository.IngredientRepository;
import edu.nsimat.wallonia.jparepository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WalloniaTacosCloudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WalloniaTacosCloudApplication.class, args);
		/*
		 * for(String name: context.getBeanDefinitionNames()) { log.info("Bean: " +
		 * name); }
		 */
	}
}
