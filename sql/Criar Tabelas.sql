DROP TABLE IF EXISTS tb_itens;
DROP TABLE IF EXISTS tb_produtos;
DROP TABLE IF EXISTS tb_fornecedores;
DROP TABLE IF EXISTS tb_vendas;
DROP TABLE IF EXISTS tb_funcionarios;
DROP TABLE IF EXISTS tb_usuario;

Create table tb_fornecedores(
	for_codigo BIGINT PRIMARY KEY,
	for_descricao VARCHAR(45) NOT NULL
);

Create table tb_funcionarios(
	fun_codigo BIGINT PRIMARY KEY,
	fun_nome VARCHAR(30),
	fun_cpf VARCHAR(15),
	fun_senha VARCHAR(11),
	fun_funcao VARCHAR(20)
);

Create table tb_vendas(
	ven_codigo BIGINT PRIMARY KEY,
	ven_horario TIMESTAMP,
	ven_valor_total DECIMAL(7,2),
	tb_funcionarios_fun_codigo BIGINT,
    FOREIGN KEY(tb_funcionarios_fun_codigo)
    REFERENCES tb_funcionarios(fun_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

Create table tb_produtos(
	pro_codigo BIGINT PRIMARY KEY,
	pro_descricao VARCHAR(45),
	pro_valor DECIMAL(7,2),
	pro_quantidade INT,
	tb_fornecedores_for_codigo BIGINT,
	FOREIGN KEY(tb_fornecedores_for_codigo)
    REFERENCES tb_fornecedores(for_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

Create table tb_itens(
	ite_codigo BIGINT PRIMARY KEY,
	ite_quantidade INT,
	ite_valor_parcial DECIMAL(7,2),
	tb_produtos_pro_codigo BIGINT,
	tb_vendas_ven_codigo BIGINT,
    FOREIGN KEY(tb_vendas_ven_codigo)
    REFERENCES tb_vendas(ven_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
    FOREIGN KEY(tb_produtos_pro_codigo)
    REFERENCES tb_produtos(pro_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE backup_programado (
    id SERIAL PRIMARY KEY,
    ultimo_backup TIMESTAMP NOT NULL,
    proximo_backup TIMESTAMP NOT NULL
);

INSERT INTO backup_programado VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 week');