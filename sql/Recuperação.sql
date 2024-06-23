--Recuperação 

/*(Pontos 1,0) Pensando na garantia de consistência dos dados, crie uma função que envolva
um contexto onde haverá uma verificação se os dados estão corretos, caso sim “Commit” caso
contrário Exception (no PLPgSQL o Exception em uma função é considerado um Rollback).*/

CREATE OR REPLACE FUNCTION realizarVenda(funcionario_codigo BIGINT, produto_codigo BIGINT, quantidade_venda INT)
RETURNS VARCHAR AS $$
DECLARE
	quantidade_estoque INTEGER;
	id_vendas INTEGER;
	id_item INTEGER;
	valor_produto DECIMAL;
	valor_total DECIMAL;
    venda_id BIGINT;
BEGIN
    -- Iniciar transação
    BEGIN
        SELECT pro_quantidade INTO quantidade_estoque FROM tb_produtos WHERE pro_codigo = produto_codigo;
        
        IF quantidade_estoque >= quantidade_venda THEN
            SELECT pro_valor INTO valor_produto FROM tb_produtos WHERE pro_codigo = produto_codigo;
            UPDATE tb_produtos SET pro_quantidade = pro_quantidade - quantidade_venda WHERE pro_codigo = produto_codigo;
            
            valor_total := quantidade_venda * valor_produto;
			
            SELECT MAX(ven_codigo) INTO id_vendas FROM tb_vendas;
            INSERT INTO tb_vendas (ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo) 
				
            VALUES (id_vendas + 1, CURRENT_TIMESTAMP, valor_total, funcionario_codigo) RETURNING ven_codigo INTO venda_id;
            
            SELECT COUNT(*) INTO id_item FROM tb_itens;
            INSERT INTO tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo)
            VALUES (id_item + 1, quantidade_venda, valor_total, produto_codigo, venda_id);
            
            RETURN 'Venda realizada com sucesso!'; 
		ELSE
			RETURN 'Quantidade insuficiente em estoque!';
		END IF;
    EXCEPTION
        WHEN others THEN
			RETURN 'Erro ao realizar venda!';
    END;
END;
$$ LANGUAGE plpgsql;

SELECT realizarVenda(1, 2, 20);

select * from tb_produtos WHERE pro_codigo = 2;


DROP FUNCTION realizarVenda

DO $$
DECLARE
    resultado VARCHAR;
BEGIN
    resultado := realizarVenda(1, 2, 20);
    RAISE NOTICE 'Resultado da função: %', resultado;
END;
$$ LANGUAGE plpgsql;




/*Desenvolver uma rotina de backup do Banco de Dados e integra-la ao sistema.*/
CREATE OR REPLACE FUNCTION programar_backup(prox_backup TIMESTAMP)
RETURNS VOID AS $$
BEGIN
    UPDATE backup_programado SET proximo_backup = prox_backup WHERE id = (SELECT MAX(id) FROM backup_programado);
END;
$$ LANGUAGE plpgsql;

select * from backup_programado

CREATE OR REPLACE FUNCTION realizar_backup()
RETURNS VOID AS $$
DECLARE
    ultimo TIMESTAMP;
    proximo TIMESTAMP;
BEGIN
    SELECT ultimo_backup, proximo_backup INTO ultimo, proximo FROM backup_programado WHERE id = (SELECT MAX(id) FROM backup_programado);
	
	IF proximo IS NULL THEN
        proximo := CURRENT_TIMESTAMP + INTERVAL '1 week';
    ELSE
        proximo := CURRENT_TIMESTAMP + (proximo - ultimo);
    END IF;
	
    INSERT INTO backup_programado (ultimo_backup, proximo_backup) VALUES (CURRENT_TIMESTAMP, proximo);
END;
$$ LANGUAGE plpgsql;

SELECT realizar_backup();
SELECT atualizar_proximo_backup(CURRENT_TIMESTAMP);

SELECT * from backup_programado;