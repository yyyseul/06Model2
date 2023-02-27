package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

//==> 판매관리 DAO CRUD 구현
@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println("여기는 PurchaseDaoImpl 생성자");
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("purchaseMapper.addPurchase", purchase);

	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("purchaseMapper.getPurchase",tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		System.out.println("userId는 무엇인가? "+userId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(search != null) {
			System.out.println("search는 무엇인가? "+search);
			map.put("search", search);
			map.put("userId", userId);
		}		
		System.out.println("Map은 무엇인가?" + map);
		
	
		return sqlSession.selectList("purchaseMapper.getPurchaseList",map);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updatePurchase", purchase);

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updateTranCode", purchase);
	
	}

	@Override
	public void updateTranCodeByProd(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updateTranCodeByProd", purchase);

	}

	@Override
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("purchaseMapper.getTotalCount", userId);
	}

}
