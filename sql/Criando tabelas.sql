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

Create table tb_usuario(
	user_codigo BIGINT PRIMARY KEY,
	user_nome varchar(30),
	user_cpf varchar(15),
	user_username varchar(20),
	user_senha varchar(11)
);

INSERT INTO tb_funcionarios VALUES('1','Franscisco Aguiar', '081.654.759-66','12345678','gerente');

CREATE OR REPLACE FUNCTION gerar_registros_fornecedores()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    descricao TEXT[] := ARRAY['Colombo', 'Eletrolux', 'Teste', 'Casas Bahia', 'Lojas Americanas', 'Carrefour', 'Lojas Becker', 'Ricardo Eletro', 'Ponto Frio', 'Extra', 'Fast Shop', 'Submarino', 'Walmart', 'Eletrosom']; 
BEGIN
    WHILE i <= array_length(descricao, 1) LOOP
        INSERT INTO tb_fornecedores (for_codigo, for_descricao)
        VALUES (
            i, 
            descricao[i]
        );
        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

-- Chame a função para gerar registros de fornecedores
SELECT gerar_registros_fornecedores();


select * from tb_fornecedores;






CREATE OR REPLACE FUNCTION gerar_registros_produto()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    produtos VARCHAR[] := ARRAY['Geladeira', 'Liquidificador', 'Fogão', 'Máquina de lavar roupa', 'Máquina de secar roupa', 'Micro-ondas', 'Máquina de lavar louça','Aspirador de pó', 'Cafeteira', 'Torradeira','Forno elétrico', 'Ferro de passar roupa', 'Freezer','Ar condicionado', 'Ventilador','Purificador de água','Exaustor de cozinha','Aquecedor elétrico','Panela elétrica de arroz','Grill elétrico'];
    descricao TEXT;
    posicao INTEGER;
    valor FLOAT;
    quantidade INTEGER;
    geladeira_tipo TEXT;
    fornecedor INTEGER;

BEGIN
    WHILE i <= 1000 LOOP
        posicao := FLOOR(RANDOM() * 20 + 1)::INTEGER;
        valor := FLOOR((RANDOM() * 100000) / 100.0) + 100;  -- Gera um valor randômico entre 0 e 100
        quantidade := FLOOR(RANDOM() * 100 + 1)::INTEGER;   -- Gera uma quantidade randômica entre 1 e 100
        fornecedor := floor(random() * 13 + 1);

        descricao := produtos[posicao];

        IF produtos[posicao] = 'Geladeira' OR produtos[posicao] = 'Freezer' THEN
            IF RANDOM() < 0.5 THEN
                geladeira_tipo := 'uma porta';
            ELSE
                geladeira_tipo := 'duas portas';
				valor := valor + 200;
            END IF;
            descricao := descricao || ' ' || geladeira_tipo;
        END IF;

        INSERT INTO tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo)
        VALUES (
            i,
            descricao,
            valor,
            quantidade,
            fornecedor
        );

        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

SELECT gerar_registros_produto();

select * from tb_produtos;
 

select *from tb_funcionarios;

