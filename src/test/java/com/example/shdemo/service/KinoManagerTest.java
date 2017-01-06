package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Film;
import com.example.shdemo.domain.Kategoria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional	
public class KinoManagerTest {
	
	@Autowired
	KinoM kinoM;
	private final String NAZWA = "Komedia";
	private final String NAZWA_1 = "Horror";
	private final String NAZWA_2 = "Piła 3";
	private final int DLUGOSC = 124;
	private final int IL_MIEJSC = 43;

	@Before
	public void setup(){
		Film film = new Film();
		film.setTytul(NAZWA_2);
		film.setDlugosc(DLUGOSC);
		film.setilMiejsc(IL_MIEJSC);
		film.setInKategoria(false);
		
		Kategoria kategoria = new Kategoria();
		kategoria.setNazwa(NAZWA);
		kategoria.getFilmy().add(film);
		film.setInKategoria(true);
		
		kinoM.addKategoria(kategoria);
	}
	// KATEGORIA 
	@Test
	public void addKategoria(){
		Kategoria kategoria = new Kategoria();
		kategoria.setNazwa(NAZWA_1);
		
		kinoM.addKategoria(kategoria);
		Kategoria recievedKategoria = kinoM.findKategoriabyNazwa(NAZWA_1);
		assertEquals(NAZWA_1, recievedKategoria.getNazwa());
	}
	@Test
	public void editKategoria(){
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		kategoria.setNazwa("Edit");
		Long KategoriaId = kategoria.getId();
		kinoM.editKategoria(kategoria);
		
		Kategoria kategoria2 = kinoM.findKategoriabyId(KategoriaId);
		
		assertEquals("Edit", kategoria2.getNazwa());
	}
	@Test
	public void deleteKategoria(){
		int KategoriaCount = kinoM.getAllKategoria().size();
		int FilmCount = kinoM.getAllFilm().size();
		
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		Long KategoriaId = kategoria.getId();
		kinoM.deleteKategoria(kategoria);
		
		assertEquals(null, kinoM.findKategoriabyId(KategoriaId));
		assertEquals(KategoriaCount - 1, kinoM.getAllKategoria().size());
		assertEquals(FilmCount - 1, kinoM.getAllFilm().size());
	}

}
