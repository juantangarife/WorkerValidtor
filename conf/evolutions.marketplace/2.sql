# Marketplace schema inserts

# === !Ups
INSERT INTO productos (id,descripcion,precio) VALUES(1,'lampara', 25000);
INSERT INTO productos (id,descripcion,precio) VALUES(2,'bombillo', 4200);
INSERT INTO productos (id,descripcion,precio) VALUES(3,'lampara rustica', 60000);
INSERT INTO productos (id,descripcion,precio) VALUES(4,'lampara moderna', 80000);

# === !Downs
DELETE FROM compras_de_productos;
DELETE FROM compras;
DELETE FROM usuarios;
DELETE FROM productos;