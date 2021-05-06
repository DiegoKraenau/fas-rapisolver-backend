--Roles
INSERT INTO roles (can_publish,name) VALUES  (true,'ROLE_CUSTOMER');
INSERT INTO roles (can_publish,name) VALUES  (true,'ROLE_SUPPLIER');

--Locations
INSERT INTO locations( address, city, country, state) VALUES ('Perú1', 'Perú2', 'Perú3', 'Perú4');
INSERT INTO locations( address, city, country, state) VALUES ('Chile1', 'Chile2', 'Chile3', 'Chile4');
INSERT INTO locations( address, city, country, state) VALUES ('Argentina1', 'Argentina2', 'Argentina3', 'Argentina4');
INSERT INTO locations( address, city, country, state) VALUES ('Mexico1', 'Mexico2', 'Mexico3', 'Mexico4');
--Users
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('CUSTOMER',  '2000-06-20 19:00:00', 'diegokraenau@gmail.com', 'Diego', 'Kraenau', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '123456789', null, null, 1, 1);
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('SUPPLIER', '1999-06-20 19:00:00', 'andre@gmail.com', 'Andre', 'Soncco', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '987654321', null, null, 2, 2);
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('SUPPLIER', '2000-11-08 19:00:00', 'danklp132@gmail.com', 'Daniel', 'Jauregui', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '987654321', null, null, 3, 2);
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('SUPPLIER', '2021-06-05 19:00:00', 'aliaga@ramirez@mail.com', 'Aliaga', 'Ramirez', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '987654321', null, null, 4, 2);

--Categories
INSERT INTO categories (name) VALUES ('Electricidad');
INSERT INTO categories (name) VALUES ('Educación');
--Attentions
INSERT INTO attentions (detail,name,category_id) VALUES ('Reparaciones','Reparacion de luces',1);
INSERT INTO attentions (detail,name,category_id) VALUES ('Enseñanza','Maestro',2);
INSERT INTO attentions (detail,name,category_id) VALUES ('Programador JAVA','Maestro Programador',2);
--SupplierAttentions
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (300,1,2);
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (400,2,2);
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (500,2,3);
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (200,2,4);
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (200,3,4);