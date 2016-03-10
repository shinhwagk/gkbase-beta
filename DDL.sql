grant select,update,delete,insert on g_note.* to test_n@'%';
flush privileges;
--笔记数据库
create database g_note;

--top种类
create table g_note.category (
  id int auto_increment primary key,
  name varchar(20) not null,
  father_id int,
  createdata datetime not null,
  updatedata datetime not null,
  foreign key (father_id) references g_note.category(id)
);
--测试数据
insert into g_note.category values(3,'backup',null,now(),now());

--note内容
create table g_note.content (
  id int auto_increment primary key,
  content_1 varchar(2000),
  content_2 varchar(2000),
  category_id int not null,
  createdata datetime not null,
  updatedata datetime not null,
  document_id int,
  foreign key (category_id) REFERENCES g_note.category(id),
  foreign key (document_id) REFERENCES g_note.document(id)
);

--文档编号
create table g_note.document(
  id int auto_increment primary key
);
