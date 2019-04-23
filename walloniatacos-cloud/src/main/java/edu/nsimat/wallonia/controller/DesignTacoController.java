package edu.nsimat.wallonia.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.nsimat.wallonia.domain.Ingredient;
import edu.nsimat.wallonia.domain.Taco;
import edu.nsimat.wallonia.domain.User;
import edu.nsimat.wallonia.jparepository.IngredientRepository;
import edu.nsimat.wallonia.jparepository.TacoRepository;
import edu.nsimat.wallonia.jparepository.UserRepository;
//import edu.nsimat.wallonia.jdbcrepository.IngredientRepository;
//import edu.nsimat.wallonia.jdbcrepository.TacoRepository;
import edu.nsimat.wallonia.domain.Ingredient.Type;
import edu.nsimat.wallonia.domain.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;

	private TacoRepository designRepo;

	private UserRepository userRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo,
			UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
		this.userRepo = userRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "tacodesign")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model, Principal principal) {

		log.info("-----Showing checkboxes for taco design-----");
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			log.info("Type : " + type.toString() + " - " + filterByType(ingredients, type) + ".");
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		// model.addAttribute("design", new Taco());
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);

		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		
		log.info("-----Processing and saving: " + design + "-----");

		if (errors.hasErrors()) {
			log.debug("-----Errors happened during design validation!-----");
			return "design";
		}

		// Save the taco design...
		Taco saved = designRepo.save(design);
		order().addDesign(saved);

		// logging
		log.debug("-----Size of ingredients is: " + design.getIngredients().size() + "-----");

		return "redirect:/orders/current";
	}

	// tag::filterByType[]
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	// end::filterByType[]
}
