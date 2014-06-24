package com.epam.traveling.view.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.epam.traveling.view.validators.DateValidator")
public class DateValidator implements Validator {
 
	private static final float MAX_PRICE = 10_000_000;
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		Date date = (Date) value;
		Date current = new Date();
		
		if(date.getTime() < current.getTime()){
 
			FacesMessage msg = 
				new FacesMessage("Date validation failed.", 
						"Invalid date value.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}
