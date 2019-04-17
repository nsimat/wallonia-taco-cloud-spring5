package edu.nsimat.wallonia.jparepository;

import org.springframework.data.repository.CrudRepository;

import edu.nsimat.wallonia.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
