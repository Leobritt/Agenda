create table contatos(
  id serial primary key,
  nome varchar(50) not null,
  fone varchar(20) not null, 
  email varchar(50));


insert into contatos (nome, fone,email) values ('Linus Torvalds','42 0923-2121','linustorvalds@hotmail.com') ;
