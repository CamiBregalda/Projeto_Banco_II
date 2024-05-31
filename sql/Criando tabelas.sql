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
	fun_nome VARCHAR(50),
	fun_cpf VARCHAR(50),
	fun_senha VARCHAR(50),
	fun_funcao VARCHAR(50)
);

Create table tb_vendas(
	ven_codigo BIGINT PRIMARY KEY,
	ven_horario TIMESTAMP,
	ven_valor_total DECIMAL(7,2),
	tb_funcionarios_fun_codigo BIGINT,
    FOREIGN KEY(ven_codigo)
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
	FOREIGN KEY(pro_codigo)
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
    FOREIGN KEY(ite_codigo)
    REFERENCES tb_vendas(ven_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
    FOREIGN KEY(ite_codigo)
    REFERENCES tb_produtos(pro_codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

Create table tb_usuario(
	user_codigo BIGINT PRIMARY KEY,
	user_nome varchar(45),
	user_cpf varchar(45),
	user_funcao varchar(45),
	user_username varchar(45),
	user_senha varchar(45)
);



CREATE OR REPLACE FUNCTION gerar_registros_fornecedores()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;

	descricao TEXT[] := ARRAY['Colombo', 'Eletrolux', 'Casas Bahia']; 
	posicao INTEGER;
BEGIN
    WHILE i <= 1003 LOOP
		posicao := FLOOR(RANDOM() * (3) + 1);
	
        INSERT INTO tb_fornecedores (for_codigo, for_descricao)
        VALUES (
			i, 
            descricao[posicao]
        );
        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

Select gerar_registros_fornecedores();

select * from tb_fornecedores;




-----PROBLEMA, A DESCRIÇÃO DIZ A MARCA e o FORNECEDOR DIZ OUTRO, NAO USAR O CODIGO ABAIXO
CREATE OR REPLACE FUNCTION gerar_registros_produto()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    produtos VARCHAR[] := ARRAY['Geladeira', 'Liquidificador'];
    marcas VARCHAR[] := ARRAY['Colombo', 'Eletrolux', 'Casas Bahia'];
    descricao TEXT;
    posicao INTEGER;
    valor FLOAT;
    quantidade INTEGER;
    geladeira_tipo TEXT;
    
BEGIN
    WHILE i <= 1002 LOOP
        posicao := FLOOR(RANDOM() * 2 + 1)::INTEGER;
        valor := FLOOR(RANDOM() * 10000) / 100.0;  -- Gera um valor randômico entre 0 e 100
        quantidade := FLOOR(RANDOM() * 100 + 1)::INTEGER;   -- Gera uma quantidade randômica entre 1 e 100
    
        descricao := produtos[posicao] || ' ' || marcas[posicao];
        
        IF produtos[posicao] = 'Geladeira' THEN
            IF RANDOM() < 0.5 THEN
                geladeira_tipo := 'uma porta';
            ELSE
                geladeira_tipo := 'duas portas';
            END IF;
            descricao := descricao || ' - ' || geladeira_tipo;
        END IF;
        
        INSERT INTO tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo)
        VALUES (
            2 + i,
            descricao,
            valor,
            quantidade,
            i
        );

        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;
---------------------------------------------------------------------


CREATE OR REPLACE FUNCTION gerar_registros_produto()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    produtos VARCHAR[] := ARRAY['Geladeira', 'Liquidificador'];
    descricao TEXT;
    posicao INTEGER;
    valor FLOAT;
    quantidade INTEGER;
    geladeira_tipo TEXT;
    
BEGIN
    WHILE i <= 1000 LOOP
        posicao := FLOOR(RANDOM() * 2 + 1)::INTEGER;
        valor := FLOOR(RANDOM() * 10000) / 100.0;  -- Gera um valor randômico entre 0 e 100
        quantidade := FLOOR(RANDOM() * 100 + 1)::INTEGER;   -- Gera uma quantidade randômica entre 1 e 100
    
        descricao := produtos[posicao];
        
        IF produtos[posicao] = 'Geladeira' THEN
            IF RANDOM() < 0.5 THEN
                geladeira_tipo := 'uma porta';
            ELSE
                geladeira_tipo := 'duas portas';
            END IF;
            descricao := descricao || ' - ' || geladeira_tipo;
        END IF;
        
        INSERT INTO tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo)
        VALUES (
            2 + i,
            descricao,
            valor,
            quantidade,
            i
        );

        i := i + 1;
    END LOOP;
END;
$$
LANGUAGE plpgsql;


select gerar_registros_produto();

select * from tb_produtos;




CREATE OR REPLACE FUNCTION gerar_registros_Funcionarios()
RETURNS VOID AS
$$
DECLARE
    i INTEGER := 1;
    cpf_text TEXT;
    nome TEXT[] := ARRAY['João', 'José', 'Antônio', 'Francisco', 'Carlos', 'Paulo', 'Lucas', 'Pedro', 'Marcos', 'Luiz', 'Gabriel', 'André', 'Bruno', 'Rafael', 'Felipe', 'Maria', 'Ana', 'Francisca', 'Adriana', 'Juliana', 'Fernanda', 'Patricia', 'Mariana', 'Vanessa', 'Amanda', 'Tatiane', 'Bianca', 'Marcela', 'Larissa', 'Paula'];
    sobrenomes TEXT[] := ARRAY['Silva', 'Santos', 'Menezes', 'Souza', 'Pereira', 'Costa', 'Carvalho', 'Almeida', 'Ferreira', 'Rodrigues', 'Martins', 'Lima', 'Araújo', 'Fernandes', 'Oliveira', 'Rodrigues', 'Alves', 'Barbosa', 'Ribeiro', 'Gonçalves', 'Miranda', 'Cardoso', 'Castro', 'Rocha', 'Neves', 'Santos', 'Morais', 'Azevedo', 'Cunha', 'Mendes'];
    posicaoNome INTEGER;
    posicaoSobrenome INTEGER;
    funcao VARCHAR;
    senha TEXT;
    
BEGIN
    WHILE i <= 100000 LOOP
        -- Formatar o CPF para ter 11 dígitos
        cpf_text := LPAD(CAST(1000000000 + i AS TEXT), 11, '0');
        
        posicaoNome := FLOOR(RANDOM() * (30) + 1);
        posicaoSobrenome := FLOOR(RANDOM() * (30) + 1);

        -- Gerar um número aleatório entre 1 e 3
        funcao := (FLOOR(RANDOM() * 3) + 1)::VARCHAR;

        -- Mapear o número gerado para os valores desejados
        CASE funcao
            WHEN '1' THEN funcao := 'vendedor';
            WHEN '2' THEN funcao := 'gerente';
            WHEN '3' THEN funcao := 'analista';
        END CASE;

        senha := (10 * i)::TEXT;

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


select gerar_registros_Funcionarios();

select *from tb_funcionarios;

