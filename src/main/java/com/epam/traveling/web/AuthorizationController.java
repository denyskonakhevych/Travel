package com.epam.traveling.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIGraphic;
import javax.faces.component.UIInput;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.Booking;
import com.epam.traveling.entities.Person;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.AnaliticRepository;
import com.epam.traveling.repositories.UserRepository;
import com.epam.traveling.services.AnaliticService;
import com.epam.traveling.services.UserService;

@ManagedBean(name = "authorizationController")
@SessionScoped
@Component
public class AuthorizationController {

	@Autowired
	UserService userService;
	
	@Autowired
	AnaliticService analiticService;
	
	private Person person;

	private String emailValue = "";
	private String passwordValue = "";
	
	private boolean isAdmin;
	private boolean isUser;
	
	private boolean isLogedIn;
	
	@PostConstruct
	public void init() {
		person = new User();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getEmailValue() {
		return emailValue;
	}

	public void setEmailValue(String emailValue) {
		this.emailValue = emailValue;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public boolean getIsLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}

	public synchronized void loginUser(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        
        SaltedHasher sh = new SaltedHasher();
        User user = userService.findByEmail(emailValue);
        String salt = user.getSalt();
        
		String securePassword = sh.getSecurePassword(passwordValue, salt);
        
        user = userService.findByEmailAndPassword(emailValue, securePassword);
        
        UIComponent components = event.getComponent();
        UIComponent uiInputPassword = (UIComponent) components.findComponent("loginDialog");
        String loginFormId = uiInputPassword.getClientId();

        if(user != null) {
        	person = user;
        	isLogedIn = true;
        	isUser = true;
        	isAdmin = false;
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "Welcome!");
        	emailValue = "";
        	passwordValue = "";
        	FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", isLogedIn);
        } else {
        	isLogedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(loginFormId, message);
            context.addCallbackParam("loggedIn", isLogedIn);
        }        
    }
	
	public synchronized void loginAnalitic(ActionEvent event) {
		
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        
        SaltedHasher sh = new SaltedHasher();
        Analitic analitic = analiticService.findByEmail(emailValue);
        String salt = analitic.getSalt();
        
		String securePassword = sh.getSecurePassword(passwordValue, salt);
        
		analitic = analiticService.findByEmailAndPassword(emailValue, securePassword);
        
        UIComponent components = event.getComponent();
        UIComponent uiInputPassword = (UIComponent) components.findComponent("loginDialog");
        String loginFormId = uiInputPassword.getClientId();

        if(analitic != null) {
        	person = analitic;
        	isLogedIn = true;
        	isUser = false;
        	isAdmin = true;
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "Welcome!");
        	emailValue = "";
        	passwordValue = "";
        	FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", isLogedIn);
        } else {
        	isLogedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(loginFormId, message);
            context.addCallbackParam("loggedIn", isLogedIn);
        } 
    }
	
	public synchronized void logOut() {
		person = null;
		isLogedIn = false;
		isAdmin = false;
		isUser = false;
	}

}
