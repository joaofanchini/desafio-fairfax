create table facility (id serial not null, name varchar(255) not null, primary key (id));
create table hotel (id serial not null, location varchar(255), name varchar(255) not null, primary key (id));
create table hotel_facilities (facility_id integer not null, hotel_id integer not null);
create table reservation (check_in date not null, check_out date not null, confirm_check_in boolean not null, confirm_check_out boolean not null, id serial not null, paymentuuid uuid, pre_authorize_paymentuuid uuid, contact varchar(255) not null, name varchar(255) not null, primary key (id));
create table reservation_rooms (reservation_id integer not null, room_id integer not null unique);
create table review (hotel_id integer not null, note integer, reservation_id integer not null, primary key (hotel_id, reservation_id));
create table room (hotel_id integer not null, id serial not null, max_guests integer, price float(53), status varchar(255) check (status in ('AVAILABLE','IN_USE','RESERVED')), primary key (id));
create table room_facilities (facility_id integer not null, room_id integer not null);

alter table if exists hotel_facilities add constraint fk_hotel_facilities_facility foreign key (facility_id) references facility;
alter table if exists hotel_facilities add constraint fk_hotel_facilities_hotel foreign key (hotel_id) references hotel;
alter table if exists reservation_rooms add constraint fk_resertion_rooms_room foreign key (room_id) references room;
alter table if exists reservation_rooms add constraint fk_resertion_rooms_reservation foreign key (reservation_id) references reservation;
alter table if exists review add constraint fk_review_reservation foreign key (reservation_id) references reservation;
alter table if exists review add constraint fk_review_hotel foreign key (hotel_id) references hotel;
alter table if exists room add constraint fk_room_hotel foreign key (hotel_id) references hotel;
alter table if exists room_facilities add constraint fk_room_facilities_facility foreign key (facility_id) references facility;
alter table if exists room_facilities add constraint fk_room_facilities_room foreign key (room_id) references room;