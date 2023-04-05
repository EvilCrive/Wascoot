-- PostgreSQL stuff
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

-- ADMIN(id, email, password, profileImage)
INSERT INTO public.admin VALUES (DEFAULT, 'admin@wascoot.com', '123321', NULL);
INSERT INTO public.admin VALUES (DEFAULT, 'ferror@gmail.com', '1234', NULL);
INSERT INTO public.admin VALUES (DEFAULT, 'paria@gmail.com', '1357', NULL);

-- PAYMENTMETHOD(id, paymenttype, activation)
INSERT INTO public.paymentmethod VALUES (DEFAULT, 'Credit Card', DEFAULT);
INSERT INTO public.paymentmethod VALUES (DEFAULT, 'Visa Debit', DEFAULT);
INSERT INTO public.paymentmethod VALUES (DEFAULT, 'Paypal', DEFAULT);

-- CUSTOMER(CF, name, surname, email, sex, birthdate, postalCode)
INSERT INTO public.customer VALUES ('PSCGLI56G45I623E', 'Giulia', 'Primo', 'g.primo@virgilio.it', 'F', DEFAULT, NULL, 'Credit Card');
INSERT INTO public.customer VALUES ('SCNGNN99D09C352U', 'Giovanni', 'Secondo', 'g.secondo@gmail.com', 'M', DEFAULT, NULL, 'Credit Card');
INSERT INTO public.customer VALUES ('LBRPPN14S07D612X', 'Pipino', 'Il Breve', 'p.ilbreve@libero.it'. 'M', DEFAULT, NULL, 'Credit Card');
INSERT INTO public.customer VALUES ('RSSFRC83L49F111G', 'Federica', 'Rossi', 'f.rossi@libero.it', 'F', DEFAULT, NULL, 'Visa Debit');
INSERT INTO public.customer VALUES ('ZREFNC99T16L781Q', 'Francesco', 'Zero', 'f.zero@studenti.unisa.it', 'M', DEFAULT, NULL, 'Paypal');
INSERT INTO public.customer VALUES ('TRZDIA99L60L157U', 'Ida', 'Terzigno', 'iterzigno@pikolo.it', 'F', DEFAULT, NULL, 'Visa Debit');
INSERT INTO public.customer VALUES ('SNTGPP86E10A465X', 'Giuseppe', 'Senatore', 'g.senatore@libero.it', 'M', DEFAULT, NULL, 'Paypal');
INSERT INTO public.customer VALUES ('RGNCMN78D10A399I', 'Carmine', 'Ragno', 'c.ragno@virgilio.it', 'M', DEFAULT, NULL, 'Visa Debit');
INSERT INTO public.customer VALUES ('MTRGPP52M65I608H', 'Giuseppina', 'Amatore', 'g.amatore@gmail.com', 'F', DEFAULT, NULL, 'Paypal');
INSERT INTO public.customer VALUES ('SPSGNR86L31B076I', 'Gennaro', 'Esposito', 'g.esposito@libero.it', 'M', DEFAULT, NULL, 'Visa Debit');
INSERT INTO public.customer VALUES ('PHNTEW74B37Z444L', 'Paria', 'Tahan', 'paria.tahan@gmail.com', 'F', '1994-02-09', '35141', 'Credit Card');
INSERT INTO public.customer VALUES ('PHNTEW74B37Z555L', 'Nicola', 'Ferro', 'nicola.ferro@gmail.com', 'M', '1970-01-01', '14891', 'Credit Card');
INSERT INTO public.customer VALUES ('PHNTEW74B37Z449L', 'Fabio', 'Vandin', 'fabio.vandin@gmail.com', 'M', '1977-04-05', '54321', 'Paypal');
INSERT INTO public.customer VALUES ('PHNTEW76B37Z344L', 'Matteo', 'Fischetti', 'matteo.fischetti@gmail.com', 'M', '1966-10-03', '98765', 'Paypal');

-- MODEL(name, brand, battery_life, price_per_min)
INSERT INTO public.model VALUES ('Dot', 'Decathlon', '01:00:00', 1);
INSERT INTO public.model VALUES ('Wolf', 'Giant', '03:00:00', 3);
INSERT INTO public.model VALUES ('Corona', 'Merida', '02:20:00', 2);
INSERT INTO public.model VALUES ('Liszt', 'Giant', '02:02:00', 3);
INSERT INTO public.model VALUES ('Mozart', 'ASUS', '10:10:00', 2);
INSERT INTO public.model VALUES ('Bach', 'ACER', '04:01:00', 0.900);
INSERT INTO public.model VALUES ('E90', 'Razor', '01:00:00', 0.25);
INSERT INTO public.model VALUES ('E100', 'Razor', '02:00:00', 0.15);
INSERT INTO public.model VALUES ('E300', 'Razor', '01:30:00', 0.1);
INSERT INTO public.model VALUES ('R9 PRO', 'LEXGO', '01:00:00', 0.2);

-- SCOOTERRACKS(id, total_parking_spots, available_parking_spots, postalCode, address)
INSERT INTO public.scooterracks VALUES (DEFAULT, 20, 15, '35141', 'Via Eugana 80');
INSERT INTO public.scooterracks VALUES (DEFAULT, 20, 2, '12345', 'Via Roma');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 10, '15678', 'Via Strat');
INSERT INTO public.scooterracks VALUES (DEFAULT, 30, 10, '65432', 'Via Debussey');
INSERT INTO public.scooterracks VALUES (DEFAULT, 20, 1, '35133', 'The fifth ave.');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Castel Sant''Angelo');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Piazza Navona');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Pantheon');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Piazza Venezia');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Foro Romano');
INSERT INTO public.scooterracks VALUES (DEFAULT, 10, 5, NULL, 'Colosseo');

--
-- TOC entry 3682 (class 0 OID 17838)
-- Dependencies: 218
-- Data for Name: scooter; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.scooter VALUES ('SC005', '2023-03-20', 10, 'Dot', 100.00, 'SR005');
INSERT INTO public.scooter VALUES ('SC001', '2023-03-29', 50, 'Dot', 100.00, 'SR001');
INSERT INTO public.scooter VALUES ('SC002', '2022-09-09', 120, 'Mozart', 40.11, 'SR002');
INSERT INTO public.scooter VALUES ('SC003', '2010-03-01', 1000, 'Bach', 60.00, 'SR003');
INSERT INTO public.scooter VALUES ('SC004', '1977-03-09', 700, 'Liszt', 37.69, 'SR004');


--
-- TOC entry 3683 (class 0 OID 17856)
-- Dependencies: 219
-- Data for Name: rental; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rental VALUES (1, NULL, '2016-06-22 19:10:25', 'SC001 ', 'SR002', 'SR001', 'PHNTEW74B37Z444L', 10);
INSERT INTO public.rental VALUES (2, NULL, '2019-01-01 00:00:00', 'SC002 ', 'SR003', 'SR002', 'PHNTEW74B37Z444L', 4);
INSERT INTO public.rental VALUES (3, NULL, '2029-01-01 00:00:00', 'SC004 ', 'SR003', 'SR001', 'PHNTEW74B37Z444L', 3);


--
-- TOC entry 3685 (class 0 OID 17910)
-- Dependencies: 221
-- Data for Name: paymentwithoutsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.paymentwithoutsubscription VALUES (1, 100.00, '2011-01-01 00:00:00', 1);
INSERT INTO public.paymentwithoutsubscription VALUES (2, 50.00, '2020-01-11 00:00:00', 2);
INSERT INTO public.paymentwithoutsubscription VALUES (3, 10.00, '2021-10-01 00:00:00', 3);


--
-- TOC entry 3681 (class 0 OID 17828)
-- Dependencies: 217
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.subscription VALUES ('SUB01', '1 day', 1, '00:00:01', 10.00);
INSERT INTO public.subscription VALUES ('SUB02', '1 day', 2, '02:00:00', 10.00);
INSERT INTO public.subscription VALUES ('SUB03', '00:00:00', 3, '00:00:03', 4.00);


--
-- TOC entry 3684 (class 0 OID 17890)
-- Dependencies: 220
-- Data for Name: usedsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3686 (class 0 OID 17925)
-- Dependencies: 222
-- Data for Name: paymentwithsubscription; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3693 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_order_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rental_order_number_seq', 1, false);


--
-- TOC entry 3694 (class 0 OID 0)
-- Dependencies: 212
-- Name: sequence_id_ca; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id_ca', 2, false);


--
-- TOC entry 3695 (class 0 OID 0)
-- Dependencies: 211
-- Name: sequence_id_sa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id_sa', 1, false);


--
-- TOC entry 3696 (class 0 OID 0)
-- Dependencies: 209
-- Name: usedsubscription_id_a_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usedsubscription_id_a_seq', 1, false);


-- Completed on 2023-03-31 17:18:42 CEST

--
-- PostgreSQL database dump complete
--

