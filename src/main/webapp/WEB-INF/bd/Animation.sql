use mavenprojectDB;
#创建动漫数据--动漫导航栏
create table animation_navigationBar
(
	id bigint auto_increment primary key comment '主键，标识列',
	animationWallpaper varchar(50) not null comment '动漫壁纸'
);
INSERT into animation_navigationBar values
	(null,'AnimationHeaderImg.png');
select * from animation_navigationBar;

#创建动漫数据--动漫类型
create table animation_type
(
	id bigint auto_increment primary key comment '主键，标识列',
	type varchar(10) not null comment '类型'
);
insert into animation_type values
	(null,'游戏改'),
	(null,'漫改'),
	(null,'轻小说'),
	(null,'恋爱'),
	(null,'百合'),
	(null,'搞基'),
	(null,'搞笑'),
	(null,'校园'),
	(null,'机战'),
	(null,'后宫'),
	(null,'日常'),
	(null,'热血'),
	(null,'其他');
select * from animation_type;

#创建动漫数据--动漫信息
create table animation_info
(
	id bigint auto_increment primary key comment '主键，标识列',
	animationPicture varchar(50) not null comment '动漫图片',
	animationName varchar(40) not null comment '动漫名称',
	animationQuarter varchar(2) not null comment '动漫季度，季度：春、夏、秋、冬',
	animationType varchar(100) not null comment '动漫标签',
	animationDate date not null comment '动漫日期',
	classificationId bigint not null comment '动漫分类id,引用animation_classification表',
	animationContent varchar(200) not null comment '动漫简介',
	foreign key(classificationId) references animation_classification(id)
);

#创建动漫数据--动漫视频
create table t_animation
(
	id bigint auto_increment primary key comment '主键，标识列',
	setName varchar(20) not null comment '集名称',
	animationInfoId bigint not null comment '动漫信息id,引用animation_info表',
	dateTime datetime not null comment '日期时间',
	animation varchar(50) comment '本地资源',
	animationPath varchar(1000) comment '其他资源路径',
	foreign key(animationInfoId) references animation_info(id)
);

#创建动漫数据--动漫分类
create table animation_classification
(
	id bigint auto_increment primary key comment '主键，标识列',
	name varchar(20) not null comment '周分类'
);
insert into animation_classification values
	(null,'周日'),
	(null,'周一'),
	(null,'周二'),
	(null,'周三'),
	(null,'周四'),
	(null,'周五'),
	(null,'周六'),
	(null,'无');
select * from animation_classification;

#创建动漫数据--娱乐类型
create table animation_entertainmentType
(
	id bigint auto_increment primary key comment '主键，标识列',
	typeName varchar(4) not null comment '名称'
);
insert into animation_entertainmentType values
	(null,'推广'),
	(null,'动画'),
	(null,'娱乐'),
	(null,'音乐');
select * from animation_entertainmentType;

#创建动漫数据--娱乐动漫
create table animation_entertainmentAnimation
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '封面',
	name varchar(30) not null comment '名称',
	entertainmentAnimation varchar(50) comment '本地娱乐动漫资源',
	entertainmentAnimationPath varchar(1000) comment '其他资源路径',
	entertainmentTypeId bigint not null comment '娱乐类型id,引用animation_entertainmentType表',
	dateTime dateTime not null comment '上传时间',
	foreign key(entertainmentTypeId) references animation_entertainmentType(id)
);

