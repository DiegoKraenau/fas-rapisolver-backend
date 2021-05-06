--Roles
INSERT INTO roles (can_publish,name) VALUES  (true,'ROLE_CUSTOMER');
INSERT INTO roles (can_publish,name) VALUES  (true,'ROLE_SUPPLIER');

--Locations
INSERT INTO locations( address, city, country, state) VALUES ('Perú1', 'Perú2', 'Perú3', 'Perú4');
INSERT INTO locations( address, city, country, state) VALUES ('Chile1', 'Chile2', 'Chile3', 'Chile4');

--Users
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('CUSTOMER',  '2000-06-20 19:00:00', 'diegokraenau@gmail.com', 'Diego', 'Kraenau', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '123456789', null, null, 1, 1);
INSERT INTO users(user_type,  birthdate, email, first_name, last_name, password, phone, random_column, comercial_name, location_id, role_id)
VALUES ('SUPPLIER', '1999-06-20 19:00:00', 'andre@gmail.com', 'Andre', 'Soncco', '$2a$10$1o29yHo9p7wG17Uu9p/mduy7SAsEt4m4sWblY6jyO1I1ZtvQDCoh.', '987654321', null, null, 2, 2);

--Categories
INSERT INTO categories (name) VALUES ('Electricidad');

--Attentions
INSERT INTO attentions (name,category_id) VALUES ('Reparacion de luces',1);

--SupplierAttentions
INSERT INTO supplier_attentions (price,attention_id,user_supplier_id) VALUES (300,1,2);

