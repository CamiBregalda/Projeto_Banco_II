--
-- PostgreSQL database dump
--

-- Dumped from database version 14.8
-- Dumped by pg_dump version 14.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


--
-- Name: atualizar_proximo_backup(timestamp without time zone); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualizar_proximo_backup(prox_backup timestamp without time zone) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE backup_programado SET proximo_backup = prox_backup WHERE id = (SELECT MAX(id) FROM backup_programado);
END;
$$;


ALTER FUNCTION public.atualizar_proximo_backup(prox_backup timestamp without time zone) OWNER TO postgres;

--
-- Name: atualizar_proximo_backup(timestamp with time zone); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualizar_proximo_backup(prox_backup timestamp with time zone) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE backup_programado SET proximo_backup = prox_backup WHERE id = (SELECT MAX(id) FROM backup_programado);
END;
$$;


ALTER FUNCTION public.atualizar_proximo_backup(prox_backup timestamp with time zone) OWNER TO postgres;

--
-- Name: atualizar_users_role(text, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualizar_users_role(name_role text, users text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
	cont INTEGER;
BEGIN
	FOR i IN 1..array_length(users, 1)
    LOOP
		EXECUTE format('GRANT %I TO %I', name_role, users[i]);
    END LOOP;
END;
$$;


ALTER FUNCTION public.atualizar_users_role(name_role text, users text[]) OWNER TO postgres;

--
-- Name: cadastrar_funcionario(character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.cadastrar_funcionario(nome character varying, cpf character varying, senha character varying, funcao character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
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
    
    BEGIN
        INSERT INTO tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_senha, fun_funcao)
        VALUES (id_funcionario + 1, nome, cpf, senha, funcao);
    EXCEPTION
        WHEN unique_violation THEN
            RAISE NOTICE 'Erro de violação de unicidade. Rollback necessário.';
    END;
END;
$$;


ALTER FUNCTION public.cadastrar_funcionario(nome character varying, cpf character varying, senha character varying, funcao character varying) OWNER TO postgres;

--
-- Name: cadastrar_role(text, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.cadastrar_role(new_role text, users text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.cadastrar_role(new_role text, users text[]) OWNER TO postgres;

--
-- Name: cadastrar_usuario(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.cadastrar_usuario(username text, userpassword text) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
	EXECUTE format('CREATE USER %I WITH LOGIN PASSWORD %L', username, userPassword);
END;
$$;


ALTER FUNCTION public.cadastrar_usuario(username text, userpassword text) OWNER TO postgres;

--
-- Name: conceder_privilegio_grupo(text, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.conceder_privilegio_grupo(name_grupo text, privilegios text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
    i INTEGER;
BEGIN
    -- Loop para passar por todos os privilégios
    FOR i IN 1..array_length(privilegios, 1)
    LOOP
        IF privilegios[i] = 'SELECT' THEN
            EXECUTE format('GRANT SELECT ON func TO %I', name_grupo);
        ELSIF privilegios[i] = 'INSERT' THEN
            EXECUTE format('GRANT INSERT ON func TO %I', name_grupo);
        ELSIF privilegios[i] = 'DELETE' THEN
            EXECUTE format('GRANT DELETE ON func TO %I', name_grupo);
        ELSIF privilegios[i] = 'UPDATE' THEN
            EXECUTE format('GRANT UPDATE ON func TO %I', name_grupo);
        END IF;
    END LOOP;
END;
$$;


ALTER FUNCTION public.conceder_privilegio_grupo(name_grupo text, privilegios text[]) OWNER TO postgres;

--
-- Name: conceder_privilegio_grupo(text, text, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.conceder_privilegio_grupo(name_grupo text, nome_da_tabela text, privilegios text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
    i INTEGER;
BEGIN
    -- Loop para conceder cada privilégio listado ao grupo
    FOR i IN 1..array_length(privilegios, 1) LOOP
        EXECUTE format('GRANT %I ON TABLE %I TO %I', privilegios[i], nome_da_tabela, name_grupo);
    END LOOP;
END;
$$;


ALTER FUNCTION public.conceder_privilegio_grupo(name_grupo text, nome_da_tabela text, privilegios text[]) OWNER TO postgres;

--
-- Name: conceder_privilegio_usuario(text, text, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.conceder_privilegio_usuario(name_usuario text, nome_da_tabela text, privilegios text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.conceder_privilegio_usuario(name_usuario text, nome_da_tabela text, privilegios text[]) OWNER TO postgres;

--
-- Name: fazer_backup(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.fazer_backup(path text) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    PERFORM pg_notify('backup_event', 'Iniciando backup...');
    PERFORM dblink_exec(
        'dbname=' || current_database(),
        'SELECT pg_catalog.pg_start_backup(''backup'', true);'
    );

    PERFORM dblink_exec(
        'dbname=' || current_database(),
        'SELECT pg_catalog.pg_stop_backup();'
    );

    PERFORM pg_notify('backup_event', 'Backup concluído.');
END;
$$;


ALTER FUNCTION public.fazer_backup(path text) OWNER TO postgres;

--
-- Name: gerar_registros_fornecedores(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.gerar_registros_fornecedores() RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.gerar_registros_fornecedores() OWNER TO postgres;

--
-- Name: gerar_registros_produto(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.gerar_registros_produto() RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.gerar_registros_produto() OWNER TO postgres;

--
-- Name: realizar_backup(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.realizar_backup() RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.realizar_backup() OWNER TO postgres;

--
-- Name: realizar_venda(timestamp without time zone, bigint, bigint, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.realizar_venda(ven_horario timestamp without time zone, fun_codigo bigint, pro_codigo bigint, ite_quantidade integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.realizar_venda(ven_horario timestamp without time zone, fun_codigo bigint, pro_codigo bigint, ite_quantidade integer) OWNER TO postgres;

--
-- Name: realizarvenda(bigint, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.realizarvenda(produto_codigo bigint, quantidade_venda integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION public.realizarvenda(produto_codigo bigint, quantidade_venda integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: backup_programado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.backup_programado (
    id integer NOT NULL,
    ultimo_backup timestamp without time zone NOT NULL,
    proximo_backup timestamp without time zone NOT NULL
);


ALTER TABLE public.backup_programado OWNER TO postgres;

--
-- Name: backup_programado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.backup_programado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.backup_programado_id_seq OWNER TO postgres;

--
-- Name: backup_programado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.backup_programado_id_seq OWNED BY public.backup_programado.id;


--
-- Name: tb_fornecedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_fornecedores (
    for_codigo bigint NOT NULL,
    for_descricao character varying(45) NOT NULL
);


ALTER TABLE public.tb_fornecedores OWNER TO postgres;

--
-- Name: tb_funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_funcionarios (
    fun_codigo bigint NOT NULL,
    fun_nome character varying(30),
    fun_cpf character varying(15),
    fun_senha character varying(11),
    fun_funcao character varying(20)
);


ALTER TABLE public.tb_funcionarios OWNER TO postgres;

--
-- Name: tb_itens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_itens (
    ite_codigo bigint NOT NULL,
    ite_quantidade integer,
    ite_valor_parcial numeric(7,2),
    tb_produtos_pro_codigo bigint,
    tb_vendas_ven_codigo bigint
);


ALTER TABLE public.tb_itens OWNER TO postgres;

--
-- Name: tb_produtos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_produtos (
    pro_codigo bigint NOT NULL,
    pro_descricao character varying(45),
    pro_valor numeric(7,2),
    pro_quantidade integer,
    tb_fornecedores_for_codigo bigint
);


ALTER TABLE public.tb_produtos OWNER TO postgres;

--
-- Name: tb_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_usuario (
    user_codigo bigint NOT NULL,
    user_nome character varying(30),
    user_cpf character varying(15),
    user_username character varying(20),
    user_senha character varying(11)
);


ALTER TABLE public.tb_usuario OWNER TO postgres;

--
-- Name: tb_vendas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_vendas (
    ven_codigo bigint NOT NULL,
    ven_horario timestamp without time zone,
    ven_valor_total numeric(7,2),
    tb_funcionarios_fun_codigo bigint
);


ALTER TABLE public.tb_vendas OWNER TO postgres;

--
-- Name: backup_programado id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.backup_programado ALTER COLUMN id SET DEFAULT nextval('public.backup_programado_id_seq'::regclass);


--
-- Data for Name: backup_programado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.backup_programado (id, ultimo_backup, proximo_backup) FROM stdin;
2	2024-06-21 11:08:50.227029	2024-06-28 11:08:50.227029
3	2024-06-21 11:09:41.044331	2024-06-21 11:14:56.6291
4	2024-06-21 11:15:24.783888	2024-06-21 11:20:40.368657
5	2024-06-22 13:45:36.946459	2024-06-22 13:50:52.531228
6	2024-06-22 13:48:42.390586	2024-06-22 13:53:57.975355
7	2024-06-22 14:27:30.532836	2024-06-22 14:32:46.117605
\.


--
-- Data for Name: tb_fornecedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_fornecedores (for_codigo, for_descricao) FROM stdin;
1	Colombo
2	Eletrolux
3	Teste
4	Casas Bahia
5	Lojas Americanas
6	Carrefour
7	Lojas Becker
8	Ricardo Eletro
9	Ponto Frio
10	Extra
11	Fast Shop
12	Submarino
13	Walmart
14	Eletrosom
\.


--
-- Data for Name: tb_funcionarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_senha, fun_funcao) FROM stdin;
2	Camila Bregalda	33333333333	senha	Gerente
3	Paulo Silveira	323232323	senha	Vendedor
4	Pedro Nunes	323232323	senha	Vendedor
1	Franscisco Aguiar	12345678	senha	Vendedor
5	postgres	12345678	root	Gerente
6	postgres	12345678	root	Vendedor
\.


--
-- Data for Name: tb_itens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo) FROM stdin;
1	10	2310.00	1	1
2	10	2310.00	1	2
3	10	2310.00	1	3
4	20	4620.00	1	4
\.


--
-- Data for Name: tb_produtos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo) FROM stdin;
2	Purificador de água	241.00	81	3
3	Panela elétrica de arroz	593.00	19	2
4	Forno elétrico	500.00	85	2
5	Fogão	816.00	89	5
6	Máquina de lavar louça	462.00	91	8
7	Aquecedor elétrico	116.00	34	12
8	Geladeira uma porta	964.00	49	12
9	Forno elétrico	1035.00	32	1
10	Exaustor de cozinha	930.00	7	10
11	Aspirador de pó	371.00	65	1
12	Máquina de lavar roupa	653.00	34	13
13	Panela elétrica de arroz	475.00	20	12
14	Ventilador	928.00	68	7
15	Purificador de água	823.00	92	7
16	Fogão	149.00	41	9
17	Ventilador	643.00	64	11
18	Ferro de passar roupa	712.00	66	11
19	Micro-ondas	700.00	97	3
20	Ferro de passar roupa	416.00	11	1
21	Torradeira	373.00	62	12
22	Geladeira duas portas	794.00	26	7
23	Aspirador de pó	166.00	75	13
24	Grill elétrico	1014.00	6	4
25	Grill elétrico	1003.00	41	3
26	Ferro de passar roupa	284.00	41	12
27	Exaustor de cozinha	1059.00	78	1
28	Geladeira duas portas	723.00	24	13
29	Forno elétrico	272.00	57	10
30	Máquina de lavar louça	1094.00	55	11
31	Forno elétrico	1018.00	96	2
32	Ventilador	1090.00	96	12
33	Purificador de água	756.00	60	9
34	Geladeira duas portas	450.00	17	8
35	Máquina de secar roupa	498.00	5	7
36	Grill elétrico	448.00	4	8
37	Grill elétrico	327.00	25	9
38	Exaustor de cozinha	874.00	46	4
39	Micro-ondas	181.00	25	1
40	Panela elétrica de arroz	920.00	28	11
41	Máquina de lavar louça	454.00	28	7
42	Forno elétrico	566.00	19	10
43	Máquina de secar roupa	360.00	33	2
44	Máquina de lavar louça	436.00	82	2
45	Panela elétrica de arroz	267.00	49	4
46	Ventilador	221.00	22	3
47	Liquidificador	912.00	37	11
48	Purificador de água	256.00	87	3
49	Geladeira uma porta	371.00	82	11
50	Geladeira uma porta	504.00	32	7
51	Freezer uma porta	320.00	27	6
52	Aquecedor elétrico	1043.00	51	11
53	Ar condicionado	132.00	77	6
54	Ferro de passar roupa	253.00	43	3
55	Máquina de lavar louça	905.00	50	3
56	Máquina de secar roupa	436.00	67	10
57	Micro-ondas	783.00	64	5
58	Exaustor de cozinha	897.00	23	10
59	Cafeteira	746.00	48	3
60	Aspirador de pó	562.00	58	12
61	Aquecedor elétrico	725.00	81	13
62	Purificador de água	898.00	23	3
63	Micro-ondas	562.00	49	10
64	Liquidificador	906.00	31	7
65	Máquina de lavar roupa	574.00	77	6
66	Ar condicionado	376.00	78	4
67	Forno elétrico	986.00	20	9
68	Máquina de lavar roupa	419.00	27	7
69	Geladeira duas portas	996.00	71	12
70	Micro-ondas	643.00	18	3
71	Freezer uma porta	290.00	52	6
72	Ventilador	383.00	64	10
73	Liquidificador	517.00	57	13
74	Máquina de lavar louça	328.00	81	12
75	Geladeira duas portas	470.00	2	12
76	Máquina de lavar louça	936.00	36	3
77	Ferro de passar roupa	291.00	78	11
78	Exaustor de cozinha	792.00	52	7
79	Freezer uma porta	415.00	16	3
80	Panela elétrica de arroz	871.00	23	7
81	Ar condicionado	768.00	28	12
82	Cafeteira	1048.00	71	7
83	Torradeira	568.00	85	8
84	Liquidificador	530.00	1	7
85	Freezer uma porta	127.00	54	6
86	Panela elétrica de arroz	951.00	89	10
87	Liquidificador	465.00	88	3
88	Máquina de lavar louça	295.00	81	7
89	Exaustor de cozinha	750.00	77	10
90	Ventilador	945.00	36	7
91	Máquina de lavar louça	768.00	46	5
92	Aquecedor elétrico	962.00	24	10
93	Máquina de lavar roupa	116.00	92	9
94	Máquina de lavar louça	274.00	67	4
95	Aspirador de pó	119.00	23	5
96	Máquina de lavar louça	855.00	45	2
97	Liquidificador	1076.00	96	8
98	Torradeira	774.00	5	12
99	Purificador de água	1029.00	81	7
100	Ferro de passar roupa	629.00	42	10
101	Máquina de secar roupa	384.00	65	1
102	Máquina de lavar louça	180.00	9	3
103	Geladeira duas portas	1215.00	75	7
104	Cafeteira	311.00	77	9
105	Micro-ondas	413.00	52	10
106	Torradeira	362.00	7	1
107	Freezer uma porta	597.00	85	6
108	Panela elétrica de arroz	638.00	34	11
109	Máquina de lavar louça	124.00	92	3
110	Panela elétrica de arroz	170.00	33	12
111	Liquidificador	768.00	95	11
112	Grill elétrico	1023.00	19	6
113	Liquidificador	105.00	41	6
114	Máquina de lavar roupa	519.00	4	2
115	Aquecedor elétrico	756.00	13	1
116	Torradeira	630.00	89	1
117	Máquina de secar roupa	973.00	91	13
118	Exaustor de cozinha	515.00	17	7
119	Grill elétrico	564.00	36	3
120	Torradeira	360.00	91	6
121	Torradeira	566.00	59	12
122	Purificador de água	551.00	98	2
123	Micro-ondas	555.00	38	13
124	Grill elétrico	365.00	89	1
125	Máquina de lavar louça	136.00	35	6
126	Geladeira duas portas	1040.00	94	6
127	Ar condicionado	408.00	68	13
128	Aspirador de pó	952.00	16	8
129	Freezer uma porta	831.00	23	6
130	Grill elétrico	1093.00	31	10
131	Máquina de lavar roupa	877.00	44	3
132	Máquina de secar roupa	163.00	16	13
133	Máquina de secar roupa	417.00	65	5
134	Geladeira uma porta	1055.00	63	13
135	Ferro de passar roupa	1040.00	56	7
136	Liquidificador	295.00	22	1
137	Máquina de secar roupa	648.00	64	6
138	Micro-ondas	963.00	39	1
139	Ventilador	124.00	19	13
140	Exaustor de cozinha	904.00	30	12
141	Ferro de passar roupa	380.00	14	13
142	Ventilador	585.00	45	4
143	Geladeira uma porta	522.00	14	9
144	Máquina de secar roupa	503.00	6	10
145	Ventilador	1041.00	33	12
146	Grill elétrico	755.00	31	10
147	Panela elétrica de arroz	205.00	40	12
148	Ferro de passar roupa	225.00	93	11
149	Máquina de lavar roupa	653.00	43	6
150	Ventilador	695.00	22	3
151	Ventilador	532.00	34	2
152	Purificador de água	440.00	87	3
153	Aquecedor elétrico	995.00	24	3
154	Máquina de lavar louça	812.00	2	7
155	Aquecedor elétrico	165.00	67	2
156	Exaustor de cozinha	646.00	79	3
157	Grill elétrico	516.00	26	4
158	Aspirador de pó	483.00	65	11
159	Ferro de passar roupa	391.00	34	5
160	Ar condicionado	605.00	28	13
161	Forno elétrico	152.00	65	9
162	Aquecedor elétrico	772.00	29	6
163	Exaustor de cozinha	741.00	80	7
164	Torradeira	405.00	66	10
165	Ferro de passar roupa	1054.00	84	13
166	Geladeira duas portas	832.00	38	13
167	Torradeira	829.00	88	2
168	Máquina de secar roupa	719.00	26	9
169	Máquina de lavar roupa	773.00	53	11
170	Cafeteira	154.00	79	13
171	Fogão	135.00	72	7
172	Exaustor de cozinha	361.00	20	13
173	Freezer duas portas	985.00	35	8
174	Liquidificador	428.00	44	7
175	Ar condicionado	920.00	44	11
176	Ferro de passar roupa	316.00	48	4
177	Grill elétrico	226.00	62	12
178	Máquina de secar roupa	858.00	39	7
179	Máquina de secar roupa	869.00	56	4
180	Panela elétrica de arroz	1089.00	15	3
181	Ar condicionado	636.00	41	3
182	Liquidificador	749.00	15	1
183	Ar condicionado	176.00	14	1
184	Ventilador	730.00	60	7
185	Micro-ondas	304.00	79	10
186	Aspirador de pó	1015.00	72	4
187	Aquecedor elétrico	420.00	5	1
188	Forno elétrico	940.00	64	8
189	Liquidificador	588.00	97	11
190	Micro-ondas	941.00	89	7
191	Ar condicionado	615.00	89	7
192	Ventilador	496.00	24	8
193	Máquina de secar roupa	966.00	40	1
194	Ventilador	605.00	24	7
195	Exaustor de cozinha	433.00	55	12
196	Ferro de passar roupa	997.00	46	1
197	Geladeira uma porta	299.00	15	6
198	Fogão	625.00	36	12
199	Geladeira duas portas	1088.00	46	10
200	Aquecedor elétrico	165.00	49	5
201	Torradeira	428.00	86	2
202	Forno elétrico	507.00	11	1
203	Grill elétrico	348.00	31	12
204	Ventilador	1053.00	80	8
205	Grill elétrico	458.00	49	12
206	Máquina de secar roupa	155.00	67	13
207	Panela elétrica de arroz	434.00	38	9
208	Panela elétrica de arroz	782.00	29	8
209	Geladeira uma porta	1061.00	46	4
210	Grill elétrico	756.00	69	7
211	Panela elétrica de arroz	177.00	81	6
212	Máquina de lavar roupa	653.00	64	13
213	Aspirador de pó	188.00	36	6
214	Panela elétrica de arroz	517.00	61	10
215	Purificador de água	348.00	38	11
216	Panela elétrica de arroz	609.00	66	12
217	Ferro de passar roupa	972.00	28	9
218	Cafeteira	402.00	9	11
219	Fogão	529.00	45	12
220	Purificador de água	985.00	48	12
221	Liquidificador	286.00	49	9
222	Aquecedor elétrico	251.00	94	2
223	Freezer duas portas	1258.00	97	13
224	Aquecedor elétrico	258.00	58	9
225	Aquecedor elétrico	983.00	86	10
226	Micro-ondas	470.00	66	3
227	Ferro de passar roupa	712.00	73	4
228	Purificador de água	652.00	46	8
229	Geladeira uma porta	366.00	56	3
230	Aspirador de pó	905.00	10	4
231	Cafeteira	867.00	66	10
232	Exaustor de cozinha	698.00	11	13
233	Ferro de passar roupa	864.00	94	6
234	Torradeira	999.00	77	11
235	Máquina de lavar roupa	244.00	83	5
236	Exaustor de cozinha	823.00	34	13
237	Liquidificador	197.00	95	7
238	Fogão	167.00	75	8
239	Forno elétrico	592.00	19	2
240	Ventilador	844.00	3	3
241	Forno elétrico	462.00	7	9
242	Ferro de passar roupa	229.00	95	12
243	Freezer uma porta	803.00	16	4
244	Geladeira duas portas	770.00	71	2
245	Cafeteira	880.00	96	6
246	Aspirador de pó	891.00	20	10
247	Freezer uma porta	266.00	51	3
248	Máquina de secar roupa	922.00	11	8
249	Panela elétrica de arroz	551.00	34	2
250	Ferro de passar roupa	896.00	76	11
251	Ventilador	202.00	65	7
252	Purificador de água	288.00	51	12
253	Grill elétrico	205.00	23	1
254	Máquina de lavar louça	892.00	35	12
255	Geladeira duas portas	428.00	13	12
256	Purificador de água	817.00	11	3
257	Aquecedor elétrico	450.00	9	7
258	Ventilador	195.00	23	10
259	Aspirador de pó	684.00	14	4
260	Grill elétrico	1028.00	97	6
261	Torradeira	396.00	77	11
262	Ferro de passar roupa	720.00	3	2
263	Exaustor de cozinha	755.00	95	5
264	Forno elétrico	718.00	48	13
265	Ventilador	505.00	74	6
266	Panela elétrica de arroz	402.00	10	11
267	Geladeira duas portas	708.00	81	6
268	Grill elétrico	1085.00	36	9
269	Forno elétrico	654.00	63	1
270	Geladeira duas portas	949.00	54	4
271	Exaustor de cozinha	717.00	40	6
272	Máquina de lavar louça	390.00	25	5
273	Aspirador de pó	623.00	68	5
274	Aspirador de pó	613.00	74	13
275	Liquidificador	1039.00	46	2
276	Forno elétrico	605.00	61	9
277	Fogão	238.00	30	13
278	Purificador de água	374.00	26	5
279	Torradeira	605.00	73	12
280	Panela elétrica de arroz	885.00	16	2
281	Purificador de água	648.00	87	13
282	Forno elétrico	1043.00	97	8
283	Ar condicionado	161.00	75	8
284	Geladeira duas portas	428.00	6	7
285	Geladeira uma porta	428.00	50	12
286	Cafeteira	859.00	99	11
287	Forno elétrico	441.00	67	1
288	Torradeira	642.00	1	4
289	Ventilador	223.00	86	13
290	Ar condicionado	513.00	80	13
291	Exaustor de cozinha	733.00	81	12
292	Forno elétrico	243.00	100	2
293	Aspirador de pó	117.00	98	12
294	Ferro de passar roupa	996.00	61	4
295	Ferro de passar roupa	432.00	48	12
296	Purificador de água	1012.00	95	2
297	Liquidificador	345.00	66	13
298	Panela elétrica de arroz	156.00	69	6
299	Forno elétrico	140.00	75	9
300	Geladeira uma porta	125.00	59	4
301	Micro-ondas	687.00	73	13
302	Liquidificador	426.00	94	4
303	Torradeira	311.00	87	9
304	Forno elétrico	545.00	29	8
305	Exaustor de cozinha	696.00	95	6
306	Geladeira uma porta	788.00	63	4
307	Ferro de passar roupa	725.00	39	9
308	Ventilador	957.00	93	3
309	Micro-ondas	444.00	10	6
310	Fogão	726.00	71	13
311	Panela elétrica de arroz	672.00	81	13
312	Máquina de lavar roupa	886.00	52	8
313	Aquecedor elétrico	1084.00	50	2
314	Aspirador de pó	1026.00	98	12
315	Fogão	1066.00	46	6
316	Purificador de água	450.00	98	11
317	Aquecedor elétrico	642.00	97	3
318	Ventilador	323.00	17	4
319	Grill elétrico	1048.00	71	7
320	Cafeteira	755.00	44	10
321	Ar condicionado	497.00	58	2
322	Aspirador de pó	489.00	21	13
323	Freezer duas portas	557.00	77	13
324	Cafeteira	907.00	36	12
325	Purificador de água	299.00	35	10
326	Micro-ondas	370.00	5	1
327	Freezer uma porta	255.00	23	2
328	Aspirador de pó	630.00	40	4
329	Máquina de secar roupa	349.00	2	12
330	Forno elétrico	132.00	40	7
331	Aquecedor elétrico	811.00	36	2
332	Forno elétrico	474.00	11	9
333	Purificador de água	446.00	100	13
334	Torradeira	809.00	7	6
335	Aquecedor elétrico	502.00	19	8
336	Máquina de secar roupa	273.00	7	4
337	Grill elétrico	176.00	99	10
338	Aspirador de pó	381.00	34	4
339	Freezer uma porta	685.00	9	8
340	Freezer duas portas	832.00	17	2
341	Freezer uma porta	586.00	74	2
342	Freezer duas portas	349.00	18	1
343	Máquina de lavar roupa	330.00	68	3
344	Fogão	848.00	62	7
345	Aspirador de pó	1072.00	96	3
346	Purificador de água	713.00	2	12
347	Purificador de água	606.00	62	11
348	Geladeira uma porta	346.00	44	3
349	Forno elétrico	1023.00	24	9
350	Aquecedor elétrico	284.00	22	7
351	Exaustor de cozinha	725.00	87	1
352	Fogão	154.00	84	10
353	Fogão	286.00	72	6
354	Máquina de secar roupa	635.00	54	4
355	Aspirador de pó	819.00	58	10
356	Exaustor de cozinha	577.00	82	9
357	Máquina de lavar louça	947.00	64	13
358	Forno elétrico	289.00	27	11
359	Purificador de água	148.00	36	9
360	Fogão	467.00	70	3
361	Geladeira duas portas	951.00	80	4
362	Torradeira	748.00	93	8
363	Purificador de água	1066.00	40	7
364	Micro-ondas	194.00	20	7
365	Grill elétrico	867.00	53	1
366	Ventilador	616.00	15	9
367	Aspirador de pó	1068.00	12	12
368	Máquina de lavar louça	743.00	45	3
369	Purificador de água	453.00	37	2
370	Torradeira	291.00	5	10
371	Cafeteira	700.00	31	12
372	Exaustor de cozinha	412.00	64	1
373	Aquecedor elétrico	620.00	72	6
374	Grill elétrico	452.00	41	1
375	Ferro de passar roupa	613.00	51	1
376	Fogão	422.00	80	3
377	Máquina de lavar louça	672.00	38	2
378	Fogão	1080.00	98	12
379	Liquidificador	128.00	49	10
380	Purificador de água	836.00	82	4
381	Forno elétrico	332.00	57	11
382	Liquidificador	831.00	39	9
383	Panela elétrica de arroz	910.00	27	4
384	Panela elétrica de arroz	347.00	96	8
385	Panela elétrica de arroz	796.00	7	1
386	Máquina de secar roupa	426.00	33	10
387	Purificador de água	227.00	40	1
388	Ferro de passar roupa	982.00	94	9
389	Panela elétrica de arroz	1051.00	1	7
390	Aquecedor elétrico	907.00	55	2
391	Máquina de lavar roupa	206.00	22	6
392	Micro-ondas	692.00	67	11
393	Freezer uma porta	983.00	83	5
394	Purificador de água	280.00	46	8
395	Freezer duas portas	1260.00	71	1
396	Aquecedor elétrico	180.00	77	13
397	Ar condicionado	186.00	80	12
398	Aspirador de pó	328.00	31	12
399	Liquidificador	134.00	5	6
400	Micro-ondas	854.00	4	8
401	Aspirador de pó	1068.00	98	4
402	Máquina de secar roupa	504.00	18	5
403	Geladeira duas portas	1115.00	61	13
404	Cafeteira	794.00	6	13
405	Exaustor de cozinha	199.00	4	10
406	Grill elétrico	1051.00	43	7
407	Ventilador	883.00	78	1
408	Ar condicionado	1084.00	67	11
409	Máquina de lavar louça	279.00	81	12
410	Geladeira duas portas	969.00	34	12
411	Exaustor de cozinha	1031.00	46	3
412	Liquidificador	175.00	73	11
413	Fogão	528.00	62	7
414	Grill elétrico	148.00	91	11
415	Máquina de lavar louça	495.00	28	11
416	Aquecedor elétrico	778.00	59	8
417	Máquina de lavar roupa	973.00	56	7
418	Ar condicionado	1061.00	52	6
419	Geladeira duas portas	508.00	33	1
420	Máquina de lavar roupa	202.00	32	13
421	Grill elétrico	339.00	82	13
422	Aquecedor elétrico	426.00	58	8
423	Fogão	707.00	48	12
424	Micro-ondas	849.00	95	13
425	Exaustor de cozinha	720.00	85	11
426	Aquecedor elétrico	574.00	100	11
427	Máquina de lavar louça	165.00	3	10
428	Aspirador de pó	243.00	55	1
429	Ferro de passar roupa	106.00	10	12
430	Máquina de lavar roupa	501.00	8	1
431	Liquidificador	895.00	10	4
432	Exaustor de cozinha	638.00	59	1
433	Máquina de lavar louça	702.00	4	5
434	Ventilador	915.00	94	3
435	Geladeira duas portas	1008.00	94	10
436	Torradeira	138.00	22	4
437	Geladeira duas portas	573.00	84	8
438	Ferro de passar roupa	148.00	22	8
439	Cafeteira	461.00	67	13
440	Aquecedor elétrico	1083.00	80	5
441	Cafeteira	133.00	93	10
442	Máquina de secar roupa	889.00	39	8
443	Fogão	773.00	51	10
444	Grill elétrico	1065.00	29	11
445	Liquidificador	430.00	57	1
446	Máquina de lavar roupa	241.00	14	4
447	Ar condicionado	705.00	95	7
448	Ar condicionado	754.00	80	13
449	Freezer duas portas	613.00	14	13
450	Aspirador de pó	632.00	42	5
451	Panela elétrica de arroz	279.00	9	7
452	Ventilador	698.00	3	11
453	Máquina de secar roupa	176.00	93	7
454	Panela elétrica de arroz	206.00	52	13
455	Grill elétrico	660.00	18	3
456	Purificador de água	271.00	100	1
457	Grill elétrico	932.00	88	13
458	Torradeira	712.00	91	11
459	Ar condicionado	425.00	61	1
460	Cafeteira	1098.00	97	3
461	Máquina de secar roupa	213.00	30	6
462	Panela elétrica de arroz	962.00	47	9
463	Purificador de água	596.00	74	5
464	Ventilador	983.00	60	4
465	Freezer duas portas	772.00	77	1
466	Torradeira	920.00	21	12
467	Máquina de secar roupa	657.00	16	6
468	Ferro de passar roupa	587.00	9	11
469	Ferro de passar roupa	908.00	61	5
470	Máquina de lavar louça	216.00	15	9
471	Máquina de lavar roupa	1020.00	89	4
472	Aquecedor elétrico	484.00	45	13
473	Exaustor de cozinha	567.00	63	3
474	Cafeteira	697.00	58	8
475	Freezer uma porta	704.00	84	5
476	Grill elétrico	370.00	59	5
477	Forno elétrico	605.00	44	10
478	Torradeira	168.00	15	5
479	Panela elétrica de arroz	340.00	30	7
480	Ferro de passar roupa	645.00	75	1
481	Geladeira uma porta	989.00	22	12
482	Máquina de lavar louça	511.00	92	1
483	Máquina de secar roupa	489.00	44	13
484	Máquina de secar roupa	355.00	7	9
485	Geladeira duas portas	382.00	68	8
486	Panela elétrica de arroz	124.00	22	12
487	Micro-ondas	230.00	14	5
488	Ar condicionado	497.00	21	11
489	Micro-ondas	228.00	94	5
490	Aquecedor elétrico	997.00	75	8
491	Exaustor de cozinha	236.00	51	12
492	Aspirador de pó	893.00	69	6
493	Ar condicionado	729.00	10	9
494	Cafeteira	812.00	23	8
495	Liquidificador	140.00	54	8
496	Aquecedor elétrico	616.00	59	5
497	Aquecedor elétrico	624.00	2	2
498	Ventilador	1048.00	35	12
499	Aspirador de pó	315.00	9	1
500	Aquecedor elétrico	1036.00	93	13
501	Aspirador de pó	522.00	47	8
502	Freezer uma porta	1067.00	55	13
503	Ventilador	139.00	69	5
504	Purificador de água	830.00	44	4
505	Máquina de lavar louça	907.00	26	10
506	Ar condicionado	115.00	59	5
507	Exaustor de cozinha	692.00	38	3
508	Grill elétrico	994.00	23	9
509	Fogão	249.00	34	11
510	Máquina de secar roupa	102.00	2	8
511	Ferro de passar roupa	632.00	79	1
512	Geladeira duas portas	1195.00	63	7
513	Exaustor de cozinha	470.00	4	3
514	Cafeteira	376.00	49	4
515	Aquecedor elétrico	881.00	82	11
516	Fogão	671.00	41	11
517	Máquina de lavar roupa	532.00	60	8
518	Fogão	400.00	80	7
519	Freezer duas portas	306.00	47	13
520	Freezer uma porta	714.00	30	9
521	Purificador de água	812.00	45	12
522	Fogão	1040.00	1	2
523	Máquina de lavar roupa	1010.00	40	6
524	Panela elétrica de arroz	1072.00	45	7
525	Máquina de lavar roupa	572.00	68	7
526	Purificador de água	697.00	44	8
527	Exaustor de cozinha	845.00	81	9
528	Geladeira uma porta	347.00	34	4
529	Geladeira uma porta	1047.00	45	12
530	Máquina de secar roupa	797.00	44	9
531	Ventilador	313.00	70	9
532	Panela elétrica de arroz	241.00	62	10
533	Aspirador de pó	101.00	76	2
534	Aquecedor elétrico	837.00	6	12
535	Forno elétrico	529.00	53	10
536	Ar condicionado	633.00	86	11
537	Máquina de lavar louça	1036.00	23	10
538	Aquecedor elétrico	967.00	56	9
539	Purificador de água	424.00	22	2
540	Ar condicionado	982.00	13	12
541	Purificador de água	847.00	5	12
542	Máquina de lavar roupa	427.00	10	6
543	Forno elétrico	288.00	91	2
544	Panela elétrica de arroz	878.00	73	5
545	Micro-ondas	1002.00	27	7
546	Purificador de água	580.00	91	12
547	Exaustor de cozinha	396.00	22	6
548	Ventilador	242.00	86	7
549	Forno elétrico	245.00	94	12
550	Aspirador de pó	211.00	94	5
551	Máquina de lavar louça	249.00	26	13
552	Micro-ondas	658.00	17	10
553	Panela elétrica de arroz	420.00	89	8
554	Forno elétrico	560.00	38	8
555	Máquina de lavar louça	910.00	38	8
556	Exaustor de cozinha	540.00	51	4
557	Aspirador de pó	517.00	23	5
558	Freezer duas portas	1039.00	20	11
559	Fogão	887.00	65	9
560	Panela elétrica de arroz	956.00	46	8
561	Purificador de água	149.00	90	7
562	Fogão	226.00	6	2
563	Grill elétrico	151.00	38	2
564	Torradeira	386.00	5	11
565	Ferro de passar roupa	295.00	14	8
566	Freezer duas portas	1057.00	33	5
567	Fogão	1002.00	70	2
568	Cafeteira	149.00	91	3
569	Máquina de secar roupa	963.00	61	11
570	Ar condicionado	1047.00	89	4
571	Ar condicionado	997.00	55	9
572	Exaustor de cozinha	760.00	56	3
573	Panela elétrica de arroz	1006.00	17	4
574	Panela elétrica de arroz	337.00	92	13
575	Cafeteira	131.00	59	12
576	Máquina de secar roupa	403.00	29	1
577	Fogão	984.00	63	2
578	Purificador de água	919.00	48	3
579	Máquina de secar roupa	996.00	29	4
580	Torradeira	263.00	47	9
581	Máquina de lavar louça	764.00	41	9
582	Torradeira	376.00	9	13
583	Ventilador	859.00	19	12
584	Exaustor de cozinha	1066.00	83	10
585	Freezer uma porta	881.00	50	3
586	Liquidificador	702.00	98	3
587	Ferro de passar roupa	1037.00	4	7
588	Liquidificador	468.00	50	4
589	Torradeira	415.00	91	7
590	Aquecedor elétrico	552.00	62	4
591	Geladeira uma porta	422.00	88	6
592	Ventilador	430.00	86	3
593	Máquina de lavar louça	568.00	78	9
594	Máquina de secar roupa	348.00	72	5
595	Ferro de passar roupa	1063.00	68	11
596	Máquina de lavar louça	718.00	38	6
597	Aquecedor elétrico	397.00	99	7
598	Aspirador de pó	480.00	19	11
599	Grill elétrico	439.00	36	9
600	Liquidificador	533.00	41	1
601	Exaustor de cozinha	635.00	34	5
602	Micro-ondas	320.00	39	1
603	Ar condicionado	859.00	67	9
604	Panela elétrica de arroz	137.00	45	4
605	Exaustor de cozinha	358.00	4	10
606	Micro-ondas	521.00	63	11
607	Aquecedor elétrico	709.00	11	1
608	Geladeira uma porta	465.00	6	6
609	Ar condicionado	906.00	48	3
610	Liquidificador	980.00	100	1
611	Ventilador	103.00	87	8
612	Ar condicionado	1067.00	62	6
613	Ventilador	252.00	46	3
614	Cafeteira	939.00	22	12
615	Torradeira	638.00	40	8
616	Freezer uma porta	426.00	1	6
617	Micro-ondas	765.00	70	6
618	Aspirador de pó	756.00	25	10
619	Exaustor de cozinha	807.00	61	1
620	Micro-ondas	701.00	46	10
621	Panela elétrica de arroz	521.00	34	7
622	Freezer uma porta	310.00	79	9
623	Máquina de lavar louça	433.00	34	8
624	Ar condicionado	492.00	32	4
625	Panela elétrica de arroz	675.00	43	13
626	Fogão	480.00	13	1
627	Máquina de lavar louça	755.00	74	3
628	Ar condicionado	1042.00	26	12
629	Aspirador de pó	588.00	36	3
630	Ferro de passar roupa	1043.00	5	2
631	Cafeteira	542.00	26	12
632	Ventilador	492.00	90	10
633	Máquina de secar roupa	522.00	73	10
634	Grill elétrico	460.00	86	5
635	Torradeira	1032.00	19	2
636	Ferro de passar roupa	1058.00	14	11
637	Ferro de passar roupa	288.00	10	1
638	Fogão	438.00	23	8
639	Forno elétrico	217.00	9	12
640	Aspirador de pó	205.00	14	5
641	Máquina de lavar louça	580.00	63	12
642	Torradeira	224.00	65	9
643	Ferro de passar roupa	520.00	44	7
644	Geladeira uma porta	601.00	30	10
645	Cafeteira	885.00	95	3
646	Cafeteira	316.00	12	8
647	Máquina de secar roupa	504.00	64	11
648	Torradeira	153.00	54	5
649	Freezer duas portas	936.00	33	7
650	Aspirador de pó	579.00	10	2
651	Torradeira	948.00	31	3
652	Máquina de lavar louça	478.00	6	11
653	Ar condicionado	1037.00	97	4
654	Torradeira	828.00	96	13
655	Ar condicionado	139.00	25	10
656	Cafeteira	604.00	83	4
657	Máquina de lavar roupa	565.00	13	13
658	Máquina de lavar louça	884.00	3	11
659	Geladeira uma porta	146.00	60	2
660	Aspirador de pó	690.00	66	8
661	Torradeira	985.00	94	11
662	Cafeteira	1082.00	100	1
663	Cafeteira	1052.00	70	1
664	Ventilador	806.00	43	2
665	Ar condicionado	329.00	72	11
666	Aspirador de pó	1019.00	40	7
667	Aspirador de pó	174.00	51	5
668	Geladeira duas portas	504.00	68	9
669	Purificador de água	679.00	62	8
670	Forno elétrico	385.00	25	12
671	Máquina de lavar louça	581.00	67	11
672	Forno elétrico	700.00	31	8
673	Máquina de lavar roupa	405.00	57	2
674	Exaustor de cozinha	423.00	97	12
675	Grill elétrico	796.00	59	8
676	Máquina de lavar louça	985.00	15	7
677	Liquidificador	161.00	72	2
678	Ar condicionado	1057.00	2	7
679	Fogão	284.00	8	1
680	Ar condicionado	245.00	44	2
681	Grill elétrico	274.00	72	11
682	Ventilador	291.00	19	1
683	Micro-ondas	673.00	65	3
684	Máquina de secar roupa	909.00	84	2
685	Fogão	608.00	38	12
686	Aquecedor elétrico	896.00	100	4
687	Exaustor de cozinha	1044.00	18	4
688	Geladeira uma porta	323.00	66	10
689	Torradeira	352.00	32	2
690	Micro-ondas	410.00	14	6
691	Ferro de passar roupa	704.00	64	13
692	Panela elétrica de arroz	662.00	69	13
693	Panela elétrica de arroz	684.00	43	13
694	Máquina de secar roupa	101.00	44	11
695	Máquina de lavar louça	1061.00	31	3
696	Máquina de lavar roupa	354.00	92	8
697	Cafeteira	240.00	18	12
698	Aquecedor elétrico	205.00	82	11
699	Fogão	679.00	11	13
700	Torradeira	1022.00	34	10
701	Geladeira duas portas	1273.00	36	5
702	Ferro de passar roupa	1081.00	82	9
703	Aspirador de pó	462.00	47	8
704	Ferro de passar roupa	191.00	46	7
705	Fogão	933.00	29	11
706	Exaustor de cozinha	621.00	67	10
707	Freezer duas portas	728.00	75	9
708	Micro-ondas	252.00	12	10
709	Exaustor de cozinha	919.00	96	3
710	Cafeteira	597.00	6	7
711	Fogão	609.00	76	9
712	Exaustor de cozinha	607.00	38	13
713	Micro-ondas	723.00	44	12
714	Aquecedor elétrico	713.00	32	3
715	Fogão	153.00	88	4
716	Liquidificador	993.00	24	2
717	Ferro de passar roupa	982.00	11	9
718	Ferro de passar roupa	879.00	18	1
719	Geladeira uma porta	426.00	9	5
720	Ventilador	892.00	40	1
721	Forno elétrico	499.00	93	13
722	Máquina de secar roupa	122.00	22	6
723	Ferro de passar roupa	649.00	11	13
724	Máquina de lavar roupa	181.00	26	5
725	Forno elétrico	514.00	97	12
726	Freezer uma porta	724.00	78	12
727	Aspirador de pó	899.00	65	8
728	Torradeira	755.00	71	1
729	Exaustor de cozinha	207.00	61	10
730	Panela elétrica de arroz	313.00	75	11
731	Fogão	1024.00	11	8
732	Cafeteira	356.00	72	7
733	Ar condicionado	763.00	44	7
734	Máquina de lavar louça	963.00	89	12
735	Máquina de lavar louça	904.00	24	4
736	Ferro de passar roupa	688.00	41	10
737	Freezer duas portas	517.00	77	10
738	Aquecedor elétrico	473.00	46	4
739	Panela elétrica de arroz	1063.00	76	6
740	Freezer duas portas	512.00	40	10
741	Geladeira uma porta	503.00	29	1
742	Ventilador	126.00	44	3
743	Máquina de secar roupa	341.00	32	10
744	Grill elétrico	141.00	23	8
745	Máquina de lavar roupa	409.00	39	5
746	Liquidificador	620.00	95	2
747	Máquina de secar roupa	1092.00	18	13
748	Ferro de passar roupa	189.00	90	5
749	Máquina de lavar louça	684.00	27	3
750	Ferro de passar roupa	754.00	23	11
751	Panela elétrica de arroz	1015.00	66	2
752	Ferro de passar roupa	283.00	98	9
753	Panela elétrica de arroz	453.00	63	10
754	Máquina de secar roupa	435.00	56	8
755	Panela elétrica de arroz	688.00	14	8
756	Máquina de secar roupa	652.00	68	4
757	Máquina de lavar louça	621.00	53	1
758	Grill elétrico	1063.00	6	9
759	Freezer duas portas	903.00	87	8
760	Panela elétrica de arroz	1011.00	1	5
761	Liquidificador	997.00	98	11
762	Liquidificador	987.00	66	9
763	Purificador de água	855.00	75	11
764	Ar condicionado	929.00	96	6
765	Geladeira duas portas	854.00	99	3
766	Máquina de lavar louça	586.00	60	11
767	Máquina de lavar louça	959.00	78	13
768	Fogão	506.00	32	10
769	Ferro de passar roupa	931.00	20	9
770	Máquina de secar roupa	627.00	66	1
771	Geladeira uma porta	856.00	48	3
772	Máquina de secar roupa	611.00	37	9
773	Aquecedor elétrico	290.00	67	1
774	Exaustor de cozinha	463.00	76	7
775	Ventilador	1055.00	90	4
776	Máquina de secar roupa	795.00	38	3
777	Micro-ondas	213.00	31	8
778	Cafeteira	187.00	72	1
779	Purificador de água	1051.00	22	10
780	Geladeira duas portas	868.00	7	3
781	Torradeira	978.00	79	3
782	Liquidificador	813.00	79	9
783	Purificador de água	786.00	25	6
784	Exaustor de cozinha	318.00	77	11
785	Torradeira	702.00	74	3
786	Aspirador de pó	228.00	52	6
787	Panela elétrica de arroz	828.00	68	6
788	Ar condicionado	129.00	55	6
789	Micro-ondas	103.00	9	12
790	Micro-ondas	238.00	58	11
791	Máquina de lavar roupa	196.00	12	9
792	Ferro de passar roupa	480.00	7	1
793	Aspirador de pó	161.00	30	10
794	Forno elétrico	747.00	62	10
795	Ar condicionado	725.00	29	4
796	Fogão	1002.00	43	11
797	Ventilador	955.00	56	8
798	Ventilador	980.00	43	6
799	Ferro de passar roupa	618.00	14	13
800	Ar condicionado	531.00	44	5
801	Micro-ondas	759.00	48	7
802	Micro-ondas	1025.00	30	6
803	Ventilador	900.00	17	12
804	Grill elétrico	864.00	75	11
805	Máquina de lavar louça	793.00	4	8
806	Torradeira	838.00	35	1
807	Freezer uma porta	833.00	28	13
808	Aquecedor elétrico	815.00	43	12
809	Grill elétrico	844.00	41	8
810	Aspirador de pó	1029.00	61	5
811	Máquina de lavar louça	1079.00	92	1
812	Aquecedor elétrico	125.00	50	4
813	Liquidificador	101.00	44	13
814	Ferro de passar roupa	129.00	22	10
815	Liquidificador	357.00	7	12
816	Purificador de água	373.00	6	5
817	Cafeteira	104.00	45	6
818	Freezer uma porta	270.00	21	5
819	Aspirador de pó	560.00	52	13
820	Liquidificador	660.00	31	13
821	Ventilador	993.00	69	8
822	Cafeteira	968.00	78	9
823	Ferro de passar roupa	187.00	46	3
824	Torradeira	289.00	62	13
825	Panela elétrica de arroz	638.00	3	2
826	Aspirador de pó	225.00	42	6
827	Fogão	527.00	32	5
828	Torradeira	1093.00	25	11
829	Exaustor de cozinha	944.00	52	2
830	Fogão	426.00	70	10
831	Ventilador	373.00	28	11
832	Máquina de lavar roupa	743.00	27	10
833	Freezer uma porta	634.00	9	9
834	Cafeteira	200.00	86	10
835	Grill elétrico	802.00	59	13
836	Ar condicionado	135.00	60	5
837	Liquidificador	887.00	54	2
838	Liquidificador	721.00	30	9
839	Aspirador de pó	688.00	36	7
840	Aspirador de pó	107.00	69	9
841	Fogão	479.00	76	1
842	Forno elétrico	141.00	72	7
843	Máquina de lavar louça	852.00	41	3
844	Aquecedor elétrico	751.00	6	6
845	Geladeira uma porta	210.00	10	8
846	Freezer duas portas	1213.00	32	2
847	Máquina de secar roupa	1078.00	86	2
848	Exaustor de cozinha	351.00	5	13
849	Panela elétrica de arroz	1021.00	92	12
850	Cafeteira	225.00	68	2
851	Freezer duas portas	1082.00	10	5
852	Ar condicionado	1096.00	69	4
853	Aspirador de pó	665.00	69	8
854	Aspirador de pó	1097.00	4	10
855	Ventilador	942.00	44	1
856	Purificador de água	226.00	76	3
857	Cafeteira	485.00	70	3
858	Micro-ondas	482.00	78	7
859	Fogão	600.00	73	3
860	Ferro de passar roupa	807.00	57	5
861	Cafeteira	123.00	88	13
862	Geladeira duas portas	859.00	99	11
863	Micro-ondas	1089.00	32	10
864	Ar condicionado	778.00	3	8
865	Cafeteira	914.00	48	3
866	Máquina de secar roupa	754.00	20	8
867	Exaustor de cozinha	535.00	50	2
868	Máquina de lavar roupa	455.00	67	6
869	Máquina de lavar roupa	937.00	50	3
870	Máquina de secar roupa	1096.00	30	4
871	Ventilador	660.00	5	6
872	Ventilador	134.00	90	6
873	Liquidificador	378.00	50	13
874	Liquidificador	746.00	62	11
875	Exaustor de cozinha	514.00	30	3
876	Purificador de água	964.00	66	1
877	Exaustor de cozinha	477.00	8	7
878	Forno elétrico	613.00	65	7
879	Ferro de passar roupa	954.00	80	13
880	Torradeira	528.00	32	2
881	Aspirador de pó	721.00	51	2
882	Ferro de passar roupa	628.00	42	3
883	Máquina de lavar louça	830.00	60	5
884	Grill elétrico	271.00	10	4
885	Exaustor de cozinha	662.00	52	13
886	Purificador de água	1019.00	1	11
887	Máquina de secar roupa	249.00	40	5
888	Exaustor de cozinha	329.00	66	9
889	Panela elétrica de arroz	488.00	21	10
890	Máquina de secar roupa	948.00	17	2
891	Freezer duas portas	541.00	17	5
892	Ar condicionado	895.00	53	10
893	Purificador de água	623.00	10	3
894	Forno elétrico	1031.00	73	11
895	Liquidificador	203.00	92	12
896	Micro-ondas	685.00	82	1
897	Panela elétrica de arroz	954.00	52	5
898	Fogão	1033.00	18	6
899	Freezer uma porta	381.00	86	2
900	Cafeteira	324.00	14	6
901	Ferro de passar roupa	1048.00	44	2
902	Freezer uma porta	538.00	84	1
903	Liquidificador	912.00	14	13
904	Máquina de secar roupa	337.00	83	7
905	Geladeira duas portas	1173.00	78	12
906	Ventilador	425.00	12	13
907	Máquina de lavar roupa	1029.00	16	3
908	Ventilador	285.00	14	11
909	Aquecedor elétrico	486.00	52	9
910	Aspirador de pó	809.00	64	4
911	Máquina de lavar louça	994.00	40	5
912	Grill elétrico	971.00	7	9
913	Cafeteira	675.00	48	6
914	Micro-ondas	888.00	56	2
915	Máquina de lavar louça	477.00	100	6
916	Purificador de água	721.00	4	2
917	Aquecedor elétrico	545.00	40	9
918	Cafeteira	423.00	68	7
919	Freezer uma porta	296.00	71	13
920	Aspirador de pó	310.00	38	8
921	Freezer duas portas	550.00	49	11
922	Ventilador	1015.00	40	13
923	Máquina de secar roupa	637.00	67	11
924	Grill elétrico	879.00	87	9
925	Liquidificador	565.00	63	12
926	Geladeira uma porta	658.00	45	11
927	Máquina de secar roupa	317.00	16	6
928	Ventilador	700.00	96	10
929	Aquecedor elétrico	571.00	52	11
930	Ferro de passar roupa	350.00	88	10
931	Panela elétrica de arroz	611.00	30	3
932	Cafeteira	858.00	78	7
933	Forno elétrico	1060.00	100	11
934	Cafeteira	975.00	66	9
935	Máquina de lavar louça	420.00	88	10
936	Aspirador de pó	854.00	64	9
937	Grill elétrico	159.00	88	11
938	Máquina de secar roupa	799.00	53	9
939	Micro-ondas	136.00	35	6
940	Panela elétrica de arroz	968.00	46	6
941	Fogão	351.00	24	13
942	Exaustor de cozinha	614.00	34	8
943	Forno elétrico	722.00	4	8
944	Aquecedor elétrico	815.00	46	6
945	Torradeira	756.00	45	6
946	Máquina de lavar louça	812.00	14	11
947	Grill elétrico	530.00	38	11
948	Grill elétrico	256.00	99	4
949	Forno elétrico	341.00	53	7
950	Máquina de secar roupa	666.00	94	6
951	Purificador de água	770.00	38	7
952	Liquidificador	355.00	34	7
953	Freezer uma porta	660.00	62	12
954	Exaustor de cozinha	423.00	9	11
955	Ferro de passar roupa	1024.00	23	11
956	Máquina de lavar roupa	722.00	95	12
957	Ar condicionado	214.00	41	2
958	Máquina de lavar roupa	468.00	9	11
959	Freezer duas portas	1087.00	84	10
960	Grill elétrico	790.00	28	4
961	Forno elétrico	1030.00	62	3
962	Liquidificador	507.00	47	3
963	Micro-ondas	865.00	50	2
964	Ventilador	1030.00	84	13
965	Grill elétrico	617.00	31	9
966	Máquina de secar roupa	383.00	1	11
967	Purificador de água	131.00	60	6
968	Cafeteira	1061.00	72	6
969	Geladeira duas portas	1015.00	95	5
970	Panela elétrica de arroz	769.00	37	1
971	Máquina de lavar roupa	774.00	73	10
972	Freezer duas portas	306.00	78	5
973	Máquina de lavar roupa	121.00	97	10
974	Liquidificador	821.00	38	5
975	Grill elétrico	520.00	88	11
976	Grill elétrico	702.00	10	13
977	Fogão	425.00	18	10
978	Freezer uma porta	958.00	45	8
979	Cafeteira	309.00	56	11
980	Fogão	235.00	60	5
981	Micro-ondas	612.00	75	5
982	Ferro de passar roupa	689.00	21	11
983	Máquina de lavar louça	560.00	24	8
984	Torradeira	988.00	2	10
985	Panela elétrica de arroz	640.00	81	8
986	Geladeira duas portas	543.00	65	12
987	Liquidificador	946.00	30	9
988	Panela elétrica de arroz	367.00	15	2
989	Cafeteira	609.00	48	13
990	Máquina de lavar louça	596.00	51	11
991	Liquidificador	1061.00	63	11
992	Cafeteira	996.00	75	4
993	Ferro de passar roupa	382.00	14	8
994	Forno elétrico	489.00	36	12
995	Cafeteira	421.00	84	5
996	Fogão	857.00	44	9
997	Torradeira	1027.00	22	4
998	Aquecedor elétrico	986.00	43	11
999	Micro-ondas	250.00	64	4
1000	Fogão	703.00	93	13
1	Máquina de lavar roupa	231.00	1	7
\.


--
-- Data for Name: tb_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_usuario (user_codigo, user_nome, user_cpf, user_username, user_senha) FROM stdin;
2	Pedro	987654321	Pedro	789
3	João Silva	123.456.789-00	joaosilva	senha123
4	João da Silva	12345678901	joao	senha123
5	Vinicius Almada	12345678901	joao	senha123
22	Paulo Silveira	00000000000	Paulo	123
1000	Paulo Silveira	00000000000	Paulo	123
1001	Lara Barbosa	22222222222	Lara	123
1002	Camila Bregalda	11111111111	Cami	123
1003	Camila Bregalda	11111111111	Cami	123
\.


--
-- Data for Name: tb_vendas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_vendas (ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo) FROM stdin;
1	2024-06-11 17:43:01.88262	2310.00	\N
2	2024-06-11 17:43:10.425221	2310.00	\N
3	2024-06-11 17:43:18.321144	2310.00	\N
4	2024-06-15 15:47:36.692993	4620.00	\N
\.


--
-- Name: backup_programado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.backup_programado_id_seq', 7, true);


--
-- Name: backup_programado backup_programado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.backup_programado
    ADD CONSTRAINT backup_programado_pkey PRIMARY KEY (id);


--
-- Name: tb_fornecedores tb_fornecedores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_fornecedores
    ADD CONSTRAINT tb_fornecedores_pkey PRIMARY KEY (for_codigo);


--
-- Name: tb_funcionarios tb_funcionarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_funcionarios
    ADD CONSTRAINT tb_funcionarios_pkey PRIMARY KEY (fun_codigo);


--
-- Name: tb_itens tb_itens_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_itens
    ADD CONSTRAINT tb_itens_pkey PRIMARY KEY (ite_codigo);


--
-- Name: tb_produtos tb_produtos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produtos
    ADD CONSTRAINT tb_produtos_pkey PRIMARY KEY (pro_codigo);


--
-- Name: tb_usuario tb_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario
    ADD CONSTRAINT tb_usuario_pkey PRIMARY KEY (user_codigo);


--
-- Name: tb_vendas tb_vendas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_vendas
    ADD CONSTRAINT tb_vendas_pkey PRIMARY KEY (ven_codigo);


--
-- Name: tb_itens tb_itens_tb_produtos_pro_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_itens
    ADD CONSTRAINT tb_itens_tb_produtos_pro_codigo_fkey FOREIGN KEY (tb_produtos_pro_codigo) REFERENCES public.tb_produtos(pro_codigo);


--
-- Name: tb_itens tb_itens_tb_vendas_ven_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_itens
    ADD CONSTRAINT tb_itens_tb_vendas_ven_codigo_fkey FOREIGN KEY (tb_vendas_ven_codigo) REFERENCES public.tb_vendas(ven_codigo);


--
-- Name: tb_produtos tb_produtos_tb_fornecedores_for_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produtos
    ADD CONSTRAINT tb_produtos_tb_fornecedores_for_codigo_fkey FOREIGN KEY (tb_fornecedores_for_codigo) REFERENCES public.tb_fornecedores(for_codigo);


--
-- Name: tb_vendas tb_vendas_tb_funcionarios_fun_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_vendas
    ADD CONSTRAINT tb_vendas_tb_funcionarios_fun_codigo_fkey FOREIGN KEY (tb_funcionarios_fun_codigo) REFERENCES public.tb_funcionarios(fun_codigo);


--
-- Name: TABLE tb_usuario; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.tb_usuario TO "newRole";
GRANT UPDATE ON TABLE public.tb_usuario TO "novoUsuario3";


--
-- PostgreSQL database dump complete
--

