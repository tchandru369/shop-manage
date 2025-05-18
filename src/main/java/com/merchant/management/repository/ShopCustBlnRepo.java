package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.ShopCustBalanceDetails;

public interface ShopCustBlnRepo extends JpaRepository<ShopCustBalanceDetails, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BS' WHERE \r\n"
			+ "cust_bal_email_id =:custEmail AND cust_bal_owner_name =:ownerName AND cust_bal_phone_no =:custPhoneNo AND cust_bal_date =:procDate AND cust_bal_order_status ='BP'", nativeQuery = true)
	void updateCustBln(double amountPaid,double blanceAmt,String custEmail,String ownerName, String custPhoneNo,String procDate);

	
}
