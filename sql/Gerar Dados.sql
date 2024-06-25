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