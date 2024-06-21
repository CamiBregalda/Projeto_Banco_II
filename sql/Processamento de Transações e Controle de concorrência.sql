--Processamento de Transações e Controle de concorrência

/*a. (Pontos 0,5) Crie uma função e no corpo da mesma teste o comando Rollback (no PLPgSQL o 
Exception em uma função é considerado um Rollback).*/

Create table tb_funcionarios(
	fun_codigo BIGINT PRIMARY KEY,
	fun_nome VARCHAR(30),
	fun_cpf VARCHAR(15),
	fun_senha VARCHAR(11),
	fun_funcao VARCHAR(20)
);

CREATE OR REPLACE FUNCTION cadastrar_funcionario(
    nome VARCHAR(30),
    cpf VARCHAR(15),
    senha VARCHAR(11),
    funcao VARCHAR(20)
)
RETURNS VOID AS $$
DECLARE
    codigo BIGINT;
	id_funcionario BIGINT;
BEGIN
    -- Verifica se o nome do funcionário já existe
    SELECT fun_codigo INTO codigo FROM tb_funcionarios WHERE fun_nome = nome;

    -- Se o código foi encontrado, significa que o nome já existe
    IF FOUND THEN
        RAISE EXCEPTION 'Erro: Funcionário com nome % já existe. Rollback necessário.', nome;
    END IF;


	SELECT MAX(fun_codigo) INTO id_funcionario FROM tb_funcionarios;
    INSERT INTO tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_senha, fun_funcao) VALUES (id_funcionario + 1, nome, cpf, senha, funcao);
END;
$$ LANGUAGE plpgsql;

select cadastrar_funcionario('Camila Bregalda', '333.333.333-33', 'senha', 'Gerente');

SELECT * FROM tb_funcionarios;


/*
CREATE OR REPLACE FUNCTION realizar_venda(
    ven_horario TIMESTAMP,
    fun_codigo BIGINT,
    pro_codigo BIGINT,
    ite_quantidade INT
) RETURNS VARCHAR AS $$
DECLARE
	valor_produto DECIMAL;
	valor_total DECIMAL;
	quantidade_estoque INTEGER;
	id_vendas INTEGER;
	id_item INTEGER;
BEGIN
    -- Iniciar transação
    BEGIN
    
    -- Verificar a quantidade disponível no estoque
    SELECT pro_quantidade INTO quantidade_estoque
    FROM tb_produtos WHERE pro_codigo = p_pro_codigo;
	
	SELECT pro_valor INTO valor_produto
    FROM tb_produtos WHERE pro_codigo = p_pro_codigo;
    
    -- Checar se há quantidade suficiente no estoque
    IF quantidade_estoque < ite_quantidade THEN
        -- Se não houver estoque suficiente, lançar exceção
        RETURN 'Estoque insuficiente para o produto %', p_pro_codigo;
    END IF;
	
	valor_total := ite_quantidade * valor_produto;

    -- Inserir a venda na tabela tb_vendas
	SELECT COUNT(*) INTO id_vendas FROM tb_vendas;
    INSERT INTO tb_vendas (ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo)
    VALUES (id_vendas + 1, ven_horario, valor_total, fun_codigo);

    -- Inserir o item na tabela tb_itens
	SELECT COUNT(*) INTO id_item FROM tb_itens;
    INSERT INTO tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo)
    VALUES (id_item + 1, ite_quantidade, valor_produto, pro_codigo, id_vendas + 1);

    -- Atualizar a quantidade no estoque
    UPDATE tb_produtos
    SET pro_quantidade = pro_quantidade - ite_quantidade
    WHERE pro_codigo = p_pro_codigo;

    -- Comitar a transação
    COMMIT;
	RETURN 'Venda realizada com sucesso!';
EXCEPTION
    WHEN OTHERS THEN
        -- Fazer rollback em caso de erro
        ROLLBACK;
		RETURN 'Erro ao realizar a venda';
	END;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM tb_produtos WHERE pro_codigo = 1;

DO $$
DECLARE
    resultado VARCHAR;
BEGIN
    resultado := realizarVenda('2004-10-19 10:23:54', 1, 101, 5);
    RAISE NOTICE 'Resultado da função: %', resultado;
END;
$$ LANGUAGE plpgsql;

drop function realizar_venda()
--Ideia: valor pago é menor que valor total da venda
*/

/*b. (Pontos 0,5) Criar duas ou mais transações que utilizam um mesmo recurso de forma
simultânea para realizar uma simulação e poder visualizar o controle de transação funcionando.*/