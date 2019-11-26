use mavenprojectDB;
#创建游戏数据--游戏分类
create table game_classification
(
	id bigint auto_increment primary key comment '主键，标识列',
	classificationName varchar(10) not null comment '分类名称'
);
insert into game_classification values
	(null,'无'),
	(null,'即将上市'),
	(null,'最新上市'),
	(null,'近期大作');
select * from game_classification;

#创建游戏数据--游戏类型
create table game_type
(
	id bigint auto_increment primary key comment '主键，标识列',
	typeName varchar(30) not null comment '类型名称'
);
insert into game_type values
	(null,'动作游戏ACT'),
	(null,'第三人称射击TPS'),
	(null,'第一人称射击FPS'),
	(null,'角色扮演RPG'),
	(null,'冒险游戏AVG'),
	(null,'模拟经营SIM'),
	(null,'即时战略RTS'),
	(null,'赛车竞速RAC'),
	(null,'策略战棋SLG'),
	(null,'体育竞技SPG'),
	(null,'其他类型');
select * from game_type;

#创建游戏数据--游戏平台
create table game_platform
(
	id bigint auto_increment primary key comment '主键，标识列',
	platformName varchar(20) not null comment '平台名称'
);
insert into game_platform values
	(null,'PC'),
	(null,'PS4'),
	(null,'PSV'),
	(null,'XBOXONE'),
	(null,'Switch'),
	(null,'3DS');
select * from game_platform;

#创建游戏数据--游戏信息
create table t_game
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '游戏封面',
	name varchar(30) not null comment '游戏名称',
	platformId bigint not null comment '游戏平台Id，引用game_platform表',
	language varchar(100) not null comment '游戏语言',
	issuer varchar(50) not null comment '发行商',
	typeId bigint not null comment '游戏类型id，引用game_type表',
	classificationId bigint not null comment '游戏分类，引用game_classification表',
	game varchar(50) comment '本地资源',
	gamePath varchar(1000) comment '其他资源地址',
	content varchar(300) not null comment '游戏介绍',
	gameExplain varchar(100) not null comment '游戏安装说明',
	date date not null comment '游戏日期',
	foreign key(typeId) references game_type(id),
	foreign key(classificationId) references game_classification(id),
	foreign key(platformId) references game_platform(id)
);

#创建游戏数据--游戏介绍图
create table game_wallpaper
(
	id bigint auto_increment primary key comment '主键，标识列',
	wallpaper varchar(50) not null comment '游戏介绍图',
	gameId bigint not null comment '游戏id，引用t_game表',
	foreign key(gameId) references t_game(id)
);

#创建游戏数据--游戏资讯主题
create table game_informationTheme
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '资讯封面',
	theme varchar(50) not null comment '资讯主题',
	date datetime NOT NULL COMMENT '日期'
);

#创建游戏数据--游戏资讯
create table game_information
(
	id bigint auto_increment primary key comment '主键，标识列',
	informationThemeId bigint not null comment '资讯主题Id，引用game_informationTheme表',
	serialNumber int not null comment '序号列(控制顺序)',
	infoOrImg varchar(1000) not null comment '信息或图片',
	infoOrImgType int not null comment '信息或图片类型：0-文字，1-图片',
	foreign key(informationThemeId) references game_informationTheme(id)
);