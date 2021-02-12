package com.example.modul2hotel.hotel.service;

import java.util.List;

import com.example.modul2hotel.hotel.model.Soba;

public interface SobeService {

	List<Soba> sobe();
	Soba  update(Soba soba);
	Soba find(String nazivSobe);
}
