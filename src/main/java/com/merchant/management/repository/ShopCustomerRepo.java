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
	
	@Query(value = "SELECT * FROM shop_customer_details_tb ea WHERE ea.cust_phone_no =:customerPhNo AND (ea.cust_type='I' OR ea.cust_type='S')", nativeQuery = true)
	ShopCustomerDetails getShopCustDetailsByPhNo(String customerPhNo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_amt =:recentBlnce, cust_bal_paid_amt =:paidAmount WHERE cust_bal_email_id =:custEmail \r\n"
			+ "	AND cust_bal_owner_name =:ownerName AND cust_bal_phone_no =:custPhoneNo", nativeQuery = true)
	void updateMilkProdQty(double recentBlnce,double paidAmount,String custEmail, String ownerName,String custPhoneNo );
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE shop_customer_details_tb SET cust_balance_flg =:blanceFlg WHERE shop_cust_ref_id =:custRefId AND cust_owner_ref_id =:ownerRefId", nativeQuery = true)
	void updateCustBlnDtlFlag(String blanceFlg,String custRefId,String ownerRefId);
	
	@Query(value = "SELECT ea.cust_name FROM shop_customer_details_tb ea WHERE ea.shop_cust_ref_id =:custEmail", nativeQuery = true)
	String getCustomerDetails(@Param("custEmail") String custEmail);
	
	@Query(value = "SELECT ea.cust_email_id FROM shop_customer_details_tb ea WHERE ea.shop_cust_ref_id =:custRefId", nativeQuery = true)
	String getCustomerEmailDetails(@Param("custRefId") String custEmail);
	
	@Query(value = "SELECT ea.cust_balance_flg FROM shop_customer_details_tb ea WHERE ea.shop_cust_ref_id =:custRefId", nativeQuery = true)
	String getCustBalanceFlag( String custRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE shop_customer_details_tb SET cust_owner_ref_id =:ownerRefId WHERE shop_cust_ref_id =:custRefId", nativeQuery = true)
	void updateDealerForCustomer(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT * FROM shop_customer_details_tb WHERE cust_city =:cityPlace AND cust_type = 'D'", nativeQuery = true)
	List<ShopCustomerDetails> getDealerDetailsForCust(String cityPlace);
	
	@Query(value = "SELECT COUNT(*) FROM shop_customer_details_tb WHERE cust_email_id =:custEmail", nativeQuery = true)
	int getCustMailCount(String custEmail);
	
	@Query(value = "SELECT shop_cust_ref_id FROM shop_customer_details_tb WHERE cust_phone_no =:custPhone", nativeQuery = true)
	String getCustPhoneCount(String custPhone);
	
	@Query(value = "SELECT * FROM shop_customer_details_tb ea WHERE ea.shop_cust_ref_id =:custEmail", nativeQuery = true)
	ShopCustomerDetails getShopCustDtlsEmailPh(String custEmail);
	
	@Query(value = "SELECT * FROM shop_customer_details_tb WHERE cust_owner_ref_id =:custEmail", nativeQuery = true)
	List<ShopCustomerDetails> getShopCustDtlsByOwnerE(String custEmail);
	
	

	

}
