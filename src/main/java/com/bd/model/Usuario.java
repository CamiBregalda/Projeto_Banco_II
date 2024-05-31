package com.bd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nome;
    private String cpf;
    private String username;
    private String senha;

    public Usuario(Integer codigo, String nome, String cpf, String username) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.username = username;
    }
}
