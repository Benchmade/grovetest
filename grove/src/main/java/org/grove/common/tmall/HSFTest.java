package org.grove.common.tmall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.hsf.hsfunit.HSFEasyStarter;
import com.taobao.hsf.hsfunit.util.ServiceUtil;
import com.taobao.tmallsearch.query.api.ItemQuery;
import com.taobao.tmallsearch.query.api.ShopParamQuery;
import com.taobao.tmallsearch.result.api.ApiProductSearchResultDO;
import com.taobao.tmallsearch.result.api.ShopItemDO;
import com.taobao.tmallsearch.result.api.ShopSearchHSFResultDO;
import com.taobao.tmallsearch.result.api.ShopWithItemInfoDOHsf;
import com.taobao.tmallsearch.service.ItemSearchService;
import com.taobao.tmallsearch.service.ShopSearchService;

public class HSFTest {
	//http://ops.jm.taobao.net/service-manager/
	public static void main(String[] args) throws Exception{
		searchShop();
	}

	private static void searchShop(){
		HSFEasyStarter.start("D:/resource/taobao-hsf.sar", "1.4.9.6");

		String springResourcePath = "applicationContext.xml";
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(springResourcePath);
		ShopSearchService shopSearchService = (ShopSearchService) ctx.getBean("shopSearchServiceClient");

		// 等待相关服务的地址推送
		ServiceUtil.waitServiceReady(shopSearchService);
		ShopParamQuery query = new ShopParamQuery();
		query.setQ("耐克");
		ShopSearchHSFResultDO sro = shopSearchService.searchShop(query);
		System.out.println("召回店铺数: " + sro.getTotalItem());
		List<ShopWithItemInfoDOHsf> list = sro.getShopList();
		System.out.println("@@@@@@@@@@@@@@@@@"+list.size());
		for(ShopWithItemInfoDOHsf item : list){
			System.out.println("店铺名: \t" + item.getShopTitle() + "\t"+item.getUniqCount());
			/*List<ShopItemDO> lio = item.getItems(); //店铺下商品
			for(ShopItemDO iod : lio){
				System.out.println("\t\t宝贝标题: " +iod.getTitle() + "  @宝贝ID:" +  iod.getNid());
				System.out.println("\t\t宝贝pic:" +  iod.getPic());
				System.out.println("\t\tprice: " +iod.getMinPrice() + "  @30天销售笔数:" +  iod.getMonthSoldQuantity4Gaiban());
				System.out.println("\t\t累计销售数量: " +iod.getTotalSoldQuantity4Gaiban() + "  @评论数:" +  iod.getCommentNum());
				System.out.println();
				System.out.println();
			}*/
		}
		ctx.close();
		
	}
	
	
	
	private static void searchItem(){
		HSFEasyStarter.start("D:/h2/taobao-hsf.sar", "1.8.0.8");

		String springResourcePath = "applicationContext.xml";
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(springResourcePath);
		ItemSearchService shopSearchService = (ItemSearchService) ctx.getBean("itemSearchService");

		// 等待相关服务的地址推送
		ServiceUtil.waitServiceReady(shopSearchService);
		ItemQuery query = new ItemQuery();
		query.setQ("nike");
		ApiProductSearchResultDO sro = shopSearchService.searchItems(query);
		System.out.println(sro.getTotalResults());
		/*System.out.println("召回店铺数: " + sro.getTotalItem());
		List<ShopWithItemInfoDOHsf> list = sro.getShopList();
		for(ShopWithItemInfoDOHsf item : list){
			System.out.println("店铺名:  "+item.getShopPic() + "\t" + item.getUserId() + "\t"+item.getId() + "\t" + item.getShopId() + "\t" + item.getShopTitle());
			List<ShopItemDO> lio = item.getItems(); //店铺下商品
			for(ShopItemDO iod : lio){
				System.out.println("\t\t宝贝标题: " +iod.getTitle() + "  @宝贝ID:" +  iod.getNid());
				System.out.println("\t\t宝贝pic:" +  iod.getPic());
				System.out.println("\t\tprice: " +iod.getMinPrice() + "  @30天销售笔数:" +  iod.getMonthSoldQuantity4Gaiban());
				System.out.println("\t\t累计销售数量: " +iod.getTotalSoldQuantity4Gaiban() + "  @评论数:" +  iod.getCommentNum());
				System.out.println();
				System.out.println();
			}
		}*/
		ctx.close();
		
	}
}
