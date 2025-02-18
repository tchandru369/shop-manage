package com.merchant.management.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.merchant.management.entity.ProductDetailRes;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.repository.ProductRepository;

@Service
public class ProductServices {

 @Autowired
 private ProductRepository productRepository;
 
 
 @Autowired
 public ProductServices(ProductRepository productRepository) {
     this.productRepository = productRepository;
 }
 
 public ResponseEntity<ProductDetailRes> addProducts(List<ProductDetails> productList){
	 List<ProductDetails> newProductList = new ArrayList<ProductDetails>();
	 ProductDetailRes productDetailRes = new ProductDetailRes();
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	 for(int i=0;i<productList.size();i++) {
		 System.out.println(productList.get(i));
	 }
	 for(int i=0;i<productList.size();i++) {
		 productList.get(i).setProductPrimaryId(productRepository.getNextSequenceValue());
		 productList.get(i).setProductCreatedOn(timestamp.toString());
		 newProductList.add(productList.get(i));
	 }
	 for(int i=0;i<newProductList.size();i++) {
		 System.out.println(newProductList.get(i));
	 }
       productRepository.saveAll(newProductList);
	 productDetailRes.setResponse("success");
	 return ResponseEntity.ok(productDetailRes);
 }
 
 public ResponseEntity updateProductDetails(ProductDetails productDetails) {
	ProductDetailRes productRes = new ProductDetailRes();
	int currentProductQty = Integer.parseInt(productDetails.getProductQuantity());
	int prodQtyFromDB = Integer.parseInt(productRepository.getProductQuantity(productDetails.getProductOwner(), productDetails.getProductName()));
	int productQuantity = prodQtyFromDB + currentProductQty;
	System.out.println("Product Quantity : "+productQuantity);
	String strPrdQty = Integer.toString(productQuantity);
	
 	productRepository.updateProductQty(productDetails.getProductOwner(), productDetails.getProductName(),strPrdQty);
    productRes.setResponse("success");
    productRes.setErrorCode("0");
	 return ResponseEntity.ok(productRes);
 }
 
 public List<ProductDetails> getProductDetails(String ownerName){
	 
	 List<ProductDetails> productDetails = productRepository.getProductDetails(ownerName);
	 
	 return productDetails;
 }
 
 public List<ProductDetails> getDemandProductDetails(String ownerName){
	 
	 List<ProductDetails> productDemandDetails = productRepository.getDemandProductDetails(ownerName);
	 
	 return productDemandDetails;
 }
 

}
