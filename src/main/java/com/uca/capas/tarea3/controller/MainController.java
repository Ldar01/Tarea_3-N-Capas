package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/parametros")
	public ModelAndView parametros1(HttpServletRequest request) throws ParseException {
		List<String> lista_errores = new ArrayList<>();
				
		ModelAndView mav = new ModelAndView();
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String birth = request.getParameter("birth");
		String place = request.getParameter("place");
		String school = request.getParameter("school");
		String phone = request.getParameter("phone");
		String cellphone = request.getParameter("cellphone");
		
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		Date date_not = new SimpleDateFormat("yyyy-MM-dd").parse("2003-01-01");
				
		if(date.compareTo(date_not) < 0) {
			lista_errores.add("- La fecha debe ser mayor que 1/1/2003");
		} 
		if(name.length() == 0 || name.length() > 25) {
			lista_errores.add("- NO ha escrito el nombre o supera el limite de 25 caracteres");
		}
		if(lastname.length() == 0 || lastname.length() > 25) {
			lista_errores.add("- NO ha escrito el apellido o supera el limite de 25 caracteres");
		}
		if(place.length() == 0 || birth.length() > 25) {
			lista_errores.add("- NO ha escrito el lugar de nacimiento supera el limite de 25 caracteres");
		}
		if(school.length() == 0 || school.length() > 100) {
			lista_errores.add("- NO ha escrito el instituto/colegio o supera el limite de 100 caracteres");
		}
		if(phone.length() != 8 || cellphone.length() != 8) {
			lista_errores.add("- El numero de telefono/movil debe ser de 8 caracteres");
		}
		
		if(lista_errores.size() > 0) {
			mav.addObject("errores", lista_errores);
			mav.setViewName("/errors");
			return mav;
		}
		
		mav.setViewName("/results");
		return mav;
	
		//mav.addObject("user", usuario);
		//mav.addObject("pass", password);
		//mav.setViewName("/results");
		
		
	}
}
