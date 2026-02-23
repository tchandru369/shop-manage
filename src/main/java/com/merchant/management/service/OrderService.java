package com.merchant.management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.merchant.management.dto.CustOverAllPymtStatusRes;
import com.merchant.management.dto.MilkOrderList;
import com.merchant.management.dto.OrderRequestDto;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.CustOrderPlacedDtls;
import com.merchant.management.entity.CustOverallPymtStatus;
import com.merchant.management.entity.CustTransOrderList;
import com.merchant.management.entity.CustomerTransEntity;
import com.merchant.management.entity.MilkProductEntity;
import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.OwnerPaymtDetails;
import com.merchant.management.entity.ShopCustBalanceDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.BillingHistoryRepo;
import com.merchant.management.repository.CustOrdPlDtlsListRepo;
import com.merchant.management.repository.CustOrderPlDtlRepo;
import com.merchant.management.repository.CustOverallPymtStatusRepo;
import com.merchant.management.repository.CustTransEntityRepo;
import com.merchant.management.repository.CustTransOrderListRepo;
import com.merchant.management.repository.MilkProductRepo;
import com.merchant.management.repository.OrderReqDetailRepo;
import com.merchant.management.repository.OrderReqRepo;
import com.merchant.management.repository.OwnerPaymtDetailRepo;
import com.merchant.management.repository.ShopCustBlnRepo;
import com.merchant.management.repository.ShopCustomerRepo;

@Service
public class OrderService {

	@Autowired
	public OrderReqDetailRepo orderDtlsRepo;
	
	@Autowired
	public OrderReqRepo orderRepo;
	
	@Autowired
	public MilkProductRepo milkProdRepo;
	
	@Autowired
	public ShopCustBlnRepo shopBlnRepo;
	
	@Autowired
	public BillingHistoryRepo billHistRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private CustOrderPlDtlRepo custOrdPlDtlRepo;
	
	@Autowired
	private CustOrdPlDtlsListRepo custOrdPlListRepo;
	
	@Autowired
	private CustOverallPymtStatusRepo custOverallPymtStatusRepo;
	
	@Autowired
	private OwnerPaymtDetailRepo ownerPaymtDtlRepo;
	@Autowired
	private CustTransEntityRepo custTransEntityRepo;
	@Autowired
	private CustTransOrderListRepo custTransOrdrLstRepo;
	
	@Autowired
	private ShopCustomerRepo shopCustDetailRepo;
	
	
	public OrderService(OrderReqDetailRepo orderDtlsRepo,OrderReqRepo orderRepo,MilkProductRepo milkProdRepo,ShopCustBlnRepo shopBlnRepo,JavaMailSender mailSender,BillingHistoryRepo billHistRepo,CustOrderPlDtlRepo custOrdPlDtlRepo,CustOrdPlDtlsListRepo custOrdPlListRepo) {
		this.orderDtlsRepo = orderDtlsRepo;
		this.orderRepo = orderRepo;
		this.milkProdRepo = milkProdRepo;
		this.shopBlnRepo = shopBlnRepo;
		this.mailSender = mailSender;
		this.billHistRepo = billHistRepo;
		this.custOrdPlDtlRepo = custOrdPlDtlRepo;
		this.custOrdPlListRepo = custOrdPlListRepo; 
	}
	
	public BillingEntityRes saveCustOrderDetails(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
//		int orderCount = orderRepo.getCustOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
//		if(orderCount >= 1) {
//			
//			 response.setResponse("success");
//			 response.setErrorCode("0");
//			 response.setErrorMsg("Order already placed, Please visit My Requests");
//			 
//			 return response;
//		}else {
//		
//		} 
		
		List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
		OrderRequestDetails orderReq = new OrderRequestDetails();
		orderReq.setOrderBalanceFlg("YES");
		orderReq.setOrderBillPayFlg("YES");
		orderReq.setOrderOwnerRefId(custOrderDtls.getOrderOwnerRefId());
		orderReq.setOrderCustRefId(custOrderDtls.getOrderCustRefId());
		orderReq.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
		orderReq.setOrderCustPhNo(custOrderDtls.getOrderCustPhoneNo());
		orderReq.setOrderPlacedDate(custOrderDtls.getOrderCustCrtdDate());
		orderReq.setOrderProdTotalAmt(custOrderDtls.getOrderCustTotalPrice());
		orderReq.setOrderProductCustType(custOrderDtls.getOrderCustType());		
		orderReq.setOrderRequestStatus("OPA");
		orderReq.setOrderProductCustName(custOrderDtls.getOrderCustName());
		 String orderRefId = "ODR" +LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
			orderReq.setOrderRefId(orderRefId);
		 CustOrderPlacedDtls orderPlacedDtls = new CustOrderPlacedDtls();
		 orderPlacedDtls.setCustCustRefId(custOrderDtls.getOrderCustRefId());
		orderPlacedDtls.setCustOrderCrtdDate(custOrderDtls.getOrderCustCrtdDate());
		orderPlacedDtls.setCustOrderEmail(custOrderDtls.getOrderCustEmailId());
		orderPlacedDtls.setCustOrderLiveFlg("1");
		orderPlacedDtls.setCustOrderName(custOrderDtls.getOrderCustName());
		orderPlacedDtls.setCustOrderOwnerRefId(custOrderDtls.getOrderOwnerRefId());
		orderPlacedDtls.setCustOrderPhNo(custOrderDtls.getOrderCustPhoneNo());
		orderPlacedDtls.setCustOrderReqStatus("OPA");
		orderPlacedDtls.setCustOrderType(custOrderDtls.getOrderCustType());
		orderPlacedDtls.setCustOrderRefId(orderRefId);
		List<CustOrderDtlList> dtlList = new ArrayList<CustOrderDtlList>();
		
		for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
			
			
			ShopCustOrderDetails shopCust =  new ShopCustOrderDetails();
			CustOrderDtlList custLists = new CustOrderDtlList();
			shopCust.setOrderCustCrtdDate(custOrderDtls.getOrderCustCrtdDate());
			shopCust.setOrderCustProdCmp(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
			shopCust.setOrderCustProdName(custOrderDtls.getOrderList().get(i).getOrderCustProdName());
			shopCust.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdPrice());
			shopCust.setOrderCustProdQty(custOrderDtls.getOrderList().get(i).getOrderCustProdQty());
			shopCust.setOrderCustProdType(custOrderDtls.getOrderList().get(i).getOrderCustProdType());
			shopCust.setOrderCustRefId(orderRefId);
			custLists.setCustOrderCrdDate(custOrderDtls.getOrderCustCrtdDate());
			custLists.setCustOrderLiveFlg("1");
			custLists.setCustOrderProdCmp(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
			custLists.setCustOrderProdType(custOrderDtls.getOrderList().get(i).getOrderCustProdType());
			custLists.setCustOrderProdName(custOrderDtls.getOrderList().get(i).getOrderCustProdName());
			custLists.setCustOrderProdQty(custOrderDtls.getOrderList().get(i).getOrderCustProdQty());
			custLists.setCustOrderRefId(orderRefId);
			dtlList.add(custLists);
			custList.add(shopCust);
		}
		 
		 if(orderRepo.save(orderReq) != null &&  orderDtlsRepo.saveAll(custList) != null && custOrdPlDtlRepo.save(orderPlacedDtls) != null && custOrdPlListRepo.saveAll(dtlList) != null) {
			 response.setResponse("success");
			 response.setErrorCode("0");
			 response.setErrorMsg("success");
		 }else {
			 response.setResponse("Not able to process your Request");
			 response.setErrorCode("ERR002");
			 response.setErrorMsg("persistence Error");
		 }
		
		 return response;
	}
	
	@Transactional(readOnly = false)
	public BillingEntityRes deleteCustOrderDetails(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		 
		 int deleteOrderCount =  orderDtlsRepo.deleteOrderDetailReq(custOrderDtls.getOrderRefId(),custOrderDtls.getOrderCustCrtdDate());
		 int delteteorderListCnt = orderRepo.deleteOrderReqTable(custOrderDtls.getOrderCustRefId(), custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(),custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderRefId());
		 //shopBlnRepo
		 response.setErrorCode("0");
		 response.setResponse("success");
		 response.setErrorMsg("success");
		 
		 return response;
		 
	}
	
	@Transactional(readOnly = false)
	public BillingEntityRes deleteProcessOrderDetails(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		 
		 int deleteOrderCount =  orderDtlsRepo.deleteOrderProcReq( custOrderDtls.getOrderRefId(), custOrderDtls.getOrderCustCrtdDate());
		 int delteteorderListCnt = orderRepo.deleteProcOrderReqTable(custOrderDtls.getOrderCustRefId(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(),custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderRefId());
		 int deleteCustList = shopBlnRepo.deleteOrderReqTable(custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderRefId(),custOrderDtls.getOrderCustRefId(), custOrderDtls.getOrderOwnerRefId());
 
		 response.setErrorCode("0");
		 response.setResponse("success");
		 response.setErrorMsg("success");
		 
		 return response;
		 
	}
	
	public BillingEntityRes customerPlaceOrder(OrderRequestDto custOrderDtl) {
		BillingEntityRes response = new BillingEntityRes();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
		CustOrderPlacedDtls orderPlacedDtls = new CustOrderPlacedDtls();
		orderPlacedDtls.setCustOrderCrtdDate(formattedDate);
		orderPlacedDtls.setCustOrderEmail(custOrderDtl.getOrderCustEmailId());
		orderPlacedDtls.setCustOrderLiveFlg("1");
		orderPlacedDtls.setCustOrderName(custOrderDtl.getOrderCustName());
		orderPlacedDtls.setCustOrderOwnerRefId(custOrderDtl.getOrderOwnerRefId());
		orderPlacedDtls.setCustOrderPhNo(custOrderDtl.getOrderCustPhoneNo());
		orderPlacedDtls.setCustOrderReqStatus("OP");
		orderPlacedDtls.setCustOrderType(custOrderDtl.getOrderCustType());
		orderPlacedDtls.setCustCustRefId(custOrderDtl.getOrderCustRefId());
		 String orderRefId = "ODR" +LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		orderPlacedDtls.setCustOrderRefId(orderRefId);
		 List<CustOrderDtlList> dtlList = new ArrayList<CustOrderDtlList>();
		for(int i=0;i<custOrderDtl.getOrderList().size();i++) {
			CustOrderDtlList custList = new CustOrderDtlList();
			custList.setCustOrderCrdDate(formattedDate);
			custList.setCustOrderLiveFlg("1");
			custList.setCustOrderProdCmp(custOrderDtl.getOrderList().get(i).getOrderCustProdCmp());
			custList.setCustOrderProdType(custOrderDtl.getOrderList().get(i).getOrderCustProdType());
			custList.setCustOrderProdName(custOrderDtl.getOrderList().get(i).getOrderCustProdName());
			custList.setCustOrderProdQty(custOrderDtl.getOrderList().get(i).getOrderCustProdQty());
			custList.setCustOrderRefId(orderRefId);
			dtlList.add(custList);
		}
	    custOrdPlDtlRepo.save(orderPlacedDtls);
	    custOrdPlListRepo.saveAll(dtlList);
	    response.setErrorCode("0");
	    response.setResponse("success");
	    response.setErrorMsg("success");
	    
	    return response;
	}
	
	public BillingEntityRes paidOrderRequest(OrderRequestDto paidOrderReq) {
		BillingEntityRes response = new BillingEntityRes();
		ShopCustBalanceDetails shopCustBlnDtls = new ShopCustBalanceDetails();
		CustOverallPymtStatus custOverallStatus = new CustOverallPymtStatus();
		BillingHistory billingHistory = new BillingHistory();
		String pymtRefId = "INP" +LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		shopBlnRepo.updateCustBln(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId()
				,paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderRefId(),pymtRefId);
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDateTime currentDate = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String formattedTime = currentDate.format(timeFormatter);
	        String formattedDate = currentDate.format(formatter);
//	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
//				 orderDtlsRepo.updtOrdStsBillList(paidOrderReq.getOrderCustCrtdDate(),
//						 paidOrderReq.getOrderRefId(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//				 custOrdPlListRepo.updateCustPlcdDtlLstOwnerPaidBS( paidOrderReq.getOrderCustCrtdDate(),
//						 paidOrderReq.getOrderRefId(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//				 
//			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     custOverallStatus.setPymtAmountBalance(remainAmt);
	     if(remainAmt != 0) {
	    	 custOverallStatus.setFullPaymentFlg("Y");  
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("1", paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 billingHistory.setCustFullyPaidFlg("N");
	    	 orderRepo.updateordStsBill("N", formattedDate, paidOrderReq.getOrderCustRefId(),
	    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId(),pymtRefId);
	     }else {
	    	 custOverallStatus.setFullPaymentFlg("N");
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("0", paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 billingHistory.setCustFullyPaidFlg("Y");
	    	 orderRepo.updateordStsBill("Y", formattedDate, paidOrderReq.getOrderCustRefId(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId(),pymtRefId);
	     }
	     custOrdPlDtlRepo.updateCustPlcdOrderOwnerToBs(paidOrderReq.getOrderOwnerRefId(),
    			 paidOrderReq.getOrderCustType(), paidOrderReq.getOrderCustCrtdDate(),
    			 paidOrderReq.getOrderRefId(), paidOrderReq.getOrderCustRefId(),pymtRefId);
	     custOverallStatus.setBillDate(formattedDate);
	     custOverallStatus.setCustRefId(paidOrderReq.getOrderCustRefId());
	     custOverallStatus.setCustName(paidOrderReq.getOrderCustName());
	     custOverallStatus.setOverAllOrderStatus("BS");
	     custOverallStatus.setOwnerRefId(paidOrderReq.getOrderOwnerRefId());
	     custOverallStatus.setPymtAmount(paidOrderReq.getOrderCustTotalPrice());
	     custOverallStatus.setCustPaidAmount(paidOrderReq.getOrderFinalAmtPaid());
	     custOverallStatus.setPymtDate(formattedDate);
	     custOverallStatus.setCustRefId(paidOrderReq.getOrderCustRefId());
	     custOverallStatus.setTransPymtRefId(pymtRefId);
	     custOverallStatus.setCustName(paidOrderReq.getOrderCustName());
	     custOverallStatus.setPymtDateTime(formattedDate+" "+formattedTime);
	     custOverallStatus.setCustOrderRefId(paidOrderReq.getOrderRefId());
	     billingHistory.setCustInvoiceDate(formattedDate);
	     billingHistory.setCustPaidAmt(paidOrderReq.getOrderFinalAmtPaid());
	     billingHistory.setCustPhnNo(paidOrderReq.getOrderCustPhoneNo());
	     billingHistory.setCustOwnerEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderOwnerRefId()));
	     billingHistory.setCustTotalAmt(paidOrderReq.getOrderCustTotalPrice());
	     billingHistory.setCutEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderCustRefId()));
	     billingHistory.setCustInvoiceId("INV"+String.valueOf(uniqueNumber));
	     billingHistory.setShopCustRefId(paidOrderReq.getOrderCustRefId());
	     billingHistory.setShopOwnerRefId(paidOrderReq.getOrderOwnerRefId());
	     billingHistory.setShopOrderRefId(paidOrderReq.getOrderRefId());
	     billingHistory.setCustPymtRefId(pymtRefId);
	     billHistRepo.save(billingHistory);
	    
	     custOverallPymtStatusRepo.save(custOverallStatus);
	     pdfService.generatePaidBillPdf(paidOrderReq, billingHistory);
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes custAmntPaidVerifiedAmnt(OrderRequestDto paidOrderReq) {
		BillingEntityRes response = new BillingEntityRes();
		ShopCustBalanceDetails shopCustBlnDtls = new ShopCustBalanceDetails();
		BillingHistory billingHistory = new BillingHistory();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		shopBlnRepo.updateCustpymtVerifiyBalance(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId()
				, paidOrderReq.getOrderCustCrtdDate());
		//custTransEntityRepo.updtCustPymntOrdList(paidOrderReq.getOrderCustOwnerName(),paidOrderReq.get, null);
		
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
//	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
//				 orderDtlsRepo.updtCustPymntOrdList(paidOrderReq.getOrderCustCrtdDate(),
//						paidOrderReq.getOrderRefId(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//				 custOrdPlListRepo.updateCustPlcdDtlListBS(paidOrderReq.getOrderRefId(),paidOrderReq.getOrderCustCrtdDate(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     if(remainAmt != 0) {
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("1", paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 orderRepo.updatecustPymntVerBill("N", formattedDate, paidOrderReq.getOrderCustRefId(),
	    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 custOrdPlDtlRepo.updateCustPlcdOrderToBS(paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId(),
	    			 paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderCustPhoneNo(),paidOrderReq.getOrderCustRefId());
	     }else {
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("0", paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 orderRepo.updatecustPymntVerBill("Y", formattedDate, paidOrderReq.getOrderCustRefId(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderRefId(), paidOrderReq.getOrderOwnerRefId());
	    	 custOrdPlDtlRepo.updateCustPlcdOrderToBS(paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId(),
	    			 paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderCustPhoneNo(),paidOrderReq.getOrderCustRefId());
	     }
	     
	     custOverallPymtStatusRepo.updateOwnerOverAllUpdate(paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId());
	     pdfService.generatePaidBillPdf(paidOrderReq, billingHistory);
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes ownerCustNotPaidConf(OrderRequestDto paidOrderReq) {
		BillingEntityRes response = new BillingEntityRes();
		ShopCustBalanceDetails shopCustBlnDtls = new ShopCustBalanceDetails();
		BillingHistory billingHistory = new BillingHistory();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		shopBlnRepo.updateOwnerCustNtPaidConfm(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId()
				, paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderRefId());
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
//	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
//				 orderDtlsRepo.updtOwnerCustNotPaidConfm(paidOrderReq.getOrderCustCrtdDate(), paidOrderReq.getOrderRefId(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     if(remainAmt != 0) {
	    	 billingHistory.setCustFullyPaidFlg("N");
	    	 orderRepo.updtOwnerCustNotPaidConfirm("N", formattedDate, paidOrderReq.getOrderCustRefId(),
	    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId(), paidOrderReq.getOrderRefId());
	     }else {
	    	 billingHistory.setCustFullyPaidFlg("Y");
	    	 orderRepo.updtOwnerCustNotPaidConfirm("Y", formattedDate, paidOrderReq.getOrderCustRefId(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId());
	     }
	     billingHistory.setCustInvoiceDate(formattedDate);
	     billingHistory.setCustPaidAmt(paidOrderReq.getOrderFinalAmtPaid());
	     billingHistory.setCustPhnNo(paidOrderReq.getOrderCustPhoneNo());
	     billingHistory.setCustOwnerEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderOwnerRefId()));
	     billingHistory.setCustTotalAmt(paidOrderReq.getOrderCustTotalPrice());
	     billingHistory.setCutEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderCustRefId()));
	     billingHistory.setCustInvoiceId("INV"+String.valueOf(uniqueNumber));
	     billHistRepo.save(billingHistory);
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes custConfirmRequest(OrderRequestDto paidOrderReq) {
		BillingEntityRes response = new BillingEntityRes();
		CustomerTransEntity custTransEntity = new CustomerTransEntity();
		ShopCustBalanceDetails shopCustBlnDtls = new ShopCustBalanceDetails();
		BillingHistory billingHistory = new BillingHistory();
		CustOverallPymtStatus custOverallStatus = new CustOverallPymtStatus();
		CustTransOrderList custTransOrder = new CustTransOrderList();
		List<CustTransOrderList> custTransOrderList = new ArrayList<CustTransOrderList>();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDateTime currentDate = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String formattedDate = currentDate.format(formatter);
	        String formattedTime = currentDate.format(timeFormatter);
	        String custPayId = ownerPaymtDtlRepo.getOverAllPymtDetails(paidOrderReq.getOrderCustEmailId());
	        OwnerPaymtDetails ownerPayId = ownerPaymtDtlRepo.getDealerPymtDetails(paidOrderReq.getOrderOwnerRefId());
	        String pymtRefId = "PAY" +LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
	        shopBlnRepo.updateConfirmPymtCustBln(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId()
					, paidOrderReq.getOrderCustCrtdDate(), paidOrderReq.getOrderRefId(),pymtRefId);
	        custTransEntity.setPayeeNote(paidOrderReq.getNoteToPayer());
	        custTransEntity.setPayeeUpiId(custPayId);
	        custTransEntity.setPayerUpiId(ownerPayId.getPymtUpiId());
	        custTransEntity.setTransActAmt(paidOrderReq.getOrderCustTotalPrice());
	        custTransEntity.setTransBlnAmt(remainAmt);
	        custTransEntity.setTransCustRefId(paidOrderReq.getOrderCustRefId());
	        custTransEntity.setTransOwnerRefId(paidOrderReq.getOrderOwnerRefId());
	        custTransEntity.setTransPymtAmt(paidOrderReq.getOrderFinalAmtPaid());
	        custTransEntity.setTransPymtDate(formattedDate);
	        custTransEntity.setTransPymtTime(formattedDate+" "+formattedTime);
	        custTransEntity.setTransPymtRefId(pymtRefId);
	        custTransEntity.setPayeePhoneNo(paidOrderReq.getOrderCustPhoneNo());
	        custTransEntity.setPayerPhoneNo(ownerPayId.getPymtPhNumber());
	        custTransEntity.setTransCustOrderId(paidOrderReq.getOrderRefId());
	        //custTransEntity.setPayerPhoneNo(paidOrderReq.get);
	        custTransEntity.setTransPymtOrderStatus("BSV");
	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
	        	custTransOrder.setTransPymtRefId(pymtRefId);
	        	custTransOrder.setTransPymtDate(formattedDate);
	        	custTransOrder.setTransOrderProdCmp(paidOrderReq.getOrderList().get(i).getOrderCustProdCmp());
	        	custTransOrder.setTransOrderProdName(paidOrderReq.getOrderList().get(i).getOrderCustProdName());
	        	custTransOrder.setTransOrderProdType(paidOrderReq.getOrderList().get(i).getOrderCustProdType());
	        	custTransOrder.setTransOrderProdQty(paidOrderReq.getOrderList().get(i).getOrderCustProdQty());
	        	custTransOrder.setTransCustOrderRefId(paidOrderReq.getOrderRefId());
	        	custTransOrderList.add(custTransOrder);
//				 orderDtlsRepo.updtCustPymtOrdStsBillList( paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderRefId(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
//				 custOrdPlListRepo.updateCustPlcdDtlListBSV(paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderRefId(),
//						  paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(),
//						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),paidOrderReq.getOrderList().get(i).getOrderCustProdName());
				 
			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     custOverallStatus.setPymtAmountBalance(remainAmt);
	     
	     if(remainAmt != 0) {
	    	 billingHistory.setCustFullyPaidFlg("N");
	    	 custOverallStatus.setFullPaymentFlg("N");
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("0",paidOrderReq.getOrderCustRefId(),paidOrderReq.getOrderOwnerRefId());
	    	 orderRepo.updateCustConfPymtordStsBill("N", formattedDate, paidOrderReq.getOrderCustRefId(),
	    			paidOrderReq.getOrderCustCrtdDate(), paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId(),pymtRefId);
	    	 custOrdPlDtlRepo.updateCustPlcdOrderToBSV(paidOrderReq.getOrderOwnerRefId(),
	    			 paidOrderReq.getOrderCustCrtdDate(), paidOrderReq.getOrderCustPhoneNo(), paidOrderReq.getOrderCustRefId(),paidOrderReq.getOrderRefId(),pymtRefId);
	    	 
	     }else {
	    	 billingHistory.setCustFullyPaidFlg("Y");
	    	 custOverallStatus.setFullPaymentFlg("Y");
	    	 shopCustDetailRepo.updateCustBlnDtlFlag("1",paidOrderReq.getOrderCustRefId(),paidOrderReq.getOrderOwnerRefId());
	    	 orderRepo.updateCustConfPymtordStsBill("Y", formattedDate, paidOrderReq.getOrderCustRefId(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderOwnerRefId(),paidOrderReq.getOrderRefId(),pymtRefId);
	    	 custOrdPlDtlRepo.updateCustPlcdOrderToBSV(paidOrderReq.getOrderOwnerRefId(),
	    			 paidOrderReq.getOrderCustCrtdDate(), paidOrderReq.getOrderCustPhoneNo(), paidOrderReq.getOrderCustRefId(),paidOrderReq.getOrderRefId(),pymtRefId);
	     }
	     //Cust Overall Status Update
	     custOverallStatus.setBillDate(formattedDate);
	     custOverallStatus.setCustRefId(paidOrderReq.getOrderCustRefId());
	     custOverallStatus.setCustName(paidOrderReq.getOrderCustName());
	     custOverallStatus.setOverAllOrderStatus("BSV");
	     custOverallStatus.setOwnerRefId(paidOrderReq.getOrderOwnerRefId());
	     custOverallStatus.setPymtAmount(paidOrderReq.getOrderCustTotalPrice());
	     custOverallStatus.setCustPaidAmount(paidOrderReq.getOrderFinalAmtPaid());
	     custOverallStatus.setPymtDate(formattedDate);
	     custOverallStatus.setCustRefId(paidOrderReq.getOrderCustRefId());
	     custOverallStatus.setTransPymtRefId(pymtRefId);
	     custOverallStatus.setCustName(paidOrderReq.getOrderCustName());
	     custOverallStatus.setPymtDateTime(formattedDate+" "+formattedTime);
	     custOverallStatus.setCustOrderRefId(paidOrderReq.getOrderRefId());
	     billingHistory.setShopCustRefId(paidOrderReq.getOrderCustRefId());
	     billingHistory.setShopOwnerRefId(paidOrderReq.getOrderOwnerRefId());
	     billingHistory.setShopOrderRefId(paidOrderReq.getOrderRefId());
	     billingHistory.setCustInvoiceDate(formattedDate);
	     billingHistory.setCustPaidAmt(paidOrderReq.getOrderFinalAmtPaid());
	     billingHistory.setCustPhnNo(paidOrderReq.getOrderCustPhoneNo());
	     billingHistory.setCustOwnerEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderOwnerRefId()));
	     billingHistory.setCustTotalAmt(paidOrderReq.getOrderCustTotalPrice());
	     billingHistory.setCutEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderCustRefId()));
	     billingHistory.setCustInvoiceId("INV"+String.valueOf(uniqueNumber));
	     billingHistory.setCustPymtRefId(pymtRefId);
	     custTransEntityRepo.save(custTransEntity);
	     custTransOrdrLstRepo.saveAll(custTransOrderList);
	     billHistRepo.save(billingHistory);
	     custOverallPymtStatusRepo.save(custOverallStatus);
	     
	     emailService.sendConfmCustPymtEmail(billingHistory.getCustEmailId(), paidOrderReq.getOrderCustName(), paidOrderReq.getOrderOwnerRefId());
	    
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes ownerVerifyCustPymntRequest(OrderRequestDto paidOrderReq) {
		BillingEntityRes response = new BillingEntityRes();
		ShopCustBalanceDetails shopCustBlnDtls = new ShopCustBalanceDetails();
		BillingHistory billingHistory = new BillingHistory();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		shopBlnRepo.updateOwnerCustPymtBln(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustRefId(), paidOrderReq.getOrderOwnerRefId()
				, paidOrderReq.getOrderCustCrtdDate());
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
				 orderDtlsRepo.updtOwnerPymtCustConfBillList(paidOrderReq.getOrderCustCrtdDate(),paidOrderReq.getOrderOwnerRefId(),  
						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     if(remainAmt != 0) {
	    	 billingHistory.setCustFullyPaidFlg("N");
	    	 orderRepo.updateOwnerConfPymtCustStsBill("N", formattedDate, paidOrderReq.getOrderCustRefId(),
	    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId());
	     }else {
	    	 billingHistory.setCustFullyPaidFlg("Y");
	    	 orderRepo.updateOwnerConfPymtCustStsBill("Y", formattedDate, paidOrderReq.getOrderCustRefId(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderOwnerRefId());
	     }
	     billingHistory.setCustInvoiceDate(formattedDate);
	     billingHistory.setCustPaidAmt(paidOrderReq.getOrderFinalAmtPaid());
	     billingHistory.setCustPhnNo(paidOrderReq.getOrderCustPhoneNo());
	     billingHistory.setCustOwnerEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderOwnerRefId()));
	     billingHistory.setCustTotalAmt(paidOrderReq.getOrderCustTotalPrice());
	     billingHistory.setCutEmailId(shopCustDetailRepo.getCustomerEmailDetails(paidOrderReq.getOrderCustRefId()));
	     billingHistory.setCustInvoiceId("INV"+String.valueOf(uniqueNumber));
	     billHistRepo.save(billingHistory);
	    
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes orderProcessRequest(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		
		
		String productName = "";
		int actualQty = 0;
		for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
			actualQty = milkProdRepo.getCurrentMilkProdQty(custOrderDtls.getOrderOwnerRefId(), custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
					custOrderDtls.getOrderList().get(i).getOrderCustProdName(),custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
			if(actualQty<custOrderDtls.getOrderList().get(i).getOrderCustProdQty()) {
				productName += custOrderDtls.getOrderList().get(i).getOrderCustProdCmp()+" - "+custOrderDtls.getOrderList().get(i).getOrderCustProdName() + " - " +custOrderDtls.getOrderList().get(i).getOrderCustProdType()+" : "+actualQty+" | ";
			}	
		}
//		int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
//        System.out.println("The count is : "+orderCount);
		 if(!productName.equals("")){
        	response.setResponse("success");
			 response.setErrorCode("1");
			 response.setErrorMsg("Actual Quantity : "+productName);
			 
			 return response;
        	
        }else {
        	ShopCustBalanceDetails blnDtls = new ShopCustBalanceDetails();
    		
    		LocalDate currentDate = LocalDate.now();
    	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	     String formattedDate = currentDate.format(formatter);
    		
    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
    			 int actualPrdQty = milkProdRepo.getCurrentMilkProdQty(custOrderDtls.getOrderOwnerRefId(), custOrderDtls.getOrderList().get(i).getOrderCustProdType(), custOrderDtls.getOrderList().get(i).getOrderCustProdName(),custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
    			 int recentProdQty = actualPrdQty - custOrderDtls.getOrderList().get(i).getOrderCustProdQty();
    			 System.out.println("the recent product qty :"+recentProdQty);
    			 milkProdRepo.updateMilkProcQty(custOrderDtls.getOrderOwnerRefId(), custOrderDtls.getOrderList().get(i).getOrderCustProdName(), custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), custOrderDtls.getOrderList().get(i).getOrderCustProdType(), recentProdQty);
    		 }
    		 orderRepo.updateOrderStatus(custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustRefId(),custOrderDtls.getOrderRefId());
    		 custOrdPlDtlRepo.updateCustPlcdOrderToBp(custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustRefId(),custOrderDtls.getOrderRefId());
    		 blnDtls.setCustBalActAmt(custOrderDtls.getOrderCustTotalPrice());
    		 blnDtls.setCustBalAmt(custOrderDtls.getOrderCustTotalPrice());
    		 blnDtls.setCustBalDate(formattedDate);
    		 blnDtls.setCustBalCustRefId(custOrderDtls.getOrderCustRefId());
    		 blnDtls.setCustBalName(custOrderDtls.getOrderCustName());
    		 blnDtls.setCustBalOwnerRefId(custOrderDtls.getOrderOwnerRefId());
    		 blnDtls.setCustBalPhoneNo(custOrderDtls.getOrderCustPhoneNo());
    		 blnDtls.setCustBalPaidAmt(0);
    		 blnDtls.setCustBalStatus("BP");
    		 blnDtls.setCustBalOrderRefId(custOrderDtls.getOrderRefId());
    		 shopBlnRepo.save(blnDtls);
    		 
    		 response.setErrorCode("0");
    		 response.setResponse("success");
    		 response.setErrorMsg("success");
    		 
    		 return response;
        }
        }
	
	public BillingEntityRes rejectCustPlacedOrder(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		OrderRequestDetails orderPrcDetails = new OrderRequestDetails();
		int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustRefId(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderRefId());
		int PlcdorderCount = custOrdPlDtlRepo.getCustPlcdOrderCount(custOrderDtls.getOrderCustRefId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderOwnerRefId());
        System.out.println("The count is : "+orderCount);
		if(PlcdorderCount >= 1 && orderCount >=1) {
        	 response.setResponse("success");
			 response.setErrorCode("1");
			 response.setErrorMsg("Order has been already approved, Please verify processing list..");
			 
			 return response;
        }else {
             
    		List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
    		double finalAmt = 0;
//    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
//    			 System.out.println("Inside List started...");
//    			 
//    			 custOrdPlListRepo.updateCustPlcdDtlListR( custOrderDtls.getOrderCustCrtdDate(),custOrderDtls.getOrderRefId(),
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), 
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName());
//    		 }
    		 
    		 custOrdPlDtlRepo.updateCustPlcdOrderStatusR(custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustRefId(),custOrderDtls.getOrderRefId());
    		 
    		 
    		 response.setErrorCode("0");
    		 response.setResponse("success");
    		 response.setErrorMsg("success");
    		 
    		 return response;
        }
	
	 
}
		
		public BillingEntityRes approveCustPlacedOrder(OrderRequestDto custOrderDtls) {
			BillingEntityRes response = new BillingEntityRes();
			OrderRequestDetails orderPrcDetails = new OrderRequestDetails();
			//int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
			int PlcdorderCount = custOrdPlDtlRepo.getCustPlcdOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderRefId());
			List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
    		double finalAmt = 0;
    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
    			 System.out.println("Inside List started...");
    			 MilkProductEntity milkObj = milkProdRepo.getMilkProdPrice(custOrderDtls.getOrderOwnerRefId(), custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(),
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName(), custOrderDtls.getOrderList().get(i).getOrderCustProdType()); 
    			 System.out.println("milkObj List Ended.....");
    			 if(milkObj == null) {
    				response.setErrorCode("1");
    				response.setResponse("success"); 
    				response.setErrorMsg(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp()+" "+
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName()+" "+custOrderDtls.getOrderList().get(i).getOrderCustProdType()+" "+"Not present in DB");
    				//custOrdPlListRepo.updateCustPlcdDtlListBck(custOrderDtls.getOrderOwnerRefId(), custOrderDtls.getOrderCustCrtdDate());
    				return response;
    			 }
    			 ShopCustOrderDetails shopCustList = new ShopCustOrderDetails();
    			 shopCustList.setOrderCustCrtdDate(custOrderDtls.getOrderCustCrtdDate());
				 shopCustList.setOrderCustRefId(custOrderDtls.getOrderRefId());
				 shopCustList.setOrderCustProdCmp(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
				 shopCustList.setOrderCustProdName(custOrderDtls.getOrderList().get(i).getOrderCustProdName());
				 shopCustList.setOrderCustProdQty(custOrderDtls.getOrderList().get(i).getOrderCustProdQty());
				 shopCustList.setOrderCustProdType(custOrderDtls.getOrderList().get(i).getOrderCustProdType());
    			 if(custOrderDtls.getOrderCustType().equalsIgnoreCase("S")) {
    				 shopCustList.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdQty()*milkObj.getProductShopPrice());
    				 finalAmt = finalAmt+shopCustList.getOrderCustProdPrice();
    				 
    			 }else if(custOrderDtls.getOrderCustType().equalsIgnoreCase("I")) {
    				 shopCustList.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdQty()*milkObj.getProductCustPrice());
    				 finalAmt = finalAmt+shopCustList.getOrderCustProdPrice();
    			 }
//    			 custOrdPlListRepo.updateCustPlcdDtlList(custOrderDtls.getOrderCustCrtdDate(),custOrderDtls.getOrderRefId(), 
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), 
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
//    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName());
    			 custList.add(shopCustList);
    		 }
    		 
    		 orderPrcDetails.setOrderBalanceFlg("YES");
    		 orderPrcDetails.setOrderBillPayFlg("YES");
    		 orderPrcDetails.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
    		 orderPrcDetails.setOrderCustPhNo(custOrderDtls.getOrderCustPhoneNo());
    		 orderPrcDetails.setOrderPlacedDate(custOrderDtls.getOrderCustCrtdDate());
    		 orderPrcDetails.setOrderCustRefId(custOrderDtls.getOrderCustRefId());
    		 orderPrcDetails.setOrderRefId(custOrderDtls.getOrderRefId());
    		 orderPrcDetails.setOrderProdTotalAmt(finalAmt);
    		 orderPrcDetails.setOrderProductCustName(custOrderDtls.getOrderCustName());
    		 orderPrcDetails.setOrderProductCustType(custOrderDtls.getOrderCustType());
    		 orderPrcDetails.setOrderOwnerRefId(custOrderDtls.getOrderOwnerRefId());
    		 orderPrcDetails.setOrderRequestStatus("OPA");
    		 custOrdPlDtlRepo.updateCustPlcdOrderStatus(custOrderDtls.getOrderOwnerRefId(),custOrderDtls.getOrderRefId(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustRefId());
    		 
    		 orderDtlsRepo.saveAll(custList);
    		 orderRepo.save(orderPrcDetails);
    		 
    		 
    		 response.setErrorCode("0");
    		 response.setResponse("success");
    		 response.setErrorMsg("success");
    		 
    		 return response;
		
		 
	}
	
	public List<OrderRequestDto> getOrderDetails(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<OrderRequestDetails> orderDetailList = orderRepo.getOrderRequestCreated(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getOrderOwnerRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			orderReqDto.setOrderCustRefId(orderDetailList.get(i).getOrderCustRefId());
			orderReqDto.setOrderRefId(orderDetailList.get(i).getOrderRefId());
			orderReqDto.setOrderPymtRefId(orderDetailList.get(i).getorderPymtRefId());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getOrderList(orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderRefId());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getOrderCustProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getOrderCustProdName());
				milkList.setOrderCustProdPrice(custOrderList.get(j).getOrderCustProdPrice());
				milkList.setOrderCustProdQty(custOrderList.get(j).getOrderCustProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getOrderCustProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
		}
		return finalOrderList;
	}
	
	public List<OrderRequestDto> getCustPlacedOrderForOwner(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<CustOrderPlacedDtls> orderDetailList = custOrdPlDtlRepo.getCustOrderPLacedDtls(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getCustOrderCrtdDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getCustOrderEmail());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getCustOrderName());
			orderReqDto.setOrderCustRefId(orderDetailList.get(i).getCustCustRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
			orderReqDto.setOrderCustTotalPrice(0);
			orderReqDto.setOrderRefId(orderDetailList.get(i).getCustOrderRefId());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getCustOrderOwnerRefId());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
			List<CustOrderDtlList> custOrderList = custOrdPlListRepo.getCustOrderPlacedList( orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderRefId());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getCustOrderProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getCustOrderProdName());
				milkList.setOrderCustProdPrice(0);
				milkList.setOrderCustProdQty(custOrderList.get(j).getCustOrderProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getCustOrderProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
		}
		return finalOrderList;
	}
	

	
	public List<OrderRequestDto> getCustOrderRequestApproved(String email,String ownerEmail,String date){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		if(date.equals("recent")) {
			List<CustOrderPlacedDtls> orderDetailList = custOrdPlDtlRepo.getCustOrderReqApprovedRcntDate(ownerEmail,email);
			for(int i=0;i<orderDetailList.size();i++) {
				OrderRequestDto orderReqDto = new OrderRequestDto();
				orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getCustOrderCrtdDate());
				orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getCustOrderEmail());
				orderReqDto.setOrderCustName(orderDetailList.get(i).getCustOrderName());
				orderReqDto.setOrderCustRefId(orderDetailList.get(i).getCustOrderOwnerRefId());
				orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
				orderReqDto.setOrderCustTotalPrice(0);
				orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
				orderReqDto.setOrderReqStatus(orderDetailList.get(i).getCustOrderReqStatus());
				List<CustOrderDtlList> custOrderList = custOrdPlListRepo.getCustOrderPlacedListAprvd(orderDetailList.get(i).getCustOrderRefId(),orderDetailList.get(i).getCustOrderCrtdDate());
				List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
				for(int j=0;j<custOrderList.size();j++) {
					MilkOrderList milkList = new MilkOrderList();
					milkList.setOrderCustProdCmp(custOrderList.get(j).getCustOrderProdCmp());
					milkList.setOrderCustProdName(custOrderList.get(j).getCustOrderProdName());
					milkList.setOrderCustProdPrice(0);
					milkList.setOrderCustProdQty(custOrderList.get(j).getCustOrderProdQty());
					milkList.setOrderCustProdType(custOrderList.get(j).getCustOrderProdType());
					milkOrderList.add(milkList);
				}
				orderReqDto.setOrderList(milkOrderList);
				finalOrderList.add(orderReqDto);
			}
		}else {
			List<CustOrderPlacedDtls> orderDetailList = custOrdPlDtlRepo.getCustOrderReqApproved(ownerEmail,email,date);
			for(int i=0;i<orderDetailList.size();i++) {
				OrderRequestDto orderReqDto = new OrderRequestDto();
				orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getCustOrderCrtdDate());
				orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getCustOrderEmail());
				orderReqDto.setOrderCustName(orderDetailList.get(i).getCustOrderName());
				orderReqDto.setOrderCustRefId(orderDetailList.get(i).getCustOrderOwnerRefId());
				orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
				orderReqDto.setOrderCustTotalPrice(0);
				orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
				orderReqDto.setOrderReqStatus(orderDetailList.get(i).getCustOrderReqStatus());
				List<CustOrderDtlList> custOrderList = custOrdPlListRepo.getCustOrderPlacedListAprvd(orderDetailList.get(i).getCustOrderRefId(),orderDetailList.get(i).getCustOrderCrtdDate());
				List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
				for(int j=0;j<custOrderList.size();j++) {
					MilkOrderList milkList = new MilkOrderList();
					milkList.setOrderCustProdCmp(custOrderList.get(j).getCustOrderProdCmp());
					milkList.setOrderCustProdName(custOrderList.get(j).getCustOrderProdName());
					milkList.setOrderCustProdPrice(0);
					milkList.setOrderCustProdQty(custOrderList.get(j).getCustOrderProdQty());
					milkList.setOrderCustProdType(custOrderList.get(j).getCustOrderProdType());
					milkOrderList.add(milkList);
				}
				orderReqDto.setOrderList(milkOrderList);
				finalOrderList.add(orderReqDto);
			}
			
		}
		return finalOrderList;
		
	}
	
	public List<OrderRequestDto> getProcessedOrders(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<OrderRequestDetails> orderDetailList = orderRepo.getProcessedOrders(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getOrderOwnerRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderRefId(orderDetailList.get(i).getOrderRefId());
			orderReqDto.setOrderCustRefId(orderDetailList.get(i).getOrderCustRefId());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getProcOrderList(orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderRefId());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getOrderCustProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getOrderCustProdName());
				milkList.setOrderCustProdPrice(custOrderList.get(j).getOrderCustProdPrice());
				milkList.setOrderCustProdQty(custOrderList.get(j).getOrderCustProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getOrderCustProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
			
			
		}
		System.out.println("Customer Order Proccess List End");
		return finalOrderList;
	}
	
	public List<OrderRequestDto> getCustConfPymtOrders(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<OrderRequestDetails> orderDetailList = orderRepo.getCustPymtConfirmOrders(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getOrderOwnerRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			orderReqDto.setOrderCustRefId(orderDetailList.get(i).getOrderCustRefId());
			orderReqDto.setOrderRefId(orderDetailList.get(i).getOrderRefId());
			orderReqDto.setOrderFinalAmtPaid(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderPymtRefId(orderDetailList.get(i).getOrderPymtRefId());
			orderReqDto.setOrderReqStatus(orderDetailList.get(i).getOrderRequestStatus());
//			orderReqDto.setOrderFinalAmtPaid(shopBlnRepo.custPaidAmt(orderDetailList.get(i).getOrderOwnerRefId(),
//					orderDetailList.get(i).getOrderCustRefId(), orderDetailList.get(i).getOrderPlacedDate()));
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getCustConfPymtList(orderReqDto.getOrderRefId(),orderReqDto.getOrderCustCrtdDate());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getOrderCustProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getOrderCustProdName());
				milkList.setOrderCustProdPrice(custOrderList.get(j).getOrderCustProdPrice());
				milkList.setOrderCustProdQty(custOrderList.get(j).getOrderCustProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getOrderCustProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
			
			
		}
		System.out.println("Customer Order Proccess List End");
		return finalOrderList;
	}
	
	public List<OrderRequestDto> getCustProcessedOrders(String ownerEmail,String custMail){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		System.out.println("Customer Order Proccess List");
		List<OrderRequestDetails> orderDetailList = orderRepo.getCustProcessedOrders(ownerEmail,custMail);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getOrderOwnerRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			orderReqDto.setOrderCustRefId(orderDetailList.get(i).getOrderCustRefId());
			orderReqDto.setOrderRefId(orderDetailList.get(i).getOrderRefId());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getProcOrderList( orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderRefId());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getOrderCustProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getOrderCustProdName());
				milkList.setOrderCustProdPrice(custOrderList.get(j).getOrderCustProdPrice());
				milkList.setOrderCustProdQty(custOrderList.get(j).getOrderCustProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getOrderCustProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
		}
		return finalOrderList;
	}
}
