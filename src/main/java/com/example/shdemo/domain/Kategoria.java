package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "kategoria.all", query = "Select k from Kategoria k"),
	@NamedQuery(name = "kategoria.byId", query = "Select k from Kategoria k where k.id = :id"),
	@NamedQuery(name = "kategoria.byNazwa", query = "Select k from Kategoria k where k.nazwa = :name")
})
public class Kategoria {
	private Long id;
	private String nazwa = "";
	private List<Film> filmy = new ArrayList<Film>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Film> getMedicines() {
		return filmy;
	}
	public void setFilmy(List<Film> filmy) {
		this.filmy = filmy;
	}
}
