use mavenprojectDB;
#������ҳ����--��ҳ������
create table home_navigationBar
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment 'ͼƬ',
	title varchar(30) not null comment '����',
	backgroundColor varchar(20) not null comment '������ɫ',
	date date not null comment '����',
	homeNavigationBar varchar(50) comment '������Դ',
	homeNavigationBarPath varchar(1000) comment '������Դ·��'
);
insert into home_navigationBar values
	(null,'5120dd597d91e201cff50567c6576929.jpg','����δ�����þ�ѡ��ʮ����','red',now(),null,'http://player.pptv.com/iframe/index.html#id=29110628&ctx=o%3Dv_share');
	
select * from home_navigationBar;