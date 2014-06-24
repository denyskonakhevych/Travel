package com.epam.traveling.view.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.epam.traveling.view.validators.PhoneNumberValidator")
public class PhoneNumberValidator implements Validator {

	private static final String PHONE_NUMBER_PATTERN = "^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public PhoneNumberValidator(){
		  pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Phone number validation failed.", 
						"Invalid phone number format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}
