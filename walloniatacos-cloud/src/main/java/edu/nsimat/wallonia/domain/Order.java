package edu.nsimat.wallonia.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	private Long id;
	
	private Date placedAt;

	@NotBlank(message="Name is required.")
	private String deliveryName;
	
	@NotBlank(message="Street is required.")
	private String deliveryStreet;
	
	@NotBlank(message="City is required.")
	private String deliveryCity;
	
	@NotBlank(message="Region is required.")
	private String deliveryRegion;//Region
	
	@NotBlank(message="Postal code is required.")
	private String deliveryPostalCode;//zipcode
	
	@CreditCardNumber(message="Not a valid credit card number.")
	private String creditCardNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String creditCardExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV.")
	private String creditCardCVV;//'Cardholder Verification Value' 
	                             //= numéro de vérification de la carte de credit
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}
