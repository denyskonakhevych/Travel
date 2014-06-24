/**
 * 
 */
package com.epam.traveling.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = -7522609234232367231L;

	private Locale locale = Locale.ENGLISH;

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
