package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//==> 구매관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface PurchaseDao {
	
	//INSERT
	public void addPurchase(Purchase purchase) throws Exception;
	
	//SELECT ONE
	public Purchase getPurchase(int TranNo) throws Exception;
	
	//SELECT LIST
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception;
	
	//SELECT LIST SaleList
	//UPDATE
	public void updatePurchase(Purchase purchase)throws Exception;
	
	//UPDATE BY TRANNO
	public void updateTranCode(Purchase purchase)throws Exception;
	
	//UPDATE BY PRODNO
	public void updateTranCodeByProd(Purchase purchase)throws Exception;
	
	//게시판 Page 처리를 위한 전체 Row(totalCount) return
	public int getTotalCount(String userId)throws Exception;

}
