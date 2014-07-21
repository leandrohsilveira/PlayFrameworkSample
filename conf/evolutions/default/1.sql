# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id                        bigint not null,
  ativo                     boolean not null,
  nome                      varchar(50),
  login                     varchar(16),
  senha                     varchar(16),
  email                     varchar(50),
  papel                     varchar(13) not null,
  constraint ck_usuario_papel check (papel in ('ADMINISTRADOR','USUARIO')),
  constraint pk_usuario primary key (id))
;

create sequence usuario_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists usuario_seq;

