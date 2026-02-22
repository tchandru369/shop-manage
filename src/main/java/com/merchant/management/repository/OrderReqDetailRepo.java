package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.entity.ShopCustomerDetails;

@Repository
@EnableJpaRepositories
public interface OrderReqDetailRepo extends JpaRepository<ShopCustOrderDetails, Integer> {
	
	@Query(value = "SELECT * FROM order_details_tb ea WHERE ea.order_cust_crtd_date =:orderDate AND order_cust_ref_id =:orderRefId", nativeQuery = true)
	List<ShopCustOrderDetails> getOrderList(String orderDate,String orderRefId);
	
	@Query(value = "SELECT * FROM order_details_tb ea WHERE ea.order_cust_crtd_date =:orderDate AND ea.order_cust_ref_id =:orderRefId", nativeQuery = true)
	List<ShopCustOrderDetails> getProcOrderList(String orderDate,String orderRefId);
	
	@Query(value = "SELECT * FROM order_details_tb ea WHERE ea.order_cust_ref_id =:orderRefId AND ea.order_cust_crtd_date =:orderDate", nativeQuery = true)
	List<ShopCustOrderDetails> getCustConfPymtList(String orderRefId,String orderDate);
	
	@Query(value = "SELECT to_char(to_date(order_cust_crtd_date, 'DD-MM-YYYY'),'MON') AS month,COUNT(*) AS totalOrders,SUM(order_cust_prod_price) AS totalCount,SUM(order_cust_prod_qty) AS totalProduct FROM order_details_tb WHERE order_cust_ref_id IN (SELECT order_ref_id FROM order_request_details WHERE order_cust_ref_id =:custRefId) GROUP BY to_char(to_date(order_cust_crtd_date, 'DD-MM-YYYY'), 'MON') ORDER BY month", nativeQuery = true)
	List<Object[]> getMonthlyPriceProduct(String custRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_details_tb WHERE order_cust_ref_id =:orderRefId AND order_cust_crtd_date =:orderDate", nativeQuery = true)
	int deleteOrderDetailReq(@Param("orderRefId") String orderRefId,
            @Param("orderDate") String orderDate);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_details_tb WHERE order_cust_ref_id =:orderRefId AND order_cust_crtd_date =:orderDate", nativeQuery = true)
	int deleteOrderProcReq(@Param("orderRefId") String orderRefId,
            @Param("orderDate") String orderDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BP'  WHERE order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate AND order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType", nativeQuery = true)
	void updateOrderStatusList(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_sta"
			+ "tus ='BS'  WHERE \r\n"
			+ " order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate \r\n"
			+ "AND order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BP'", nativeQuery = true)
	void updtOrdStsBillList(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BS'  WHERE \r\n"
			+ "order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate \r\n"
			+ "AND order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BSV'", nativeQuery = true)
	void updtCustPymntOrdList(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BP'  WHERE \r\n"
			+ "order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate \r\n"
			+ "AND order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BSV'", nativeQuery = true)
	void updtOwnerCustNotPaidConfm(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BSV'  WHERE \r\n"
			+ "order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate AND\r\n"
			+ " order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BP'", nativeQuery = true)
	void updtCustPymtOrdStsBillList(String orderedDate, String orderRefId,String companyName,String productType,String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BS'  WHERE \r\n"
			+ "order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate\r\n"
			+ "AND order_cust_ref_id =:orderRefId AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BSV'", nativeQuery = true)
	void updtOwnerPymtCustConfBillList(String orderedDate, String orderRefId,String companyName,String productType,String productName);

	@Query(value = "SELECT order_cust_prod_price FROM order_details_tb WHERE order_cust_ref_id =:orderRefId AND order_cust_crtd_date =:orderDate AND order_cust_prod_cmp =:cpmName AND order_cust_prod_name =:prodName AND order_cust_prod_type =:prodType AND order_cust_prod_qty =:prodQty", nativeQuery = true)
    double getOrderRequestAmountList(String orderRefId,String orderDate,String cpmName,
    		String prodName,String prodType,int prodQty);
}
