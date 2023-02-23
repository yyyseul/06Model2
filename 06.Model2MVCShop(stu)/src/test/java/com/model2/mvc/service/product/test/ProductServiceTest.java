package com.model2.mvc.service.product.test;

import java.sql.Date;
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
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
		product.setProdNo(1);
		product.setProdName("testProduct");
		product.setProdDetail("testProductDetail");
		product.setPrice(100);
		product.setManuDate("23000101");
		product.setFileName("test.jpg");
		//product.setProTranCode("1");
		

		productService.addProduct(product);
		product = productService.getProduct(1);
		
		//콘솔확인
		System.out.println(product);
		
		//API 확인
		Assert.assertEquals(1, product.getProdNo());
		Assert.assertEquals("testProduct", product.getProdName());		
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("23000101", product.getManuDate());
		Assert.assertEquals("test.jpg", product.getFileName());
		//Assert.assertEquals("1", product.getProTranCode());
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		//product.setProdNo(1);
		//product.setProdName("testProduct");
		//product.setProdDetail("testProductDetail");
		//product.setPrice(100);
		//product.setManuDate("23000101");
		//product.setFileName("test.jpg");
		
		product = productService.getProduct(1);
		
		//콘솔확인
		System.out.println(product);
		
		//API 확인
		Assert.assertEquals(1, product.getProdNo());
		Assert.assertEquals("testProduct", product.getProdName());		
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("23000101", product.getManuDate());
		Assert.assertEquals("test.jpg", product.getFileName());
		
		Assert.assertNotNull(productService.getProduct(10000));
		Assert.assertNotNull(productService.getProduct(10001));	
		
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(1);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testProduct", product.getProdName());		
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("23000101", product.getManuDate());
		Assert.assertEquals("test.jpg", product.getFileName());
		
		product.setProdName("change");
		product.setProdDetail("changeDetail");
		product.setPrice(777);
		product.setManuDate("232323");
		product.setFileName("change.jpg");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(1);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("change", product.getProdName());		
		Assert.assertEquals("changeDetail", product.getProdDetail());
		Assert.assertEquals(777, product.getPrice());
		Assert.assertEquals("232323", product.getManuDate());
		Assert.assertEquals("change.jpg", product.getFileName());
		
		
	}
	
	//@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	Map<String, Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//콘솔 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//콘솔 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdName() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(4);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("test");
	 	Map<String, Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(1, list.size());
	 	
	 	//콘솔 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(0, list.size());
	 	
	 	//콘솔 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	

}
