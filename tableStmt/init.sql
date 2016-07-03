set global autocommit = 0;
set global tx_isolation = "read-committed";
set global character_set_database='utf8';
set global character_set_server='utf8';
grant all PRIVILEGES on *.* to root@'%' identified by '123456aA+';
flush privileges;
#create database g_note;

