package com.example.modul2hotel.hotel.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.modul2hotel.hotel.model.Rezervacija;
import com.example.modul2hotel.hotel.model.Soba;
import com.example.modul2hotel.hotel.service.RezervacijeService;


@Controller
@RequestMapping("/Rezervacije")
public class RezervacijeController {

	public static final String SOBE_KEY = "sobe";
	public static final String REZERVACIJE_KEY = "rezervacije";
	public static final String PRZENICE_KEY = "przenice";
	public static final String OMLET_KEY = "omlet";
	public static final String REZERVACIJE_KORISNIKA_KEY = "rezervacijeKorisnika";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private RezervacijeService rezervacijeService;

	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}
	
	@PostMapping("/Create")
	public void zadatak2(
			@RequestParam Integer sobaId,
			@RequestParam String ulazak,
			@RequestParam String izlazak,
			@RequestParam String gost,
			@RequestParam String placanje,
			@RequestParam String dorucak,
			HttpServletResponse response, HttpSession session) throws IOException {
		HashMap<Integer, Soba> sobe = (HashMap<Integer, Soba>) servletContext.getAttribute(RezervacijeController.SOBE_KEY);
		HashMap<Long, Rezervacija> rezervacije = (HashMap<Long, Rezervacija>) servletContext.getAttribute(RezervacijeController.REZERVACIJE_KEY);
		List<Rezervacija> rezervacijeKorisnika = (List<Rezervacija>) session.getAttribute(RezervacijeController.REZERVACIJE_KORISNIKA_KEY);
		
		Soba soba = sobe.get(sobaId);
		Rezervacija rezervacija = new Rezervacija(Long.valueOf(rezervacije.size() + 1), soba, izlazak, izlazak, gost, placanje, dorucak);
		rezervacije.put(rezervacija.getId(), rezervacija);
		rezervacijeKorisnika.add(rezervacija);
		
		int przenice = (int) servletContext.getAttribute(RezervacijeController.PRZENICE_KEY);
		int omlet = (int) servletContext.getAttribute(RezervacijeController.OMLET_KEY);
		for(Rezervacija r : rezervacije.values()) {
			System.out.println(r);
			if(r.getDorucak().equals("przenice")) {
				przenice++;
			} else if(r.getDorucak().equals("omlet")) {
				omlet++;
			}
		}
		System.out.println("Broj narucenih przenica: " + przenice);
		System.out.println("Broj narucenih omleta: " + omlet);
		
		response.sendRedirect(bURL + "Rezervacije/Zadatak3");
	}
	
	@GetMapping("/Zadatak3")
	public String zadatak3(ModelMap modelMap, HttpSession session) {
		HashMap<Long, Rezervacija> rezervacije = (HashMap<Long, Rezervacija>) servletContext.getAttribute(RezervacijeController.REZERVACIJE_KEY);
		modelMap.addAttribute("rezervacije", rezervacije.values());
		
		List<Rezervacija> rezervacijeKorisnika = (List<Rezervacija>) session.getAttribute(RezervacijeController.REZERVACIJE_KORISNIKA_KEY);
		System.out.println("***** Rezervacije Korisnika ***********");
		for(Rezervacija r : rezervacijeKorisnika) {
			System.out.println(r);
		}
		
		double zarada = 0;
		for(Rezervacija r : rezervacije.values()) {
			zarada += r.getSoba().getCenaNocenja();
			if(r.getSoba().getTip().equals("apartman")) {
				continue;
			} else {
				if(r.getDorucak().equals("omlet")) {
					zarada += 150;
				} else if(r.getDorucak().equals("przenice")) {
					zarada += 200;
				}
			}
		}
		modelMap.addAttribute("zarada", zarada);
		
		return "zadatak3";
	}
	
	@GetMapping("/Zadatak5")
	public String zadatak5(
			@RequestParam(defaultValue = "") String placanje,
			@RequestParam(defaultValue = "") String dorucak,
			@RequestParam(defaultValue = "") String gost,
			@RequestParam(defaultValue = "") String tip,
			ModelMap modelMap) {
		
		List<Rezervacija> rezervacije = rezervacijeService.getAllBy(placanje, dorucak, gost, tip);
		modelMap.addAttribute("rezervacije", rezervacije);
		
		List<String> placanjeLista = new ArrayList<String>();
		placanjeLista.add("kartica");
		placanjeLista.add("gotovina");
		modelMap.addAttribute("placanjeLista", placanjeLista);
		
		List<String> dorucakLista = new ArrayList<String>();
		dorucakLista.add("omlet");
		dorucakLista.add("przenice");
		modelMap.addAttribute("dorucakLista", dorucakLista);
		
		return "zadatak5";
	}
}
