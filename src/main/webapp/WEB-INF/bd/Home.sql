use mavenprojectDB;
#创建首页数据--首页导航栏
create table home_navigationBar
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '图片',
	title varchar(30) not null comment '标题',
	backgroundColor varchar(20) not null comment '背景颜色',
	date date not null comment '日期',
	homeNavigationBar varchar(50) comment '本地资源',
	homeNavigationBarPath varchar(1000) comment '其他资源路径'
);
insert into home_navigationBar values
	(null,'5120dd597d91e201cff50567c6576929.jpg','初音未来殿堂精选第十二弹','red',now(),null,'http://player.pptv.com/iframe/index.html#id=29110628&ctx=o%3Dv_share');
	
select * from home_navigationBar;