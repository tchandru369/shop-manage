package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.dto.MyRequestNotifyDto;
import com.merchant.management.entity.OrderRequestDetails;

@EnableJpaRepositories
public interface OrderReqRepo extends JpaRepository<OrderRequestDetails, Integer>{

	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='OPA' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	List<OrderRequestDetails> getOrderRequestCreated(String ownerRefId); 
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	List<OrderRequestDetails> getProcessedOrders(String ownerRefId);
	
	@Query(value = "SELECT COUNT(CASE WHEN ea.order_request_status = 'OPA' THEN 1 END) AS recentReqCount,COUNT(CASE WHEN ea.order_request_status = 'BP' THEN 1 END) AS processOdrCount,COUNT(CASE WHEN ea.order_request_status = 'RAP' THEN 1 END) AS balCustPaidCount,COUNT(CASE WHEN ea.order_request_status = 'BSV' THEN 1 END) AS custVerCount,(SELECT COUNT(*)  FROM cust_order_placed_tb   WHERE cust_order_owner_ref_id =:ownerRefId AND cust_order_req_status = 'OP') AS custReqCount FROM  order_request_details ea WHERE  ea.order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<Object[]> getNotifyCount(String ownerRefId);
	
	@Query(value = "SELECT COUNT(DISTINCT ord.order_ref_id) AS numOrders, COALESCE(SUM(prod.order_cust_prod_price), 0) AS totalPrice FROM order_request_details ord JOIN order_details_tb prod ON ord.order_ref_id = prod.order_cust_ref_id WHERE ord.order_owner_ref_id =:ownerRefId AND TO_DATE(ord.order_bill_pay_date, 'DD-MM-YYYY') >= NOW()", nativeQuery = true)
	List<Object[]> getUserDashCltOdrPrcNow(String ownerRefId);
	
	@Query(value = "SELECT COUNT(DISTINCT ord.order_ref_id) AS numOrders, COALESCE(SUM(prod.order_cust_prod_price), 0) AS totalPrice FROM order_request_details ord JOIN order_details_tb prod ON ord.order_ref_id = prod.order_cust_ref_id WHERE ord.order_owner_ref_id =:ownerRefId AND TO_DATE(ord.order_bill_pay_date, 'DD-MM-YYYY') >= NOW() - INTERVAL '7 days'", nativeQuery = true)
	List<Object[]> getUserDashCltOdrPrcLSDays(String ownerRefId);
	
	@Query(value = "SELECT COUNT(DISTINCT ord.order_ref_id) AS numOrders, COALESCE(SUM(prod.order_cust_prod_price), 0) AS totalPrice FROM order_request_details ord JOIN order_details_tb prod ON ord.order_ref_id = prod.order_cust_ref_id WHERE ord.order_owner_ref_id =:ownerRefId AND TO_DATE(ord.order_bill_pay_date, 'DD-MM-YYYY') >= DATE_TRUNC('month', NOW())", nativeQuery = true)
	List<Object[]> getUserDashCltOdrPrcNMonth(String ownerRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BSV' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	List<OrderRequestDetails> getCustPymtConfirmOrders(String ownerRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_owner_ref_id =:ownerRefId AND ea.order_request_status = 'RAP'", nativeQuery = true)
	List<OrderRequestDetails> getBalVerifyPymtList(String ownerRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_cust_ref_id=:custRefId AND ea.order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	List<OrderRequestDetails> getCustProcessedOrders(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='RA' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_cust_ref_id=:custRefId", nativeQuery = true)
	List<OrderRequestDetails> getCustRemainderRequest(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BS' AND ea.order_owner_ref_id =:ownerRefId AND ea.order_cust_ref_id=:custRefId AND ea.order_ref_id=:orderRefId AND ea.order_pymt_ref_id=:pymtRefId", nativeQuery = true)
	OrderRequestDetails getCustInduvidualRequestDetails(String ownerRefId,String custRefId,String orderRefId, String pymtRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status IN ('RA','RAP','RAV') AND ea.order_owner_ref_id =:ownerRefId AND ea.order_cust_ref_id=:custRefId AND ea.order_ref_id =:orderRefId AND ea.order_pymt_ref_id =:pymtRefId", nativeQuery = true)
	List<OrderRequestDetails> getCustRemainderSentFlg(String ownerRefId,String custRefId,String orderRefId,String pymtRefId);
	
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_ref_id =:custRefId AND\r\n"
			+ "	order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_product_cust_type =:custType\r\n"
			+ "	AND order_owner_ref_id =:ownerRefId AND order_request_status = 'OPA' AND order_request_status NOT IN ('RA', 'RAP', 'RAV');", nativeQuery = true)
	int getCustOrderCount(String custRefId, String custPhNo, String orderDate, String custType,String ownerRefId);
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_ref_id =:custRefId AND\r\n"
			+ " order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_product_cust_type =:custType\r\n"
			+ "	AND order_owner_ref_id =:ownerRefId AND order_request_status = 'BP' AND order_request_status NOT IN ('RA', 'RAP', 'RAV');", nativeQuery = true)
	int getProcOrderCount(String custRefId, String orderDate, String custType,String ownerRefId, String orderRefId);
	
	@Query(value = "SELECT order_prod_total_amt FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_placed_date =:placedDate AND order_owner_ref_id=:ownerRefId AND order_ref_id=:orderRefId AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
    double getOrderplcdTotalAmt(String custRefId,String placedDate,String ownerRefId,String orderRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_request_status ='OPA' AND order_ref_id=:orderRefId AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	int deleteOrderReqTable(@Param("custRefId") String custRefId,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerRefId") String ownerRefId,@Param("orderRefId") String orderRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_request_status = 'RA'", nativeQuery = true)
	int deleteRemSentTable();
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_request_status ='BP' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	int deleteProcOrderReqTable(@Param("custRefId") String custRefId,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerRefId") String ownerRefId,@Param("orderRefId") String orderRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_owner_ref_id =:ownerRefId AND order_pymt_ref_id =:pymtRefId  AND order_request_status ='RAP'", nativeQuery = true)
	int deleteRemBalPaidAmt(@Param("custRefId") String custRefId,
            @Param("pymtRefId") String pymtRefId,
            @Param("ownerRefId") String ownerRefId,@Param("orderRefId") String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_request_status ='BP'  WHERE order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_placed_date =:orderedDate AND order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_request_status ='OPA' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updateOrderStatus(String ownerRefId,String custType,String orderedDate,String custRefId,String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_request_status ='RAP'  WHERE order_owner_ref_id =:ownerRefId AND order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_request_status ='RA' AND order_pymt_ref_id =:pymtRefId", nativeQuery = true)
	void updateRemStatusPaidCustRAP(String ownerRefId,String pymtRefId,String custRefId,String orderRefId);
	

	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_request_status ='RA'  WHERE order_owner_ref_id =:ownerRefId AND order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_request_status ='RAP' AND order_pymt_ref_id =:pymtRefId", nativeQuery = true)
	void updateRemStatusPaidCustRA(String ownerRefId,String pymtRefId,String custRefId,String orderRefId);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS',order_pymt_ref_id =:pymtRefId WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BP' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updateordStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId,String pymtRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS' WHERE order_cust_ref_id =:custRefId AND order_ref_id =:orderRefId \r\n"
			+ "AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updatecustPymntVerBill(String blnceFlg,String billDate,String custRefId,String orderDate,String orderRefId,String ownerRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_final_amt_paid =:totalAmt, order_balance_flg ='Y' \r\n"
			+ " WHERE order_cust_ref_id =:custRefId AND order_ref_id =:orderRefId \r\n"
			+ "AND order_owner_ref_id =:ownerRefId AND order_pymt_ref_id =:pymtRefId AND order_request_status ='BS'", nativeQuery = true)
	void updateBalVerifyPymt(double totalAmt,String custRefId,String orderRefId,String ownerRefId,String pymtRefId);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BP' WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updtOwnerCustNotPaidConfirm(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId,String orderRefId);

	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg,order_pymt_ref_id =:pymtRefId, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BSV',order_final_amt_paid=:blnAmt WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_owner_ref_id =:ownerRefId AND order_request_status ='BP' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updateCustConfPymtordStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String ownerRefId,String orderRefId,String pymtRefId,double blnAmt);

	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS' WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV' AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	void updateOwnerConfPymtCustStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId);

	
	
	@Query(value = "SELECT * FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_owner_ref_id =:ownerRefId AND order_request_status NOT IN ('RA', 'RAP', 'RAV')", nativeQuery = true)
	List<OrderRequestDetails> getCustOrderTranDetails(String ownerRefId,String custRefId);
}
