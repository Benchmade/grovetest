package org.grove.common;

import java.nio.charset.Charset;
import java.util.Arrays;
/**
 * 测试的目的是一个大的byte[] 转换为string的时候, 分批次的转换,但是每次都是传入一个大的数组,
 * 使用String的 new String( data, p, 26, ASCII );方法截取速度非常的慢,
 * 使用arraycopy非常的快,但是实际测试并没有变快,可能因为数组容量不够大,同时
 * 测试了直接转换为String,速度上比优化过的还快,可能是jdk7的优化.
 * @author xiaolin.mxl
 *
 */
public class ByteArray2String {
	private static final int BUF_SIZE = 200000;
    private static final Charset ASCII = Charset.forName( "US-ASCII" );
	/**
	 * Try to avoid unnecessary memory allocations in your program, because it may impact performance of 
	 * your program in case if it is already using enough memory (1G+).
       Especially try to avoid discussed constructor calls with long buffers. 
       For 100,000 byte buffer provided programs runs for 21.011 sec for original 
       constructor call and 0.191 sec for fixed call. After extending source buffer to 200,000 bytes,
        runtime for original constructor call predictably increased 4-fold: 83.203 sec. 
        Time for fixed call has increased less than 2-fold: 0.354 sec 
        (this is right, because now we have 2 times more data to process).
	 * @param args
	 
	public static void main(String[] args) {

		String str = "V3.0OK15const65113906071002011echo300stat917184166;99;3974;99;2049;99;1478;99;267;7;1922;4;385;4;4550;3;907;3;843;3;587;3;651;2;2882;1;2689;1;2562;1;2433;1;2178;1;775;1;1-4294947296;95;0650025258;1;50102773;0;50100707;0;50025825;0;50025145;0;50023647;0;18657719480549396000;0;540461942746140192;0;413879120410201632;0;272029007891942944;0;71370884601892384;0;36928158875799072;0;19483015331728928;0;19474704570011168;0;18355831229664800;0;14765573577657888;0;495798139768352;0;495454542384672;0;495424477613600;0;471235221802528;0;291147243081248;0;131155416337952;0;128428112104992;0;128398047333920;0;28121620588941341;2;3435976345060901277;1;3435974506814898589;1;3435974219052089757;1;559685778310673;1;121616293974045;1;121611999006749;1;121603409072157;1;3435976495384756637;0;3435976491089789341;0;3435976332175999389;0;3435975971398746525;0;3435974803167642013;0;3435974622779015581;0;3435974523994767773;0;3435974287771566493;0;3435973995713790365;0;125054861950617105;0;559711548114449;0;559702958179857;0;559690073277969;0;559148907398673;0;128612795713041;0;121904056782877;0;121895466848285;0;121629178875933;0;121624883908637;0;121607704039453;0;1250099191;51;50025163;37;50024531;37;50024406;25;50020578;25;60312020;6;50025829;5;50025174;5;50020856;5;50094078;3;50094108;2;50025135;2;data113554zk_timezk_grouppromotionssku_min_promotionssku_max_promotionszk_moneyusertmall_titlespuidnidpidvidspu_pictsku_minsku_maxauction_pointuser_tag2categorytitlepict_urltotal_sold_quantityweek_salesgrade_avgbiz30dayoldbiz30daycomment_countuser_idauction_tagpropertiesauction_imageshop_titlenickseller_locshop_pictreal_post_feeprovcitystatic_trans_scorepersonal_datatk_rateunitunit_pricesputitlealg_taggrant_brandprom_optionsprom_start_feeprom_discountprom_giftprom_biz30dayprom_quantityshop_typeshopidassist_imageuniq_countuniq_info0100001000010000f288fe92e6fca2949653ba1703f5c7bd88925952150000538205834321:130312 1625899:3216971 20666:29457 20664:28105 20509:28315 20509:28317 1627207:28341 21541:38487 1627207:28320 20665:29453 1627207:3232478 1625894:3216794 20000:-1 20509:28316 412:800000057 1632501:-1 20681:130205 1627207:3232481 20511:28385 20666:29938 413:800000089300.00300.005180156354600632321623超级性感迷你<span class=H>短裙</span>i2/T1maVcXoRNXXb.zdI2_043534.jpg000.00002071981665587 843 907 1478 2049 3974 4166 4550物流宝yg测试店铺1物流宝yg测试店铺1浙江 杭州12.00浙江 杭州200000221800.003304212 218435790000304962349811110115000053820581i2/T1maVcXoRNXXb.zdI2_043534.jpg1超级性感迷你<span class=H>短裙</span>110000101300.001300.001100001101000010000100006071369973979422255153642041328520224316511500021413409412:800000078 1632501:-1 20509:28317 412:800000057 20000:-1 413:800000156 1627207:28326 21541:11102084.0084.00111709359031163289601623沙箱测试 韩版<span class=H>短裙</span>女半身裙夏季包臀裙秋冬裙子i1/T1IPmyXjJgXXb.wUo8_072123.jpg000.00002064448043587 843 907 4550sandbox_tmall的测试商城店铺sandbox_tmall浙江省  杭州10.00江苏 南京200000141800.003227315 3231620 3227317 3231623 3231622 107380 3227325 3231618 107381 46864 3231624 3231619 3231604 3231626 3227303 29051578 19851003 4294478 4076972 29050806 29050263 29050748 29051301 29050572 12386818 29050133 29050637 29050842 15474814 13075340 29051820 29051784 3389318 28920510 10518561 8111923 8095958 3457749 4305681 29051677 3216801 3216787 3216784 3216785 3216786 29051509 8065012 29050335 29050371 4341183 6113819 29050296 8219488 17654235 3280668 29051727 3551856 29050191 29051414 29050401 31938 30887 30889 42868 30695 97071 42866 42862 30893 30892 117198 42865 21428 308860000106005434611110115000214134091i1/T1IPmyXjJgXXb.wUo8_072123.jpg1沙箱测试 韩版<span class=H>短裙</span>女半身裙夏季包臀裙秋冬裙子11000010184.00184.00110000110100001000010000659e6f94c2a86d7fa4972a2021b8f69e202222126215000111049201627207:28320 21541:111018 1632501:-1 20000:-1 20509:2831310.0010.005180145359484354561623<span class=H>短裙</span>i2/T12ZJcXhdcXXchEM32_044248.jpg69000.00552050782802587 775 843 907 1478 2049 3974 4166 4550 2562 2689 21781627207:28320-i4/2050782802/T2mFRcXgdaXXXXXXXX_!!2050782802.jpgnmgv871728nmgv871728浙江 杭州0.00重庆;上海;黑龙江 哈尔滨;北京20000061800.00330419600057120491856141111690115000111049201i2/T12ZJcXhdcXXchEM32_044248.jpg1<span class=H>短裙</span>11000015110.00110.001100001101000010000100006b9d63e9e144ff2746f787fcc3783249-1449065463144906546331357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 20879:-1 22572:-1 20100:-1 20122:-1 20000:-1 10004:-1 20574:-1 10006:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-149.0049.00513743895347250000563可爱绒布荷包超<span class=H>短裙</span>0949i4/921/c00/T1DZJcXddAtgBXXXXX.jpg000.00001284165931478 2049 3974 4166pcrown旗舰店湖北 武汉0.00湖北省 武汉200000231500.0000001802112002144906546314487400132i4/921/c00/T1DZJcXddAtgBXXXXX.jpgi4/e60/dc0/T1.UxcXfXcE3BXXXXX.jpg2可爱绒布荷包超<span class=H>短裙</span>094908昕薇款蓬蓬格子<span class=H>短裙</span>02621000010000200249.004939.00249.004939.0021000010000220100001000010000fe7b0a066d083cc20ee423271ffab8b4-1481543754148154375431185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 31696:28964 20100:-1 22572:-1 21315:-1 20879:21456 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 10000:-1 20145:-135.0035.005137438953472110203韩国品牌百搭格子蕾丝超<span class=H>短裙</span>促销F2214i2/d31/190/T1v2NdXf2WsdJXXXXX.jpg000.0000924592501478 2049 3974 4166irisy夜巴黎专卖店山东 聊城6.00山东省 聊城20000071500.00000029011110114815437541i2/d31/190/T1v2NdXf2WsdJXXXXX.jpg1韩国品牌百搭格子蕾丝超<span class=H>短裙</span>促销F221411000010135.00135.0011000011010000100001000091e8c3eb7e8cc2e5e87014620ab60e73-1479786730147978673031185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 31696:28964 20100:-1 22572:-1 21315:-1 20879:21456 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 10000:-1 20145:-129.0029.0010137438953472110203翠啼鸟*千层下摆格子超<span class=H>短裙</span>i4/f71/a31/T1DDXaXexXq.BXXXXX.jpg000.00001331966521478 2049 3974 4166翠啼鸟女装旗舰店上海 上海5.00上海20000071500.000000230311300031479786730147981404214805577823i4/f71/a31/T1DDXaXexXq.BXXXXX.jpgi1/d61/c30/T1F4FcXo0byEBXXXXX.jpgi4/e80/d00/T1QxlcXolhycBXXXXX.jpg3翠啼鸟*千层下摆格子超<span class=H>短裙</span>翠啼鸟2199#毛茸茸春秋冬保暖百搭超<span class=H>短裙</span> 三色可选翠啼鸟2313#百折百搭超<span class=H>短裙</span>31000010000100003000329.0038.0038.00329.0038.0038.003100001000010000330100001000010000041f7820513aee5b387acfed8a8cb468-1448392439144839243930000:12054602855.0055.00513743895347250012027日.韩.新.街.钉珠公主裙.超<span class=H>短裙</span>.连衣裙.送围巾满68包邮i3/4e1/851/T1Wb4dXo9onJJXXXXX.jpg000.0000130877393651 1478 2049 3974 4166芊睿服饰旗舰店上海 上海0.00上海20000061800.00000070311300031448392439144907188514494474233i3/4e1/851/T1Wb4dXo9onJJXXXXX.jpgi3/080/c91/T1BH0dXgVQnJJXXXXX.jpgi2/8c0/180/T1k.XXXc0iokBXXXXX.jpg3日.韩.新.街.钉珠公主裙.超<span class=H>短裙</span>.连衣裙.送围巾满68包邮日.韩.新.街.钉珠公主裙.超<span class=H>短裙</span>.连衣裙.送围巾满68包邮卡娃依牛仔小裙围超<span class=H>短裙</span>31000010000100003000355.004939.0039.00355.004939.0039.0031000010000100003301000010000100007d31839cc7fb1fcfa555ab1b93976efc-144974718114497471814939.004939.00513743895347250012028日.韩.新.街.钉珠公主裙.超<span class=H>短裙</span>.连衣裙.送围巾满68包邮i4/5a0/220/T1nH4dXkpvH3BXXXXX.jpg000.00001546512121478 2049 3974 4166澳丝缇娜服饰旗舰店上海 上海0.00上海200000221800.00000070311300031449747181144872032714484336073i4/5a0/220/T1nH4dXkpvH3BXXXXX.jpgi2/151/4f0/T1gHleXhdGKdJXXXXX.jpgi1/b01/351/T1J6JdXgUszJJXXXXX.jpg3日.韩.新.街.钉珠公主裙.超<span class=H>短裙</span>.连衣裙.送围巾满68包邮AUSTINA英伦格子抹胸裙连衣裙 超<span class=H>短裙</span>.百褶裙AUSTINA春新款经典英伦时尚百搭格子百褶裙<span class=H>短裙</span>3100001000010000300034939.0055.0049.0034939.0055.0049.00310000100001000033010000100001000023a07470456a21424e6134083f26c263-1448853127144885312731357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 20879:-1 22572:-1 20100:-1 20122:-1 20000:-1 10004:-1 20574:-1 10006:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-159.0059.00513743895347250000563最新韩国李孝利百搭休闲时尚牛仔<span class=H>短裙</span>C101653i1/001/b21/T1I4NdXXMGwJJXXXXX.jpg000.00001336746211478 2049 3974 4166joyceshop缇奥广东 广州0.00广东省 广州200000231500.0000004011110114488531271i1/001/b21/T1I4NdXXMGwJJXXXXX.jpg1最新韩国李孝利百搭休闲时尚牛仔<span class=H>短裙</span>C10165311000010159.00159.001100001101000010000100006e5a583a48b2d8be107eeabb9e2f50ef-1448567095144856709531357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 20879:-1 22572:-1 20100:-1 20122:-1 20000:-1 10004:-1 20574:-1 10006:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-149.0049.00513743895347250000563新款修身直筒百搭宽腿混搭做旧水磨图案牛仔铅笔裤<span class=H>短裙</span>2122i2/041/680/T1qWFfXnUNRdJXXXXX.jpg000.00001525811791478 1922 2049 3974 4166唯美特旗舰店广东 深圳0.00广东省 深圳200000231500.000000190311300031448567095144885739914485670793i2/041/680/T1qWFfXnUNRdJXXXXX.jpgi4/761/da0/T1yGFfXkERRdJXXXXX.jpgi1/251/451/T1kWFfXcQQRdJXXXXX.jpg3新款修身直筒百搭宽腿混搭做旧水磨图案牛仔铅笔裤<span class=H>短裙</span>2122新款修身直筒百搭宽腿混搭做旧水磨图案牛仔铅笔裤<span class=H>短裙</span>2128新款修身直筒百搭宽腿混搭做旧水磨图案牛仔铅笔裤<span class=H>短裙</span>212331000010000100003000349.0049.0049.00349.0049.0049.0031000010000100003301000010000100007f1f4ec2871c3fe56b97feeb9184818e-1448871501144887150131357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 20879:-1 22572:-1 20100:-1 20122:-1 20000:-1 10004:-1 20574:-1 10006:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-14939.004939.00513743895347250000563HGE欧.韩.百褶.休闲毛呢百褶裙蛋糕裙<span class=H>短裙</span> 特价i3/9c0/fa1/T13JtcXllitZBXXXXX.jpg000.00001346064111478 2049 3974 4166hge服饰旗舰店广东 广州0.00广东省 广州200000231500.000000260711600000061448871501144837159914488197251449287063144849903914485044476i3/9c0/fa1/T13JtcXllitZBXXXXX.jpgi4/400/421/T1MI4dXfJNI3BXXXXX.jpgi2/T1.VtcXcleXXcAxWU0_034309.jpgi2/d80/360/T1dwpaXdFhqsBXXXXX.jpgi3/9f0/980/T17GpeXa.sJJJXXXXX.jpgi1/990/940/T17GleXkwfJJJXXXXX.jpg6HGE欧.韩.百褶.休闲毛呢百褶裙蛋糕裙<span class=H>短裙</span> 特价HGE欧.韩.百褶.休闲棉质百褶裙蛋糕裙<span class=H>短裙</span>82220特价HGE欧.韩.百褶.休闲棉质百褶裙蛋糕裙<span class=H>短裙</span>82230HGE欧.韩.百褶.休闲棉质百褶裙蛋糕裙<span class=H>短裙</span>82117HGE欧.韩.百褶.休闲棉质百褶裙蛋糕裙<span class=H>短裙</span>82113HGE欧.韩.百褶.休闲棉质百褶裙蛋糕裙<span class=H>短裙</span>821136100001000010000100001000010000600000064939.0038.004939.0060.0035.0035.0064939.0038.004939.0060.0035.0035.006100001000010000100001000010000660100001000010000b03d9dc1348789d033e36b3e90d02fe3-1481373208148137320831185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 31696:28964 20100:-1 22572:-1 21315:-1 20879:21456 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 10000:-1 20145:-158.0058.0010137438953472110203满300送49买一送一欧韩个性淑女时尚潮流完美搭配毛衣<span class=H>短裙</span>i2/1d1/910/T1Ma4bXaJg63xXXXXX.jpg000.0000110843578385 1478 2049 3974 4166闽绣服饰旗舰店福建 泉州8.00福建省 泉州20000071500.000000390311300031481373208148101932014805188863i2/1d1/910/T1Ma4bXaJg63xXXXXX.jpgi1/1f1/e31/T1gSFaXfxd.UxXXXXX.jpgi3/8a0/dd1/T1RFtcXi0omEBXXXXX.jpg3满300送49买一送一欧韩个性淑女时尚潮流完美搭配毛衣<span class=H>短裙</span>满300送49随机买一送一欧韩个性淑女时尚潮流休闲百搭春<span class=H>短裙</span>欧韩淑女个性300满送49买一送一百搭<span class=H>短裙</span>毛衣全场包邮秋装31000010000100003000358.0059.0059.00358.0059.0059.003100001000010000330100001000010000c0df4a64a2772af65f0c281c2780ee75-1481838624148183862431185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 31696:28964 20100:-1 22572:-1 21315:-1 20879:21456 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 10000:-1 20145:-129.0029.005137438953472110203格子片片<span class=H>短裙</span>(2色入)修身韩古百褶裙黑白甜美清纯_混搭或百i4/2a0/9f0/T1cnteXoiuPJJXXXXX.jpg000.0000951670801478 2049 3974 4166丹妮丝享利旗舰店上海 上海10.00上海20000071500.000000100041140000414818386241481974496148092202614805836324i4/2a0/9f0/T1cnteXoiuPJJXXXXX.jpgi1/701/510/T13_xdXd9CHtJXXXXX.jpgi4/721/5e0/T1UiRdXa4zQoBXXXXX.jpgi2/dd0/2e1/T1G.NcXfuUj0JXXXXX.jpg4格子片片<span class=H>短裙</span>(2色入)修身韩古百褶裙黑白甜美清纯_混搭或百小鹿绒球针织<span class=H>短裙</span>（5色入）~混搭~百搭~图案~新破旧之美时尚<span class=H>短裙</span>~新~混搭~百搭~图案迷人格格可爱<span class=H>短裙</span>红色~混搭~百搭~新~图案41000010000100001000040000429.0033.0045.0026.00429.0033.0045.0026.00410000100001000010000440100001000010000abaadf466b49d1f9f16edab693aa39fc-1481904734148190473431185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 31696:28964 20100:-1 22572:-1 21315:-1 20879:21456 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 10000:-1 20145:-1150.00150.005137438953472110203歌莉娅色丁印花<span class=H>短裙</span>配皮带与裤袜67385211i2/dc0/1b0/T1JGXcXdXpokBXXXXX.jpg000.00001330065621478 2049 3974 4166歌莉娅官方旗舰店广东 广州20.00广东省 广州200000111500.000000200311300031481904734148194136814818848783i2/dc0/1b0/T1JGXcXdXpokBXXXXX.jpgi2/250/a40/T1m1tcXa0hvkBXXXXX.jpgi4/f70/e51/T1T74XXa4hogBXXXXX.jpg3歌莉娅色丁印花<span class=H>短裙</span>配皮带与裤袜67385211歌莉娅拼罗纹直筒牛仔<span class=H>短裙</span>配送裤袜 31386203歌莉娅衬衣领装饰牛仔<span class=H>短裙</span>配送皮带31385208310000100001000030003150.00185.00259.003150.00185.00259.0031000010000100003301000010000100002c5eccec896bfd5d2d7fdb4fa35388f7-1447989431144798943120930:-1 10006:-1 10000:-1 20571:-1 10004:-1 30606:-1 20000:-1 20574:-1 10003:-1 20879:-1 10002:-155.0055.003013743895347250000563韩国Facoup公主小碎花可爱雪纺小<span class=H>短裙</span>SS044i3/051/e50/T1t70aXhpmHEydcUgT.jpg000.0000929201731478 2049 3974 4166facoupcoupkey专卖广东 深圳0.00广东省 深圳200000231500.000000190311300031447989431144811289314489650073i3/051/e50/T1t70aXhpmHEydcUgT.jpgi2/0f0/141/T18i0XXoxk.ExXXXXX.jpgi1/d11/3c1/T1tCNdXne8GJJXXXXX.jpg3韩国Facoup公主小碎花可爱雪纺小<span class=H>短裙</span>SS044满40包邮韩国facoup黑白条纹松紧带休闲迷你<span class=H>短裙</span>满40包邮春装预售facoup可爱加绒迷你小裙子<span class=H>短裙</span>两色入31000010000100003000355.004939.0059.00355.004939.0059.0031000010000100003301000010000100001a522b419b153d8ffdbffd90601d65e1-1449637375144963737520930:-1 10006:-1 10000:-1 20571:-1 10004:-1 30606:-1 20000:-1 20574:-1 10003:-1 20879:-1 10002:-153.0053.001013743895347250000563BINGO百搭.混搭.日韩可爱网纱迷你半身<span class=H>短裙</span>906006i4/3d0/f60/T1apVfXe10Q0JXXXXX.jpg000.00001205747471478 2049 3974 4166bingosii旗舰店广东 广州0.00广东省 广州200000231500.000000190311300031449637375144842306314484809833i4/3d0/f60/T1apVfXe10Q0JXXXXX.jpgi3/760/8f1/T1SNhdXfV3v0JXXXXX.jpgi1/481/e81/T1klhdXejQCJJXXXXX.jpg3BINGO百搭.混搭.日韩可爱网纱迷你半身<span class=H>短裙</span>906006Bingo清纯气质<span class=H>短裙</span>T190090Bingo可爱活力十足泡泡裙<span class=H>短裙</span>T19033831000010000100003000353.0023.0025.00353.0023.0025.0031000010000100003301000010000100000731d827a47d2be23e284705e9feb190-1449707015144970701520930:-1 10006:-1 10000:-1 20571:-1 10004:-1 30606:-1 20000:-1 20574:-1 10003:-1 20879:-1 10002:-165.0065.001013743895347250000563黑色雪纺花边蛋糕<span class=H>短裙</span>i3/790/1d1/T18ENeXflDQtJXXXXX.jpg000.00001015335991478 2049 3974 4166dabuwawa旗舰店上海 上海0.00上海200000231500.000000110311300031449707015144941739114487887333i3/790/1d1/T18ENeXflDQtJXXXXX.jpgi1/f00/2b0/T1YqBfXbkPRdJXXXXX.jpgi1/7e1/ae0/T1iuhcXlXdu.BXXXXX.jpg3黑色雪纺花边蛋糕<span class=H>短裙</span>白色蕾丝三层蛋糕<span class=H>短裙</span>Dabuwawa啡色点点百折<span class=H>短裙</span>31000010000100003000365.0069.004939.00365.0069.004939.003100001000010000330100001000010000d09ea6b9223eef5b9b2db99aacb06c51-1480317622148031762231185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 20879:-1 31356:-1 1626817:-1 30606:-1 20100:-1 22572:-1 21315:-1 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-175.0075.00513743895347211春妮 西装 韩 百褶裙 针织.裙.K59百褶雪纺裙边半身<span class=H>短裙</span>i3/bf1/020/T16HxeXXOqKdJXXXXX.jpg000.00001121516681478 2049 3974 4166言诺春妮专卖店福建 福州12.00福建省 福州20000031500.00000025011110114803176221i3/bf1/020/T16HxeXXOqKdJXXXXX.jpg1春妮 西装 韩 百褶裙 针织.裙.K59百褶雪纺裙边半身<span class=H>短裙</span>11000010175.00175.001100001101000010000100008b7259b72950ee953094b43eea4e3fd9-1481888310148188831031185:-1 31357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 20879:-1 31356:-1 1626817:-1 30606:-1 20100:-1 22572:-1 21315:-1 20122:-1 20000:-1 10004:-1 20574:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-139.0039.00513743895347211日韩新.时.学.闲.搭.可.淑.瑞.高档毛呢双排金扣<span class=H>短裙</span>i4/f01/8e1/T1HE8cXnTIkdJXXXXX.jpg000.0000961327781478 2049 3974 4166爱萌旗舰店上海 上海8.00上海20000031500.00000010405115000005148188831014803296521480695370148068655414806482465i4/f01/8e1/T1HE8cXnTIkdJXXXXX.jpgi4/430/890/T1DFdfXj4RQJJXXXXX.jpgi3/421/cc0/T1NKJeXo30LdJXXXXX.jpgi4/340/621/T176FdXcRDzJJXXXXX.jpgi4/610/ed0/T1jjFdXXI_ztJXXXXX.jpg5日韩新.时.学.闲.搭.可.淑.瑞.高档毛呢双排金扣<span class=H>短裙</span>日韩时尚BE016#日系混搭甜美白色蕾丝滚边<span class=H>短裙</span>2色日韩时尚s108 睃织腰带格子<span class=H>短裙</span>（配腰带 ）日韩时尚流行淑女979#小鹿图样迷你裙/<span class=H>短裙</span>日韩时尚流行淑女979#小鹿图样迷你裙/<span class=H>短裙</span>51000010000100001000010000500000539.00100.0032.0029.0029.00539.00100.0032.0029.0029.005100001000010000100001000055010000100001000009d7ce19d2690a5dfe2ab11a9591d5a2-1448608383144860838331357:-1 20930:-1 1626975:-1 20183:-1 10002:-1 20571:-1 20137:-1 31356:-1 1626817:-1 30606:-1 20879:-1 22572:-1 20100:-1 20122:-1 20000:-1 10004:-1 20574:-1 10006:-1 20121:-1 20143:-1 10003:-1 31696:-1 10000:-1 20145:-129.0029.00513743895347250000563日韩时尚流行淑女979#小鹿图样迷你裙/<span class=H>短裙</span>i2/680/d91/T1VjFdXjlwzJJXXXXX.jpg000.0000945351951478 2049 3974 4166喜子旗舰店山东 烟台0.00山东省 烟台200000231500.00000066011110114486083831i2/680/d91/T1VjFdXjlwzJJXXXXX.jpg1日韩时尚流行淑女979#小鹿图样迷你裙/<span class=H>短裙</span>11000010129.00129.0011000011";
		byte[] data = str.getBytes();
		
		long currentTime = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			new String(data);
		}
		System.out.println(System.currentTimeMillis()-currentTime); //1078
		
	}
	*/
	
	public static void main(String[] args) {
        //init data with many repetitions of A-Z
        final byte[] temp = new byte[ 1024 * 1024 * 100];
        temp[ 5 ] = 34;
        final byte[] data = getData();
 
        {
            final long start = System.currentTimeMillis();
            testFaulty(data);
            final long time = System.currentTimeMillis() - start;
            System.out.println( "Time for slow call: " + time / 1000.0 + " sec");
        }
        {
            final long start = System.currentTimeMillis();
            testFixed(data);
            final long time = System.currentTimeMillis() - start;
            System.out.println( "Time for fixed call: " + time / 1000.0 + " sec");
        }
        {
        	final long start = System.currentTimeMillis();
        	for(int i = 0; i < 100; ++i){
        		new String(data);
        	}
        	final long time = System.currentTimeMillis() - start;
        	System.out.println( "Time for native call: " + time / 1000.0 + " sec");
        }
        System.out.println( temp.length );
    }
 
	
    //test loop for slow constructor
    private static void testFaulty(byte[] data) {
        long totalLen = 0;
        //read each repetition as a separate String
        for ( int i = 0; i < 100; ++i )
        {
            totalLen += test(data);
            //System.out.println( i );
        }
 
        System.out.println( totalLen );
    }
 
    //test loop for fixed constructor call
    private static void testFixed(byte[] data) {
        long totalLen = 0;
        //read each repetition as a separate String
        for ( int i = 0; i < 100; ++i )
            totalLen += testOk(data);
 
        System.out.println( totalLen );
    }
 
    //single pass with slow constructor
    private static long test(byte[] data) {
        final int CNT = BUF_SIZE / 26;
        int p = 0;
        long len = 0;
        for ( int i = 0; i < CNT; ++i, p += 26 )
        {
            final String part = new String( data, p, 26, ASCII );
            len += part.length();
        }
        return len;
    }
 
    //single pass working ok in Java 6
    private static long testOk(byte[] data) {
        final int CNT = BUF_SIZE / 26;
        int p = 0;
        long len = 0;
        for ( int i = 0; i < CNT; ++i, p += 26 )
        {
            final byte[] tmp = Arrays.copyOfRange( data, p, p + 26 );
            final String part = new String( tmp, 0, tmp.length, ASCII );
            len += part.length();
        }
        return len;
    }
 
    private static byte[] getData() {
        final byte[] data = new byte[ BUF_SIZE ];
        byte c = 65;
        for ( int i = 0; i < BUF_SIZE; ++i )
        {
            data[ i ] = c;
            if ( c == 90 )
                c = 65;
            else
                ++c;
        }
        return data;
    }
	/*private static String getString(byte[] data){
		
	}*/

}
