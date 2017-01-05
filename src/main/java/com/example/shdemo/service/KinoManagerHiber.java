package com.example.shdemo.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Kategoria;

@Component
@Transactional
public class KinoManagerHiber implements KinoM {

	@Override
	public void addKategoria(Kategoria kategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Kategoria> getAllKategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteKategoria(Kategoria kategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Kategoria findKategoriabyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kategoria findKategoriabyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editKategoria(Kategoria kategoria) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

