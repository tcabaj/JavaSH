package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.Film;
import com.example.shdemo.domain.Kategoria;

public interface KinoM {
	//Kategoria
	void addKategoria(Kategoria kategoria);
	List<Kategoria> getAllKategoria();
	void deleteKategoria(Kategoria kategoria);
	Kategoria findKategoriabyId(Long id);
	public Kategoria findKategoriabyNazwa(String nazwa);
	public boolean editKategoria(Kategoria kategoria);
	//Film
	
}
