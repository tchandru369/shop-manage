package com.merchant.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.dto.MilkProductResponse;
import com.merchant.management.dto.MilkProductRequest;
import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.MilkProductEntity;
import com.merchant.management.entity.ProductDetailRes;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.service.ProductServices; 

@RestController
@RequestMapping("/services/v1/products")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductServices productService;
	
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDetailRes> addProducts(@RequestBody List<ProductDetails> productDetails){
		
		ProductDetailRes response = new ProductDetailRes();
		ResponseEntity<ProductDetailRes> responses =  productService.addProducts(productDetails);
		return responses;
	}
	
	@GetMapping("/viewProducts")
	public  List<ProductDetails> viewProducts(@RequestParam String name){
		List<ProductDetails> productDetail = productService.getProductDetails(name);
		for(int i=0;i<productDetail.size();i++) {
			System.out.println(productDetail.get(i).getProductName());
		}
		return productDetail;
	}
	
	@GetMapping("/demandProducts")
	public  List<ProductDetails> viewDemandProducts(@RequestParam String name){
		List<ProductDetails> productCustomerDetail = productService.getDemandProductDetails(name);
		for(int i=0;i<productCustomerDetail.size();i++) {
			System.out.println(productCustomerDetail.get(i).getProductName());
		}
		return productCustomerDetail;
	}
	
	@PostMapping("/updateProduct")
	public ResponseEntity updateProduct(@RequestBody ProductDetails productDetails){
		ResponseEntity responses =  productService.updateProductDetails(productDetails);
		return responses;
	}
	
	@PostMapping("/addMilkProd")
	public ResponseEntity addMilkProduct(@RequestBody List<MilkProductRequest> milkRequest){
		MilkProductResponse milkRes = new MilkProductResponse();
		ResponseEntity responses =  productService.addMilkProducts(milkRequest);
		
		return ResponseEntity.ok(responses);
		
	}
	
	@GetMapping("/getComDtls")
	public List<ComProdDtls> getComProdDtls(){
		List<ComProdDtls> comProdDtls = productService.getComProdDtls();
		return comProdDtls;
	}
	
	@GetMapping("/viewMilkProducts")
	public  List<MilkProductEntity> viewMilkProdDetails(@RequestParam String name){
		List<MilkProductEntity> productDetail = productService.getOwnerMilkProdDetails(name);
		for(int i=0;i<productDetail.size();i++) {
			System.out.println(productDetail.get(i).getProductName());
		}
		return productDetail;
	}
	
	@GetMapping("/viewDemandPrd")
	public  List<MilkProductEntity> viewDemandMilkProd(@RequestParam String name){
		List<MilkProductEntity> productDetail = productService.getDemandMilkProdDetail(name);
		for(int i=0;i<productDetail.size();i++) {
			System.out.println(productDetail.get(i).getProductName());
		}
		return productDetail;
	}

	@PostMapping("/updateMilkPrd")
	public ResponseEntity updateMilkProduct(@RequestBody MilkProductEntity productDetails){
		ResponseEntity responses =  productService.updateMilkProdDetails(productDetails);
		return responses;
	}
	
}
