--Users
INSERT INTO users (id,email,password) VALUES  (1,'diegokraenau@gmail.com','diego2009');
INSERT INTO users (id,email,password) VALUES  (2,'diegokraenau2@gmail.com','diego20029');
INSERT INTO users (id,email,password) VALUES  (3,'dank_lp123@outlook.com','dank123');
INSERT INTO users (id,email,password) VALUES  (4,'danieljauregui@gmail.com','dank1234');

--Customers
INSERT INTO customers (id) VALUES  (1);
INSERT INTO customers (id) VALUES  (2);
--Supplier
INSERT INTO suppliers (id) VALUES (1);  /* dank_lp123@outlook.com   */      /*personas diferentes*/
INSERT INTO suppliers (id) VALUES (2);  /* danieljauregui@gmail.com */      /*personas diferentes*/
--Attentions
INSERT INTO attentions (id,name,detail) VALUES (1,'maestro','clases privadas');
INSERT INTO attentions (id,name,detail) VALUES (2,'programador','programador freelance');
--SupplierAttentions
INSERT INTO  supplier_attentions (id,price,attention_id,user_supplier_id) VALUES (1,300,1,1);
INSERT INTO  supplier_attentions (id,price,attention_id,user_supplier_id) VALUES (2,400,1,2);
INSERT INTO  supplier_attentions (id,price,attention_id,user_supplier_id) VALUES (3,500,2,2);