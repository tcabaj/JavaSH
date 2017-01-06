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
	private final String NAZWA_3 = "Piła 2";
	private final String NAZWA_4 = "Dramat";
	private final int DLUGOSC = 124;
	private final int IL_MIEJSC = 43;
	private final int DLUGOSC_1 = 122;
	private final int IL_MIEJSC_1 = 143;

	@Before
	public void setup(){
		if(kinoM.findKategoriabyNazwa(NAZWA) == null){
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
		Film film1 = new Film();
		film1.setTytul("Kac Vegas");
		film1.setDlugosc(111);
		film1.setilMiejsc(111);
		film1.setInKategoria(false);
		
		Kategoria kategoria1 = new Kategoria();
		kategoria1.setNazwa("Kom");
		kategoria1.getFilmy().add(film1);
		film1.setInKategoria(true);
		
		kinoM.addKategoria(kategoria1);
	}
	@Before
	public void setup1(){
		if(kinoM.findKategoriabyNazwa(NAZWA_4) == null){
		Film film = new Film();
		film.setTytul(NAZWA_3);
		film.setDlugosc(DLUGOSC);
		film.setilMiejsc(IL_MIEJSC);
		film.setInKategoria(false);
		
		Kategoria kategoria = new Kategoria();
		kategoria.setNazwa(NAZWA_4);
		kategoria.getFilmy().add(film);
		film.setInKategoria(true);
		
		kinoM.addKategoria(kategoria);
		}
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
		
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		Long KategoriaId = kategoria.getId();
		kinoM.deleteKategoria(kategoria);
		
		assertEquals(null, kinoM.findKategoriabyId(KategoriaId));
		assertEquals(KategoriaCount - 1, kinoM.getAllKategoria().size());
	}
	@Test
	public void getPharmacybyId(){
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		
		Kategoria byId = kinoM.findKategoriabyId(kategoria.getId());
		
		assertEquals(kategoria.getId(), byId.getId());
	}
	@Test
	public void sellFilm(){
		Kategoria recievedKategoria = kinoM.findKategoriabyNazwa("Kom");
		int prev = recievedKategoria.getFilmy().size();
		Film film = new Film();
		film.setTytul("Kac Vegas");
		film.setDlugosc(111);
		film.setilMiejsc(111);
		film.setInKategoria(false);
		
		Long filmId = kinoM.addNewFilm(film);
	
		kinoM.sellFilm(recievedKategoria.getId(), filmId);
		
		List<Film> ownedFilm = kinoM.getOwnedFilm(recievedKategoria);
		
		assertEquals(prev+1, ownedFilm.size());
		assertEquals("Kac Vegas", ownedFilm.get(0).getTytul());
		assertEquals(111, ownedFilm.get(0).getDlugosc());
	}
	@Test
	public void getOwnedFilm(){
		Kategoria kategoria = kinoM.findKategoriabyNazwa("Kom");
		List<Film> ownedFilm = kinoM.getOwnedFilm(kategoria);
		
		assertEquals("Kac Vegas", ownedFilm.get(0).getTytul());
		assertEquals(111, ownedFilm.get(0).getDlugosc());
		assertEquals(111, ownedFilm.get(0).getIlMiejsc());	
	}
	
	
	//FILM 
	
	@Test
	public void addFilm(){
		Film film = new Film();
		film.setTytul(NAZWA_3);
		film.setDlugosc(DLUGOSC_1);
		film.setilMiejsc(IL_MIEJSC_1);
		film.setInKategoria(false);
		
		Long filmId = kinoM.addNewFilm(film);
		Film retrievedFilm = kinoM.findFilmById(filmId);
		
		assertEquals(NAZWA_3, retrievedFilm.getTytul());
		assertEquals(DLUGOSC_1, retrievedFilm.getDlugosc());
		assertEquals(IL_MIEJSC_1, retrievedFilm.getIlMiejsc());
	}
	@Test
	public void getFilmId(){
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA_4);
		Film film = kategoria.getFilmy().get(0);
		Film byId = kinoM.findFilmById(kategoria.getFilmy().get(0).getId());
		
		assertEquals(film.getId(), byId.getId());
	}
	@Test
	public void editFilm(){
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA_4);
		Film film = kategoria.getFilmy().get(0);
		film.setTytul("Zmiana");
		film.setDlugosc(999);
		film.setilMiejsc(999);
		Long KategoriaId = kategoria.getId();
		kinoM.editFilm(film);
		
		Kategoria kategoria2 = kinoM.findKategoriabyId(KategoriaId);
		
		assertEquals("Zmiana", kategoria2.getFilmy().get(0).getTytul());
		assertEquals(999, kategoria2.getFilmy().get(0).getDlugosc());
		assertEquals(999, kategoria2.getFilmy().get(0).getIlMiejsc());
	}
	@Test
	public void deleteFilm(){
		int KategoriaCount = kinoM.getAllKategoria().size();
		int FilmCount = kinoM.getAllFilm().size();
		
		Kategoria kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		int KategoriawFilmie = kategoria.getFilmy().size();
		Film film = kategoria.getFilmy().get(0);
		kinoM.deleteFilm(film);
		
		kategoria = kinoM.findKategoriabyNazwa(NAZWA);
		
		assertEquals(KategoriaCount, kinoM.getAllKategoria().size());
		assertEquals(FilmCount - 1, kinoM.getAllFilm().size());
		assertEquals(KategoriawFilmie - 1, kategoria.getFilmy().size());
	}
	
}
