-- entering metodi di pagamento
INSERT INTO paymentmethod(type) values ('card');
INSERT INTO paymentmethod(type) values ('PayPal');
INSERT INTO paymentmethod(type) values ('Promo Code');
INSERT INTO paymentmethod(type) values ('AmazonPay');
INSERT INTO paymentmethod(type) values ('ApplePay');
INSERT INTO paymentmethod(type) values ('SamsungPay');
INSERT INTO paymentmethod(type) values ('GooglePay');
	 
-- entering model
insert into Model(name, brand, battery_life, weight, height, length, depth, rate_per_min, rate_per_model) 
values ('E90', 'Razor', '1 hour', 54, 91.44, 82.55, 40.64, 0.25, 1.00);
insert into Model(name, brand, battery_life, weight, height, length, depth, rate_per_min, rate_per_model) 
values ('E100', 'Razor', '2 hour', 100, 101.44, 92.55, 30.64, 0.15, 2.00);
insert into Model(name, brand, battery_life, weight, height, length, depth, rate_per_min, rate_per_model) 
values ('E300', 'Razor', '1.5 hour', 54, 90.56, 80.00, 40.64, 0.10, 1.00);
insert into Model(name, brand, battery_life, weight, height, length, depth, rate_per_min, rate_per_model) 
values ('R9 PRO', 'LEXGO', '1 hour', 14, 112, 53, 16, 0.20, 1.80);

-- entering subscription
insert into Subscription(name, duration_subscription, number_rentals_per_day, validity_per_day, fixed_price) 
values ('One day', '1 day', 2, '2 hour', 5.00);
insert into Subscription(name, duration_subscription, number_rentals_per_day, validity_per_day, fixed_price) 
                        values ('One week', '1 week', 4, '4 hour', 20.00); 
insert into Subscription(name, duration_subscription, number_rentals_per_day, validity_per_day, fixed_price) 
                        values ('One month', '1 month', 4, '4 hour', 60.00); 
insert into Subscription(name, duration_subscription, number_rentals_per_day, validity_per_day, fixed_price) 
                        values ('One year', '1 year', 8, '8 hour', 200.00);
insert into Subscription(name, duration_subscription, number_rentals_per_day, validity_per_day, fixed_price) 
                        values ('Ten days', '10 days', 4, '2 hour', 20.00); 

 -- entering scooterrack
insert into scooterrack(id,tot_num_parking_spots,latitude,longitude,address) values
('RO001',10,41.903249323722086,12.466280707499823,'Castel Sant''Angelo'),
('RO002',10,41.899207346808915,12.473066345542321,'Piazza Navona'),
('RO003',10,41.89878063566637,12.476704114792723,'Pantheon'),
('RO004',10,41.895847889188616,12.482645456887287,'Piazza Venezia'),
('RO005',10,41.89259688317782,12.485209615016114,'Foro Romano'),
('RO006',10,41.89024581390566,12.49224541500673,'Colosseo');

 -- entering clienti da fare insieme ad associazione per il vincolo aziendale implementato
insert into Costumer(CF, name, surname, email) values ('PSCGLI56G45I623E', 'Giulia', 'Primo', 'g.primo@virgilio.it');
insert into Costumer(CF, name, surname, email) values ('SCNGNN99D09C352U', 'Giovanni', 'Secondo', 'g.secondo@gmail.com');
insert into Costumer(CF, name, surname, email) values ('LBRPPN14S07D612X', 'Pipino', 'Il Breve', 'p.ilbreve@libero.it');
insert into Costumer(CF, name, surname, email) values ('RSSFRC83L49F111G', 'Federica', 'Rossi', 'f.rossi@libero.it');
insert into Costumer(CF, name, surname, email) values ('ZREFNC99T16L781Q', 'Francesco', 'Zero', 'f.zero@studenti.unisa.it');
insert into Costumer(CF, name, surname, email) values ('TRZDIA99L60L157U', 'Ida', 'Terzigno', 'iterzigno@pikolo.it');
insert into Costumer(CF, name, surname, email) values ('SNTGPP86E10A465X', 'Giuseppe', 'Senatore', 'g.senatore@libero.it');
insert into Costumer(CF, name, surname, email) values ('RGNCMN78D10A399I', 'Carmine', 'Ragno', 'c.ragno@virgilio.it');
insert into Costumer(CF, name, surname, email) values ('MTRGPP52M65I608H', 'Giuseppina', 'Amatore', 'g.amatore@gmail.com');
insert into Costumer(CF, name, surname, email) values ('SPSGNR86L31B076I', 'Gennaro', 'Esposito', 'g.esposito@libero.it');

-- entering association
insert into Association(type, CF) values 
('card', 'PSCGLI56G45I623E'),
('PayPal', 'SCNGNN99D09C352U'),
('AmazonPay', 'LBRPPN14S07D612X'),
('SamsungPay', 'RSSFRC83L49F111G'),
('GooglePay', 'ZREFNC99T16L781Q'),
('ApplePay', 'TRZDIA99L60L157U'),
('Promo Code', 'SNTGPP86E10A465X'),
('card', 'RGNCMN78D10A399I'),
('card', 'MTRGPP52M65I608H'),
('PayPal','SPSGNR86L31B076I');

--entering monopattini
insert into Scooter(id, date_of_purchase, km_traveled, model, id_scooter_rack) 
values ('L2AYO8', '2020-01-01', 0, 'E90', null), 
('P1CR57', '2020-01-9', 10, 'E100', 'RO005'), 
('IPDZV1', '2020-01-11', 9, 'E90', 'RO001'), 
('4911OE', '2020-01-10', 6, 'E100', 'RO002'),
('QAWIOI', '2020-01-25', 3, 'E300', 'RO003'), 
('GDVAV6', '2020-01-20', 6, 'E90', 'RO003'), 
('L2AYO9', '2020-01-30', 1.5, 'E90', null),
('LL52EU', '2020-01-17', 2, 'R9 PRO','RO006'),
('ZN50ZR', '2020-01-01', 14, 'E300', 'RO004'), 
('DTQZ8M', '2020-01-4', 5, 'E90', 'RO005');

--entering Real Subscription
insert into RealSubscription(activation_date, num_remaining_rentals, remaining_time_of_usage, possession, typology) values 
('2020-01-10', 0, '0 minute', 'PSCGLI56G45I623E', 'One day'),
('2020-02-12', 0, '0 minute', 'SCNGNN99D09C352U', 'One month'),
('2020-02-10', 0, '0 minute', 'LBRPPN14S07D612X', 'One day'),
('2020-01-10', 4, '1 hour', 'RSSFRC83L49F111G', 'One year'),
('2020-04-09', 0, '0 minute', 'ZREFNC99T16L781Q', 'One month'),
('2020-06-10', 6, '2 hour', 'SCNGNN99D09C352U', 'One year');

--entering Rental
--each entry is divided into two parts as the presence of triggers is taken into account

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('DTQZ8M', '2020-01-01 10:23:00', 'RO005', 'SPSGNR86L31B076I');
update rental 
set date_hour_delivery = '2020-01-01 16:23:00' , scooterrack_collection =  'RO004' , km_traveled = 1.9
where order_number = '1';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('ZN50ZR', '2020-02-03 10:23:54', 'RO004', 'MTRGPP52M65I608H');
update rental 
set date_hour_delivery = '2020-02-03 14:23:54' , scooterrack_collection =  'RO005' , km_traveled = 1.5
where order_number = '2';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('GDVAV6', '2020-01-05 12:00:00', 'RO003', 'TRZDIA99L60L157U');
update rental 
set date_hour_delivery = '2020-01-05 12:20:20' , scooterrack_collection =  'RO002', km_traveled = 2.0
where order_number = '3';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-02-01 13:30:30', 'RO003', 'ZREFNC99T16L781Q');
update rental 
set date_hour_delivery = '2020-02-01 13:35:30' , scooterrack_collection =  'RO003', km_traveled = 0.2
where order_number = '4';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('IPDZV1', '2020-01-02 09:23:54', 'RO001', 'LBRPPN14S07D612X');
update rental 
set date_hour_delivery = '2020-01-02 10:23:54' , scooterrack_collection =  'RO005', km_traveled = 1.1
where order_number = '5';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('P1CR57', '2020-01-10 10:00:54', 'RO005', 'SCNGNN99D09C352U');
update rental 
set date_hour_delivery = '2020-01-10 10:15:54' , scooterrack_collection =  'RO001', km_traveled = 2.3
where order_number = '6';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO8', '2020-02-01 10:23:54', 'RO001', 'PSCGLI56G45I623E');
update rental 
set date_hour_delivery = '2020-02-01 12:23:54' , scooterrack_collection =  'RO001', km_traveled = 5.9
where order_number = '7';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('4911OE', '2020-02-14 12:00:00', 'RO002', 'RSSFRC83L49F111G');
update rental 
set date_hour_delivery = '2020-02-14 14:00:00' , scooterrack_collection =  'RO003', km_traveled = 6.0
where order_number = '8';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('DTQZ8M', '2020-08-01 10:23:00', 'RO004', 'ZREFNC99T16L781Q');
update rental 
set date_hour_delivery = '2020-08-01 16:23:00' , scooterrack_collection =  'RO004', km_traveled = 10.9
where order_number = '9';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-08-01 13:30:30', 'RO003', 'SPSGNR86L31B076I');
update rental 
set date_hour_delivery = '2020-08-01 13:35:30' , scooterrack_collection =  'RO001', km_traveled = 3.4
where order_number = '10';


insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO8', '2020-08-01 10:00:00', 'RO001', 'PSCGLI56G45I623E');
update rental 
set date_hour_delivery = '2020-08-01 12:00:00' , scooterrack_collection =  'RO003', km_traveled = 3.0
where order_number = '11';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO8', '2020-08-01 14:15:00', 'RO003', 'SPSGNR86L31B076I');
update rental 
set date_hour_delivery = '2020-08-01 15:15:00' , scooterrack_collection =  'RO001', km_traveled = 3.0
where order_number = '12';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('LL52EU', '2020-01-05 08:46:50', 'RO006', 'RGNCMN78D10A399I');
update rental 
set date_hour_delivery = '2020-01-05 09:00:50' , scooterrack_collection =  'RO006', km_traveled = 0.9
where order_number = '13';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO9', '2020-01-20 18:40:32', 'RO002', 'SNTGPP86E10A465X');
update rental 
set date_hour_delivery = '2020-01-20 19:10:32' , scooterrack_collection =  'RO002', km_traveled = 0.9
where order_number = '14';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO9', '2020-02-21 19:00:00', 'RO002', 'SNTGPP86E10A465X'); 
update rental 
set date_hour_delivery = '2020-02-21 20:00:00' , scooterrack_collection =  'RO002', km_traveled = 1.6
where order_number = '15';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-02-22 19:00:00', 'RO002', 'SNTGPP86E10A465X');
update rental 
set date_hour_delivery = '2020-02-22 20:00:00' , scooterrack_collection =  'RO002', km_traveled = 3.4
where order_number = '16';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('LL52EU', '2020-08-05 08:46:50', 'RO006', 'RGNCMN78D10A399I');
update rental 
set date_hour_delivery = '2020-08-05 09:00:50' , scooterrack_collection =  'RO006', km_traveled = 2.9
where order_number = '17';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('4911OE', '2020-02-13 12:00:00', 'RO002', 'RSSFRC83L49F111G');
update rental 
set date_hour_delivery = '2020-02-13 14:00:00' , scooterrack_collection =  'RO003', km_traveled = 7.7
where order_number = '18';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-03-05 08:46:50', 'RO006', 'SNTGPP86E10A465X');
update rental 
set date_hour_delivery = '2020-03-05 09:10:50' , scooterrack_collection =  'RO006', km_traveled = 2.2
where order_number = '19';

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-04-05 08:46:50', 'RO006', 'SNTGPP86E10A465X');
update rental 
set date_hour_delivery = '2020-04-05 09:10:50' , scooterrack_collection =  'RO006', km_traveled = 4.0
where order_number = '20';

-------------------------- Start Rentals not finished yet --------------------------------

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO8', '2020-08-10 17:00:00', 'RO001', 'SCNGNN99D09C352U');
insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('L2AYO9', '2020-08-10 14:15:00', 'RO003','SPSGNR86L31B076I');

insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('LL52EU', '2020-08-10 08:30:00', 'RO006', 'RGNCMN78D10A399I');
insert into rental(id_scooter, date_hour_collection, scooterrack_collection, costumer) values
('QAWIOI', '2020-02-20 19:00:00', 'RO002', 'SNTGPP86E10A465X');
------------------------------------------------------------------------------------------------

--entering PaymentWithSubscription
insert into PaymentWithSubscription(date_hour, order_number, id_card) values
('2020-02-14 14:00:00', 1, 4);

--entering PaymentWithoutSubscription
insert into PaymentWithoutSubscription(price, date_hour, order_number) values
(91, '2020-08-01 16:23:00', 2),
(4.6, '2020-08-05 09:00:50', 3),
(1.5, '2020-08-01 13:35:30', 4),
(31, '2020-08-01 12:00:00', 5),
(16,'2020-08-01 15:15:00', 6),
(91, '2020-02-13 14:00:00', 18);
