insert into meals
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'gazar, kartofil, tavari mis', 'msov ragu', 28.50);
insert into meals
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'havi mis, jarit, sunk',
        'tapakac havi mis kartofilov es snkov', 30.0);
insert into meals
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'lolik, varung, kanachexen,feta panir', 'hunakan axcan',
        15.8);

insert into users
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'abgarAharonyan@gmail.com', 'Abgar', '+37491054089',
        'Aharonyan', 'abgar96', null);
insert into users
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'anahitJabaxyan@gmail.com', 'Anahit', '+37493067809',
        'Jabaxyan', 'anahit36', null);
insert into users
values (nextval('hibernate_sequence'), uuid_generate_v4(), 'gagikShamshyan@gmail.com', 'Gagik', '+37495404909',
        'Shamshyan', 'gagik98', null);

insert into tables
values (nextval('hibernate_sequence'), uuid_generate_v4(), null, null, true, 4);
insert into tables
values (nextval('hibernate_sequence'), uuid_generate_v4(), null, null, false, 4);
insert into tables
values (nextval('hibernate_sequence'), uuid_generate_v4(), null, null, true, 4);
