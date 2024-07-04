create table facility (id integer not null, name varchar(255) not null, primary key (id));
create table hotel (id integer not null, location varchar(255), name varchar(255) not null, primary key (id));
create table hotel_facilities (facility_id integer not null, hotel_id integer not null);
create table reservation (check_in date not null, check_out date not null, confirm_check_in boolean not null, confirm_check_out boolean not null, id integer not null, paymentuuid uuid, pre_authorize_paymentuuid uuid, contact varchar(255) not null, name varchar(255) not null, primary key (id));
create table reservation_rooms (reservation_id integer not null, rooms_id integer not null unique);
create table review (hotel_id integer not null, note integer, reservation_id integer not null, primary key (hotel_id, reservation_id));
create table room (hotel_id integer not null, id integer not null, max_guests integer, price float(53), status varchar(255) check (status in ('AVAILABLE','IN_USE','RESERVED')), primary key (id));
create table room_facilities (facility_id integer not null, room_id integer not null);

alter table if exists hotel_facilities add constraint FKochg69skid3t4vv120ogbb1m4 foreign key (facility_id) references facility;
alter table if exists hotel_facilities add constraint FKh872q7p8r4jffpskko5ua31h5 foreign key (hotel_id) references hotel;
alter table if exists reservation_rooms add constraint FK3909f3xr1eghx60b7q08nsobj foreign key (rooms_id) references room;
alter table if exists reservation_rooms add constraint FKruieejln87l05fn90ykdy9d2 foreign key (reservation_id) references reservation;
alter table if exists review add constraint FK7tyi0jd0eaphyr0gsvfjqww9i foreign key (reservation_id) references reservation;
alter table if exists review add constraint FKi0ly7ivbh8ijdgoi7cwtuoavt foreign key (hotel_id) references hotel;
alter table if exists room add constraint FKdosq3ww4h9m2osim6o0lugng8 foreign key (hotel_id) references hotel;
alter table if exists room_facilities add constraint FK8kc1d6vfrkrohamqwu6vx87xb foreign key (facility_id) references facility;
alter table if exists room_facilities add constraint FKqu8hof7g5k9ypcai41nru1b3e foreign key (room_id) references room;