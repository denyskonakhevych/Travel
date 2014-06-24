package com.epam.traveling.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.traveling.entities.Hotel;

@Component
@Scope("request")
//@FacesConverter(forClass = com.epam.traveling.entities.Hotel.class, value="com.epam.traveling.view.converters.HotelConverter")
@FacesConverter("com.epam.traveling.view.converters.HotelConverter")
public class HotelConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Hotel hotel = new Hotel();
		hotel.setName(value);
		return hotel;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		Hotel hotel = (Hotel) value;
		return hotel.getName();
	}

}
