--Segurança

-- Mostrar usuários existentes
SELECT * FROM pg_user; 

-- Mostrar roles existentes
SELECT rolname FROM  pg_roles;

--Checar quais usuários fazem parte de uma role específica
SELECT pg_roles.rolname AS role_name,
       member.rolname AS member_name
FROM pg_roles
JOIN pg_auth_members ON pg_roles.oid = pg_auth_members.roleid
JOIN pg_roles AS member ON pg_auth_members.member = member.oid
WHERE pg_roles.rolname = 'newRole';


-- Verificando privilégios de um grupo ou usuário
SELECT * --grantee, privilege_type 
FROM information_schema.table_privileges 
WHERE grantee = 'novoUsuario' AND table_name = 'tb_usuario';

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

SELECT cadastrar_usuario('novoUsuario3', 'root');


--Gerentes concedem privilégios e criam novos grupos
--Podem existir funcionários e gerentes
--Criar alguns grupos(Pontos 0,1)
CREATE OR REPLACE FUNCTION cadastrar_role(new_role TEXT, users TEXT[])
RETURNS VOID AS $$
DECLARE
	cont INTEGER;
	usuario TEXT;
BEGIN
	EXECUTE format('CREATE ROLE %I', new_role);
	
	-- Adicionar cada usuário à role
	FOR i IN 1..array_length(users, 1)
    LOOP
		usuario := users[i];
		EXECUTE format('GRANT %I TO %I', new_role, usuario);
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_role('newRole1', ARRAY['postgres', 'novoUsuario3']);


--(Pontos 0,2) Adicionar os usuários aos grupos.
--Fazer login com diferentes usuários e testar os privilégios cedidos aos grupos e testar privilégios não cedidos aos grupos.
CREATE OR REPLACE FUNCTION atualizar_users_role(name_role TEXT, users TEXT[])
RETURNS VOID AS $$
DECLARE
	cont INTEGER;
BEGIN
	FOR i IN 1..array_length(users, 1)
    LOOP
		EXECUTE format('GRANT %I TO %I', name_role, users[i]);
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT atualizar_users_role('newRole', ARRAY['novoUsuario3']);


--(Pontos 0,1) Conceder diferentes privilégios aos grupos.
CREATE OR REPLACE FUNCTION conceder_privilegio_grupo(name_grupo TEXT, nome_da_tabela TEXT, privilegios TEXT[])
RETURNS VOID AS $$
DECLARE
    i INTEGER;
BEGIN
    -- Loop para passar por todos os privilégios
    FOR i IN 1..array_length(privilegios, 1)
    LOOP
        IF privilegios[i] = 'SELECT' THEN
            EXECUTE format('GRANT SELECT ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegios[i] = 'INSERT' THEN
            EXECUTE format('GRANT INSERT ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegios[i] = 'DELETE' THEN
            EXECUTE format('GRANT DELETE ON %I TO %I', nome_da_tabela, name_grupo);
        ELSIF privilegios[i] = 'UPDATE' THEN
            EXECUTE format('GRANT UPDATE ON %I TO %I', nome_da_tabela, name_grupo);
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT conceder_privilegio_grupo('novoUsuario', 'tb_usuario', ARRAY['SELECT', 'INSERT']);



--(Pontos 0,8) Conceder um “novo” privilégio a um usuário que já está em algum grupo. Testar
--se o banco cedeu o “novo” privilégio ao usuário.
--(Pontos 0,7) Testar se outro usuário pertencente ao mesmo grupo da questão ‘e’ também
--recebeu o “novo” privilégio.
CREATE OR REPLACE FUNCTION conceder_privilegio_usuario(name_usuario TEXT, nome_da_tabela TEXT, privilegios TEXT[])
RETURNS VOID AS $$
DECLARE
    i INTEGER;
BEGIN
    FOR i IN 1..array_length(privilegios, 1) LOOP
		IF privilegios[i] = 'SELECT' THEN
            EXECUTE format('GRANT SELECT ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegios[i] = 'INSERT' THEN
            EXECUTE format('GRANT INSERT ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegios[i] = 'DELETE' THEN
            EXECUTE format('GRANT DELETE ON %I TO %I', nome_da_tabela, name_usuario);
        ELSIF privilegios[i] = 'UPDATE' THEN
            EXECUTE format('GRANT UPDATE ON %I TO %I', nome_da_tabela, name_usuario);
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT conceder_privilegio_usuario('novoUsuario', 'tb_usuario', ARRAY['SELECT']);

SELECT grantee, privilege_type 
FROM information_schema.table_privileges 
WHERE grantee = 'novoUsuario3' AND table_name = 'tb_usuario';