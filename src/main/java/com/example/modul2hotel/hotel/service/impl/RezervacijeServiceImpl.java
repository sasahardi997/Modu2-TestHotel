package com.example.modul2hotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modul2hotel.hotel.dao.RezervacijaDAO;
import com.example.modul2hotel.hotel.model.Rezervacija;
import com.example.modul2hotel.hotel.service.RezervacijeService;

@Service
public class RezervacijeServiceImpl implements RezervacijeService {
	
	@Autowired
	private RezervacijaDAO rezervacijaDAO;

	@Override
	public List<Rezervacija> getAllBy(String placanje, String dorucak, String gost,String tip) {
		if(placanje == null || placanje.equals("0")) {
			placanje = "";
		}
		if(dorucak == null || dorucak.equals("0")) {
			dorucak = "";
		}
		if( gost == null) {
			gost = "";
		}
		if(tip == null) {
			tip = "";
		}
		
		return rezervacijaDAO.getAllBy(placanje, dorucak, gost, tip);
	}

}
