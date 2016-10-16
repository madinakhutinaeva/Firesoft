package io.firesoft.service;

import io.firesoft.model.Themes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("themeService")
public class ThemeServiceImpl implements ThemeService {


	public List<Themes> findAllThemes() {
		
	List<Themes> themes = new ArrayList<Themes>();
		
		Themes programming = new Themes();
		programming.setDesc("Programming");
		themes.add(programming);
		
		Themes machlen = new Themes();
		machlen.setDesc("Machine Learning");
		themes.add(machlen);
		
		Themes fashion = new Themes();
		fashion.setDesc("Fashion");
		themes.add(fashion);
		
		return themes;
		
	}
}
