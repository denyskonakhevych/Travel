package com.epam.traveling.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.epam.traveling.view.validators.PriceValidator")
public class PriceValidator implements Validator {
 
	private static final float MAX_PRICE = 10_000_000;
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		Float price = new Float(value.toString());
		if(price < 0.01f || price > MAX_PRICE){
 
			FacesMessage msg = 
				new FacesMessage("Price validation failed.", 
						"Invalid price value.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}
