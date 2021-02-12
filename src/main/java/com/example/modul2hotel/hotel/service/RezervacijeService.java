package com.example.modul2hotel.hotel.service;

import java.util.List;

import com.example.modul2hotel.hotel.model.Rezervacija;

public interface RezervacijeService {

	List<Rezervacija> getAllBy(String placanje, String dorucak, String gost, String tip);
}
