use mavenprojectDB;
#������������--����������
create table animation_navigationBar
(
	id bigint auto_increment primary key comment '��������ʶ��',
	animationWallpaper varchar(50) not null comment '������ֽ'
);
INSERT into animation_navigationBar values
	(null,'AnimationHeaderImg.png');
select * from animation_navigationBar;

#������������--��������
create table animation_type
(
	id bigint auto_increment primary key comment '��������ʶ��',
	type varchar(10) not null comment '����'
);
insert into animation_type values
	(null,'��Ϸ��'),
	(null,'����'),
	(null,'��С˵'),
	(null,'����'),
	(null,'�ٺ�'),
	(null,'���'),
	(null,'��Ц'),
	(null,'У԰'),
	(null,'��ս'),
	(null,'��'),
	(null,'�ճ�'),
	(null,'��Ѫ'),
	(null,'����');
select * from animation_type;

#������������--������Ϣ
create table animation_info
(
	id bigint auto_increment primary key comment '��������ʶ��',
	animationPicture varchar(50) not null comment '����ͼƬ',
	animationName varchar(40) not null comment '��������',
	animationQuarter varchar(2) not null comment '�������ȣ����ȣ������ġ����',
	animationType varchar(100) not null comment '������ǩ',
	animationDate date not null comment '��������',
	classificationId bigint not null comment '��������id,����animation_classification��',
	animationContent varchar(200) not null comment '�������',
	foreign key(classificationId) references animation_classification(id)
);

#������������--������Ƶ
create table t_animation
(
	id bigint auto_increment primary key comment '��������ʶ��',
	setName varchar(20) not null comment '������',
	animationInfoId bigint not null comment '������Ϣid,����animation_info��',
	dateTime datetime not null comment '����ʱ��',
	animation varchar(50) comment '������Դ',
	animationPath varchar(1000) comment '������Դ·��',
	foreign key(animationInfoId) references animation_info(id)
);

#������������--��������
create table animation_classification
(
	id bigint auto_increment primary key comment '��������ʶ��',
	name varchar(20) not null comment '�ܷ���'
);
insert into animation_classification values
	(null,'����'),
	(null,'��һ'),
	(null,'�ܶ�'),
	(null,'����'),
	(null,'����'),
	(null,'����'),
	(null,'����'),
	(null,'��');
select * from animation_classification;

#������������--��������
create table animation_entertainmentType
(
	id bigint auto_increment primary key comment '��������ʶ��',
	typeName varchar(4) not null comment '����'
);
insert into animation_entertainmentType values
	(null,'�ƹ�'),
	(null,'����'),
	(null,'����'),
	(null,'����');
select * from animation_entertainmentType;

#������������--���ֶ���
create table animation_entertainmentAnimation
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment '����',
	name varchar(30) not null comment '����',
	entertainmentAnimation varchar(50) comment '�������ֶ�����Դ',
	entertainmentAnimationPath varchar(1000) comment '������Դ·��',
	entertainmentTypeId bigint not null comment '��������id,����animation_entertainmentType��',
	dateTime dateTime not null comment '�ϴ�ʱ��',
	foreign key(entertainmentTypeId) references animation_entertainmentType(id)
);

