package com.github.acnaweb.study_api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
public class IndicadorDto {
    private String id;

    private String descricao;
}
