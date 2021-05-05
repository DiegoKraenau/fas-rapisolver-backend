--Roles
INSERT INTO roles (id,can_publish,name) VALUES  (1,true,'ROLE_CUSTOMER');
INSERT INTO roles (id,can_publish,name) VALUES  (2,true,'ROLE_SUPPLIER');
--Category
INSERT INTO categories (id,name,description) VALUES (1,'educación','enseñanza');
--Attention
INSERT INTO attentions(id,name,detail,category_id) VALUES (1,'maestro','clases privadas',1);
--Users
INSERT INTO users(
    user_type,id,birthdate,email,first_name,last_name,password,phone,random_column,comercial_name,role_id)
VALUES ('SUPPLIER', 1,'2021-05-04 21:21:12.78', 'dank.jauregui@gmail.com', 'daniel', 'jauregui', '$2a$10$nbZWnAyO4ZxVfMfzRbZwAeCfIa2f/rTfSAi0SBitNp41/3qEPTyh2', '123456789',null,'Brillo',2);/*
INSERT INTO users(
    id, birthdate, email, first_name, last_name, password, phone, role_id)
VALUES (2, '2004-08-20','jesusgonzalo@gmail.com', 'jesus', 'duran', 'password', '994587666', 1);
INSERT INTO users(
    id, birthdate, email, first_name, last_name, password, phone, role_id)
VALUES (3, '2004-08-20','dadad@gmail.com', 'dadad', 'dada', 'czczcz', '949494949', 2);
*/
--SupplierAttentions
INSERT INTO  supplier_attentions (id,price,attention_id,user_supplier_id) VALUES (1,300,1,1);
