package edu.nsimat.wallonia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Taco;
import edu.nsimat.wallonia.jdbcrepository.IngredientRepository;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo; 
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));		
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
		}		
		model.addAttribute("design", new Taco());
		log.info("We are in the showDesignForm() method.");
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute(value="design") Taco design, Errors errors, Model model){
		
		if(errors.hasErrors()) {
			log.debug("Errors happened during design validation!");
			return "design";
		}
		
		//Save the taco design...
		//We'll do this later
		log.info("Processing design: " + design);
		log.debug("Size of ingredients is " + design.getIngredients().size());
		
		return "redirect:/orders/current";
	}
	
	//tag::filterByType[]
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		
		return ingredients
				.stream()
				.filter(x->x.getType().equals(type))
				.collect(Collectors.toList());
	}
    //end::filterByType[]
}
