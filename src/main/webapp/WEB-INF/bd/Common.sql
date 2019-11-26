use mavenprojectDB;
#创建公共数据--导航栏
create table common_navigationBar
(
	id bigint primary key auto_increment comment '主键，标识列',
	name varchar(10) not null comment '名称',
	path varchar(20) not null comment '路径' 
);
insert into common_navigationBar values
	(null,'首页','home'),
	(null,'动漫','comic'),
	(null,'游戏','game'),
	(null,'音乐','music'),
	(null,'壁纸','wallpaper'),
	(null,'画展','illustrations');
select * from common_navigationBar;

#创建公共数据--友情链接
create table common_foot
(
	id bigint primary key auto_increment comment '主键，标识列',
	name varchar(20) not null comment '名称',
	path varchar(50) not null comment '路径'
);
insert into common_foot values
	(null,'游侠网','http://www.ali213.net/'),
	(null,'3DM','http://www.3dmgame.com/'),
	(null,'游民星空','http://www.gamersky.com/'),
	(null,'腾讯游戏','http://games.qq.com/'),
	(null,'电玩巴士','http://www.tgbus.com/'),
	(null,'2144小游戏','http://www.2144.cn/'),
	(null,'逗游游戏宝库','http://www.doyo.cn/'),
	(null,'乐游网','http://www.962.net/'),
	(null,'凤凰游戏','http://www.fhyx.com/'),
	(null,'7k7k小游戏','http://www.7k7k.com/'),
	(null,'哔哩哔哩','https://www.bilibili.com/'),
	(null,'新世界','http://www.x4jdm.com/'),
	(null,'风车动漫','http://www.fengchedm.com/index.html'),
	(null,'腾讯动漫','http://ac.qq.com/'),
	(null,'哈哈动漫网','http://www.hahadm.com/'),
	(null,'动漫星空','http://acg.gamersky.com/'),
	(null,'嘀哩嘀哩','http://www.dilidili.wang/'),
	(null,'酷狗音乐','http://www.kugou.com/'),
	(null,'QQ音乐','https://y.qq.com/'),
	(null,'虾米音乐','https://www.xiami.com/'),
	(null,'网易云音乐','http://music.163.com/'),
	(null,'酷我音乐','http://www.kuwo.cn/'),
	(null,'九酷音乐','http://www.9ku.com/'),
	(null,'咪咕音乐','http://music.migu.cn/v2'),
	(null,'彼岸桌面','http://www.netbian.com/'),
	(null,'ZOL桌面壁纸','http://desk.zol.com.cn/'),
	(null,'360壁纸库','http://www.xshuma.com/'),
	(null,'美桌网','http://www.win4000.com/'),
	(null,'爱壁纸','http://www.lovebizhi.com/'),
	(null,'桌面天下','http://wallpaper.desktx.com/');
select * from common_foot;

#创建公共数据--画展
create table common_illustrations
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '图片',
	title varchar(20) not null comment '标题',
	theme varchar(30) not null comment '主题',
	author varchar(20) not null comment '作者',
	datetime datetime not null comment '时间',
	content varchar(200) comment '内容'
);
insert into common_illustrations values
	(null,'070e143e607d46a6e3732d57427addebad614218.jpg','偶像大师灰姑娘','偶像大师灰姑娘女孩 人物美图','天天爱动漫-天天娘',now(),'此时此刻，魔法开始降临……'),
	(null,'2018073011082372026.png','初音版血小板','一只初音的血小板','EuRai',now(),'不知道初音殿下有没有血小板......'),
	(null,'2018073014460885529.png','雨落如花','雨落如花，花烁如星，一片绮梦','浅浅浅浅浅丶漠',now(),'#动漫壁纸##动漫#'),
	(null,'2018073012093811816.png','奥特曼娘化计划','奥特曼娘化计划','Xhuanv',now(),'应观众之邀整理'),
	(null,'2ce7e9f4695f77f70de629dea452379281ad3d21.jpg','爱丽丝人偶工坊','爱丽丝的人偶工坊','oO大黄Oo',now(),'同人  | 画友  · 插画'),
	(null,'7605d77e1dce9ddae4fae97a9740c8dc5b34c6aa.jpg','miku','miku','勇妹Elpx',now(),'lalala'),
	(null,'405e4b4171a4a55eb5f5f073cc38717301b59101.jpg','中二病也要谈恋爱','中二病也要谈恋爱','龍麟云雨',now(),'趁着中二病take on me的热播，来蹭蹭热度'),
	(null,'01963c405e02d65e59118eb24f205f212e85807c.jpg','凛音','凛音','Bison仓鼠仓',now(),'#ISLAND# 同人绘画'),
	(null,'4bbb9981ae9a123f032cda1d44c27f77cb8a93fe.jpg','チノちゃん。','チノちゃん。','ふぇありぃあい',now(),'#插画##ご注文はうさぎですか# （アクリルフィギュアのやつ。'),
	(null,'2018073011484848084.png','鲸鱼','鲸鱼','SanTEZ三太子',now(),'B站活动冠军图'),
	(null,'2018073014554283178.png','東方project','東方project','--',now(),'天子我的~~'),
	(null,'52de65a1d88990f0e814bccd1eb8559b3d3f22ac.png','钻石奔月','钻石奔月','汝子',now(),'同人  | 画友  · 插画');
select * from common_illustrations;

#创建公共数据--背景图片
create table common_loginAndRegister
(
	id bigint auto_increment primary key comment '主键，标识列',
	picture varchar(50) not null comment '背景图片',
	type int not null comment '类型:0-登录，1-忘记密码，2-注册'
);
insert into common_loginAndRegister values
	(null,'timg.jpg',0),
	(null,'timg.jpg',1),
	(null,'timg (1).jpg',2);
select * from common_loginAndRegister;
