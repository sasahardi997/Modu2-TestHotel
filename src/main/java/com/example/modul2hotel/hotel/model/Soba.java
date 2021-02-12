package com.example.modul2hotel.hotel.model;

public class Soba {

	private int id;
	private String tip;
	private int brojKreveta;
	private double cenaNocenja;
	private boolean terasa;
	
	public Soba(String tip, int brojKreveta, double cenaNocenja, boolean terasa) {
		this.tip = tip;
		this.brojKreveta = brojKreveta;
		this.cenaNocenja = cenaNocenja;
		this.terasa = terasa;
	}

	public Soba(int id, String tip, int brojKreveta, double cenaNocenja, boolean terasa) {
		this.id = id;
		this.tip = tip;
		this.brojKreveta = brojKreveta;
		this.cenaNocenja = cenaNocenja;
		this.terasa = terasa;
	}

	public Soba(int id, int brojKreveta, double cenanNocenja, boolean terasa) {
		this.id = id;
		this.brojKreveta = brojKreveta;
		this.cenaNocenja = cenanNocenja;
		this.terasa = terasa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soba other = (Soba) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public double getCenaNocenja() {
		return cenaNocenja;
	}

	public void setCenaNocenja(double cenaNocenja) {
		this.cenaNocenja = cenaNocenja;
	}

	public boolean isTerasa() {
		return terasa;
	}

	public void setTerasa(boolean terasa) {
		this.terasa = terasa;
	}

	@Override
	public String toString() {
		return "Soba [id=" + id + ", tip=" + tip + ", brojKreveta=" + brojKreveta + ", cenaNocenja=" + cenaNocenja
				+ ", terasa=" + terasa + "]";
	}
	
}
