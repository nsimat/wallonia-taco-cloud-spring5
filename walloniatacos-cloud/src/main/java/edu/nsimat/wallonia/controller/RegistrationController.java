package edu.nsimat.wallonia.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nsimat.wallonia.domain.RegistrationForm;
import edu.nsimat.wallonia.jparepository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String registerForm() {
		log.info("-----Displaying the registration form-----");
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		
		log.info("-----Processing: " + form + "-----");
		userRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}
	
}
