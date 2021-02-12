package com.example.modul2hotel.hotel.listeners;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import com.example.modul2hotel.hotel.controllers.RezervacijeController;
import com.example.modul2hotel.hotel.model.Rezervacija;
import com.example.modul2hotel.hotel.model.Soba;



@Component
public class InitServletContextInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("Inicijalizacija konteksta pri ServletContextInitializer...");
		
		HashMap<Integer, Soba> sobe = new HashMap<Integer, Soba>();
		sobe.put(1, new Soba(1, "studio", 2, 2300, true));
		sobe.put(2, new Soba(2, "jednokrevetne", 1, 2500, false));
		sobe.put(3, new Soba(3, "dvokrevetne", 2, 3500, false));
		sobe.put(4, new Soba(4, "apartman", 4, 4700, true));
		servletContext.setAttribute(RezervacijeController.SOBE_KEY, sobe);
		
		HashMap<Long, Rezervacija> rezervacije = new HashMap<Long, Rezervacija>();
		rezervacije.put(1L, new Rezervacija(1L, sobe.get(3), "2017-11-01 12:00", "2017-11-13 13:00", "Petar Petrovic", "gotovina", "przenice"));
		rezervacije.put(2L, new Rezervacija(2L, sobe.get(2), "2017-11-01 12:00", "2017-11-13 13:00", "Marko Markovic", "kartica", "przenice"));
		rezervacije.put(3L, new Rezervacija(3L, sobe.get(1), "2017-11-01 12:00", "2017-11-13 13:00", "Jovan Jovanovic", "gotovina", "omlet"));
		rezervacije.put(4L, new Rezervacija(4L, sobe.get(4), "2017-11-01 12:00", "2017-11-13 13:00", "Petar Petrovic", "kartica", "przenice"));
		rezervacije.put(5L, new Rezervacija(5L, sobe.get(2), "2017-11-01 12:00", "2017-11-13 13:00", "Marko Markovic", "gotovina", "omlet"));
		rezervacije.put(6L, new Rezervacija(6L, sobe.get(4), "2017-11-01 12:00", "2017-11-13 13:00", "Marko Markovic", "kartica", "omlet"));
		servletContext.setAttribute(RezervacijeController.REZERVACIJE_KEY, rezervacije);
		
		int przenice = 0;
		servletContext.setAttribute(RezervacijeController.PRZENICE_KEY, przenice);
		
		int omlet = 0;
		servletContext.setAttribute(RezervacijeController.OMLET_KEY, omlet);
		
		System.out.println("Uspeh ServletContextInitializer!");
		
	}

}
