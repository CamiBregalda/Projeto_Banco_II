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


    public Fornecedor(Integer for_codigo, String for_descricao) {
        this.for_codigo = for_codigo;
        this.for_descricao = for_descricao;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "for_codigo=" + for_codigo + '\'' +
                ", for_descricao=" + for_descricao + '\'' +
                '}';
    }


}
