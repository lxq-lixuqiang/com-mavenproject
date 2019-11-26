use mavenprojectDB;
#������Ϸ����--��Ϸ����
create table game_classification
(
	id bigint auto_increment primary key comment '��������ʶ��',
	classificationName varchar(10) not null comment '��������'
);
insert into game_classification values
	(null,'��'),
	(null,'��������'),
	(null,'��������'),
	(null,'���ڴ���');
select * from game_classification;

#������Ϸ����--��Ϸ����
create table game_type
(
	id bigint auto_increment primary key comment '��������ʶ��',
	typeName varchar(30) not null comment '��������'
);
insert into game_type values
	(null,'������ϷACT'),
	(null,'�����˳����TPS'),
	(null,'��һ�˳����FPS'),
	(null,'��ɫ����RPG'),
	(null,'ð����ϷAVG'),
	(null,'ģ�⾭ӪSIM'),
	(null,'��ʱս��RTS'),
	(null,'��������RAC'),
	(null,'����ս��SLG'),
	(null,'��������SPG'),
	(null,'��������');
select * from game_type;

#������Ϸ����--��Ϸƽ̨
create table game_platform
(
	id bigint auto_increment primary key comment '��������ʶ��',
	platformName varchar(20) not null comment 'ƽ̨����'
);
insert into game_platform values
	(null,'PC'),
	(null,'PS4'),
	(null,'PSV'),
	(null,'XBOXONE'),
	(null,'Switch'),
	(null,'3DS');
select * from game_platform;

#������Ϸ����--��Ϸ��Ϣ
create table t_game
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment '��Ϸ����',
	name varchar(30) not null comment '��Ϸ����',
	platformId bigint not null comment '��Ϸƽ̨Id������game_platform��',
	language varchar(100) not null comment '��Ϸ����',
	issuer varchar(50) not null comment '������',
	typeId bigint not null comment '��Ϸ����id������game_type��',
	classificationId bigint not null comment '��Ϸ���࣬����game_classification��',
	game varchar(50) comment '������Դ',
	gamePath varchar(1000) comment '������Դ��ַ',
	content varchar(300) not null comment '��Ϸ����',
	gameExplain varchar(100) not null comment '��Ϸ��װ˵��',
	date date not null comment '��Ϸ����',
	foreign key(typeId) references game_type(id),
	foreign key(classificationId) references game_classification(id),
	foreign key(platformId) references game_platform(id)
);

#������Ϸ����--��Ϸ����ͼ
create table game_wallpaper
(
	id bigint auto_increment primary key comment '��������ʶ��',
	wallpaper varchar(50) not null comment '��Ϸ����ͼ',
	gameId bigint not null comment '��Ϸid������t_game��',
	foreign key(gameId) references t_game(id)
);

#������Ϸ����--��Ϸ��Ѷ����
create table game_informationTheme
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment '��Ѷ����',
	theme varchar(50) not null comment '��Ѷ����',
	date datetime NOT NULL COMMENT '����'
);

#������Ϸ����--��Ϸ��Ѷ
create table game_information
(
	id bigint auto_increment primary key comment '��������ʶ��',
	informationThemeId bigint not null comment '��Ѷ����Id������game_informationTheme��',
	serialNumber int not null comment '�����(����˳��)',
	infoOrImg varchar(1000) not null comment '��Ϣ��ͼƬ',
	infoOrImgType int not null comment '��Ϣ��ͼƬ���ͣ�0-���֣�1-ͼƬ',
	foreign key(informationThemeId) references game_informationTheme(id)
);