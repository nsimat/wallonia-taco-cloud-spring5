package edu.nsimat.wallonia.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Taco;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = Arrays.asList(

				new Ingredient("FLTO", "Four Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
		}
		
		model.addAttribute("design", new Taco());
		log.info("We are in the showDesignForm method.");
		
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
