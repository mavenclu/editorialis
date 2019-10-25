insert into categories (name) values ('Astrophysics')
insert into categories (name) values ('Neuroscience')
insert into categories (name) values ('Behavioral neuroscience')
insert into categories (name) values ('Biology')
insert into categories (name) values ('Solid mechanics')
insert into categories (name) values ('Chemistry')
insert into categories (name) values ('Pharmacology')
insert into categories (name) values ('Biotechnology')

insert into manuscripts (title,  category_id, date_time_uploaded , closed) values ('Title comes here.', 1,   {ts '2019-08-17 18:47:52.69'}, false)
insert into manuscripts (title,  category_id, date_time_uploaded , closed) values ('Second manuscript comes here.', 2,    {ts '2019-08-17 18:47:52.69'}, false)

insert into authors (first_Name,last_Name, email, phone_Number, category_id) values ('John', 'Doe', 'john.doe@example.com', '123456789', 1)
insert into authors (first_Name,last_Name, email, phone_Number, category_id) values ('Audrey', 'Smith', 'au.smith@example.com', '456789123', 2)
insert into authors (first_Name,last_Name, email, phone_Number, category_id) values ('Thomas', 'Klein', 'jklein@example.com', '123489567', 1)

