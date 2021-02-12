package com.example.modul2hotel.hotel.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import com.example.modul2hotel.hotel.controllers.RezervacijeController;
import com.example.modul2hotel.hotel.model.Rezervacija;


@Component
public class InitHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Inicijalizacija sesisje HttpSessionListener...");
		
		HttpSession session  = se.getSession();
		System.out.println("Session id korisnika je "+ session.getId());
		
		List<Rezervacija> rezervacijeKorisnika = new ArrayList<Rezervacija>();
		session.setAttribute(RezervacijeController.REZERVACIJE_KORISNIKA_KEY, rezervacijeKorisnika);
		
		System.out.println("Uspeh HttpSessionListener!");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Brisanje sesisje HttpSessionListener...");
		System.out.println("Uspeh HttpSessionListener!");
	}

}
