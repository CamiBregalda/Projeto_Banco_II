package com.bd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ven_codigo;
    private LocalDateTime ven_horario;
    private Double ven_valor_total;
    private Integer tb_funcionarios_fun_codigo;


    @Override
    public String toString() {
        return "Venda{" +
                "ven_codigo=" + ven_codigo + '\'' +
                ", ven_horario=" + ven_horario + '\'' +
                ", ven_valor_total=" + ven_valor_total + '\'' +
                ", ven_tb_funcionarios_fun_codigo=" + tb_funcionarios_fun_codigo + '\'' +
                '}';
    }

}
