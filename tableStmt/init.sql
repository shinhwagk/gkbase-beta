#set global autocommit = 0; #mysql 5.1.73 不能再global级别设置这个参数
set global tx_isolation = "read-committed";
set global character_set_database='utf8';
set global character_set_server='utf8';
grant all PRIVILEGES on *.* to root@'%' identified by '123456aA+';
flush privileges;
#create database g_note;

