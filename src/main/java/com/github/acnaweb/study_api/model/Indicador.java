package com.github.acnaweb.study_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "indicadores")
public class Indicador {
    @Id
    private String id;

    private String descricao;
}
