--Processamento de Transações e Controle de concorrência

/*a. (Pontos 0,5) Crie uma função e no corpo da mesma teste o comando Rollback (no PLPgSQL o 
Exception em uma função é considerado um Rollback).*/

CREATE OR REPLACE FUNCTION deletar_fornecedor_e_produtos(for_codigo BIGINT)
RETURNS VOID AS $$
BEGIN
    DELETE FROM tb_produtos WHERE tb_fornecedores_for_codigo = for_codigo;

    DELETE FROM tb_fornecedores WHERE for_codigo = for_codigo;

    RAISE NOTICE 'Fornecedor e produtos associados deletados com sucesso.';
EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Erro ao deletar fornecedor e produtos associados. Mensagem: %', SQLERRM;
END;
$$ LANGUAGE plpgsql;

SELECT deletar_fornecedor_e_produtos(1);

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
    SELECT fun_codigo INTO codigo FROM tb_funcionarios WHERE fun_nome = nome;

    -- Se o código foi encontrado, significa que o nome do funcionário já existe
    IF FOUND THEN
        RAISE EXCEPTION 'Erro: Funcionário com nome % já existe. Rollback necessário.', nome;
    END IF;

	SELECT MAX(fun_codigo) INTO id_funcionario FROM tb_funcionarios;
    
	BEGIN
        INSERT INTO tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_senha, fun_funcao) VALUES (id_funcionario + 1, nome, cpf, senha, funcao);
    EXCEPTION
        WHEN unique_violation THEN
            RAISE NOTICE 'Erro de violação de unicidade. Rollback necessário.';
    END;
END;
$$ LANGUAGE plpgsql;

select cadastrar_funcionario('Camila Bregalda', '333.333.333-33', 'senha', 'Gerente');

SELECT * FROM tb_funcionarios;