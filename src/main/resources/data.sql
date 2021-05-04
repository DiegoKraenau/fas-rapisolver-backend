--Roles
INSERT INTO roles (id,can_publish,name) VALUES  (1,true,'ROLE_CUSTOMER');
INSERT INTO roles (id,can_publish,name) VALUES  (2,true,'ROLE_SUPPLIER');
--Users
/*INSERT INTO users(
    id, birthdate, email, first_name, last_name, password, phone, role_id)
VALUES (1, '2004-05-15','diegokraenau@gmail.com', 'diego', 'kraenau', 'password', '994587123', 1);
INSERT INTO users(
    id, birthdate, email, first_name, last_name, password, phone, role_id)
VALUES (2, '2004-08-20','jesusgonzalo@gmail.com', 'jesus', 'duran', 'password', '994587666', 1);*/
--SupplierAttentions
INSERT INTO  supplier_attentions (id,price) VALUES (1,300);

