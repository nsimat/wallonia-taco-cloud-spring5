package edu.nsimat.wallonia.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {

	@NotBlank(message="Name is required.")
	private String name;
	
	@NotBlank(message="Street is required.")
	private String street;
	
	@NotBlank(message="City is required.")
	private String city;
	
	@NotBlank(message="Region is required.")
	private String region;//Region
	
	@NotBlank(message="Postal code is required.")
	private String postalCode;//zipcode
	
	@CreditCardNumber(message="Not a valid credit card number.")
	private String creditCardNbr;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String creditCardExp;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV.")
	private String creditCardCVV;//'Cardholder Verification Value' 
	                             //= numéro de vérification de la carte de credit
}
