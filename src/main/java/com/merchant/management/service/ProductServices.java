package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.MilkProductResponse;
import com.merchant.management.dto.MilkProductRequest;
import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.MilkProductEntity;
import com.merchant.management.entity.ProductDetailRes;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.repository.MilkProductRepo;
import com.merchant.management.repository.ProductRepository;

@Service
public class ProductServices {

 @Autowired
 private ProductRepository productRepository;
 
 @Autowired
 private MilkProductRepo milkProductRepo;
 
 
 
 @Autowired
 public ProductServices(ProductRepository productRepository, MilkProductRepo milkProductRepo) {
     this.productRepository = productRepository;
     this.milkProductRepo = milkProductRepo;
 }
 
 public ResponseEntity addMilkProducts(List<MilkProductRequest> milkProduct) {
     String errorMsg = "";
	 MilkProductResponse milkRes = new MilkProductResponse();
	 List<MilkProductEntity> milkProdEntity = new ArrayList<MilkProductEntity>();
	 LocalDate currentDate = LocalDate.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
     String formattedDate = currentDate.format(formatter);
	 for(int i=0;i<milkProduct.size();i++) {
		 
		 int count = 0;
    	 count = milkProductRepo.getProdCountValue(milkProduct.get(i).getProductOwner(), milkProduct.get(i).getCompanyName(), 
    			 milkProduct.get(i).getProductType(), milkProduct.get(i).getProductName(),"1");
    	 System.out.println("the count is :"+count);
    	 
    	 if(count == 0) {
		 MilkProductEntity milkProdEntities = new MilkProductEntity();
		 milkProdEntities.setCompanyName(milkProduct.get(i).getCompanyName());
		 milkProdEntities.setProductOwner(milkProduct.get(i).getProductOwner());
		 milkProdEntities.setProductName(milkProduct.get(i).getProductName());
		 milkProdEntities.setProductType(milkProduct.get(i).getProductType());
		 milkProdEntities.setProductQuantity(milkProduct.get(i).getProductQuantity());
		 milkProdEntities.setProductBillPrice(milkProduct.get(i).getProductBillPrice());
		 milkProdEntities.setProductCustPrice(milkProduct.get(i).getProductCustPrice());
		 milkProdEntities.setProductShopPrice(milkProduct.get(i).getProductShopPrice());
		 milkProdEntities.setProductLive("1");
		 milkProdEntities.setProductCreatedDate(formattedDate);
		 milkProdEntity.add(milkProdEntities);
		 
		 }else {
			 errorMsg = errorMsg+" "+ String.valueOf(i)+". " + milkProduct.get(i).getCompanyName() + "-" + milkProduct.get(i).getProductType()+"-"+milkProduct.get(i).getProductName();
			 System.out.println(errorMsg);
		 }
	 }
	 
	 milkProductRepo.saveAll(milkProdEntity);
	 milkRes.setResponse("success");
	 milkRes.setErrorCode("0");
	 if(errorMsg.isEmpty()) {
		 milkRes.setErrorMsg("success");
	 }else {
		 milkRes.setErrorMsg("Product already available : "+errorMsg);
	 }
	 return ResponseEntity.ok(milkRes);
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
 
 public ResponseEntity updateMilkProdDetails(MilkProductEntity productDetails) {
	ProductDetailRes productRes = new ProductDetailRes();
	int currentProductQty = productDetails.getProductQuantity();
	int prodQtyFromDB = Integer.parseInt(milkProductRepo.getMilkProdQty(productDetails.getProductOwner(), productDetails.getProductName(),productDetails.getCompanyName(),productDetails.getProductType()));
	int productQuantity = prodQtyFromDB + currentProductQty;
	System.out.println("Product Quantity : "+productQuantity);
	String strPrdQty = Integer.toString(productQuantity);
	
 	milkProductRepo.updateMilkProdQty(productDetails.getProductOwner(),productDetails.getProductName(),productDetails.getCompanyName(),productDetails.getProductType(),productQuantity,productDetails.getProductCustPrice(), productDetails.getProductBillPrice(),productDetails.getProductShopPrice());
    productRes.setResponse("success");
    productRes.setErrorCode("0");
	 return ResponseEntity.ok(productRes);
 }
 
 public List<ProductDetails> getProductDetails(String ownerName){
	 
	 List<ProductDetails> productDetails = productRepository.getProductDetails(ownerName);
	 
	 return productDetails;
 }
 
 
 public List<ComProdDtls> getComProdDtls(){
	 List<ComProdDtls> comProdDtls = productRepository.getComProdDtls().stream()
			    .map(row -> new ComProdDtls((String) row[0], (String) row[1], (String) row[2]))
			    .collect(Collectors.toList());
	 
	 return comProdDtls;
 }
 
 
 public List<ProductDetails> getDemandProductDetails(String ownerName){
	 
	 List<ProductDetails> productDemandDetails = productRepository.getDemandProductDetails(ownerName);
	 
	 return productDemandDetails;
 }
 
 public List<MilkProductEntity> getOwnerMilkProdDetails(String ownerName){
	 
	 List<MilkProductEntity> productDetails = milkProductRepo.getOwnerMilkProdDetails(ownerName);
	 
	 return productDetails;
 }
 
public List<MilkProductEntity> getDemandMilkProdDetail(String ownerName){
	 
	 List<MilkProductEntity> productDetails = milkProductRepo.getDemandMilkProdEntity(ownerName);
	 
	 return productDetails;
 }
 

}
