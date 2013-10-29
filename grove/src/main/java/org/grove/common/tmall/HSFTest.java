package org.grove.common.tmall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.hsf.hsfunit.HSFEasyStarter;
import com.taobao.hsf.hsfunit.util.ServiceUtil;
import com.taobao.tmallsearch.domain.module.api.ShopItemProductInfoDO;
import com.taobao.tmallsearch.domain.module.shop.ExtendShopInfoDO;
import com.taobao.tmallsearch.query.api.ShopItemQuery;
import com.taobao.tmallsearch.query.api.ShopParamQuery;
import com.taobao.tmallsearch.result.api.CategoryCatInfo;
import com.taobao.tmallsearch.result.api.ShopItemResultDO;
import com.taobao.tmallsearch.result.api.ShopSearchHSFResultDO;
import com.taobao.tmallsearch.service.ShopItemSearchService;
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
		query.setQ("tworkb1447店铺");
		//query.setLoc("河北");
		//query.setUser_id("yexue_b12");
		ShopSearchHSFResultDO sro = shopSearchService.searchShop(query);
		List<ExtendShopInfoDO> list = sro.getShopList();
		System.out.println(sro.getPreSearchUrl());
		for(ExtendShopInfoDO item : list){
			System.out.println(item.getShopInfoDO().getTitle() + "\t" + item.getShopInfoDO().getShopPic());
		}
		ctx.close();
		
	}
	
	
	private static void searchItem(){
		HSFEasyStarter.start("D:/resource/taobao-hsf.sar", "1.4.9.6");

		String springResourcePath = "applicationContext.xml";
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(springResourcePath);
		ShopItemSearchService shopItemSearchService = (ShopItemSearchService) ctx.getBean("shopItemSearchServiceClient");

		// 等待相关服务的地址推送
		ServiceUtil.waitServiceReady(shopItemSearchService);
		
		ShopItemQuery query = new ShopItemQuery();
		query.setUser_id("2017475102");
		ShopItemResultDO doo =  shopItemSearchService.searchItem(query);
		List<CategoryCatInfo> lc = doo.getCatInfo();
		for(CategoryCatInfo cci : lc){
			System.out.println(cci.getCatName() + "("+cci.getItemCount()+")");
		}
		List<ShopItemProductInfoDO> sisr = doo.getListItemInfo();
		for(ShopItemProductInfoDO item : sisr){
			System.out.println(item.getName() + "\t-\t" + item.getNick() + "\t-\t" + item.getPrice() + ":" + item.getMinPrice());
		}
		
		ctx.close();
	}
	
}
