package edu.nsimat.wallonia.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.jdbcrepository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IngredientConverter implements Converter<String, Ingredient> {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public Ingredient convert(String ingredientId) {

		log.info("Trying to convert id=" + ingredientId + " into a Ingredient");
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		for(Ingredient ingredient : ingredients) {
			if(ingredient.getId().equals(ingredientId))
				return ingredient;
		}
		
		return null;
	}

}
