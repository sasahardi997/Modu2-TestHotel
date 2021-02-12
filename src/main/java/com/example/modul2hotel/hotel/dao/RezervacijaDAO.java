package com.example.modul2hotel.hotel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.modul2hotel.hotel.model.Rezervacija;
import com.example.modul2hotel.hotel.model.Soba;

@Repository
public class RezervacijaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class RezervacijeRowMapper implements RowMapper<Rezervacija>{

		@Override
		public Rezervacija mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			Integer sobaId = rs.getInt(index++);
			String ulazak = rs.getString(index++);
			String izlazak = rs.getString(index++);
			String gost = rs.getString(index++);
			String placanje = rs.getString(index++);
			String dorucak = rs.getString(index++);
			
			Integer idSobe = rs.getInt(index++);
			String tip = rs.getString(index++);
			Integer brojKreveta = rs.getInt(index++);
			double cenaNocenja = rs.getDouble(index++);
			boolean terasa = rs.getBoolean(index++);
			
			Soba soba = new Soba(idSobe, tip, brojKreveta, cenaNocenja, terasa);	
			Rezervacija rezervacija = new Rezervacija(id, soba, ulazak, izlazak, gost, placanje, dorucak);
			return rezervacija;
		}
	}

	public List<Rezervacija> getAllBy(String placanje, String dorucak, String gost, String tip) {
		placanje = "%" + placanje + "%";
		dorucak = "%" + dorucak + "%";
		gost = "%" + gost + "%";
		tip = "%" + tip + "%";
		String sql = "SELECT r.id, r.soba, r.ulazak, r.izlazak, r.gost, r.placanje, r.dorucak, s.id, s.tip, s.brojKreveta, s.cenaNocenja, s.terasa "
				+ "FROM rezervacije r LEFT JOIN sobe s ON r.soba = s.id WHERE placanje LIKE ? AND dorucak LIKE ? AND gost LIKE ? AND tip LIKE ?";
		return jdbcTemplate.query(sql, new RezervacijeRowMapper(), placanje, dorucak, gost, tip);
	}

}
