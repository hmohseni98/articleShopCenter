create table if not exists "user" (
     id serial primary key ,
     role varchar(250),
     name varchar (250),
     username varchar (250),
     password varchar (250)
);

create table if not exists category (
     id serial primary key ,
     category_id int,
     title varchar (250),
     CONSTRAINT fk_category_id foreign key (category_id) references category(id)
);
create table if not exists Article (
    id serial primary key ,
    title varchar(250),
    price int,
    category_id int,
    user_id int,
    approved boolean,
    CONSTRAINT fk_category_id foreign key (category_id) references category(id),
    CONSTRAINT fk_user_id foreign key (user_id) references "user"(id)
);


create table if not exists shopping_card (
    id serial primary key ,
    article_id int,
    user_id int,
    date date,
    payed boolean,
  CONSTRAINT fk_article_id foreign key (article_id) references Article(id),
  CONSTRAINT fk_user_id foreign key (user_id) references "user"(id)
);


