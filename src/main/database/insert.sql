
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
