--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7
-- Dumped by pg_dump version 15.1

-- Started on 2023-03-29 13:48:22 CEST

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3721 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 844 (class 1247 OID 17250)
-- Name: gender; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.gender AS character(1)
	CONSTRAINT gender_check CHECK ((VALUE = ANY (ARRAY['F'::bpchar, 'M'::bpchar])));


ALTER DOMAIN public.gender OWNER TO postgres;

--
-- TOC entry 848 (class 1247 OID 17253)
-- Name: paymenttypes; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.paymenttypes AS character varying
	CONSTRAINT paymenttypes_check CHECK (((VALUE)::text = ANY ((ARRAY['Cash'::character varying, 'Credit Card'::character varying, 'Visa Debit'::character varying, 'Paypal'::character varying])::text[])));


ALTER DOMAIN public.paymenttypes OWNER TO postgres;

--
-- TOC entry 836 (class 1247 OID 17244)
-- Name: positive_integer; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.positive_integer AS integer DEFAULT 0
	CONSTRAINT positive_integer_check CHECK ((VALUE >= 0));


ALTER DOMAIN public.positive_integer OWNER TO postgres;

--
-- TOC entry 840 (class 1247 OID 17247)
-- Name: positive_real; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.positive_real AS real DEFAULT 0
	CONSTRAINT positive_real_check CHECK ((VALUE >= (0)::double precision));


ALTER DOMAIN public.positive_real OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 17402)
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    id character(5) NOT NULL,
    email character varying,
    password character varying,
    CONSTRAINT proper_email CHECK (((email)::text ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'::text))
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17269)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    cf character(16) NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL,
    email character varying(60) NOT NULL,
    sex public.gender NOT NULL,
    birthdate date DEFAULT '1922-02-02'::date NOT NULL,
    postalcode character(5)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17260)
-- Name: model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model (
    name character varying(30) NOT NULL,
    brand character varying(30) DEFAULT 'Unknown'::character varying NOT NULL,
    battery_life interval hour to minute DEFAULT '00:00:00'::interval hour to minute,
    price_per_min numeric(4,3) DEFAULT 0.10 NOT NULL,
    CONSTRAINT check_model_price CHECK ((price_per_min > 0.0))
);


ALTER TABLE public.model OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17279)
-- Name: paymentmethod; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paymentmethod (
    id character(5) NOT NULL,
    type public.paymenttypes NOT NULL
);


ALTER TABLE public.paymentmethod OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17241)
-- Name: sequence_id_sa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_id_sa
    AS integer
    START WITH 1
    INCREMENT BY 2
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_sa OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17368)
-- Name: paymentwithoutsubscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paymentwithoutsubscription (
    id_payment integer DEFAULT nextval('public.sequence_id_sa'::regclass) NOT NULL,
    price numeric(5,2) NOT NULL,
    date_hour timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    order_number integer NOT NULL,
    CONSTRAINT check_withoutpayment_price CHECK ((price > (0)::numeric))
);


ALTER TABLE public.paymentwithoutsubscription OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17242)
-- Name: sequence_id_ca; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_id_ca
    AS integer
    START WITH 2
    INCREMENT BY 2
    MINVALUE 2
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_ca OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17383)
-- Name: paymentwithsubscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paymentwithsubscription (
    id_payment integer DEFAULT nextval('public.sequence_id_ca'::regclass) NOT NULL,
    date_hour timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    order_number integer NOT NULL,
    id_card integer NOT NULL
);


ALTER TABLE public.paymentwithsubscription OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17240)
-- Name: rental_order_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rental_order_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rental_order_number_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17314)
-- Name: rental; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rental (
    order_number integer DEFAULT nextval('public.rental_order_number_seq'::regclass) NOT NULL,
    date_hour_delivery timestamp without time zone,
    date_hour_collection timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_scooter character(6) NOT NULL,
    scooterrack_delivery character(5) DEFAULT NULL::bpchar,
    scooterrack_collection character(5) NOT NULL,
    customer character(16) NOT NULL,
    km_traveled public.positive_real DEFAULT 0,
    CONSTRAINT check_collection_delivery CHECK ((date_hour_delivery > date_hour_collection))
);


ALTER TABLE public.rental OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17296)
-- Name: scooter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scooter (
    id character(5) NOT NULL,
    date_of_purchase date DEFAULT CURRENT_TIMESTAMP,
    km_traveled public.positive_real,
    model character varying(30) NOT NULL,
    remaining_battery_life numeric(5,2) DEFAULT 100.00 NOT NULL,
    id_scooter_rack character(5),
    CONSTRAINT check_scooter_purchase CHECK ((date_of_purchase <= CURRENT_TIMESTAMP))
);


ALTER TABLE public.scooter OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17255)
-- Name: scooterracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scooterracks (
    id character(5) NOT NULL,
    tot_num_parking_spots public.positive_integer NOT NULL,
    available_parking_spots public.positive_integer NOT NULL,
    postalcode character(5),
    address character varying(50)
);


ALTER TABLE public.scooterracks OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17286)
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id character(5) NOT NULL,
    type interval day DEFAULT '1 day'::interval day NOT NULL,
    number_daily_locks public.positive_integer DEFAULT 2 NOT NULL,
    validity_per_day interval hour to second DEFAULT '02:00:00'::interval hour to second,
    fixed_price numeric(5,2) DEFAULT 5.00 NOT NULL,
    CONSTRAINT check_subscription_price CHECK ((fixed_price > (0)::numeric))
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16386)
-- Name: usedsubscription_id_a_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usedsubscription_id_a_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usedsubscription_id_a_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17348)
-- Name: usedsubscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usedsubscription (
    id integer DEFAULT nextval('public.usedsubscription_id_a_seq'::regclass) NOT NULL,
    activation_date date DEFAULT CURRENT_TIMESTAMP NOT NULL,
    expiring_date date NOT NULL,
    num_remaining_unlocks public.positive_integer DEFAULT 2 NOT NULL,
    remaining_time_of_usage interval hour to second DEFAULT '02:00:00'::interval hour to second NOT NULL,
    possession character(16) NOT NULL,
    typology character varying(20) DEFAULT 'One day'::character varying NOT NULL
);


ALTER TABLE public.usedsubscription OWNER TO postgres;

--
-- TOC entry 3715 (class 0 OID 17402)
-- Dependencies: 223
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.admin (id, email, password) VALUES ('ad001', 'admin@wascoot.com', '123321');
INSERT INTO public.admin (id, email, password) VALUES ('ad002', 'ferror@gmail.com', '1234');
INSERT INTO public.admin (id, email, password) VALUES ('ad003', 'paria@gmail.com', '1357');


--
-- TOC entry 3707 (class 0 OID 17269)
-- Dependencies: 215
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer (cf, name, surname, email, sex, birthdate, postalcode) VALUES ('PHNTEW74B37Z444L', 'Paria', 'Tahan', 'paria.tahan@gmail.com', 'F', '1994-02-09', '35141');
INSERT INTO public.customer (cf, name, surname, email, sex, birthdate, postalcode) VALUES ('PHNTEW74B37Z555L', 'Nicola', 'Ferro', 'nicola.ferro@gmail.com', 'M', '1970-01-01', '14891');
INSERT INTO public.customer (cf, name, surname, email, sex, birthdate, postalcode) VALUES ('PHNTEW74B37Z449L', 'Fabio', 'Vandin', 'fabio.vandin@gmail.com', 'M', '1977-04-05', '54321');
INSERT INTO public.customer (cf, name, surname, email, sex, birthdate, postalcode) VALUES ('PHNTEW76B37Z344L', 'Matteo', 'Fischetti', 'matteo.fischetti@gmail.com', 'M', '1966-10-03', '98765');


--
-- TOC entry 3706 (class 0 OID 17260)
-- Dependencies: 214
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Dot', 'Decathlon', '01:00:00', 1.000);
INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Wolf', 'Giant', '03:00:00', 3.000);
INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Corona', 'Merida', '02:20:00', 2.000);
INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Liszt', 'Giant', '02:02:00', 3.000);
INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Mozart', 'ASUS', '10:10:00', 2.000);
INSERT INTO public.model (name, brand, battery_life, price_per_min) VALUES ('Bach', 'ACER', '04:01:00', 9.000);


--
-- TOC entry 3708 (class 0 OID 17279)
-- Dependencies: 216
-- Data for Name: paymentmethod; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.paymentmethod (id, type) VALUES ('PM001', 'Credit Card');


--
-- TOC entry 3713 (class 0 OID 17368)
-- Dependencies: 221
-- Data for Name: paymentwithoutsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3714 (class 0 OID 17383)
-- Dependencies: 222
-- Data for Name: paymentwithsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3711 (class 0 OID 17314)
-- Dependencies: 219
-- Data for Name: rental; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3710 (class 0 OID 17296)
-- Dependencies: 218
-- Data for Name: scooter; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.scooter (id, date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack) VALUES ('SC001', '2023-03-29', 50, 'Dot', 100.00, 'SR001');
INSERT INTO public.scooter (id, date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack) VALUES ('SC002', '2022-09-09', 120, 'Mozart', 40.11, 'SR002');
INSERT INTO public.scooter (id, date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack) VALUES ('SC003', '2010-03-01', 1000, 'Bach', 60.00, 'SR003');
INSERT INTO public.scooter (id, date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack) VALUES ('SC004', '1977-03-09', 700, 'Liszt', 37.69, 'SR004');


--
-- TOC entry 3705 (class 0 OID 17255)
-- Dependencies: 213
-- Data for Name: scooterracks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.scooterracks (id, tot_num_parking_spots, available_parking_spots, postalcode, address) VALUES ('SR001', 20, 15, '35141', 'Via Eugana 80');
INSERT INTO public.scooterracks (id, tot_num_parking_spots, available_parking_spots, postalcode, address) VALUES ('SR002', 20, 2, '12345', 'Via Roma');
INSERT INTO public.scooterracks (id, tot_num_parking_spots, available_parking_spots, postalcode, address) VALUES ('SR003', 10, 10, '15678', 'Via Strat');
INSERT INTO public.scooterracks (id, tot_num_parking_spots, available_parking_spots, postalcode, address) VALUES ('SR004', 30, 10, '65432', 'Via Debussey');


--
-- TOC entry 3709 (class 0 OID 17286)
-- Dependencies: 217
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.subscription (id, type, number_daily_locks, validity_per_day, fixed_price) VALUES ('SB001', '1 day', 2, '02:00:00', 3.00);
INSERT INTO public.subscription (id, type, number_daily_locks, validity_per_day, fixed_price) VALUES ('SB002', '3 days', 4, '03:01:01', 2.00);
INSERT INTO public.subscription (id, type, number_daily_locks, validity_per_day, fixed_price) VALUES ('SB003', '4 days', 2, '01:01:01', 2.00);
INSERT INTO public.subscription (id, type, number_daily_locks, validity_per_day, fixed_price) VALUES ('SB004', '3 days', 1, '09:00:00', 9.00);


--
-- TOC entry 3712 (class 0 OID 17348)
-- Dependencies: 220
-- Data for Name: usedsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3723 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_order_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rental_order_number_seq', 1, false);


--
-- TOC entry 3724 (class 0 OID 0)
-- Dependencies: 212
-- Name: sequence_id_ca; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id_ca', 2, false);


--
-- TOC entry 3725 (class 0 OID 0)
-- Dependencies: 211
-- Name: sequence_id_sa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id_sa', 1, false);


--
-- TOC entry 3726 (class 0 OID 0)
-- Dependencies: 209
-- Name: usedsubscription_id_a_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usedsubscription_id_a_seq', 1, false);


--
-- TOC entry 3534 (class 2606 OID 17327)
-- Name: rental check_collection_customer; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT check_collection_customer UNIQUE (date_hour_collection, customer);


--
-- TOC entry 3536 (class 2606 OID 17325)
-- Name: rental check_collection_scooter; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT check_collection_scooter UNIQUE (date_hour_collection, id_scooter);


--
-- TOC entry 3524 (class 2606 OID 17278)
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- TOC entry 3550 (class 2606 OID 17409)
-- Name: admin key_admin; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT key_admin PRIMARY KEY (id);


--
-- TOC entry 3526 (class 2606 OID 17276)
-- Name: customer key_customer; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT key_customer PRIMARY KEY (cf);


--
-- TOC entry 3522 (class 2606 OID 17268)
-- Name: model key_model; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT key_model PRIMARY KEY (name);


--
-- TOC entry 3528 (class 2606 OID 17285)
-- Name: paymentmethod key_payment_method; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentmethod
    ADD CONSTRAINT key_payment_method PRIMARY KEY (id);


--
-- TOC entry 3538 (class 2606 OID 17323)
-- Name: rental key_rental; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT key_rental PRIMARY KEY (order_number);


--
-- TOC entry 3532 (class 2606 OID 17303)
-- Name: scooter key_scooter; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scooter
    ADD CONSTRAINT key_scooter PRIMARY KEY (id);


--
-- TOC entry 3520 (class 2606 OID 17259)
-- Name: scooterracks key_scooter_rack; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scooterracks
    ADD CONSTRAINT key_scooter_rack PRIMARY KEY (id);


--
-- TOC entry 3530 (class 2606 OID 17295)
-- Name: subscription key_subscription; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT key_subscription PRIMARY KEY (id);


--
-- TOC entry 3540 (class 2606 OID 17357)
-- Name: usedsubscription key_used_subscription; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usedsubscription
    ADD CONSTRAINT key_used_subscription PRIMARY KEY (id);


--
-- TOC entry 3546 (class 2606 OID 17389)
-- Name: paymentwithsubscription key_with_subscription; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithsubscription
    ADD CONSTRAINT key_with_subscription PRIMARY KEY (id_payment);


--
-- TOC entry 3542 (class 2606 OID 17375)
-- Name: paymentwithoutsubscription key_without_subscription; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithoutsubscription
    ADD CONSTRAINT key_without_subscription PRIMARY KEY (id_payment);


--
-- TOC entry 3544 (class 2606 OID 17377)
-- Name: paymentwithoutsubscription paymentwithoutsubscription_order_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithoutsubscription
    ADD CONSTRAINT paymentwithoutsubscription_order_number_key UNIQUE (order_number);


--
-- TOC entry 3548 (class 2606 OID 17391)
-- Name: paymentwithsubscription paymentwithsubscription_order_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithsubscription
    ADD CONSTRAINT paymentwithsubscription_order_number_key UNIQUE (order_number);


--
-- TOC entry 3553 (class 2606 OID 17343)
-- Name: rental fk_rental_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_rental_customer FOREIGN KEY (customer) REFERENCES public.customer(cf) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3554 (class 2606 OID 17328)
-- Name: rental fk_rental_scooter; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_rental_scooter FOREIGN KEY (id_scooter) REFERENCES public.scooter(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3555 (class 2606 OID 17333)
-- Name: rental fk_rental_scooterrack_collection; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_rental_scooterrack_collection FOREIGN KEY (scooterrack_collection) REFERENCES public.scooterracks(id) ON UPDATE CASCADE;


--
-- TOC entry 3556 (class 2606 OID 17338)
-- Name: rental fk_rental_scooterrack_delivery; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_rental_scooterrack_delivery FOREIGN KEY (scooterrack_delivery) REFERENCES public.scooterracks(id) ON UPDATE CASCADE;


--
-- TOC entry 3551 (class 2606 OID 17309)
-- Name: scooter fk_scooter_model; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scooter
    ADD CONSTRAINT fk_scooter_model FOREIGN KEY (model) REFERENCES public.model(name) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3552 (class 2606 OID 17304)
-- Name: scooter fk_scooter_scooterrack; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scooter
    ADD CONSTRAINT fk_scooter_scooterrack FOREIGN KEY (id_scooter_rack) REFERENCES public.scooterracks(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3557 (class 2606 OID 17358)
-- Name: usedsubscription fk_used_subscription; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usedsubscription
    ADD CONSTRAINT fk_used_subscription FOREIGN KEY (typology) REFERENCES public.subscription(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3558 (class 2606 OID 17363)
-- Name: usedsubscription fk_usedsubscription_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usedsubscription
    ADD CONSTRAINT fk_usedsubscription_customer FOREIGN KEY (possession) REFERENCES public.customer(cf) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3559 (class 2606 OID 17378)
-- Name: paymentwithoutsubscription fk_withoutsubscription_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithoutsubscription
    ADD CONSTRAINT fk_withoutsubscription_rental FOREIGN KEY (order_number) REFERENCES public.rental(order_number) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3560 (class 2606 OID 17392)
-- Name: paymentwithsubscription fk_withsubscription_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithsubscription
    ADD CONSTRAINT fk_withsubscription_rental FOREIGN KEY (order_number) REFERENCES public.rental(order_number) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3561 (class 2606 OID 17397)
-- Name: paymentwithsubscription fk_withsubscription_usedsubscription; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymentwithsubscription
    ADD CONSTRAINT fk_withsubscription_usedsubscription FOREIGN KEY (id_card) REFERENCES public.usedsubscription(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3722 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-03-29 13:48:22 CEST

--
-- PostgreSQL database dump complete
--

