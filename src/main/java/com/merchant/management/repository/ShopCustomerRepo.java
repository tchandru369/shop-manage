package com.merchant.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ShopCustomerDetails;

public interface ShopCustomerRepo extends JpaRepository<ShopCustomerDetails, Integer> {
	
	@Query(value = "SELECT * FROM shop_customer_details_tb ea WHERE ea.cust_phone_no =:customerPhNo", nativeQuery = true)
	ShopCustomerDetails getShopCustDetailsByPhNo(String customerPhNo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_amt =:recentBlnce, cust_bal_paid_amt =:paidAmount WHERE cust_bal_email_id =:custEmail \r\n"
			+ "	AND cust_bal_owner_name =:ownerName AND cust_bal_phone_no =:custPhoneNo", nativeQuery = true)
	void updateMilkProdQty(double recentBlnce,double paidAmount,String custEmail, String ownerName,String custPhoneNo );
	
	@Query(value = "SELECT ea.cust_name FROM shop_customer_details_tb ea WHERE ea.cust_email_id =:custEmail", nativeQuery = true)
	String getCustomerDetails(@Param("custEmail") String custEmail);
	
	@Query(value = "SELECT COUNT(*) FROM shop_customer_details_tb WHERE cust_email_id =:custEmail", nativeQuery = true)
	int getCustMailCount(String custEmail);
	
	@Query(value = "SELECT cust_email_id FROM shop_customer_details_tb WHERE cust_phone_no =:custPhone", nativeQuery = true)
	String getCustPhoneCount(String custPhone);
	
	@Query(value = "SELECT * FROM shop_customer_details_tb ea WHERE ea.cust_email_id =:custEmail", nativeQuery = true)
	ShopCustomerDetails getShopCustDtlsEmailPh(String custEmail);
	
	@Query(value = "SELECT * FROM shop_customer_details_tb WHERE cust_owner_details =:custEmail", nativeQuery = true)
	List<ShopCustomerDetails> getShopCustDtlsByOwnerE(String custEmail);
	
	

	

}
