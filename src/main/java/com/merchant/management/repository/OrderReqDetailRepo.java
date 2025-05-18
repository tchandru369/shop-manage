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
	
	@Query(value = "SELECT * FROM order_details_tb ea WHERE ea.order_cust_email_id =:custEmail AND ea.order_cust_owner_name =:ownerEmail AND ea.order_cust_crtd_date =:orderDate AND ea.order_cust_phone_no =:custPhoneNo AND order_cust_status ='RA'", nativeQuery = true)
	List<ShopCustOrderDetails> getOrderList(String custEmail,String ownerEmail,String orderDate,String custPhoneNo);
	
	@Query(value = "SELECT * FROM order_details_tb ea WHERE ea.order_cust_email_id =:custEmail AND ea.order_cust_owner_name =:ownerEmail AND ea.order_cust_crtd_date =:orderDate AND ea.order_cust_phone_no =:custPhoneNo AND order_cust_status ='BP'", nativeQuery = true)
	List<ShopCustOrderDetails> getProcOrderList(String custEmail,String ownerEmail,String orderDate,String custPhoneNo);
	
	
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_details_tb WHERE order_cust_email_id =:custEmail AND order_cust_crtd_date =:orderDate AND order_cust_owner_name =:ownerEmail AND order_cust_phone_no =:custPhoneNo AND order_cust_status ='RA'", nativeQuery = true)
	int deleteOrderDetailReq(@Param("custEmail") String custEmail,
            @Param("ownerEmail") String ownerEmail,
            @Param("orderDate") String orderDate,
            @Param("custPhoneNo") String custPhoneNo);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_details_tb WHERE order_cust_email_id =:custEmail AND order_cust_crtd_date =:orderDate AND order_cust_owner_name =:ownerEmail AND order_cust_phone_no =:custPhoneNo AND order_cust_status ='BP'", nativeQuery = true)
	int deleteOrderProcReq(@Param("custEmail") String custEmail,
            @Param("ownerEmail") String ownerEmail,
            @Param("orderDate") String orderDate,
            @Param("custPhoneNo") String custPhoneNo);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BP'  WHERE order_cust_owner_name =:ownerEmail \r\n"
			+ "AND order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate AND order_cust_phone_no =:custPhNo\r\n"
			+ "AND order_cust_email_id =:custEmail AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='RA'", nativeQuery = true)
	void updateOrderStatusList(String ownerEmail,String orderedDate,String custPhNo, String custEmail,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_details_tb SET order_cust_status ='BS'  WHERE order_cust_owner_name =:ownerEmail \r\n"
			+ "AND order_cust_prod_name =:productName AND order_cust_crtd_date =:orderedDate AND order_cust_phone_no =:custPhNo\r\n"
			+ "AND order_cust_email_id =:custEmail AND order_cust_prod_cmp =:companyName AND order_cust_prod_type =:productType AND order_cust_status ='BP'", nativeQuery = true)
	void updtOrdStsBillList(String ownerEmail,String orderedDate,String custPhNo, String custEmail,String companyName,String productType,String productName);

}
