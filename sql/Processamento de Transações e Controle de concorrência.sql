--Processamento de Transações e Controle de concorrência

/*a. (Pontos 0,5) Crie uma função e no corpo da mesma teste o comando Rollback (no PLPgSQL o 
Exception em uma função é considerado um Rollback).*/
CREATE OR REPLACE FUNCTION realizar_venda(
    p_ven_codigo BIGINT,
    p_ven_horario TIMESTAMP,
    p_ven_valor_total DECIMAL(7,2),
    p_fun_codigo BIGINT,
    p_pro_codigo BIGINT,
    p_ite_quantidade INT
) RETURNS VOID AS $$
DECLARE
    v_pro_quantidade INT;
BEGIN
    -- Iniciar transação
    BEGIN;
    
    -- Verificar a quantidade disponível no estoque
    SELECT pro_quantidade INTO v_pro_quantidade
    FROM tb_produtos
    WHERE pro_codigo = p_pro_codigo;
    
    -- Checar se há quantidade suficiente no estoque
    IF v_pro_quantidade < p_ite_quantidade THEN
        -- Se não houver estoque suficiente, lançar exceção
        RAISE EXCEPTION 'Estoque insuficiente para o produto %', p_pro_codigo;
    END IF;

    -- Inserir a venda na tabela tb_vendas
    INSERT INTO tb_vendas (ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo)
    VALUES (p_ven_codigo, p_ven_horario, p_ven_valor_total, p_fun_codigo);

    -- Inserir o item na tabela tb_itens
    INSERT INTO tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo)
    VALUES (p_ven_codigo, p_ite_quantidade, p_ven_valor_total, p_pro_codigo, p_ven_codigo);

    -- Atualizar a quantidade no estoque
    UPDATE tb_produtos
    SET pro_quantidade = pro_quantidade - p_ite_quantidade
    WHERE pro_codigo = p_pro_codigo;

    -- Comitar a transação
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Fazer rollback em caso de erro
        ROLLBACK;
        RAISE NOTICE 'Transação revertida devido a: %', SQLERRM;
END;
$$ LANGUAGE plpgsql;

--Ideia: valor pago é menor que valor total da venda


/*b. (Pontos 0,5) Criar duas ou mais transações que utilizam um mesmo recurso de forma
simultânea para realizar uma simulação e poder visualizar o controle de transação funcionando.*/