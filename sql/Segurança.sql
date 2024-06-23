--Segurança

-- Mostrar usuários existentes
SELECT * FROM pg_user; 

-- Mostrar roles existentes
SELECT * FROM  pg_roles;

--Checar quais usuários fazem parte de uma role específica
SELECT pg_roles.rolname AS role_name,
       member.rolname AS member_name
FROM pg_roles
JOIN pg_auth_members ON pg_roles.oid = pg_auth_members.roleid
JOIN pg_roles AS member ON pg_auth_members.member = member.oid
WHERE pg_roles.rolname = 'nãoAguentoMais';

select * from tb_funcionarios
UPDATE tb_funcionarios
SET fun_funcao = 'Gerente'
WHERE fun_codigo = 1;

delete from tb_funcionarios where fun_codigo = 6

select * from tb_vendas

-- Verificando privilégios de um grupo ou usuário
SELECT * --grantee, privilege_type 
FROM information_schema.table_privileges 
WHERE grantee = 'Camila Bregalda' AND table_name = 'tb_funcionarios';

--Criar papeis
CREATE ROLE funcionarios;
CREATE ROLE gerente;

--Usuário pode se cadastrar
--Funcionário pode cadastrar tanto funcionário quanto fornecedor
--Criar alguns usuários (Pontos 0,1)
CREATE OR REPLACE FUNCTION cadastrar_usuario(username TEXT, userPassword TEXT)
RETURNS VOID AS $$
BEGIN
	EXECUTE format('CREATE USER %I WITH LOGIN PASSWORD %L', username, userPassword);
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_usuario('Guilherme Lago', 'root');


--Gerentes concedem privilégios e criam novos grupos
--Podem existir funcionários e gerentes
--Criar alguns grupos(Pontos 0,1)
CREATE OR REPLACE FUNCTION cadastrar_role(new_role TEXT, users TEXT)
RETURNS VOID AS $$
DECLARE
    usuario TEXT;
    user_array TEXT[];
BEGIN
    EXECUTE format('CREATE ROLE %I', new_role);
    
    user_array := string_to_array(users, ',');
	
    FOREACH usuario IN ARRAY user_array
    LOOP
        EXECUTE format('GRANT %I TO %I', new_role, usuario);
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_role('nãoAguentoMais', 'joao,manuel');

DROP FUNCTION cadastrar_role


--(Pontos 0,2) Adicionar os usuários aos grupos.
--Fazer login com diferentes usuários e testar os privilégios cedidos aos grupos e testar privilégios não cedidos aos grupos.
CREATE OR REPLACE FUNCTION atualizar_users_role(name_role TEXT, users TEXT)
RETURNS VOID AS $$
DECLARE
    usuario TEXT;
	user_array TEXT[];
BEGIN
	user_array := string_to_array(users, ',');

	FOREACH usuario IN ARRAY user_array
    LOOP
        EXECUTE format('GRANT %I TO %I', name_role, usuario);
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT atualizar_users_role('nãoAguentoMais', 'novoUsuario3');


--(Pontos 0,1) Conceder diferentes privilégios aos grupos.
CREATE OR REPLACE FUNCTION conceder_privilegio_grupo(name_grupo TEXT, nome_da_tabela TEXT, privilegios TEXT)
RETURNS VOID AS $$
DECLARE
    privilegio TEXT;
BEGIN
    FOREACH privilegio IN ARRAY string_to_array(privilegios, ',')
    LOOP
        IF privilegio = 'SELECT' THEN
            EXECUTE format('GRANT SELECT ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegio = 'INSERT' THEN
            EXECUTE format('GRANT INSERT ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegio = 'DELETE' THEN
            EXECUTE format('GRANT DELETE ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegio = 'UPDATE' THEN
            EXECUTE format('GRANT UPDATE ON %I TO %I', nome_da_tabela, name_grupo);
        ELSE
            RAISE EXCEPTION 'Privilégio desconhecido: %', privilegio;
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT conceder_privilegio_grupo('novoUsuario', 'tb_funcionarios', 'SELECT,INSERT');



--(Pontos 0,8) Conceder um “novo” privilégio a um usuário que já está em algum grupo. Testar
--se o banco cedeu o “novo” privilégio ao usuário.
--(Pontos 0,7) Testar se outro usuário pertencente ao mesmo grupo da questão ‘e’ também
--recebeu o “novo” privilégio.
CREATE OR REPLACE FUNCTION conceder_privilegio_usuario(name_usuario TEXT, nome_da_tabela TEXT, privilegios TEXT)
RETURNS VOID AS $$
DECLARE
    privilegio TEXT;
BEGIN
	FOREACH privilegio IN ARRAY string_to_array(privilegios, ',')
    LOOP
        IF privilegio = 'SELECT' THEN
            EXECUTE format('GRANT SELECT ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegio = 'INSERT' THEN
            EXECUTE format('GRANT INSERT ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegio = 'DELETE' THEN
            EXECUTE format('GRANT DELETE ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegio = 'UPDATE' THEN
            EXECUTE format('GRANT UPDATE ON %I TO %I', nome_da_tabela, name_usuario);
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT conceder_privilegio_usuario('novoUsuario', 'tb_funcionarios', 'SELECT,DELETE');

SELECT grantee, privilege_type 
FROM information_schema.table_privileges 
WHERE grantee = 'novoUsuario3' AND table_name = 'tb_usuario';