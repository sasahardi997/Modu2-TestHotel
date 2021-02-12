package com.example.modul2hotel.hotel.model;

public class Rezervacija {
	
	private Long id;
	private Soba soba;
	private String ulazak;
	private String izlazak;
	private String gost;
	private String placanje;
	private String dorucak;
	
	public Rezervacija(Soba soba, String ulazak, String izlazak, String gost, String placanje, String dorucak) {
		this.soba = soba;
		this.ulazak = ulazak;
		this.izlazak = izlazak;
		this.gost = gost;
		this.placanje = placanje;
		this.dorucak = dorucak;
	}

	public Rezervacija(Long id, Soba soba, String ulazak, String izlazak, String gost, String placanje,
			String dorucak) {
		this.id = id;
		this.soba = soba;
		this.ulazak = ulazak;
		this.izlazak = izlazak;
		this.gost = gost;
		this.placanje = placanje;
		this.dorucak = dorucak;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Rezervacija other = (Rezervacija) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public String getUlazak() {
		return ulazak;
	}

	public void setUlazak(String ulazak) {
		this.ulazak = ulazak;
	}

	public String getIzlazak() {
		return izlazak;
	}

	public void setIzlazak(String izlazak) {
		this.izlazak = izlazak;
	}

	public String getGost() {
		return gost;
	}

	public void setGost(String gost) {
		this.gost = gost;
	}

	public String getPlacanje() {
		return placanje;
	}

	public void setPlacanje(String placanje) {
		this.placanje = placanje;
	}

	public String getDorucak() {
		return dorucak;
	}

	public void setDorucak(String dorucak) {
		this.dorucak = dorucak;
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", soba=" + soba + ", ulazak=" + ulazak + ", izlazak=" + izlazak + ", gost="
				+ gost + ", placanje=" + placanje + ", dorucak=" + dorucak + "]";
	}
	
}
