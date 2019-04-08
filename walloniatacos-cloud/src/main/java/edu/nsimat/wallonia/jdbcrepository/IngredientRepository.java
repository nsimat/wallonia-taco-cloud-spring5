package edu.nsimat.wallonia.jdbcrepository;

import edu.nsimat.wallonia.domain.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	Ingredient findOne(String id);
	Ingredient save(Ingredient ingredient);

}
