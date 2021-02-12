package com.example.modul2hotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modul2hotel.hotel.dao.SobaDAO;
import com.example.modul2hotel.hotel.model.Soba;
import com.example.modul2hotel.hotel.service.SobeService;

@Service
public class SobeSeviceImpl implements SobeService {
	
	@Autowired
	private SobaDAO sobaDAO;

	@Override
	public List<Soba> sobe() {
		return sobaDAO.getAll();
	}

	@Override
	public Soba update(Soba soba) {
		sobaDAO.update(soba);
		return soba;
	}

	@Override
	public Soba find(String nazivSobe) {
		return sobaDAO.find(nazivSobe);
	}

}
