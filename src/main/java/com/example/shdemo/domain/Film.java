package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "film.all", query = "Select f from Film f"),
	@NamedQuery(name = "film.byId", query = "Select f from Film f where f.id = :id"),
	@NamedQuery(name = "film.notSold", query = "Select f from Film f where f.inFilm = false")
})
public class Film {
	private Long id;
	private String tytul;
	private int dlugosc;
	private int il_miejsc;
	private boolean inFilm = false;
	
	public boolean isInFilm() {
		return inFilm;
	}
	public void setInFilm(boolean inFilm) {
		this.inFilm = inFilm;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public int getDlugosc() {
		return dlugosc;
	}
	public void setDlugosc(int dlugosc) {
		this.dlugosc = dlugosc;
	}
	public int getIlMiejsc() {
		return il_miejsc;
	}
	public void setIlMIesjc(int il_miejsc) {
		this.il_miejsc = il_miejsc;
	}
}
