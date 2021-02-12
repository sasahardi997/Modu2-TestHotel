package com.example.modul2hotel.hotel.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.modul2hotel.hotel.model.Soba;
import com.example.modul2hotel.hotel.service.SobeService;

@Controller
@RequestMapping("/Sobe")
public class SobeController {

	@Autowired
	private SobeService sobeService;
	
	@GetMapping
	@ResponseBody
	public HashMap<String, Object> index(){
		
		List<Soba> sobe = sobeService.sobe();
		HashMap<String, Object> odgovor = new HashMap<String, Object>();
		odgovor.put("status", "ok");
		odgovor.put("sobe", sobe);
		return odgovor;
	}
	
	@GetMapping(value="/Details")
	@ResponseBody
	public Map<String, Object> details (
			@RequestParam String tipSobe,
			HttpSession session, HttpServletResponse response
			){
		Map<String, Object> odgovor = new LinkedHashMap<>();
		
		if (tipSobe.equals("")) {
			odgovor.put("status", "greska");
			return odgovor;
		}
		
		Soba soba = sobeService.find(tipSobe);
		
		if (soba != null) {
			odgovor.put("status", "ok");
			odgovor.put("soba", soba);
			return odgovor;
		}
		
		odgovor.put("status", "greska");
		
		return odgovor;
	}
	
	@PostMapping("/Edit")
	@ResponseBody
	public Map<String, Object> update(
			@RequestParam int id,
			@RequestParam int brojKreveta,
			@RequestParam double cenaNocenja,
			@RequestParam String terasa){
		
		try {
			boolean terasaBool = false;
			if(terasa.equals("true")) {
				terasaBool = true;
			}
			
			Soba soba = new Soba(id, brojKreveta, cenaNocenja, terasaBool);
			sobeService.update(soba);
			
			Map<String, Object> odgovor = new LinkedHashMap<String, Object>();
			odgovor.put("status", "ok");
			return odgovor;	
		} catch (Exception e) {
			String poruka = e.getMessage();
			if (poruka == "") {
				poruka = "Neuspešno dodavanje!";
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}
}
