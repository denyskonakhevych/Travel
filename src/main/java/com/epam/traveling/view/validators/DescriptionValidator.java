package com.epam.traveling.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("descriptionValidator")
public class DescriptionValidator implements Validator {
	
	@Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        //Check if user has typed only blank spaces
		if(value == null) {
			FacesMessage msg = 
	                new FacesMessage("The input must not be null");
	            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	            throw new ValidatorException(msg);
		}
        if(value.toString().trim().isEmpty()){
            FacesMessage msg = 
                new FacesMessage("The input must not have only blank spaces");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
    }
}
