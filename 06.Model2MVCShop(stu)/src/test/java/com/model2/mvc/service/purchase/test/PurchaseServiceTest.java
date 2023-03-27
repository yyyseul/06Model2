package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"})
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void testAddPurchase() throws Exception{
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		//purchase.setTranNo(6); ==> �������� ó��
		product.setProdNo(10023);
		purchase.setPurchaseProd(product);
		user.setUserId("user08");
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("testName");
		purchase.setReceiverPhone("000000000");
		purchase.setDivyAddr("����");
		purchase.setDivyRequest("test");
		purchase.setTranCode("1");
		purchase.setDivyDate("2023-02-03");
		
		purchaseService.addPurchase(purchase);
		System.out.println(purchase);
		//purchase = purchaseService.getPurchase(10148);
		
				
		
	}
	
	//@Test //==> ��¥����...
	public void testGetPurchase() throws Exception{
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10149);

		//�ܼ�Ȯ��
		System.out.println(purchase);
		
		//API Ȯ��
		Assert.assertEquals(10149, purchase.getTranNo());
		Assert.assertEquals(10023, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user08", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("testName", purchase.getReceiverName());
		Assert.assertEquals("000000000", purchase.getReceiverPhone());
		Assert.assertEquals("����", purchase.getDivyAddr());
		Assert.assertEquals("test", purchase.getDivyRequest());
		Assert.assertEquals("1", purchase.getTranCode().trim());
		//dateŸ���� ��� ǥ��?
		//Assert.assertEquals("2023-02-03", purchase.getDivyDate());
		
		
		Assert.assertNotNull(purchaseService.getPurchase(10148));
		Assert.assertNotNull(purchaseService.getPurchase(10147));	
		
	}
	
	//@Test
	public void testGetPurchaseList() throws Exception{
		
		//����������
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	Map<String, Object> map = purchaseService.getPurchaseList(search, "user08");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
				
		//�ܼ� Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);

	}
	
	//@Test //==> ��¥����...
	public void testUpdateProduct() throws Exception{
			
		Purchase purchase = purchaseService.getPurchase(10149);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("1",purchase.getPaymentOption().trim());		
		Assert.assertEquals("testName", purchase.getReceiverName());
		Assert.assertEquals("000000000", purchase.getReceiverPhone());
		Assert.assertEquals("����", purchase.getDivyAddr());
		Assert.assertEquals("test", purchase.getDivyRequest());
		
		System.out.println(purchase);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("change222");
		purchase.setReceiverPhone("22");
		purchase.setDivyAddr("�λ�");
		purchase.setDivyRequest("change222");
		//purchase.setDivyDate("20220222");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10149);
		
		Assert.assertNotNull(purchase);

		
		Assert.assertEquals("2", purchase.getPaymentOption().trim());		
		Assert.assertEquals("change222", purchase.getReceiverName());
		Assert.assertEquals("22", purchase.getReceiverPhone());
		Assert.assertEquals("�λ�", purchase.getDivyAddr());
		Assert.assertEquals("change222", purchase.getDivyRequest());
		
			
	}
	
	@Test
	public void testUpdateTranCode() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10147);
		
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("2", purchase.getTranCode().trim());
		Assert.assertEquals(10147, purchase.getTranNo());
		
		purchase.setTranCode("3");
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10147);
		
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("3", purchase.getTranCode().trim());	
		
	}
	
	//@Test
	public void testUpdateTranCodeByProdNo() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10147);
	
		Assert.assertEquals("2", purchase.getTranCode().trim());
		Assert.assertEquals(10024, purchase.getPurchaseProd().getProdNo());
		
		purchase.setTranCode("3");

		purchaseService.updateTranCodeByProd(purchase);
		
		purchase = purchaseService.getPurchase(10147);
		
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("3", purchase.getTranCode().trim());	
		
	}

}
