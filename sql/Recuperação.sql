--Recuperação 

/*(Pontos 1,0) Pensando na garantia de consistência dos dados, crie uma função que envolva
um contexto onde haverá uma verificação se os dados estão corretos, caso sim “Commit” caso
contrário Exception (no PLPgSQL o Exception em uma função é considerado um Rollback).*/

CREATE OR REPLACE FUNCTION realizarVenda(produto_codigo BIGINT, quantidade_venda INT)
RETURNS VOID AS $$
DECLARE
	quantidade_estoque INTEGER;
	quantidade_vendas INTEGER;
	id_itens INTEGER;
	valor_produto DECIMAL;
	valor_total DECIMAL;
    venda_id BIGINT;
BEGIN
    -- Iniciar transação
    BEGIN
        SELECT pro_quantidade INTO quantidade_estoque FROM tb_produtos WHERE pro_codigo = produto_codigo;
        
        RAISE NOTICE 'Quantidade em Estoque: %', quantidade_estoque;
        
        IF quantidade_estoque >= quantidade_venda THEN
            SELECT pro_valor INTO valor_produto FROM tb_produtos WHERE pro_codigo = produto_codigo;
            UPDATE tb_produtos SET pro_quantidade = pro_quantidade - quantidade_venda WHERE pro_codigo = produto_codigo;
            
            RAISE NOTICE 'Valor do produto: %', valor_produto;
            valor_total := quantidade_venda * valor_produto;
			RAISE NOTICE 'Valor do total: %', valor_total;
			
            SELECT COUNT(*) INTO quantidade_vendas FROM tb_vendas;
            INSERT INTO tb_vendas (ven_codigo, ven_horario, ven_valor_total) 
            VALUES (quantidade_vendas + 1, CURRENT_TIMESTAMP, valor_total) RETURNING ven_codigo INTO venda_id;
            
            SELECT COUNT(*) INTO id_itens FROM tb_itens;
            INSERT INTO tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo)
            VALUES (id_itens + 1, quantidade_venda, valor_total, produto_codigo, quantidade_vendas + 1);
            
            RAISE NOTICE 'Venda realizada com sucesso!';
        ELSE
            RAISE EXCEPTION 'Quantidade em estoque insuficiente para realizar a venda.';
        END IF;
    EXCEPTION
        WHEN others THEN
            RAISE NOTICE 'Erro ao realizar a venda. Rollback executado.';
            ROLLBACK;
    END;
END;
$$ LANGUAGE plpgsql;

SELECT realizarVenda(1, 10);

select * from tb_produtos WHERE pro_codigo = 1;









/*Desenvolver uma rotina de backup do Banco de Dados e integra-la ao sistema.*/
CREATE OR REPLACE FUNCTION realizarBackup(nome_usuario TEXT, nome_bd TEXT)
RETURNS VOID AS $$
DECLARE
    backup_filename TEXT;
	cmd TEXT;
BEGIN
    -- Define o nome do arquivo de backup com a data atual
    backup_filename := 'C:/Users/Camil/Desktop/Faculdade 2024/Banco de Dados II/Trabalho Prático II/Projeto_Banco_II/sql/backup_loja_virtual_' || TO_CHAR(NOW(), 'YYYY-MM-DD_HH24-MI-SS') || '.sql';

    -- Define o comando de backup
    --EXECUTE 'pg_dump -U ' || nome_usuario || ' -d ' || nome_bd || ' > ' || quote_literal(backup_filename);
	EXECUTE 'pg_dump ' || nome_bd || ' > ' || backup_filename;
	--EXECUTE 'COPY (SELECT * FROM pg_dump(' || quote_literal(nome_bd) || ')) TO PROGRAM ''pg_dump ' || quote_ident(nome_bd) || ' > ' || quote_literal(backup_filename) || '''';
	--EXECUTE 'pg_dump -d ' || nome_bd || ' > ' || backup_filename;
	--cmd := 'COPY ' || table_name || ' TO || backup_filename || ''' WITH (FORMAT CSV, HEADER)';
    
    -- Executa a query
    --EXECUTE cmd;
	
    RAISE NOTICE 'Backup realizado com sucesso: %', backup_filename;
END;
$$ LANGUAGE plpgsql;

SELECT realizarBackup('postgres', 'loja_virtual');

CREATE OR REPLACE FUNCTION pg_backup(database_name TEXT)
RETURNS VOID AS $$
BEGIN
    PERFORM pg_catalog.pg_start_backup('label', true);

    -- Execute the pg_dump command
    PERFORM pg_catalog.pg_create_restore_point('backup');
    
    -- Aqui você pode adicionar mais lógica para gerenciar os backups, como armazenar o caminho do backup em uma tabela de auditoria.

    PERFORM pg_catalog.pg_stop_backup();

    RAISE NOTICE 'Backup completed for database %', database_name;
END;
$$ LANGUAGE plpgsql;

SELECT pg_backup('loja_virtual');