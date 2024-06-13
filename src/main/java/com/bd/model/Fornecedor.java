package com.bd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer for_codigo;
    private String for_descricao;


    public Fornecedor(Integer forCodigo, String forDescricao) {
        this.for_codigo = forCodigo;
        this.for_descricao = forDescricao;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "for_codigo=" + for_codigo + '\'' +
                ", for_descricao=" + for_descricao + '\'' +
                '}';
    }


}
