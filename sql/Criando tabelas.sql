DROP TABLE IF EXISTS tb_fornecedores;
DROP TABLE IF EXISTS tb_produtos;
DROP TABLE IF EXISTS tb_itens;
DROP TABLE IF EXISTS tb_vendas;
DROP TABLE IF EXISTS tb_funcionarios;


Create table tb_fornecedores(
	for_codigo BIGINT;
	for_descricao VARCHAR(45) NOTNULL;
	PRIMARY KEY(for_codigo)
);

Create table tb_produtos(
	pro_codigo BIGINT;
	pro_descricao VARCHAR(45);
	pro_valor DECIMAL;
	pro_quantidade INT;
	tb_fornecedores_fur_codigo BIGINT;
	FOREIGN KEY(for_codigo)
    REFERENCES tb_fornecedores(for_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
	PRIMARY KEY(pro_codigo)
);

Create table tb_itens(
	ite_codigo BIGINT;
	ite_quantidade INT;
	ite_valor_parcial DECIMAL;
	tb_produtos_pro_codigo BIGINT;
	tb_vendas_ven_codigo BIGINT;
    FOREIGN KEY(ven_codigo)
    REFERENCES tb_vendas(ven_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
    FOREIGN KEY(pro_codigo)
    REFERENCES tb_produtos(pro_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
	PRIMARY KEY(ite_codigo)
);

Create table tb_vendas(
	ven_codigo BIGINT;
	ven_horario TIMESTAMP;
	ven_valor_total DECIMAL(7,2);
	tb_funcionarios_fun_codigo BIGINT;
    FOREIGN KEY(fun_codigo)
    REFERENCES tb_funcionarios(fun_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
	PRIMARY KEY(ven_codigo)
);

Create table tb_funcionarios(
	fun_codigo BIGINT;
	fun_nome VARCHAR(50);
	fun_cpf VARCHAR(50);
	fun_senha VARCHAR(50);
	fun_funcao VARCHAR(50);
	PRIMARY KEY(fun_codigo)
);