package com.github.acnaweb.study_api.service;

import com.github.acnaweb.study_api.model.Indicador;
import com.github.acnaweb.study_api.model.Objetivo;
import com.github.acnaweb.study_api.repository.IndicadorRepository;
import com.github.acnaweb.study_api.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicadorService {

	@Autowired
	private IndicadorRepository indicadorRepository;

	public List<Indicador> list() {
		return indicadorRepository.findAll();
	}
}
