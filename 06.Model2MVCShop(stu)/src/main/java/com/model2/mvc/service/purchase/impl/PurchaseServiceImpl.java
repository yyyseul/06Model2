package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

//==> 상품관리 서비스 구현
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	///Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao dao;
	public void setDao(PurchaseDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao prodDao;
	public void setProdDao(ProductDao prodDao) {
		this.prodDao = prodDao;
	}
	
	///Construct
	public PurchaseServiceImpl() {
		System.out.println("여기는 PurchaseServiceImpl 생성자");
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		
		//tranNo="2" 판매완료, 현재는 재고없음
		purchase.setTranCode("2");
		
		dao.addPurchase(purchase);

	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return dao.getPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		
		List<Purchase> list= dao.getPurchaseList(search, userId);
		int totalCount = dao.getTotalCount(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	//==> SaleList 추후에 구현하기
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		dao.updatePurchase(purchase);

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		dao.updateTranCode(purchase);
	}

	@Override
	public void updateTranCodeByProd(Purchase purchase) throws Exception {
		dao.updateTranCodeByProd(purchase);	
	}
}
