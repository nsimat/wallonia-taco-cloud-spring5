package edu.nsimat.wallonia.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.nsimat.wallonia.domain.Order;
import edu.nsimat.wallonia.domain.User;
import edu.nsimat.wallonia.jparepository.OrderRepository;
import edu.nsimat.wallonia.jparepository.UserRepository;
//import edu.nsimat.wallonia.jdbcrepository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;

	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
		
		log.info("-----Displaying form for: " + user + "-----");
		
		if(order.getDeliveryName() == null)
			order.setDeliveryName(user.getFullname());
		
		if(order.getDeliveryStreet() == null)
			order.setDeliveryStreet(user.getStreet());
		
		if(order.getDeliveryCity() == null)
			order.setDeliveryCity(user.getCity());
		
		if(order.getDeliveryRegion() == null)
			order.setDeliveryRegion(user.getRegion());
		
		if(order.getDeliveryPostalCode() == null)
			order.setDeliveryPostalCode(user.getPostalCode());

		//model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {

		log.info("-----Processing and saving: " + order + "-----");

		if (errors.hasErrors()) {
			return "orderForm";
		}

		// Associating the user with the order
		order.setUser(user);

		// Saving the order in the database
		orderRepo.save(order);
		sessionStatus.setComplete();

		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}
