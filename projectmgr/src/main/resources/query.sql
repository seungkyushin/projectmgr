drop database todo_db; 
drop database projectmgr_db; 
 
Create database projectmgr_db; 
Use projectmgr_db; 


create table file_info(id int primary key auto_increment, 
save_path text NOT NULL,
url_path text NOT NULL,
type varchar(50) NOT NULL, 
name varchar(100) NOT NULL, 
create_date datetime  
) DEFAULT CHARSET=utf8; 

create table visiter(id int primary key auto_increment,  
name varchar(40) NOT NULL, 
password varchar(255) NOT NULL, 
email varchar(30) unique key NOT NULL,
organization varchar(50) DEFAULT 'NONE',
file_id int DEFAULT 4,
create_date datetime NOT NULL, 
last_login_date datetime  DEFAULT '0000-00-00 00:00:00',
FOREIGN KEY (file_id) REFERENCES file_info(id)
) DEFAULT CHARSET=utf8; 

create table log(id int primary key auto_increment, 
create_date datetime NOT NULL, 
type varchar(50) NOT NULL, 
description text NOT NULL, 
client_ip varchar(50) NOT NULL, 
visiter_email varchar(30) NOT NULL 
) DEFAULT CHARSET=utf8; 

create table project(id int primary key auto_increment, 
name varchar(100) NOT NULL, 
sub_description text NOT NULL, 
url text NOT NULL, 
description text NOT NULL, 
file_id int , 
FOREIGN KEY (file_id) REFERENCES file_info(id) 
) DEFAULT CHARSET=utf8; 

create table user_comment(id int primary key auto_increment, 
content text NOT NULL, 
score decimal(5,1)  NOT NULL, 
type varchar(20) NOT NULL, 
show_check varchar(10) DEFAULT 'off', 
create_date datetime  NOT NULL, 
project_id int  NOT NULL, 
visiter_id int  NOT NULL, 
FOREIGN KEY (project_id) REFERENCES project(id), 
FOREIGN KEY (visiter_id) REFERENCES visiter(id) 
) DEFAULT CHARSET=utf8; 

Create database todo_db; 
Use todo_db; 

create table todo(id int primary key auto_increment, 
email varchar(50), 
regdate datetime NOT NULL, 
sequence int NOT NULL, 
title varchar(100)  NOT NULL, 
type varchar(50)  NOT NULL 

)DEFAULT CHARSET=utf8;  

Use projectmgr_db; 

INSERT INTO file_info(save_path,url_path,type,name,create_date)
VALUES('C:\\Users\\Administrator\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images','images/todo.gif','image/gif', 'todo.gif', now()); 
INSERT INTO file_info(save_path,url_path,type,name,create_date) 
VALUES('C:\\Users\\Administrator\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images','images/naver.gif','image/gif', 'naver.gif', now()); 
INSERT INTO file_info(save_path,url_path,type,name,create_date) 
VALUES('C:\\Users\\Administrator\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images','images/movie.png','image/png', 'move.png', now()); 
INSERT INTO file_info(save_path,url_path,type,name,create_date) 
VALUES('C:\\Users\\Administrator\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images','images/upload/defaultImage.jpg','image/jpg', 'defaultImage.jpg', now()); 

INSERT INTO project(name, url, sub_description, description, file_id) VALUES('TODO LIST','http://localhost:8080/Todo/main.jsp', '해야할 일을 등록하고 관리합니다.', 'MYSQL DB에 연결하여 입력한 데이터를 저장하고 AJAX를 통해 화면 갱신없이 DATA를 갱신하는 부분을 연습했습니다.',1);  
INSERT INTO project(name, url, sub_description, description, file_id) VALUES('NAVER RESERVATION','http://localhost:8080/naverreserve/main', '상품을 예약하고 확인 및 관리 합니다.', 'edwith에서 제공하는 Full Stack Web Developer 강의로 공부하면서 웹에 대한 기초적인 개발기술인 HTML,JAVASCRIPT,CSS, JSP, SPRING을 활용해 봤습니다.',2); 
INSERT INTO project(name, url, sub_description, description, file_id) VALUES('WEB DBMS','http://localhost:8080/main', '웹으로  DATA를 관리 합니다.', 'COMMING SOON',3);



