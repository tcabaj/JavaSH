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
	Long addNewFilm(Film film);
	List<Film> getAllFilm();
	void deleteFilm(Film film);
	Film findFilmById(Long id);
	public boolean editFilm(Film film);
	
	List<Film> getOwnedFilm(Kategoria kategoria);
	List<Film> getFreeFilm();
	void sellFilm(Long kategoriaId, Long filmId);
}
