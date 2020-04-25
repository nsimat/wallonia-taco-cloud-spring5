package edu.nsimat.wallonia.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import edu.nsimat.wallonia.domain.User;
import edu.nsimat.wallonia.jparepository.IngredientRepository;
import edu.nsimat.wallonia.jparepository.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

	@Bean
	@Profile("!prod")
	public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo, PasswordEncoder encoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

				userRepo.save(new User("blackbrian", encoder.encode("djuken1"), "Brian Kapesa",
						"Boulevard Roi Baudouin, 125", "Brussels", "Brussels", "1005", "+30477568921"));
			}
		};
	}
}
