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
--create table if not exists role (
  --  id serial primary key ,
    --code varchar(250)
--);


create table if not exists "user" (
    id serial primary key ,
    role varchar(250),
   -- role_id int,
    name varchar (250),
    username varchar (250),
    password varchar (250)
   -- CONSTRAINT fk_role_id foreign key (role_id) references role(id)
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

create table if not exists category (
    id serial primary key ,
    category_id int,
    title varchar (250),
    CONSTRAINT fk_category_id foreign key (category_id) references category(id)
);

insert into user ( name, username, password) values (?,?,?,?);
update Article set title=?, price=? , category_id=?, user_id=? , approved = ? where id = ? ;
delete  from Article where id = ? ;
select * from Article inner join user u on u.id = Article.user_id inner join category c on Article.category_id = c.id ;
select * from shopping_card inner join Article A on A.id = shopping_card.article_id inner join "user" u on u.id = A.user_id inner join category c on c.id = A.category_id  where payed = false;





