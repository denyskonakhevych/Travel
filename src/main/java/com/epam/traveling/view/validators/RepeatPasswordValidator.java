package com.epam.traveling.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.epam.traveling.view.validators.RepeatPasswordValidator")
public class RepeatPasswordValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
				.get("passwordInput");
		String password = uiInputConfirmPassword.getSubmittedValue().toString();
		String repeatedPassword = value.toString();

		if (password == null || password.isEmpty() || repeatedPassword == null
				|| repeatedPassword.isEmpty()) {
			return;
		}

		if (!password.equals(repeatedPassword)) {
			FacesMessage msg = new FacesMessage("Password validation failed.",
				"Password doesn't match.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR); throw new
			ValidatorException(msg);
		}

	}
}
