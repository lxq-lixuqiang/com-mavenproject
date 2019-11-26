use mavenprojectDB;
#创建音乐数据--音乐歌手
create table music_singer
(
	id bigint auto_increment primary key comment '主键，标识列',
	singerPicture varchar(50) not null comment '歌手图片',
	name varchar(50) not null comment '歌手名',
	sex int not null comment '性别：0-男，1-女',
	synopsis varchar(1000) not null comment '人物资料'
);
insert into music_singer values
	(null,'20180515002522714.jpg','周杰伦',0,'周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。2001年发行的专辑《范特西》奠定其融合中西方音乐的风格。'),
	(null,'20171113174536113.jpg','龙梅子',1,'龙梅子，1988年8月18日出生于甘肃兰州，中国内地女歌手、演员。2005年，凭借歌曲《光彩第二街》而正式出道。'),
	(null,'20130328095421798999.jpg','96猫',1,'96猫是日本niconico动画上的高人气女性多声类翻唱歌手（又称唱见），声音悠扬、细腻、富有磁性。主要翻唱vocaloid家族歌曲和动画、游戏曲歌。以上乘的唱功、灵活多变的声线、独树一帜的歌曲演绎风格和幽默温柔的人格魅力博得了许多听者的喜爱，并拥有数量相对庞大的粉丝群体'),
	(null,'20170602141818448.jpg','By2',1,'BY2，1992年3月23日出生于新加坡，华语流行乐双胞胎女子组合，由姐姐白纬芬（Miko）和妹妹白纬玲（Yumi）组成。'),
	(null,'20161013202232574118.jpg','孙子涵',0,'孙子涵，90后唱作型男歌手。2011年8月6日发表第一张个人专辑《一年一度的夏天》；同年荣获“2011年度新人奖”。'),
	(null,'20180616165108861.jpg','格子兮',1,'格子兮，原名陈文君，1995年7月16日出生于安徽省宣城市，中国内地流行音乐女歌手。'),
	(null,'20151219102335671.jpg','Mi2',1,'Mi2，华语流行乐双人组合，由AKAMA MIKI、张木易组成。'),
	(null,'20180118152429550.jpg','韩安旭',0,'韩安旭，1991年5月10日出生于黑龙江齐齐哈尔，YY主播，歌手。2015年10月23日发行个人首支单曲《多幸运》，正式进军内地乐坛。'),
	(null,'20180612151724929.jpg','许嵩',0,'许嵩（Vae），1986年5月14日生于安徽省合肥市，中国内地创作型男歌手，毕业于安徽医科大学。'),
	(null,'20140606141244458038.jpg','颜小健',0,'颜小健，1989年9月6日出生于广东汕头，原创音乐人。中国内地男歌手。');
select * from music_singer;

#创建音乐数据--音乐MV
create table music_mv
(
	id bigint auto_increment primary key comment '主键，标识列',
	themePicture varchar(50) not null comment '封面',
	themeName varchar(50) not null comment '主题名',
	mv varchar(50) not null comment 'mv',
	singerId bigint not null comment '歌手id引用music_singer表',
	date date not null comment '日期',
	foreign key(singerId) references music_singer(id)
);
insert into music_mv values
	(null,'7e26a3e05d51f0a4ad06f646aa4f8204.jpg','嘘の火花','96 cat - spark of lies.mp4',3,now()),
	(null,'20160503140347218799.jpg','秋殇别恋','Teeth, music, lattice - Autumn Love.mp4',6,now()),
	(null,'88dec891a77716c28bd3fb8e409a0db5.jpg','勇敢爱','Mi2- brave love.mp4',7,now()),
	(null,'d7bfab2894fc804e43565e2939c77581.jpg','多幸运','Han Anxu - how lucky.mp4',8,now()),
	(null,'587e18ccf318cd52ae283595d101bfe8.jpg','解药','Yan Xiaojian antidote.mp4',10,now());
select * from music_mv;

#创建音乐数据--音乐歌曲
create table t_music
(
	id bigint auto_increment primary key comment '主键，标识列',
	music varchar(50) not null comment '音乐',
	songName varchar(50) not null comment '歌名',
	date date not null comment '发布日期',
	singerId bigint not null comment '歌手id引用music_singer表',
	foreign key(singerId) references music_singer(id)
);
insert into t_music values
	(null,'JayChou-ricefragrance.m4a','稻香',now(),1),
	(null,'LongMeizi-Holdslove.m4a','Hold不住的爱',now(),2),
	(null,'96cat-sparkoflies.mp3','嘘の火花',now(),3),
	(null,'By2-loveyayaya.m4a','爱丫爱丫',now(),4),
	(null,'SunTzuHan-treatingbadgirls.m4a','对待坏女孩',now(),5),
	(null,'Lattice-AutumnLove.m4a','秋殇别恋',now(),6),
	(null,'Mi2-bravelove.mp3','勇敢爱',now(),7),
	(null,'HanAnxu-howlucky.m4a','多幸运',now(),8),
	(null,'XuSonggrayhead.mp3','灰色头像',now(),9),
	(null,'YanXiaojianantidote.mp3','解药',now(),10);
select * from t_music;

#创建音乐数据--音乐歌单
create table music_songsheet
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '歌单图片',
	name varchar(50) not null comment '歌单名',
	date date not null comment '日期'
);
insert into music_songsheet values
	(null,'20180118152429550.jpg','每日精选歌单',now()),
	(null,'20180118152429550.jpg','每日歌曲推荐',now()),
	(null,'20180118152429550.jpg','好听的网络歌曲',now());
select * from music_songsheet;

#创建音乐数据--歌单信息
create table music_songsheetInfo
(
	id bigint auto_increment primary key comment '主键，标识列',
	songsheetId bigint not null comment '歌单id,引用music_songsheet表',
	musicId bigint not null comment '歌曲id,引用t_music表',
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
	