package edu.nsimat.wallonia.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String region;
	private String postalcode;
	private String phone;

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, 
				          passwordEncoder.encode(password), 
				          fullname, 
				          street, 
				          city, 
				          region, 
				          postalcode, 
				          phone);
	}
}
