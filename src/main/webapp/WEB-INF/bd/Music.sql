use mavenprojectDB;
#������������--���ָ���
create table music_singer
(
	id bigint auto_increment primary key comment '��������ʶ��',
	singerPicture varchar(50) not null comment '����ͼƬ',
	name varchar(50) not null comment '������',
	sex int not null comment '�Ա�0-�У�1-Ů',
	synopsis varchar(1000) not null comment '��������'
);
insert into music_singer values
	(null,'20180515002522714.jpg','�ܽ���',0,'�ܽ��ף�Jay Chou����1979��1��18�ճ�����̨��ʡ�±��У��й�̨���������и��֡������ˡ���Ա�����ݡ���硢���ơ����ˡ�2000�귢�����Ÿ���ר����Jay����2001�귢�е�ר�������������춨���ں����������ֵķ��'),
	(null,'20171113174536113.jpg','��÷��',1,'��÷�ӣ�1988��8��18�ճ����ڸ������ݣ��й��ڵ�Ů���֡���Ա��2005�꣬ƾ���������ʵڶ��֡�����ʽ������'),
	(null,'20130328095421798999.jpg','96è',1,'96è���ձ�niconico�����ϵĸ�����Ů�Զ����෭�����֣��ֳƳ��������������ϸ�塢���д��ԡ���Ҫ����vocaloid��������Ͷ�������Ϸ���衣���ϳ˵ĳ��������������ߡ�����һ�ĵĸ������������Ĭ������˸�����������������ߵ�ϲ������ӵ����������Ӵ�ķ�˿Ⱥ��'),
	(null,'20170602141818448.jpg','By2',1,'BY2��1992��3��23�ճ������¼��£�����������˫��̥Ů����ϣ��ɽ���γ�ң�Miko�������ð�γ�ᣨYumi����ɡ�'),
	(null,'20161013202232574118.jpg','���Ӻ�',0,'���Ӻ���90�������и��֡�2011��8��6�շ����һ�Ÿ���ר����һ��һ�ȵ����졷��ͬ���ٻ�2011������˽�����'),
	(null,'20180616165108861.jpg','������',1,'�����⣬ԭ�����ľ���1995��7��16�ճ����ڰ���ʡ�����У��й��ڵ���������Ů���֡�'),
	(null,'20151219102335671.jpg','Mi2',1,'Mi2������������˫����ϣ���AKAMA MIKI����ľ����ɡ�'),
	(null,'20180118152429550.jpg','������',0,'������1991��5��10�ճ����ں��������������YY���������֡�2015��10��23�շ��и�����֧�����������ˡ�����ʽ�����ڵ���̳��'),
	(null,'20180612151724929.jpg','����',0,'���ԣ�Vae����1986��5��14�����ڰ���ʡ�Ϸ��У��й��ڵش������и��֣���ҵ�ڰ���ҽ�ƴ�ѧ��'),
	(null,'20140606141244458038.jpg','��С��',0,'��С����1989��9��6�ճ����ڹ㶫��ͷ��ԭ�������ˡ��й��ڵ��и��֡�');
select * from music_singer;

#������������--����MV
create table music_mv
(
	id bigint auto_increment primary key comment '��������ʶ��',
	themePicture varchar(50) not null comment '����',
	themeName varchar(50) not null comment '������',
	mv varchar(50) not null comment 'mv',
	singerId bigint not null comment '����id����music_singer��',
	date date not null comment '����',
	foreign key(singerId) references music_singer(id)
);
insert into music_mv values
	(null,'7e26a3e05d51f0a4ad06f646aa4f8204.jpg','��λ�','96 cat - spark of lies.mp4',3,now()),
	(null,'20160503140347218799.jpg','�������','Teeth, music, lattice - Autumn Love.mp4',6,now()),
	(null,'88dec891a77716c28bd3fb8e409a0db5.jpg','�¸Ұ�','Mi2- brave love.mp4',7,now()),
	(null,'d7bfab2894fc804e43565e2939c77581.jpg','������','Han Anxu - how lucky.mp4',8,now()),
	(null,'587e18ccf318cd52ae283595d101bfe8.jpg','��ҩ','Yan Xiaojian antidote.mp4',10,now());
select * from music_mv;

#������������--���ָ���
create table t_music
(
	id bigint auto_increment primary key comment '��������ʶ��',
	music varchar(50) not null comment '����',
	songName varchar(50) not null comment '����',
	date date not null comment '��������',
	singerId bigint not null comment '����id����music_singer��',
	foreign key(singerId) references music_singer(id)
);
insert into t_music values
	(null,'JayChou-ricefragrance.m4a','����',now(),1),
	(null,'LongMeizi-Holdslove.m4a','Hold��ס�İ�',now(),2),
	(null,'96cat-sparkoflies.mp3','��λ�',now(),3),
	(null,'By2-loveyayaya.m4a','��Ѿ��Ѿ',now(),4),
	(null,'SunTzuHan-treatingbadgirls.m4a','�Դ���Ů��',now(),5),
	(null,'Lattice-AutumnLove.m4a','�������',now(),6),
	(null,'Mi2-bravelove.mp3','�¸Ұ�',now(),7),
	(null,'HanAnxu-howlucky.m4a','������',now(),8),
	(null,'XuSonggrayhead.mp3','��ɫͷ��',now(),9),
	(null,'YanXiaojianantidote.mp3','��ҩ',now(),10);
select * from t_music;

#������������--���ָ赥
create table music_songsheet
(
	id bigint auto_increment primary key comment '��������ʶ��',
	picture varchar(50) not null comment '�赥ͼƬ',
	name varchar(50) not null comment '�赥��',
	date date not null comment '����'
);
insert into music_songsheet values
	(null,'20180118152429550.jpg','ÿ�վ�ѡ�赥',now()),
	(null,'20180118152429550.jpg','ÿ�ո����Ƽ�',now()),
	(null,'20180118152429550.jpg','�������������',now());
select * from music_songsheet;

#������������--�赥��Ϣ
create table music_songsheetInfo
(
	id bigint auto_increment primary key comment '��������ʶ��',
	songsheetId bigint not null comment '�赥id,����music_songsheet��',
	musicId bigint not null comment '����id,����t_music��',
	foreign key(songsheetId) references music_songsheet(id),
	foreign key(musicId) references t_music(id)
);
insert into music_songsheetInfo values
	(null,1,1),
	(null,1,3),
	(null,2,2),
	(null,3,4),
	(null,3,5);
select * from music_songsheetInfo;
	