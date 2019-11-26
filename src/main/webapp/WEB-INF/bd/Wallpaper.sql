use mavenprojectDB;
#������ֽ����--��������ֽ
create table wallpaper_navigationBar
(
	id bigint auto_increment primary key comment '��������ʶ��',
	wallpaper varchar(50) not null comment '��ֽ'
);
INSERT into wallpaper_navigationBar values
	(null,'logwallpaper.jpg');
select * from wallpaper_navigationBar;

#������ֽ����--��ֽ����
create table wallpaper_type
(
	id bigint auto_increment primary key comment '��������ʶ��',
	name varchar(20) not null comment '����'
);
insert into wallpaper_type values
	(null,'�羰'),
	(null,'����'),
	(null,'��Ů'),
	(null,'��Ϸ'),
	(null,'����'),
	(null,'����'),
	(null,'�ȳ�'),
	(null,'Ӱ��'),
	(null,'��ͨ'),
	(null,'����');
select * from wallpaper_type;

#������ֽ����--��ֽ����
create table wallpaper_theme
(
	id bigint primary key auto_increment comment '��������ʶ��',
	wallpaper varchar(50) not null comment '�����ֽ',
	theme varchar(20) not null comment '����',
	date date not null comment '����',
	typeId bigint not null comment '����id,���������wallpaper_type',
	foreign key(typeId) references wallpaper_type(id)
);
insert into wallpaper_theme values
	(null,'1534474086369.jpg','ɳĮ�羰�����ֽ',now(),1),
	(null,'1534474159368.jpg','������ѡ�����ֽ',now(),2),
	(null,'1534474225820.jpg','�崿����Ů�����ֽ',now(),3),
	(null,'1534474423128.jpg','����508�����ֽ',now(),6),
	(null,'1534474471931.jpg','����è�����',now(),9),
	(null,'153447452186.jpg','֣ˬ���������ֽ ',now(),5),
	(null,'1534474591704.jpg','��������ʮ���һ����ձ�ֽ',now(),8),
	(null,'1534474644511.jpg','Ӣ������LOL̨�ʱ�ֽ',now(),4),
	(null,'1534474704919.jpg','��ҵ��ȳ������ֽ',now(),7),
	(null,'1534474772528.jpg','����������Ӱ�����ֽ',now(),10);
select * from wallpaper_theme;

#������ֽ����--��ֽ
create table t_wallpaper
(
	id bigint primary key auto_increment comment '��������ʶ��',
	wallpaper varchar(50) not null comment '��ֽ',
	themeId bigint not null comment '����id,���������wallpaper_theme',
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