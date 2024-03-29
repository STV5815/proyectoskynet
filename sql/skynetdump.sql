--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id integer NOT NULL,
    nombres character varying(255),
    apellidos character varying(255),
    email character varying(255),
    telefono character varying(255),
    direccion character varying(255),
    coordenadas character varying(255)
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- Name: clientes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clientes_id_seq OWNER TO postgres;

--
-- Name: clientes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_id_seq OWNED BY public.clientes.id;


--
-- Name: clientes_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clientes_seq OWNER TO postgres;

--
-- Name: configuraciones_sistema; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuraciones_sistema (
    id integer NOT NULL,
    nombre character varying(100),
    valor text
);


ALTER TABLE public.configuraciones_sistema OWNER TO postgres;

--
-- Name: configuraciones_sistema_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.configuraciones_sistema_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.configuraciones_sistema_id_seq OWNER TO postgres;

--
-- Name: configuraciones_sistema_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.configuraciones_sistema_id_seq OWNED BY public.configuraciones_sistema.id;


--
-- Name: estados_visitas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estados_visitas (
    id integer NOT NULL,
    nombre character varying(255)
);


ALTER TABLE public.estados_visitas OWNER TO postgres;

--
-- Name: estados_visitas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estados_visitas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estados_visitas_id_seq OWNER TO postgres;

--
-- Name: estados_visitas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estados_visitas_id_seq OWNED BY public.estados_visitas.id;


--
-- Name: estados_visitas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estados_visitas_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estados_visitas_seq OWNER TO postgres;

--
-- Name: planificacion_visitas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planificacion_visitas (
    id integer NOT NULL,
    supervisor_id integer,
    cliente_id integer,
    fecha_hora_visita date
);


ALTER TABLE public.planificacion_visitas OWNER TO postgres;

--
-- Name: planificacion_visitas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planificacion_visitas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.planificacion_visitas_id_seq OWNER TO postgres;

--
-- Name: planificacion_visitas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planificacion_visitas_id_seq OWNED BY public.planificacion_visitas.id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id_rol integer NOT NULL,
    nombre character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id_rol;


--
-- Name: roles_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_seq OWNER TO postgres;

--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nombre character varying(255),
    email character varying(255),
    contrasena character varying(255),
    id_rol integer
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- Name: usuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_seq OWNER TO postgres;

--
-- Name: visitas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.visitas (
    id integer NOT NULL,
    cliente_id integer,
    tecnico_id integer,
    fecha_hora_inicio timestamp without time zone,
    fecha_hora_fin timestamp without time zone,
    coordenadas_visita character varying(50),
    reporte character varying(500),
    enviado boolean DEFAULT false,
    estado_id integer DEFAULT 1
);


ALTER TABLE public.visitas OWNER TO postgres;

--
-- Name: visitas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.visitas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.visitas_id_seq OWNER TO postgres;

--
-- Name: visitas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.visitas_id_seq OWNED BY public.visitas.id;


--
-- Name: clientes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id SET DEFAULT nextval('public.clientes_id_seq'::regclass);


--
-- Name: configuraciones_sistema id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuraciones_sistema ALTER COLUMN id SET DEFAULT nextval('public.configuraciones_sistema_id_seq'::regclass);


--
-- Name: estados_visitas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados_visitas ALTER COLUMN id SET DEFAULT nextval('public.estados_visitas_id_seq'::regclass);


--
-- Name: planificacion_visitas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planificacion_visitas ALTER COLUMN id SET DEFAULT nextval('public.planificacion_visitas_id_seq'::regclass);


--
-- Name: roles id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id_rol SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- Name: visitas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visitas ALTER COLUMN id SET DEFAULT nextval('public.visitas_id_seq'::regclass);


--
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientes (id, nombres, apellidos, email, telefono, direccion, coordenadas) FROM stdin;
3	Steven Gabriel	Perez Guzman	cliente3@example.com	456789123	Gasolinera Puma llano Largo	14.688101452795303, -90.40045235425421
4	Luis Fernando	Cotoc Vasquez	cliente4@example.com	321654987	Plaza Atanasio Tzul	14.559731236348155, -90.54901293328389
5	Andrea	Lopez Lopez	marroquinkenneth70@gmail.com	654321987	Centro Comercial Flores del Lago	14.472681936250334, -90.63857529287387
1	Juan Carlos	Perez Rodriguez	landaverde.andrea1105@gmail.com	123456789	Centra Norte	14.647771597004738, -90.45098289331538
2	Peter Ramiro	Yoc Coc	sacm5815@gmail.com	987654321	Cargo Expreso Zona 17	14.646434168055498, -90.46525865856711
\.


--
-- Data for Name: configuraciones_sistema; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configuraciones_sistema (id, nombre, valor) FROM stdin;
\.


--
-- Data for Name: estados_visitas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estados_visitas (id, nombre) FROM stdin;
1	Pendiente
2	En progreso
3	Completada
\.


--
-- Data for Name: planificacion_visitas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planificacion_visitas (id, supervisor_id, cliente_id, fecha_hora_visita) FROM stdin;
6	2	1	2023-05-15
7	2	2	2023-05-16
8	3	3	2023-05-17
9	3	4	2023-05-18
10	3	5	2023-05-19
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id_rol, nombre) FROM stdin;
1	Administrador
2	Supervisor
3	T├®cnico
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, nombre, email, contrasena, id_rol) FROM stdin;
1	Administrador	admin@example.com	123456	1
2	Supervisor 1	supervisor1@example.com	123456	2
3	Supervisor 2	supervisor2@example.com	123456	2
4	Tecnico 1	tecnico1@example.com	123456	3
5	Tecnico 2	tecnico2@example.com	123456	3
30	Administrador2	marroquinkenneth@gmail.com	123456	\N
31	JuanPereza	marroquinkenneth@gmail.com	123456	\N
32	Administrador22	marroquinkenneth@gmail.com	123456	\N
33	Administrador23	marroquinkenneth@gmail.com	123456	\N
34	Administrador232	marroquinkenneth@gmail.com	123456	\N
39	Supervisor	marroquinkenneth@gmail.com	123456	1
40	kennethas	kenneth@gmail.com	123456	\N
42	kennethasd	kenneth@gmail.com	123456	\N
44	kennethasdwe	kenneth@gmail.com	123456	\N
46	kennethasdwee	kenneth@gmail.com	123456	\N
48	kennghethasdwee	kenneth@gmail.com	123456	\N
50	kennghethasddwee	kenneth@gmail.com	123456	\N
52	kennghewthasddwee	kenneth@gmail.com	123456	\N
53	Steve Cruz	steveandres@gmail.com	123456	2
\.


--
-- Data for Name: visitas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.visitas (id, cliente_id, tecnico_id, fecha_hora_inicio, fecha_hora_fin, coordenadas_visita, reporte, enviado, estado_id) FROM stdin;
3	3	5	2023-05-17 09:00:00	2023-05-17 10:30:00	14.688101452795303, -90.40045235425421	Visita realizada correctamente	t	3
12	2	4	2023-06-17 20:14:00	2023-06-17 23:15:00	5454545151		t	3
15	5	4	2023-06-14 23:30:00	\N	123456	ANdrea lopez 123456 a las 2029 del 14 0 6	f	3
2	2	4	2023-06-17 12:35:00	2023-06-17 13:36:00	14.646434168055498, -90.46525865856711	El se├▒or Steve nos indica que la instalacion de su cable se realizo correctamente.	t	3
1	1	4	\N	\N	14.647771597004738, -90.45098289331538	La se├▒orita muy amable, se realizo la instalacion del cable sin problemas por lo que si indica que todo correcto.	f	3
\.


--
-- Name: clientes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_id_seq', 10, true);


--
-- Name: clientes_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_seq', 51, true);


--
-- Name: configuraciones_sistema_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.configuraciones_sistema_id_seq', 1, false);


--
-- Name: estados_visitas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estados_visitas_id_seq', 6, true);


--
-- Name: estados_visitas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estados_visitas_seq', 1, false);


--
-- Name: planificacion_visitas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planificacion_visitas_id_seq', 10, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 11, true);


--
-- Name: roles_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_seq', 1, false);


--
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 54, true);


--
-- Name: usuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_seq', 1, true);


--
-- Name: visitas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.visitas_id_seq', 15, true);


--
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id);


--
-- Name: configuraciones_sistema configuraciones_sistema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuraciones_sistema
    ADD CONSTRAINT configuraciones_sistema_pkey PRIMARY KEY (id);


--
-- Name: estados_visitas estados_visitas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados_visitas
    ADD CONSTRAINT estados_visitas_pkey PRIMARY KEY (id);


--
-- Name: usuarios nombre_unico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT nombre_unico UNIQUE (nombre);


--
-- Name: planificacion_visitas planificacion_visitas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planificacion_visitas
    ADD CONSTRAINT planificacion_visitas_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_rol);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- Name: visitas visitas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visitas
    ADD CONSTRAINT visitas_pkey PRIMARY KEY (id);


--
-- Name: planificacion_visitas planificacion_visitas_cliente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planificacion_visitas
    ADD CONSTRAINT planificacion_visitas_cliente_id_fkey FOREIGN KEY (cliente_id) REFERENCES public.clientes(id);


--
-- Name: planificacion_visitas planificacion_visitas_supervisor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planificacion_visitas
    ADD CONSTRAINT planificacion_visitas_supervisor_id_fkey FOREIGN KEY (supervisor_id) REFERENCES public.usuarios(id);


--
-- Name: usuarios usuarios_rol_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_rol_id_fkey FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol);


--
-- Name: visitas visitas_cliente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visitas
    ADD CONSTRAINT visitas_cliente_id_fkey FOREIGN KEY (cliente_id) REFERENCES public.clientes(id);


--
-- Name: visitas visitas_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visitas
    ADD CONSTRAINT visitas_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estados_visitas(id);


--
-- Name: visitas visitas_tecnico_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.visitas
    ADD CONSTRAINT visitas_tecnico_id_fkey FOREIGN KEY (tecnico_id) REFERENCES public.usuarios(id);


--
-- PostgreSQL database dump complete
--

