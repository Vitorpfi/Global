package com.github.acnaweb.study_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "objetivos")
public class Objetivo {
    @Id
    @Column(name = "id")
    private String idObjetivo;

    @Override
    public String toString() {
        return "Objetivo{" +
                "idObjetivo='" + idObjetivo + '\'' +
                '}';
    }
}
