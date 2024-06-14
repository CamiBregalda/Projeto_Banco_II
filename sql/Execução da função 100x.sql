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

--INSERT INTO tb_funcionarios VALUES('1','Franscisco Aguiar', '081.654.759-66','12345678','gerente');

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

CREATE OR REPLACE FUNCTION gerar_registros_funcionarios()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    cpf_text TEXT;
    nome TEXT[] := ARRAY['João', 'José', 'Antônio', 'Francisco', 'Carlos', 'Paulo', 'Lucas', 'Pedro', 'Marcos', 'Luiz', 'Gabriel', 'André', 'Bruno', 'Rafael', 'Felipe', 'Maria', 'Ana', 'Francisca', 'Adriana', 'Juliana', 'Fernanda', 'Patricia', 'Mariana', 'Vanessa', 'Amanda', 'Tatiane', 'Bianca', 'Marcela', 'Larissa', 'Paula'];
    sobrenomes TEXT[] := ARRAY['Silva', 'Santos', 'Menezes', 'Souza', 'Pereira', 'Costa', 'Carvalho', 'Almeida', 'Ferreira', 'Rodrigues', 'Martins', 'Lima', 'Araújo', 'Fernandes', 'Oliveira', 'Rodrigues', 'Alves', 'Barbosa', 'Ribeiro', 'Gonçalves', 'Miranda', 'Cardoso', 'Castro', 'Rocha', 'Neves', 'Santos', 'Morais', 'Azevedo', 'Cunha', 'Mendes'];
    posicaoNome INTEGER;
    posicaoSobrenome INTEGER;
    senha TEXT;
    j INT;
    funcao TEXT;
    funcoes TEXT[] := ARRAY['vendedor', 'encarregado', 'gerente'];
BEGIN
    WHILE i <= 100 LOOP
        -- Formatar o CPF para ter 11 dígitos
        cpf_text := LPAD(CAST(1000000000 + i AS TEXT), 11, '0');
        
        posicaoNome := FLOOR(RANDOM() * (30) + 1);
        posicaoSobrenome := FLOOR(RANDOM() * (30) + 1);
        senha := '';
        FOR j IN 1..8 LOOP
            senha := senha || CAST(FLOOR(RANDOM() * 10) AS INT);
        END LOOP;

        funcao := funcoes[1 + FLOOR(RANDOM() * 3)];

        INSERT INTO tb_funcionarios(fun_codigo, fun_nome, fun_cpf, fun_senha, fun_funcao)
        VALUES (
            i,
            nome[posicaoNome] || ' ' || sobrenomes[posicaoSobrenome],
            CONCAT(
                SUBSTRING(cpf_text FROM 1 FOR 3), '.', 
                SUBSTRING(cpf_text FROM 4 FOR 3), '.', 
                SUBSTRING(cpf_text FROM 7 FOR 3), '-',
                SUBSTRING(cpf_text FROM 10 FOR 2)
            ),
            senha,
            funcao
        );
        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

select gerar_registros_funcionarios();

select * from tb_funcionarios;

CREATE OR REPLACE FUNCTION gerar_registros_vendas()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    horario TIMESTAMP;
    valor_total DECIMAL(7,2);
    funcionario_codigo BIGINT;
    produto_codigo BIGINT;
BEGIN
    WHILE i <= 1000 LOOP
        -- Gerar um horário aleatório nos últimos 30 dias
        horario := NOW() - INTERVAL '1 day' * FLOOR(RANDOM() * 30);
        
        -- Gerar um valor total aleatório entre 50 e 5000
        valor_total := ROUND((RANDOM() * 4950 + 50)::NUMERIC, 2);

        -- Selecionar um funcionário aleatório da tabela tb_funcionarios
        SELECT fun_codigo
        INTO funcionario_codigo
        FROM tb_funcionarios
        ORDER BY RANDOM()
        LIMIT 1;

        -- Selecionar um produto aleatório da tabela tb_produtos
        SELECT pro_codigo
        INTO produto_codigo
        FROM tb_produtos
        ORDER BY RANDOM()
        LIMIT 1;

        INSERT INTO tb_vendas(ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo)
        VALUES (
            i,
            horario,
            valor_total,
            funcionario_codigo
        );

        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

select gerar_registros_vendas();

select * from tb_vendas;

CREATE OR REPLACE FUNCTION gerar_registros_itens()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    quantidade INTEGER;
    valor_parcial DECIMAL(7,2);
    produto_codigo BIGINT;
    venda_codigo BIGINT;
BEGIN
    WHILE i <= 1000 LOOP
        -- Selecionar um código de produto aleatório da tabela tb_produtos
        SELECT pro_codigo
        INTO produto_codigo
        FROM tb_produtos
        ORDER BY RANDOM()
        LIMIT 1;

        -- Selecionar um código de venda aleatório da tabela tb_vendas
        SELECT ven_codigo
        INTO venda_codigo
        FROM tb_vendas
        ORDER BY RANDOM()
        LIMIT 1;

        -- Gerar uma quantidade aleatória entre 1 e 10
        quantidade := FLOOR(RANDOM() * 10 + 1)::INTEGER;

        -- Calcular o valor parcial com base no valor do produto e quantidade
        SELECT pro_valor * quantidade
        INTO valor_parcial
        FROM tb_produtos
        WHERE pro_codigo = produto_codigo;

        INSERT INTO tb_itens(ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo)
        VALUES (
            i,
            quantidade,
            valor_parcial,
            produto_codigo,
            venda_codigo
        );

        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

SELECT gerar_registros_itens();

select * from tb_itens;


CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE EXTENSION IF NOT EXISTS btree_gist;

EXPLAIN ANALYSE SELECT COUNT(tp.pro_descricao) AS valor_por_produto, tf.for_descricao, tp.pro_valor,
SUM(ti.ite_quantidade) AS total_quantidade_itens, tp.pro_descricao
FROM tb_produtos tp
INNER JOIN tb_fornecedores tf ON tp.tb_fornecedores_for_codigo = tf.for_codigo
INNER JOIN tb_itens ti ON tp.pro_codigo = ti.tb_produtos_pro_codigo
INNER JOIN tb_vendas tv ON ti.tb_vendas_ven_codigo = tv.ven_codigo
WHERE tf.for_descricao LIKE 'Eletrolux' AND tp.pro_valor > 300
GROUP BY tp.pro_descricao, tf.for_descricao, tp.pro_valor, tp.pro_quantidade
ORDER BY valor_por_produto;

set enable_seqscan = OFF;
set enable_indexscan = OFF;
set enable_bitmapscan = OFF;
set enable_hashjoin = OFF;
set enable_mergejoin = OFF;
set enable_indexonlyscan = OFF;
set enable_nestloop = OFF;


DISCARD PLANS;
DISCARD ALL;



CREATE INDEX IF NOT EXISTS consultaBtree ON tb_itens USING btree(ite_vendas);
CREATE INDEX IF NOT EXISTS consultaBtree ON tb_itens USING btree(ite_codigo);
CREATE INDEX IF NOT EXISTS consultaBtree ON tb_fornecedores USING btree(for_codigo);
CREATE INDEX IF NOT EXISTS consultaBtree ON tb_fornecedores USING btree(for_descricao);
CREATE INDEX IF NOT EXISTS consultaBtree ON tb_vendas USING btree(ven_codigo);
CREATE INDEX IF NOT EXISTS consultaBtree ON tb_vendas USING btree(ven_valor_total);
DROP INDEX consultaBtree;

CREATE INDEX IF NOT EXISTS consultaHash ON tb_itens USING hash(ite_vendas);
CREATE INDEX IF NOT EXISTS consultaHash ON tb_itens USING hash(ite_codigo);
CREATE INDEX IF NOT EXISTS consultaHash ON tb_fornecedores USING hash(for_codigo);
CREATE INDEX IF NOT EXISTS consultaHash ON tb_fornecedores USING hash(for_descricao);
CREATE INDEX IF NOT EXISTS consultaHash ON tb_vendas USING hash(ven_codigo);
CREATE INDEX IF NOT EXISTS consultaHash ON tb_vendas USING hash(ven_valor_total);
DROP INDEX consultaHash;

CREATE INDEX IF NOT EXISTS consultaGin ON tb_itens USING gin (ite_vendas gin_trgm_ops);
CREATE INDEX IF NOT EXISTS consultaGin ON tb_itens USING gin (ite_codigo gin_trgm_ops);
CREATE INDEX IF NOT EXISTS consultaGin ON tb_fornecedores USING gin (for_codigo gin_trgm_ops);
CREATE INDEX IF NOT EXISTS consultaGin ON tb_fornecedores USING gin (for_descricao gin_trgm_ops);
CREATE INDEX IF NOT EXISTS consultaGin ON tb_vendas USING gin (ven_codigo gin_trgm_ops);
CREATE INDEX IF NOT EXISTS consultaGin ON tb_vendas USING gin (ven_valor_total gin_trgm_ops);
DROP INDEX consultaGin;

CREATE INDEX IF NOT EXISTS consultaBrin ON tb_itens USING brin (ite_vendas);
CREATE INDEX IF NOT EXISTS consultaBrin ON tb_itens USING brin (ite_codigo);
CREATE INDEX IF NOT EXISTS consultaBrin ON tb_fornecedores USING brin (for_codigo);
CREATE INDEX IF NOT EXISTS consultaBrin ON tb_fornecedores USING brin (for_descricao);
CREATE INDEX IF NOT EXISTS consultaBrin ON tb_vendas USING brin (ven_codigo);
CREATE INDEX IF NOT EXISTS consultaBrin ON tb_vendas USING brin (ven_valor_total);
DROP INDEX consultaBrin;

CREATE INDEX IF NOT EXISTS consultaGist ON tb_itens USING gist  (ite_vendas);
CREATE INDEX IF NOT EXISTS consultaGist ON tb_itens USING gist  (ite_codigo);
CREATE INDEX IF NOT EXISTS consultaGist ON tb_fornecedores USING gist  (for_codigo);
CREATE INDEX IF NOT EXISTS consultaGist ON tb_fornecedores USING gist  (for_descricao);
CREATE INDEX IF NOT EXISTS consultaGist ON tb_vendas USING gist  (ven_codigo);
CREATE INDEX IF NOT EXISTS consultaGist ON tb_vendas USING gist  (ven_valor_total);
DROP INDEX consultaGist;

DROP TABLE tempo_execucao_1_consulta;


CREATE TABLE IF NOT EXISTS tempo_execucao_1_consulta (
    id SERIAL PRIMARY KEY,
    tempo FLOAT
);



CREATE OR REPLACE FUNCTION executar_consulta_1_100_vezes() 
RETURNS FLOAT AS $$
DECLARE
    tempo_total FLOAT := 0;
    tempo_execucao FLOAT;
	explain_analyze TEXT;
BEGIN
    -- Limpa a tabela de tempos de execução antes de começar
    TRUNCATE TABLE tempo_execucao_1_consulta;

	-- Roda 100 execuções
    FOR contador IN 1..100 LOOP	
    	
		EXPLAIN (ANALYZE, TIMING, FORMAT JSON) into explain_analyze 
        SELECT COUNT(tp.pro_descricao) AS valor_por_produto, tf.for_descricao, tp.pro_valor,
		SUM(ti.ite_quantidade) AS total_quantidade_itens, tp.pro_descricao
		FROM tb_produtos tp
		INNER JOIN tb_fornecedores tf ON tp.tb_fornecedores_for_codigo = tf.for_codigo
		INNER JOIN tb_itens ti ON tp.pro_codigo = ti.tb_produtos_pro_codigo
		INNER JOIN tb_vendas tv ON ti.tb_vendas_ven_codigo = tv.ven_codigo
		WHERE tf.for_descricao LIKE 'Eletrolux' AND tp.pro_valor > 300
		GROUP BY tp.pro_descricao, tf.for_descricao, tp.pro_valor, tp.pro_quantidade
		ORDER BY valor_por_produto;
        
		-- Convertendo explain_analyze TEXT para um dado FLOAT
    	tempo_execucao := ((explain_analyze::JSONB)-> 0 ->> 'Execution Time')::FLOAT;
		
		-- Guardar dados de execução em uma tabela a parte
		INSERT INTO tempo_execucao_1_consulta (tempo) VALUES (tempo_execucao);
        
        -- Mensagem de saída com o id e o tempo da execução
        RAISE NOTICE 'Execução % concluída. Tempo de execução: % milissegundos', contador, tempo_execucao;
		
		-- Limpar dados na memória cache para não ter os valores de execução comprometidos
		EXECUTE 'DISCARD PLANS';
    END LOOP;
		
	-- Soma os valores salvos na tabela tempo_execucao_1_consulta para realizar a média
	SELECT SUM(tempo) INTO tempo_total FROM tempo_execucao_1_consulta;
	
	RAISE NOTICE 'Tempo médio de execução das 100 execuções: % milissegundos', tempo_total / 100;
		
    -- Calcula a média dos tempos de execução em milissegundos
    RETURN tempo_total / 100;
END;
$$ 
LANGUAGE plpgsql;

SELECT executar_consulta_1_100_vezes();