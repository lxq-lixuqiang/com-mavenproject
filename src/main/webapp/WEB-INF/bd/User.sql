use mavenprojectDB;
#创建用户数据--用户图片
create table user_userHeadPicture
(
	id bigint auto_increment primary key comment '主键，标识列',
	headPicture varchar(50) not null comment '用户图片'
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

#创建用户数据--用户信息
create table t_user
(
	id int primary key auto_increment comment '主键，标识列',
	userImg varchar(60) not null comment '用户头像，不能为空',
	username varchar(10) unique not null comment '用户名，唯一，不能为空',
	password varchar(16) not null comment '密码，不能为空',
	phone varchar(11) unique not null comment '手机号码，唯一，不能为空',
	loginIdentity char(20) not null comment '登录身份，不能为空',
	sex char(10) not null comment '性别：男，女，保密，不能为空',
	hobby varchar(50) comment '兴趣爱好',
	birthData date comment '出生日期',
	address varchar(20) comment '地址',
	email varchar(20) comment '邮箱',
	userHeadPictureId bigint not null comment '引用外键表user_userHeadPicture',
	foreign key(userHeadPictureId) references user_userHeadPicture(id)
);
insert  into t_user values 
	(null,'root.jpg','root','root123','13881123204','Administrators','Male','编程，游戏','1998-11-23','成都市','root@123.com',12),
	(null,'RK.jpg','RK','RK12345','18012345781','User','Female','','2000-01-01','北京市','RK@666.com',1),
	(null,'zhangsan.jpg','张三','111111','18234532321','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'bilibili.jpg','bilibili','0987654321','18523456711','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'fengxiaoxiao.jpg','枫潇潇','cccaaaca','13212121344','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'laozishiguanliyuan.jpg','老子是管理员','root123','18166666666','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'shenTMcaozuo.jpg','神TM操作','shentama','13343434343','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'chengxuyuan.jpg','程序猿','chengxuyuan','15232323212','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'yitiaoxianyu.jpg','一条咸鱼','xianyu','15254565321','User','Male','','2000-01-30','','',1),
	(null,'gebilaowang.jpg','隔壁老王','lebilaowang','13123457451','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'wogebiyoulaowang.jpg','我隔壁有老王','wo12345','18332342342','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','EX咖喱棒','exexexex','15234546772','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','龙珠迷','longzhumi','18237454232','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','核平的一天','he1111','13881123451','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','66666','666666','13212121666','User','Secrecyt',NULL,NULL,NULL,NULL,1),
	(null,'default.jpg','单身狗','121211','13212111111','User','Secrecyt',NULL,NULL,NULL,NULL,1);
select * from t_user;

#创建用户数据--消息反馈
create table user_messageFeedback
(
	id bigint auto_increment primary key comment '主键，标识列',
	title varchar(30) not null comment '标题',
	sender varchar(10) not null comment '发件人',
	dateTime datetime not null comment '日期',
	type int not null comment '类型：问题--0，建议--1',
	content varchar(500) not null comment '内容'
);
insert into user_messageFeedback values
(null,'游戏视频出现插件问题是怎么回事','张三',now(),0,'游戏视频出现插件问题是怎么回事？？？？'),
(null,'新功能','RK',now(),1,'希望添加一个可以看漫画的功能！！');
select * from user_messageFeedback;