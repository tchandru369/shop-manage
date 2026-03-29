package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.ShopCustBalanceDetails;

public interface ShopCustBlnRepo extends JpaRepository<ShopCustBalanceDetails, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_pymt_ref_id =:pymtRefId, cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BS' WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_order_ref_id =:orderRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_date =:procDate AND cust_bal_order_status ='BP'", nativeQuery = true)
	void updateCustBln(double amountPaid,double blanceAmt,String custRefId,String ownerRefId,String procDate,String orderRefId,String pymtRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_rem_sent_flg = 0", nativeQuery = true)
	void updateCustRemSentFlg();

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BS' WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_date =:procDate AND cust_bal_order_status ='BSV'", nativeQuery = true)
	void updateCustpymtVerifiyBalance(double amountPaid,double blanceAmt,String custRefId,String ownerRefId,String procDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:balAmt,cust_bal_amt =0 WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_pymt_ref_id =:pymtRefId AND cust_bal_order_status ='BS' AND cust_bal_order_ref_id =:orderRefId ", nativeQuery = true)
	void updateCustBalPymtPaidStatus(String custRefId,String ownerRefId,String orderRefId,String pymtRefId,double balAmt);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BP' WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_date =:procDate AND cust_bal_order_ref_id =:orderRefId AND cust_bal_order_status ='BSV'", nativeQuery = true)
	void updateOwnerCustNtPaidConfm(double amountPaid,double blanceAmt,String custRefId,String ownerRefId,String procDate,String orderRefId);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM customer_balance_details_tb WHERE cust_bal_date =:balDate AND cust_bal_order_ref_id =:orderRefId AND cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId", nativeQuery = true)
	int deleteOrderReqTable(String balDate,String orderRefId,String custRefId,String ownerRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BSV',cust_bal_pymt_ref_id =:pymtRefId WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_date =:procDate AND cust_bal_order_ref_id =:orderRefId AND cust_bal_order_status ='BP'", nativeQuery = true)
	void updateConfirmPymtCustBln(double amountPaid,double blanceAmt,String custRefId,String ownerRefId,String procDate,String orderRefId,String pymtRefId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_bal_paid_amt =:amountPaid,cust_bal_amt =:blanceAmt,cust_bal_order_status = 'BS' WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_date =:procDate AND cust_bal_order_status ='BSV'", nativeQuery = true)
	void updateOwnerCustPymtBln(double amountPaid,double blanceAmt,String custRefId,String ownerRefId,String procDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE customer_balance_details_tb SET cust_rem_sent_flg =:newValue WHERE \r\n"
			+ "cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_order_ref_id =:orderRefId AND cust_bal_pymt_ref_id =:pymtRefId", nativeQuery = true)
	void updateRemBalanceFlg(String pymtRefId,String custRefId,String ownerRefId,String orderRefId,int newValue);

	
	@Query(value = "SELECT * FROM customer_balance_details_tb WHERE cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_amt > 0", nativeQuery = true)
	List<ShopCustBalanceDetails> getCustBalListByCustOwner(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT * FROM customer_balance_details_tb WHERE cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId AND cust_bal_order_ref_id =:orderRefId AND cust_bal_pymt_ref_id =:pymtRefId", nativeQuery = true)
	ShopCustBalanceDetails getCustBalByCust(String ownerRefId,String custRefId,String orderRefId,String pymtRefId);
	
	@Query(value = "SELECT * FROM customer_balance_details_tb WHERE cust_bal_cust_ref_id =:custRefId AND cust_bal_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<ShopCustBalanceDetails> getCustBalList(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT cust_bal_paid_amt FROM customer_balance_details_tb WHERE cust_bal_owner_ref_id =:ownerRefId\r\n"
			+ "	 AND cust_bal_cust_ref_id =:custRefId AND cust_bal_date =:custBalDate AND \r\n"
			+ "	cust_bal_order_status = 'BSV'", nativeQuery = true)
	double custPaidAmt(String ownerRefId,String custRefId,String custBalDate);

	
}
