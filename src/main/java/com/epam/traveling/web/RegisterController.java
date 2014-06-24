package com.epam.traveling.web;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.User;
import com.epam.traveling.services.AnaliticService;
import com.epam.traveling.services.UserService;

@ManagedBean(name="registeController")
@ViewScoped
@Controller
@Component
public class RegisterController {
	
	private static Logger log = Logger.getLogger(RegisterController.class.getName());
	
	@Autowired
	UserService userService;
	
	@Autowired
	AnaliticService analiticService;
	
	private User user;
	
	private Analitic analitic;
	
	public RegisterController() {
		user = new User();
		analitic = new Analitic();
	}

	public Analitic getAnalitic() {
		return analitic;
	}

	public void setAnalitic(Analitic analitic) {
		this.analitic = analitic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void registerUser(ActionEvent event) {

		try {
			SaltedHasher sh = new SaltedHasher();
			String salt = sh.getSalt();
			String securePassword = sh.getSecurePassword(user.getPassword(), salt);
			
			user.setPassword(securePassword);
			user.setSalt(salt);

			userService.save(user);
			resetUser();
		} catch (Exception ex) {
			UIComponent components = event.getComponent();
	        UIComponent uiInputPassword = (UIComponent) components.findComponent("registrationForm");
	        String registerFormId = uiInputPassword.getClientId();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Register Error", "User with such email has already bean registered");
			FacesContext.getCurrentInstance().addMessage(registerFormId, message);
			
			log.info(ex.getMessage());
		}
	}
	
	public void registerAnalitic(ActionEvent event) {
		
		try {
			SaltedHasher sh = new SaltedHasher();
			String salt = sh.getSalt();
			String securePassword = sh.getSecurePassword(analitic.getPassword(), salt);
			
			analitic.setPassword(securePassword);
			analitic.setSalt(salt);

			analiticService.save(analitic);
			resetAnalitic();
		} catch (Exception ex) {
			UIComponent components = event.getComponent();
	        UIComponent uiInputPassword = (UIComponent) components.findComponent("registrationForm");
	        String registerFormId = uiInputPassword.getClientId();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Register Error", "User with such email has already bean registered");
			FacesContext.getCurrentInstance().addMessage(registerFormId, message);
			
			log.info(ex.getMessage());
		}
	}
	
	private void resetUser() {
		user = new User();
	}
	
	private void resetAnalitic() {
		analitic = new Analitic();
	}
}
