package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.Film;
import com.example.shdemo.domain.Kategoria;

public interface KinoM {

	void addKategoria(Kategoria kategoria);
	List<Kategoria> getAllKategoria();
	void deleteKategoria(Kategoria kategoria);
	Kategoria findKategoriabyId(Long id);
	public Kategoria findKategoriabyName(String name);
	public boolean editKategoria(Kategoria kategoria);
	
	
}
