--用户
create user gkbase identified by 'gkbase';
--grant select,update,delete,insert on g_note.* to g_note@'%';
grant all on g_note.* to gkbase@'%';
flush privileges;