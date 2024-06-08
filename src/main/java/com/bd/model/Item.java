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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ite_codigo;
    private Integer ite_quantidade;
    private double ite_valor_parcial;
    private Integer tb_produtos_pro_codigo;
    private Integer tb_vendas_ven_codigo;
}

