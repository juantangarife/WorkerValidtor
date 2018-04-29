# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table productos (
  id                            bigserial not null,
  descripcion                   varchar(255),
  precio                        float,
  constraint pk_productos primary key (id)
);


# --- !Downs

drop table if exists productos cascade;

