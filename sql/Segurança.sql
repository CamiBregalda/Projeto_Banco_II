--Segurança

--Usuário pode se cadastrar
--Funcionário pode cadastrar tanto funcionário quanto fornecedor
--Criar alguns usuários (Pontos 0,1)
CREATE OR REPLACE FUNCTION cadastrar_usuario(username TEXT, user_password TEXT)
RETURNS VOID AS $$
BEGIN
	CREATE USER username WITH LOGIN PASSWORD user_password;
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_usuario('camila', 'root');


--Gerentes concedem privilégios e criam novos grupos
--Podem existir funcionários e gerentes
--Criar alguns grupos(Pontos 0,1)
CREATE OR REPLACE FUNCTION cadastrar_role(new_role TEXT, users ARRAY[])
RETURNS VOID AS $$
DECLARE
	cont INTEGER;
BEGIN
	CREATE ROLE new_role;
	--adicionar users na role
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_role('camila', 'root');

--(Pontos 0,2) Adicionar os usuários aos grupos.
--Fazer login com diferentes usuários e testar os privilégios cedidos aos grupos e testar privilégios não cedidos aos grupos.
CREATE OR REPLACE FUNCTION atualizar_users_role(name_role TEXT, users ARRAY[])
RETURNS VOID AS $$
DECLARE
	cont INTEGER;
BEGIN
	GRANT name_role to --adicionar users na role;
END;
$$ LANGUAGE plpgsql;

SELECT cadastrar_role('camila', 'root');


--(Pontos 0,1) Conceder diferentes privilégios aos grupos.
CREATE OR REPLACE FUNCTION conceder_privilegio_grupo(name_grupo TEXT, funcoes ARRAY[])
RETURNS VOID AS $$
BEGIN
	--for para passar todas as funções
	IF privilegios[i] == 'SELECT' THEN
		GRANT SELECT ON func TO name_grupo;
	ELSE IF privilegios[i] == 'INSERT' THEN
		GRANT SELECT ON func TO name_grupo;
	ELSE IF privilegios[i] == 'DELETE' THEN
		GRANT DELETE ON func TO name_grupo;
	ELSE IF privilegios[i] == 'UPDATE' THEN
		GRANT UPDATE ON func TO name_grupo;
    END IF;
END;
$$ LANGUAGE plpgsql;


--(Pontos 0,8) Conceder um “novo” privilégio a um usuário que já está em algum grupo. Testar
--se o banco cedeu o “novo” privilégio ao usuário.
--(Pontos 0,7) Testar se outro usuário pertencente ao mesmo grupo da questão ‘e’ também
--recebeu o “novo” privilégio.
CREATE OR REPLACE FUNCTION conceder_privilegio_usuario(username TEXT, funcoes ARRAY[])
RETURNS VOID AS $$
BEGIN
	--for para passar todas as funções
	IF privilegios[i] == 'SELECT' THEN
		GRANT SELECT ON func TO username;
	ELSE IF privilegios[i] == 'INSERT' THEN
		GRANT SELECT ON func TO username;
	ELSE IF privilegios[i] == 'DELETE' THEN
		GRANT DELETE ON func TO username;
	ELSE IF privilegios[i] == 'UPDATE' THEN
		GRANT UPDATE ON func TO username;
    END IF;
END;
$$ LANGUAGE plpgsql;




-- Mostrar usuários existentes
SELECT * FROM pg_user; 

-- #Criar um usuário comum para o Banco de Dados com a capacidade de se logar no BD
CREATE USER joao   WITH LOGIN PASSWORD '123456';
CREATE USER manuel WITH LOGIN PASSWORD '123456';

-- #Criar grupos
CREATE ROLE funcionarios;
CREATE ROLE compradores;
CREATE ROLE fornecedores;

-- Atribuir os usuarios aos grupos
GRANT compradores to joao, manuel;
GRANT fornecedores to joao;

--Definir as permissões sobre a tabela - Quem define é o usuário dono da tabela (Funcionario)
GRANT SELECT ON func TO leitores;
GRANT SELECT, DELETE, UPDATE, INSERT ON func TO escritores;