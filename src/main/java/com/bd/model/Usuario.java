package com.bd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_codigo;
    private String user_nome;
    private String user_cpf;
    private String user_username;
    private String user_senha;

    public Usuario(Integer user_codigo, String user_nome, String user_cpf, String user_username) {
        this.user_codigo = user_codigo;
        this.user_nome = user_nome;
        this.user_cpf = user_cpf;
        this.user_username = user_username;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user_codigo=" + user_codigo +
                ", user_nome='" + user_nome + '\'' +
                ", user_cpf='" + user_cpf + '\'' +
                ", user_username='" + user_username + '\'' +
                ", user_senha='" + user_senha + '\'' +
                '}';
    }
}
