use mavenprojectDB;
#创建壁纸数据--导航栏壁纸
create table wallpaper_navigationBar
(
	id bigint auto_increment primary key comment '主键，标识列',
	wallpaper varchar(50) not null comment '壁纸'
);
INSERT into wallpaper_navigationBar values
	(null,'logwallpaper.jpg');
select * from wallpaper_navigationBar;

#创建壁纸数据--壁纸类型
create table wallpaper_type
(
	id bigint auto_increment primary key comment '主键，标识列',
	name varchar(20) not null comment '名称'
);
insert into wallpaper_type values
	(null,'风景'),
	(null,'动漫'),
	(null,'美女'),
	(null,'游戏'),
	(null,'明星'),
	(null,'汽车'),
	(null,'萌宠'),
	(null,'影视'),
	(null,'卡通'),
	(null,'其他');
select * from wallpaper_type;

#创建壁纸数据--壁纸主题
create table wallpaper_theme
(
	id bigint primary key auto_increment comment '主键，标识列',
	wallpaper varchar(50) not null comment '主题壁纸',
	theme varchar(20) not null comment '主题',
	date date not null comment '日期',
	typeId bigint not null comment '类型id,引用外键表wallpaper_type',
	foreign key(typeId) references wallpaper_type(id)
);
insert into wallpaper_theme values
	(null,'1534474086369.jpg','沙漠风景桌面壁纸',now(),1),
	(null,'1534474159368.jpg','动漫精选桌面壁纸',now(),2),
	(null,'1534474225820.jpg','清纯美少女桌面壁纸',now(),3),
	(null,'1534474423128.jpg','标致508桌面壁纸',now(),6),
	(null,'1534474471931.jpg','哈咪猫和咪蒂',now(),9),
	(null,'153447452186.jpg','郑爽高清桌面壁纸 ',now(),5),
	(null,'1534474591704.jpg','三生三世十里桃花剧照壁纸',now(),8),
	(null,'1534474644511.jpg','英雄联盟LOL台词壁纸',now(),4),
	(null,'1534474704919.jpg','你家的萌宠桌面壁纸',now(),7),
	(null,'1534474772528.jpg','浪漫爱情摄影桌面壁纸',now(),10);
select * from wallpaper_theme;

#创建壁纸数据--壁纸
create table t_wallpaper
(
	id bigint primary key auto_increment comment '主键，标识列',
	wallpaper varchar(50) not null comment '壁纸',
	themeId bigint not null comment '主题id,引用外键表wallpaper_theme',
	foreign key(themeId) references wallpaper_theme(id)
);
insert into t_wallpaper values
	(null,'1534474086369.jpg',1),
	(null,'1534474094205.jpg',1),
	(null,'1534474159368.jpg',2),
	(null,'153447418376.jpg',2),
	(null,'1534474225820.jpg',3),
	(null,'1534474258707.jpg',3),
	(null,'1534474423128.jpg',4),
	(null,'1534474438678.jpg',4),
	(null,'1534474471931.jpg',5),
	(null,'153447448463.jpg',5),
	(null,'153447452186.jpg',6),
	(null,'1534474545131.jpg',6),
	(null,'1534474591704.jpg',7),
	(null,'1534474604760.jpg',7),
	(null,'1534474644511.jpg',8),
	(null,'1534474659580.jpg',8),
	(null,'1534474704919.jpg',9),
	(null,'1534474725188.jpg',9),
	(null,'1534474772528.jpg',10),
	(null,'1534474786765.jpg',10);
select * from t_wallpaper;