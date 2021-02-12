package com.example.modul2hotel.hotel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.modul2hotel.hotel.model.Soba;

@Repository
public class SobaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class SobaRowMapper implements RowMapper<Soba>{

		@Override
		public Soba mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			int id = rs.getInt(index++);
			String tip = rs.getString(index++);
			int brojKreveta = rs.getInt(index++);
			double cenaNocenja =  rs.getDouble(index++);
			boolean terasa = rs.getBoolean(index++);
			
			return new Soba(id, tip, brojKreveta, cenaNocenja, terasa);
		}
		
	}

	public List<Soba> getAll() {
		String sql = "SELECT * FROM sobe";
		return jdbcTemplate.query(sql, new SobaRowMapper());
	}

	public void update(Soba soba) {
		String sql = "UPDATE sobe SET brojKreveta = ?, cenaNocenja = ?, terasa = ? WHERE id = ?";
		jdbcTemplate.update(sql, soba.getBrojKreveta(), soba.getCenaNocenja(), soba.isTerasa(), soba.getId());
	}

	public Soba find(String nazivSobe) {
		String sql = "SELECT * FROM sobe WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new SobaRowMapper(), nazivSobe);
	}

}
