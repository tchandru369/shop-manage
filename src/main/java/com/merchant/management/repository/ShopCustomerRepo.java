package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.ShopCustomerDetails;

public interface ShopCustomerRepo extends JpaRepository<ShopCustomerDetails, Integer> {
	
	@Query(value = "SELECT * FROM shop_customer_details_tb ea WHERE ea.cust_phone_no =:customerPhNo", nativeQuery = true)
	ShopCustomerDetails getShopCustDetailsByPhNo(String customerPhNo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_amt =:recentBlnce, cust_bal_paid_amt =:paidAmount WHERE cust_bal_email_id =:custEmail \r\n"
			+ "	AND cust_bal_owner_name =:ownerName AND cust_bal_phone_no =:custPhoneNo", nativeQuery = true)
	void updateMilkProdQty(double recentBlnce,double paidAmount,String custEmail, String ownerName,String custPhoneNo );

	

}
