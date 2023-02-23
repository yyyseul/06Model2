package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//상품관리를 추상화/캡슐화한 PurchaseService Interface
public interface PurchaseService {
	
	//구매내역추가
	public void addPurchase(Purchase purchase) throws Exception;
	
	//구매정보조회
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//구매목록조회
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;
	
	//판매목록조회? 아직모름...
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	//구매정보수정
	public void updatePurchase(Purchase purchase) throws Exception;
	
	//구매코드변경 by 구매번호 "3"(배송중)->"4"(배송완료)
	public void updateTranCode(Purchase purchase) throws Exception;
	
	//구매코드변경 by 상품번호 "2"(판매완료)->"3"(배송중)
	public void updateTranCodeByProd(Purchase purchase) throws Exception;

}
