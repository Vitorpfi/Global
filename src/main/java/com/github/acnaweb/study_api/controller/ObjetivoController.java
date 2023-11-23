package com.github.acnaweb.study_api.controller;

import java.util.List;

import com.github.acnaweb.study_api.controller.dto.IndicadorDto;
import com.github.acnaweb.study_api.controller.dto.ObjetivoDto;
import com.github.acnaweb.study_api.service.IndicadorService;
import com.github.acnaweb.study_api.service.ObjetivoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

	@Autowired
	private IndicadorService indicadorService;
	@Autowired
	private ObjetivoService objetivoService;
	@Autowired
	private  ModelMapper modelMapper;


	@GetMapping
	public ResponseEntity<List<ObjetivoDto>> listAllObjetivos() {
		List<ObjetivoDto> list = objetivoService.list().stream().map(objetivo -> modelMapper.map(objetivo, ObjetivoDto.class)).collect(toList()); ;
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}/indicadores")
	public ResponseEntity<List<IndicadorDto>> listAllIndicadoresByObjetivo(@PathVariable String id) {
		if (!objetivoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		List<IndicadorDto> list = indicadorService.list().stream().map(objetivo -> modelMapper.map(objetivo, IndicadorDto.class)).collect(toList()); ;
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
