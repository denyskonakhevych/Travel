CREATE TABLE country(
    id IDENTITY,
    name varchar(30),
	description varchar(500),
    climate varchar(50)
);

CREATE TABLE user(
    id IDENTITY,
    name_f varchar(20),
    name_s varchar(20),
    sex varchar(6),
    email varchar(40),
	password varchar(60),
    telephone varchar(15),
    address varchar(40)
);

CREATE TABLE analitic(
    id IDENTITY,
    name_f varchar(20),
    name_s varchar(20),
    email varchar(40),
	password varchar(60),
	possition varchar(40)
);

CREATE TABLE hottel(
    id IDENTITY,
    name varchar(30),
    stars INTEGER,
    country_id INTEGER,
    description varchar(500),
    CONSTRAINT hottel_country_fk FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE
);

CREATE TABLE tour(
    id IDENTITY,
    number_of_people INTEGER,
    date_from DATE,
    date_to DATE,
    hottel_id INTEGER,
    price FLOAT,
    CONSTRAINT tour_hottel_fk FOREIGN KEY (hottel_id) REFERENCES hottel(id) ON DELETE CASCADE
);

CREATE TABLE booking(
    id IDENTITY,
    tour_id INTEGER,
    ordered_by INTEGER,
    status varchar(15),
    managed_by INTEGER,
    total_price FLOAT,
    CONSTRAINT booking_tour_fk FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE,
    CONSTRAINT booking_user_fk FOREIGN KEY (ordered_by) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT booking_analitic_fk FOREIGN KEY (managed_by) REFERENCES analitic(id) ON DELETE CASCADE
);