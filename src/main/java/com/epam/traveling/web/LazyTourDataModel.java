package com.epam.traveling.web;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.traveling.entities.Tour;
import com.epam.traveling.services.TourService;

public class LazyTourDataModel extends LazyDataModel<Tour> {
	
	private static final long serialVersionUID = 2234967287877761796L;
	
	@Autowired
	TourService tourService;
	
	@Override
	public List<Tour> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		return tourService.findInBounds(first, pageSize);
	}

	@Override
	public List<Tour> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		return tourService.findInBounds(first, pageSize);
	}
}
