package com.github.acnaweb.study_api.repository;

import com.github.acnaweb.study_api.model.Indicador;
import com.github.acnaweb.study_api.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorRepository
	extends JpaRepository<Indicador, String> {

}
