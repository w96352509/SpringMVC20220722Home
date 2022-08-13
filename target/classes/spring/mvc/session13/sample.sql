create table if not exists employee(
 eid integer primary key not null auto_increment,
 ename varchar(30),
 salary integer,
 createtime datetime default current_timestamp 
);

create table if not exists job(
 jid integer primary key not null auto_increment,
 jname varchar(30),
 eid integer not null,
 foreign key(eid) references employee(eid)
);

-- 建立 Employee 範例資料
insert into employee(ename, salary) values('John', 40000);
insert into employee(ename, salary) values('Mary', 50000);
insert into employee(ename, salary) values('Bobo', 60000);
insert into employee(ename, salary) values('Mark', 70000);

-- 建立 Job 範例資料
insert into job(jname, eid) values('Job A', 1);
insert into job(jname, eid) values('Job B', 1);
insert into job(jname, eid) values('Job C', 2);
insert into job(jname, eid) values('Job D', 2);
insert into job(jname, eid) values('Job E', 4);
insert into job(jname, eid) values('Job F', 4);
insert into job(jname, eid) values('Job G', 4);