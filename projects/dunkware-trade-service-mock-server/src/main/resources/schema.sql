CREATE TABLE IF NOT EXISTS t_user(
   userId INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
   password varchar(256),
   firstName varchar(256),
   lastName varchar(200),
   email varchar(200),
   address varchar(250),
   updatedOn TIMESTAMP,
   createdOn TIMESTAMP
 );


--insert INTO t_user
--    (userId, firstName, lastName, email, address, password)
--VALUES
--    (1, 'admin', 'admin', 'admin@gmail.com','test123','test@123');