package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.ProductDetails;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductDetails, Long>{
	
	@Query(value="SELECT nextval('PRODUCT_PR_SEQ')",nativeQuery = true)
    String getNextSequenceValue();
	
	@Query(value = "SELECT ea.product_id,ea.product_primary_id,ea.product_owner,ea.product_name,ea.product_created_on,ea.product_modified_on,ea.product_price,ea.product_cust_price,ea.product_quantity,ea.product_type FROM product_details ea WHERE ea.product_owner =:merchantEmail", nativeQuery = true)
	List<ProductDetails> getProductDetails(String merchantEmail);
	
	@Query(value = "SELECT ea.product_quantity FROM product_details ea WHERE ea.product_owner =:merchantEmail AND ea.product_name =:productName", nativeQuery = true)
	String getProductQuantity(String merchantEmail,String productName);
	
	@Query(value = "SELECT ea.product_primary_id FROM product_details ea WHERE ea.product_owner =:merchantEmail AND ea.product_name =:productName", nativeQuery = true)
	String getProductPrimaryId(String merchantEmail,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product_details SET product_quantity =:productQty WHERE product_owner =:merchantEmail AND product_name =:productName", nativeQuery = true)
	void updateProductQty(String merchantEmail,String productName,String productQty);
	
	@Query(value = "SELECT ea.product_id,ea.product_primary_id,ea.product_owner,ea.product_name,ea.product_created_on,ea.product_modified_on,ea.product_price,ea.product_cust_price,ea.product_quantity,ea.product_type FROM product_details ea WHERE ea.product_owner = 'thanism@gmail.com' AND CAST(ea.product_quantity AS INT) < 5", nativeQuery = true)
	List<ProductDetails> getDemandProductDetails(String merchantEmail);
	
	@Query(value = "SELECT com_name, prod_type, prod_name FROM CMP_MILK_PROD_DTLS", nativeQuery = true)
	List<Object[]> getComProdDtls();
	
	
	
	
	
	
	
  
}
