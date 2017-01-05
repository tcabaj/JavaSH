package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Kategoria;
import com.example.shdemo.domain.Film;


@Component
@Transactional
public class KinoManagerHiber implements KinoM {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory _sessionFactory){
		this.sessionFactory = _sessionFactory;
	}

	@Override
	public void addKategoria(Kategoria kategoria) {
		kategoria.setId(null);
		sessionFactory.getCurrentSession().persist(kategoria);	
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Kategoria> getAllKategoria() {
		return sessionFactory.getCurrentSession().getNamedQuery("kategoria.all").list();
	}

	@Override
	public void deleteKategoria(Kategoria kategoria) {
		kategoria = (Kategoria) sessionFactory.getCurrentSession().get(Kategoria.class, kategoria.getId());
		for(Film film : kategoria.getFilmy()){
			sessionFactory.getCurrentSession().delete(film);
		}
		sessionFactory.getCurrentSession().delete(kategoria);	
	}

	@Override
	public Kategoria findKategoriabyId(Long id) {
		return (Kategoria) sessionFactory.getCurrentSession().get(Kategoria.class, id);
	}

	@Override
	public Kategoria findKategoriabyNazwa(String nazwa) {
		return (Kategoria) sessionFactory.getCurrentSession().getNamedQuery("kategoria.byNazwa").setString("nazwa", nazwa).list().get(0);
	}

	@Override
	public boolean editKategoria(Kategoria kategoria) {
		try{
			sessionFactory.getCurrentSession().update(kategoria);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	
}

