# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table global_message (
  id                        integer auto_increment not null,
  message_code              varchar(255),
  send_to                   integer,
  send_on                   varchar(255),
  constraint pk_global_message primary key (id))
;

create table gpointer (
  b_id                      varchar(255) not null,
  name                      varchar(255),
  lati                      float,
  lngi                      float,
  constraint pk_gpointer primary key (b_id))
;

create table nominee_views (
  parent_id                 varchar(255),
  user_id                   varchar(255),
  is_nominee                integer)
;

create table panic_master (
  id                        integer auto_increment not null,
  panic_code                varchar(255),
  user_id                   varchar(255),
  latitude                  varchar(255),
  longitude                 varchar(255),
  is_active                 integer,
  date_created              varchar(255),
  date_modified             varchar(255),
  constraint pk_panic_master primary key (id))
;

create table person (
  id                        varchar(255) not null,
  name                      varchar(255),
  utype                     varchar(255),
  constraint pk_person primary key (id))
;

create table personal_message_status (
  id                        integer auto_increment not null,
  message                   varchar(255),
  sender_id                 integer,
  receiver_id               integer,
  is_read                   integer,
  is_deleted                integer,
  date_send                 varchar(255),
  date_read                 varchar(255),
  date_delete               varchar(255),
  constraint pk_personal_message_status primary key (id))
;

create table user (
  id                        integer auto_increment not null,
  code                      varchar(255),
  registration_key          varchar(255),
  name                      varchar(255),
  gender                    varchar(255),
  mobile_number             varchar(255),
  email_id                  varchar(255),
  device_id                 varchar(255),
  profile_picture_name      varchar(255),
  social_media_details      varchar(255),
  ble_id                    varchar(255),
  is_clove_user             integer,
  clove_device_ble_id       varchar(255),
  status                    integer,
  is_deleted                integer,
  date_created              varchar(255),
  date_modified             varchar(255),
  update_count              integer,
  constraint pk_user primary key (id))
;

create table user_location (
  id                        integer auto_increment not null,
  user_id                   varchar(255),
  panic_code                varchar(255),
  latitude                  varchar(255),
  longitude                 varchar(255),
  located_on                varchar(255),
  constraint pk_user_location primary key (id))
;

create table user_nominee (
  id                        integer auto_increment not null,
  user_id                   integer,
  nominee_contact_number    varchar(255),
  nominee_name              varchar(255),
  accept_status             integer,
  created_by                integer,
  status                    integer,
  date_created              varchar(255),
  date_modified             varchar(255),
  constraint pk_user_nominee primary key (id))
;

create table user_temp_nominee (
  id                        integer auto_increment not null,
  user_id                   varchar(255),
  panic_code                varchar(255),
  forwarded_by              varchar(255),
  needy_id                  varchar(255),
  forwarded_on              varchar(255),
  constraint pk_user_temp_nominee primary key (id))
;

alter table panic_master add constraint fk_panic_master_panicUser_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_panic_master_panicUser_1 on panic_master (user_id);
alter table personal_message_status add constraint fk_personal_message_status_user_2 foreign key (sender_id) references user (id) on delete restrict on update restrict;
create index ix_personal_message_status_user_2 on personal_message_status (sender_id);
alter table user_nominee add constraint fk_user_nominee_nomineeUser_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_nominee_nomineeUser_3 on user_nominee (user_id);
alter table user_nominee add constraint fk_user_nominee_creator_4 foreign key (created_by) references user (id) on delete restrict on update restrict;
create index ix_user_nominee_creator_4 on user_nominee (created_by);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table global_message;

drop table gpointer;

drop table nominee_views;

drop table panic_master;

drop table person;

drop table personal_message_status;

drop table user;

drop table user_location;

drop table user_nominee;

drop table user_temp_nominee;

SET FOREIGN_KEY_CHECKS=1;

