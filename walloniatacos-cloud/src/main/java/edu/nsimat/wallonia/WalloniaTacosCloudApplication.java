package edu.nsimat.wallonia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import edu.nsimat.wallonia.jparepository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WalloniaTacosCloudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(WalloniaTacosCloudApplication.class, args);
		for(String name: context.getBeanDefinitionNames()) {
			log.info("Bean: " + name);
		}
	}
	
	@Bean
	  public CommandLineRunner dataLoader(IngredientRepository repo) {
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
	      }
	    };
	  }


}
