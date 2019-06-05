# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table avion (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  nombrearchivo                 varchar(255),
  tiempoaparicion               integer not null,
  tiempoaterrizajemastemprano   integer not null,
  tiempoaterrizajemasoptimo     integer not null,
  tiempoaterrizajemastarde      integer not null,
  costoporunidadtiempoantesoptimo double not null,
  costoporunidadtiempodespuesoptimo double not null,
  tiempoaterrizajereal          integer not null,
  constraint pk_avion primary key (id)
);


# --- !Downs

drop table if exists avion;

