INSERT INTO Users (Usuario, Contrasena, Laboratorio, Nombre, Apellidos, enabled) VALUES ('Alvaro89', '$2a$10$mqROTo5aVwlKL/SuGvaftuY/Ws/JDuEjHTETzvo4xuB4WkFLo/bTO', 'Medicina Regenerativa', 'Alvaro', 'Alcazar', 1);

INSERT INTO Users (Usuario, Contrasena, Laboratorio, Nombre, Apellidos, enabled) VALUES ('Invitado', '$2a$10$xShPLOFe.huDWi3E7X9d/eu/nHYr9fDiy8iMlAFiZ/Xif3JL9YWSS', 'Reuma', 'John', 'Witch', 1);

INSERT INTO Users (Usuario, Contrasena, Laboratorio, Nombre, Apellidos, enabled) VALUES ('Administrador', '$2a$10$29nWkD2YP6MoII2qNj4SyeF3RU36LMScOOd3EY4BV57JtGfovSR8K', 'Reuma', 'John', 'Witch', 1);

INSERT INTO Users (Usuario, Contrasena, Laboratorio, Nombre, Apellidos, enabled) VALUES ('Prueba', 'prueba', 'Reuma', 'Jeny', 'Witch', 1);


INSERT INTO Authorities (user_id, authority) VALUES (1, 'ROLE_USER');

INSERT INTO Authorities (user_id, authority) VALUES (2, 'ROLE_USER');

INSERT INTO Authorities (user_id, authority) VALUES (3, 'ROLE_ADMIN');

INSERT INTO Authorities (user_id, authority) VALUES (4, 'ROLE_USER');

INSERT INTO Rack (Tipo_Rack, Stock, Total) VALUES ('Grande', 1, 5);
INSERT INTO Rack (Tipo_Rack, Stock, Total) VALUES ('Pequeño', 20, 20);
INSERT INTO Rack (Tipo_Rack, Stock, Total) VALUES ('Pequeño Ventilado', 20, 20);




INSERT INTO Especie (Codigo_Jaula, Nombres, Laboratorio, Responsable, Especie, Cepa, Tipo_Rack, Ratas_Hembra, Ratas_Macho, Fecha_Nacimiento, Procedimiento, Observaciones, Fecha_Reserva) VALUES('A01', '1.1', 'Medicina Regenerativa', 'Alvaro', 'Rata', 'Sprague', 'Grande', 0, 1, '2020-03-05', 'SUI', 'Todo bien', '2020-05-30');

INSERT INTO Especie (Codigo_Jaula, Nombres, Laboratorio, Responsable, Especie, Cepa, Tipo_Rack, Ratas_Hembra, Ratas_Macho, Fecha_Nacimiento, Procedimiento, Observaciones, Fecha_Reserva) VALUES('A02', '2.1', 'Medicina Regenerativa', 'Juan', 'Rata', 'Sprague', 'Grande', 0, 3, '2020-03-05', 'SUI', 'Todo bien', '2020-05-29');

INSERT INTO Especie (Codigo_Jaula, Nombres, Laboratorio, Responsable, Especie, Cepa, Tipo_Rack, Ratas_Hembra, Ratas_Macho, Fecha_Nacimiento, Procedimiento, Observaciones, Fecha_Reserva) VALUES('A03', '1.1', 'Reuma', 'Alvaro', 'Rata', 'Sprague', 'Grande', 0, 1, '2020-03-05', 'SUI', 'Todo bien', '2020-05-30');


