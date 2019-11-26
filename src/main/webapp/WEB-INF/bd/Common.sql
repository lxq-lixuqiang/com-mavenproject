use mavenprojectDB;
#������������--������
create table common_navigationBar
(
	id bigint primary key auto_increment comment '��������ʶ��',
	name varchar(10) not null comment '����',
	path varchar(20) not null comment '·��' 
);
insert into common_navigationBar values
	(null,'��ҳ','home'),
	(null,'����','comic'),
	(null,'��Ϸ','game'),
	(null,'����','music'),
	(null,'��ֽ','wallpaper'),
	(null,'��չ','illustrations');
select * from common_navigationBar;

#������������--��������
create table common_foot
(
	id bigint primary key auto_increment comment '��������ʶ��',
	name varchar(20) not null comment '����',
	path varchar(50) not null comment '·��'
);
insert into common_foot values
	(null,'������','http://www.ali213.net/'),
	(null,'3DM','http://www.3dmgame.com/'),
	(null,'�����ǿ�','http://www.gamersky.com/'),
	(null,'��Ѷ��Ϸ','http://games.qq.com/'),
	(null,'�����ʿ','http://www.tgbus.com/'),
	(null,'2144С��Ϸ','http://www.2144.cn/'),
	(null,'������Ϸ����','http://www.doyo.cn/'),
	(null,'������','http://www.962.net/'),
	(null,'�����Ϸ','http://www.fhyx.com/'),
	(null,'7k7kС��Ϸ','http://www.7k7k.com/'),
	(null,'��������','https://www.bilibili.com/'),
	(null,'������','http://www.x4jdm.com/'),
	(null,'�糵����','http://www.fengchedm.com/index.html'),
	(null,'��Ѷ����','http://ac.qq.com/'),
	(null,'����������','http://www.hahadm.com/'),
	(null,'�����ǿ�','http://acg.gamersky.com/'),
	(null,'��������','http://www.dilidili.wang/'),
	(null,'�ṷ����','http://www.kugou.com/'),
	(null,'QQ����','https://y.qq.com/'),
	(null,'Ϻ������','https://www.xiami.com/'),
	(null,'����������','http://music.163.com/'),
	(null,'��������','http://www.kuwo.cn/'),
	(null,'�ſ�����','http://www.9ku.com/'),
	(null,'�乾����','http://music.migu.cn/v2'),
	(null,'�˰�����','http://www.netbian.com/'),
	(null,'ZOL�����ֽ','http://desk.zol.com.cn/'),
	(null,'360��ֽ��','http://www.xshuma.com/'),
	(null,'������','http://www.win4000.com/'),
	(null,'����ֽ','http://www.lovebizhi.com/'),
	(null,'��������','http://wallpaper.desktx.com/');
select * from common_foot;

#������������--��չ
create table common_illustrations
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment 'ͼƬ',
	title varchar(20) not null comment '����',
	theme varchar(30) not null comment '����',
	author varchar(20) not null comment '����',
	datetime datetime not null comment 'ʱ��',
	content varchar(200) comment '����'
);
insert into common_illustrations values
	(null,'070e143e607d46a6e3732d57427addebad614218.jpg','ż���ʦ�ҹ���','ż���ʦ�ҹ���Ů�� ������ͼ','���찮����-������',now(),'��ʱ�˿̣�ħ����ʼ���١���'),
	(null,'2018073011082372026.png','������ѪС��','һֻ������ѪС��','EuRai',now(),'��֪������������û��ѪС��......'),
	(null,'2018073014460885529.png','�����绨','�����绨����˸���ǣ�һƬ���','ǳǳǳǳǳؼĮ',now(),'#������ֽ##����#'),
	(null,'2018073012093811816.png','�������ﻯ�ƻ�','�������ﻯ�ƻ�','Xhuanv',now(),'Ӧ����֮������'),
	(null,'2ce7e9f4695f77f70de629dea452379281ad3d21.jpg','����˿��ż����','����˿����ż����','oO���Oo',now(),'ͬ��  | ����  �� �廭'),
	(null,'7605d77e1dce9ddae4fae97a9740c8dc5b34c6aa.jpg','miku','miku','����Elpx',now(),'lalala'),
	(null,'405e4b4171a4a55eb5f5f073cc38717301b59101.jpg','�ж���ҲҪ̸����','�ж���ҲҪ̸����','��������',now(),'�����ж���take on me���Ȳ���������ȶ�'),
	(null,'01963c405e02d65e59118eb24f205f212e85807c.jpg','����','����','Bison�����',now(),'#ISLAND# ͬ�˻滭'),
	(null,'4bbb9981ae9a123f032cda1d44c27f77cb8a93fe.jpg','���Τ����','���Τ����','�դ����ꤣ����',now(),'#�廭##��ע�ĤϤ������Ǥ���# ���������ե����奢�Τ�ġ�'),
	(null,'2018073011484848084.png','����','����','SanTEZ��̫��',now(),'Bվ��ھ�ͼ'),
	(null,'2018073014554283178.png','�|��project','�|��project','--',now(),'�����ҵ�~~'),
	(null,'52de65a1d88990f0e814bccd1eb8559b3d3f22ac.png','��ʯ����','��ʯ����','����',now(),'ͬ��  | ����  �� �廭');
select * from common_illustrations;

#������������--����ͼƬ
create table common_loginAndRegister
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment '����ͼƬ',
	type int not null comment '����:0-��¼��1-�������룬2-ע��'
);
insert into common_loginAndRegister values
	(null,'timg.jpg',0),
	(null,'timg.jpg',1),
	(null,'timg (1).jpg',2);
select * from common_loginAndRegister;
