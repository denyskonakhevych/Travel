package com.epam.traveling.view.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.epam.traveling.view.validators.PasswordValidator")
public class PasswordValidator implements Validator {

	private static final String PASSWORD_PATTERN = "^[A-Za-z0-9]{4,32}$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public PasswordValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Password validation failed.", 
						"Invalid password format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}
