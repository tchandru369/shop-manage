package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.MilkProductEntity;
import com.merchant.management.entity.ProductDetails;

@Repository
@EnableJpaRepositories
public interface MilkProductRepo extends JpaRepository<MilkProductEntity, Integer> {

   
	@Query(value = "SELECT ea.product_id,ea.company_name,ea.product_bill_price,ea.product_cust_price,ea.product_live,ea.product_name,ea.product_owner,ea.product_quantity,ea.product_shop_price,ea.product_type,ea.product_created_date FROM milk_product_entity ea WHERE ea.product_owner =:merchantEmail", nativeQuery = true)
	List<MilkProductEntity> getOwnerMilkProdDetails(String merchantEmail);
	
	@Query(value = "SELECT ea.product_quantity FROM milk_product_entity ea WHERE ea.product_owner =:merchantEmail AND ea.product_name =:productName AND ea.company_name =:companyName AND ea.product_type =:productType", nativeQuery = true)
	String getMilkProdQty(String merchantEmail,String productName,String companyName,String productType);
	
	@Query(value = "SELECT ea.product_id,ea.company_name,ea.product_bill_price,ea.product_cust_price,ea.product_live,ea.product_name,ea.product_owner,ea.product_quantity,ea.product_shop_price,ea.product_type,ea.product_created_date FROM milk_product_entity ea WHERE ea.product_quantity <= 5 AND ea.product_owner =:ownerEmail", nativeQuery = true)
	List<MilkProductEntity> getDemandMilkProdEntity(String ownerEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE milk_product_entity SET product_quantity =:productQty, product_bill_price =:productBillPrice, product_cust_price =:productCustPrice, product_shop_price =:productShopPrice WHERE product_owner =:merchantEmail AND product_name =:productName AND product_type =:productType AND company_name =:companyName", nativeQuery = true)
	void updateMilkProdQty(String merchantEmail,String productName,String companyName, String productType,int productQty,double productCustPrice, double productBillPrice,double productShopPrice);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM milk_product_entity WHERE product_owner =:merchantEmail AND product_name =:productName AND product_type =:productType AND company_name =:companyName", nativeQuery = true)
	void deleteMilkProdQty(String merchantEmail,String productName,String companyName, String productType);
	
	@Query(value = "SELECT product_quantity FROM milk_product_entity WHERE product_owner =:ownerEmail AND\r\n"
			+ "product_name =:productName AND product_type =:productType AND company_name =:companyName", nativeQuery = true)
	int getCurrentMilkProdQty(String ownerEmail,String productType,String productName,String companyName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE milk_product_entity SET product_quantity =:productQty WHERE product_owner =:ownerEmail AND product_name =:productName AND product_type =:productType AND company_name =:companyName", nativeQuery = true)
	void updateMilkProcQty(String ownerEmail,String productName,String companyName, String productType,int productQty);
	
	@Query(value = "SELECT COUNT(*) FROM milk_product_entity WHERE product_owner =:ownerEmail AND company_name =:cmpName AND product_type =:prodType\r\n"
			+ " AND product_name =:prodName AND product_live =:prodLive", nativeQuery = true)
	int getProdCountValue(String ownerEmail,String cmpName,String prodType,String prodName,String prodLive);
	
	@Query(value = "SELECT * FROM milk_product_entity WHERE product_owner =:ownerName AND company_name =:cmpName AND product_name =:prodName AND product_type =:prodType AND product_live = '1'", nativeQuery = true)
	MilkProductEntity getMilkProdPrice(String ownerName,String cmpName,String prodName,String prodType);

}
