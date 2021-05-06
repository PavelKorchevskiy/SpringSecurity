create table schoolboy (
    id serial primary key,
    name varchar (20) not null,
    rating integer
);
insert into schoolboy (name, rating) values
       ('Andry', 80),
       ('Alex', 90),
       ('Pasha', 70);
drop table if exists "user";
create table "user" (
    id serial primary key,
    schoolboy_id int not null ,
    password varchar(255) not null ,
    enabled boolean default true,
    foreign key (schoolboy_id) references schoolboy(id)
);
insert into "user" (schoolboy_id, password) VALUES
        (1, '$2y$12$Eh7vPRjDq.Ml7zn/onQO.u/hWyZB56.pqzz3F0tNTN3P0rQtXdLGe'),
        (2, '$2y$12$Eh7vPRjDq.Ml7zn/onQO.u/hWyZB56.pqzz3F0tNTN3P0rQtXdLGe'),
        (3, '$2y$12$Eh7vPRjDq.Ml7zn/onQO.u/hWyZB56.pqzz3F0tNTN3P0rQtXdLGe');
create table role (
    id serial primary key,
    role varchar(50) not null
);
insert into "role" (role) values ('ROLE_HEADMAN'),
                              ('ROLE_SPORTSMAN'),
                              ('ROLE_BOTAN');
create table user_role (
    user_id int not null,
    role_id int not null ,
    foreign key (user_id) references "user"(id),
    foreign key (role_id) references role(id)
);
insert into user_role(user_id, role_id) VALUES (1, 1),
                                               (2, 2),
                                               (3, 3);


