# Marketplace schema creation

# === !Ups
create table "productos" ("id" SERIAL NOT NULL PRIMARY KEY,
"descripcion" VARCHAR NOT NULL,
"precio" numeric(21,8) NOT NULL);

DROP TABLE "productos";