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
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pro_codigo;
    private String pro_descricao;
    private double pro_valor;
    private Integer pro_quantidade;
    private Integer tb_fornecedores_for_codigo;

    public Produto(Integer pro_codigo, String pro_descricao, double pro_valor, Integer pro_quantidade, Integer tb_fornecedores_for_codigo) {
        this.pro_codigo = pro_codigo;
        this.pro_descricao = pro_descricao;
        this.pro_valor = pro_valor;
        this.pro_quantidade = pro_quantidade;
        this.tb_fornecedores_for_codigo = tb_fornecedores_for_codigo;
    }



}