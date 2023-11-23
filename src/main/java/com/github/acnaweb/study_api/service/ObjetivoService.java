package com.github.acnaweb.study_api.service;

import com.github.acnaweb.study_api.model.Objetivo;
import com.github.acnaweb.study_api.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoService {

	@Autowired
	private ObjetivoRepository objetivoRepository;

	public List<Objetivo> list() {
		return objetivoRepository.findAll();
	}
	public Boolean existsById(String id) {
		return objetivoRepository.existsById(id);
	}
}
