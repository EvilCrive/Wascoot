--inserting into Admin--
insert into admin(id, email, password) values ('ad001', 'admin@wascoot.com', '123321');

--inserting into ScooterRacks--
insert into wascoot.public.scooterracks(id, tot_num_parking_spots, available_parking_spots, postalcode, address)values ('SR001', 20, 15, '35141', 'Via Eugana 80');

--inserting into Model--
insert into wascoot.public.model(name, brand, battery_life, price_per_min) VALUES ('Dot', 'Decathlon', '1 hour', 1.00);

insert into wascoot.public.customer(cf, name, surname, email, sex, birthdate, postalcode) VALUES ('PHNTEW74B37Z444L','Paria', 'Tahan', 'paria.tahan@gmail.com', 'F', '1994-02-09','35141');

insert into wascoot.public.paymentmethod(id, type) VALUES ('PM001', 'Credit Card');

insert into wascoot.public.subscription(id, type, number_daily_locks, validity_per_day, fixed_price) VALUES ('SB001', '1d', 2, '2h', 3.00);

insert into wascoot.public.scooter(id, km_traveled, model, id_scooter_rack) VALUES('SC001',50,'Dot', 'SR001');



