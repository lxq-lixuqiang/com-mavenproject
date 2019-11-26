use mavenprojectDB;
#�����û�����--�û�ͼƬ
create table user_userHeadPicture
(
	id bigint auto_increment primary key comment '��������ʶ��',
	headPicture varchar(50) not null comment '�û�ͼƬ'
);
insert into user_userHeadPicture values
	(null,'2018081315535531922.png'),
	(null,'2018081315424687423.png'),
	(null,'2018081315442328096.png'),
	(null,'2018081315452168764.png'),
	(null,'2018081315460328988.png'),
	(null,'2018081315465970627.png'),
	(null,'2018081315474993507.png'),
	(null,'2018081315483682367.png'),
	(null,'2018081315493052215.png'),
	(null,'2018081315501839527.png'),
	(null,'2018081315510647985.png'),
	(null,'2018081315515213135.png'),
	(null,'2018081315530629879.png'),
	(null,'2018081315535531936.png');
select * from user_userHeadPicture;

#�����û�����--�û���Ϣ
create table t_user
(
	id int primary key auto_increment comment '��������ʶ��',
	userImg varchar(60) not null comment '�û�ͷ�񣬲���Ϊ��',
	username varchar(10) unique not null comment '�û�����Ψһ������Ϊ��',
	password varchar(16) not null comment '���룬����Ϊ��',
	phone varchar(11) unique not null comment '�ֻ����룬Ψһ������Ϊ��',
	loginIdentity char(20) not null comment '��¼��ݣ�����Ϊ��',
	sex char(10) not null comment '�Ա��У�Ů�����ܣ�����Ϊ��',
	hobby varchar(50) comment '��Ȥ����',
	birthData date comment '��������',
	address varchar(20) comment '��ַ',
	email varchar(20) comment '����',
	userHeadPictureId bigint not null comment '���������user_userHeadPicture',
	foreign key(userHeadPictureId) references user_userHeadPicture(id)
);
insert  into t_user values 
	(null,'root.jpg','root','root123','13881123204','Administrators','Male','��̣���Ϸ','1998-11-23','�ɶ���','root@123.com',12),
	(null,'RK.jpg','RK','RK12345','18012345781','User','Female','','2000-01-01','������','RK@666.com',1),
	(null,'zhangsan.jpg','����','111111','18234532321','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'bilibili.jpg','bilibili','0987654321','18523456711','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'fengxiaoxiao.jpg','������','cccaaaca','13212121344','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'laozishiguanliyuan.jpg','�����ǹ���Ա','root123','18166666666','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'shenTMcaozuo.jpg','��TM����','shentama','13343434343','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'chengxuyuan.jpg','����Գ','chengxuyuan','15232323212','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'yitiaoxianyu.jpg','һ������','xianyu','15254565321','User','Male','','2000-01-30','','',1),
	(null,'gebilaowang.jpg','��������','lebilaowang','13123457451','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'wogebiyoulaowang.jpg','�Ҹ���������','wo12345','18332342342','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','EX��ର�','exexexex','15234546772','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','������','longzhumi','18237454232','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','��ƽ��һ��','he1111','13881123451','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','66666','666666','13212121666','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','����','121211','13212111111','User','Secrecyt',NULL,NULL,NULL,NULL,1);
select * from t_user;

#�����û�����--��Ϣ����
create table user_messageFeedback
(
	id bigint auto_increment primary key comment '��������ʶ��',
	title varchar(30) not null comment '����',
	sender varchar(10) not null comment '������',
	dateTime datetime not null comment '����',
	type int not null comment '���ͣ�����--0������--1',
	content varchar(500) not null comment '����'
);
insert into user_messageFeedback values
(null,'��Ϸ��Ƶ���ֲ����������ô����','����',now(),0,'��Ϸ��Ƶ���ֲ����������ô���£�������'),
(null,'�¹���','RK',now(),1,'ϣ�����һ�����Կ������Ĺ��ܣ���');
select * from user_messageFeedback;