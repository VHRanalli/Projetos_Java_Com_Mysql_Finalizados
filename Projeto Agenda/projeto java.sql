/*# Estudo de caso - Simples Agenda de contato
#@Author -> Victor Henrique Ranalli Barbosa
#create database dbagenda;
#use dbagenda;
#drop table tb_contatos;

create database dbagenda;

use dbagenda;


create table tb_contatos(
id int primary key,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50)
);
#use Dbagenda;
#drop table tb_Contatos;

insert into tb_contatos values (
1,'Bill Gates','1111-1111','bil@outlook.com');

insert into tb_contatos values(
2,'Steve Jobs','2222-2222','steve_jobs@apple.com');

insert into tb_contatos values(
3,'linus Torvalds','3333-3333','linus@linux.com');

select * from tb_contatos;

create table tb_Enderecos(
CEP varchar(255) primary key,
lougradouro varchar(200) not null,
Ncasa int not null,
bairro varchar(150) not null,
complemento varchar(45),
cidade varchar(70)not null,
estado varchar(50)not null
);

describe tb_Enderecos;
*/

#drop table tb_Enderecos;
#use dbagenda;
#drop table tb_contatos;

/*create table tb_contatos(
id int primary key,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50),
CEP varchar(255) not null,
lougradouro varchar(200) not null,
Ncasa int not null,
bairro varchar(150) not null,
complemento varchar(45),
cidade varchar(70)not null,
estado varchar(50)not null
);*/

create table tb_contatos(
id int primary key,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50)
);
#use Dbagenda;
#drop table tb_Contatos;

insert into tb_contatos values (
1,'Bill Gates','1111-1111','bil@outlook.com');

insert into tb_contatos values(
2,'Steve Jobs','2222-2222','steve_jobs@apple.com');

insert into tb_contatos values(
3,'linus Torvalds','3333-3333','linus@linux.com');

select * from tb_contatos;

create table tb_Enderecos(
CEP varchar(255) primary key,
lougradouro varchar(200) not null,
Ncasa int not null,
bairro varchar(150) not null,
complemento varchar(45),
cidade varchar(70)not null,
estado varchar(50)not null
);

describe tb_Enderecos;


