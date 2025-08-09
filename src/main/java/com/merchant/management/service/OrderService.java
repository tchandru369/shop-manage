package com.merchant.management.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.merchant.management.dto.MilkOrderList;
import com.merchant.management.dto.OrderRequestDto;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.CustOrderPlacedDtls;
import com.merchant.management.entity.MilkProductEntity;
import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.ShopCustBalanceDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.repository.BillingHistoryRepo;
import com.merchant.management.repository.CustOrdPlDtlsListRepo;
import com.merchant.management.repository.CustOrderPlDtlRepo;
import com.merchant.management.repository.MilkProductRepo;
import com.merchant.management.repository.OrderReqDetailRepo;
import com.merchant.management.repository.OrderReqRepo;
import com.merchant.management.repository.ShopCustBlnRepo;

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
		int orderCount = orderRepo.getCustOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
		if(orderCount >= 1) {
			
			 response.setResponse("success");
			 response.setErrorCode("0");
			 response.setErrorMsg("Order already placed, Please visit My Requests");
			 
			 return response;
		}else {
		List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
		OrderRequestDetails orderReq = new OrderRequestDetails();
		orderReq.setOrderBalanceFlg("YES");
		orderReq.setOrderBillPayFlg("YES");
		orderReq.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
		orderReq.setOrderCustPhNo(custOrderDtls.getOrderCustPhoneNo());
		orderReq.setOrderPlacedDate(custOrderDtls.getOrderCustCrtdDate());
		orderReq.setOrderProdTotalAmt(custOrderDtls.getOrderCustTotalPrice());
		orderReq.setOrderProductCustType(custOrderDtls.getOrderCustType());
		orderReq.setOrderProductOwnerName(custOrderDtls.getOrderCustOwnerName());
		orderReq.setOrderRequestStatus("RA");
		orderReq.setOrderProductCustName(custOrderDtls.getOrderCustName());
		
		for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
			
			ShopCustOrderDetails shopCust =  new ShopCustOrderDetails();
			shopCust.setOrderCustCrtdDate(custOrderDtls.getOrderCustCrtdDate());
			shopCust.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
			shopCust.setOrderCustOwnerName(custOrderDtls.getOrderCustOwnerName());
			shopCust.setOrderCustPhoneNo(custOrderDtls.getOrderCustPhoneNo());
			shopCust.setOrderCustProdCmp(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
			shopCust.setOrderCustProdName(custOrderDtls.getOrderList().get(i).getOrderCustProdName());
			shopCust.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdPrice());
			shopCust.setOrderCustProdQty(custOrderDtls.getOrderList().get(i).getOrderCustProdQty());
			shopCust.setOrderCustProdType(custOrderDtls.getOrderList().get(i).getOrderCustProdType());
			shopCust.setOrderCustStatus("RA");
			custList.add(shopCust);
		}
		 
		 if(orderRepo.save(orderReq) != null &&  orderDtlsRepo.saveAll(custList) != null) {
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
	}
	
	@Transactional(readOnly = false)
	public BillingEntityRes deleteCustOrderDetails(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		 
		 int deleteOrderCount =  orderDtlsRepo.deleteOrderDetailReq(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustPhoneNo());
		 int delteteorderListCnt = orderRepo.deleteOrderReqTable(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(),custOrderDtls.getOrderCustOwnerName());
		 response.setErrorCode("0");
		 response.setResponse("success");
		 response.setErrorMsg("success");
		 
		 return response;
		 
	}
	
	@Transactional(readOnly = false)
	public BillingEntityRes deleteProcessOrderDetails(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		 
		 int deleteOrderCount =  orderDtlsRepo.deleteOrderProcReq(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustPhoneNo());
		 int delteteorderListCnt = orderRepo.deleteProcOrderReqTable(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(),custOrderDtls.getOrderCustOwnerName());
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
		orderPlacedDtls.setCustOrderOwnerEmail(custOrderDtl.getOrderCustOwnerName());
		orderPlacedDtls.setCustOrderPhNo(custOrderDtl.getOrderCustPhoneNo());
		orderPlacedDtls.setCustOrderReqStatus("OP");
		orderPlacedDtls.setCustOrderType(custOrderDtl.getOrderCustType());
		List<CustOrderDtlList> dtlList = new ArrayList<CustOrderDtlList>();
		for(int i=0;i<custOrderDtl.getOrderList().size();i++) {
			CustOrderDtlList custList = new CustOrderDtlList();
			custList.setCustOrderCrdDate(formattedDate);
			custList.setCustOrderEmailId(custOrderDtl.getOrderCustEmailId());
			custList.setCustOrderLiveFlg("1");
			custList.setCustOrderOwnerName(custOrderDtl.getOrderCustOwnerName());
			custList.setCustOrderPhNo(custOrderDtl.getOrderCustPhoneNo());
			custList.setCustOrderProdCmp(custOrderDtl.getOrderList().get(i).getOrderCustProdCmp());
			custList.setCustOrderProdType(custOrderDtl.getOrderList().get(i).getOrderCustProdType());
			custList.setCustOrderProdName(custOrderDtl.getOrderList().get(i).getOrderCustProdName());
			custList.setCustOrderProdQty(custOrderDtl.getOrderList().get(i).getOrderCustProdQty());
			custList.setCustOrderReqStatus("OP");
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
		BillingHistory billingHistory = new BillingHistory();
		double remainAmt = paidOrderReq.getOrderCustTotalPrice() - paidOrderReq.getOrderFinalAmtPaid();
		shopBlnRepo.updateCustBln(paidOrderReq.getOrderFinalAmtPaid(), remainAmt, paidOrderReq.getOrderCustEmailId(), paidOrderReq.getOrderCustOwnerName()
				, paidOrderReq.getOrderCustPhoneNo(), paidOrderReq.getOrderCustCrtdDate());
		 long nanoTime = System.nanoTime();
	     long uniqueNumber = nanoTime % 10_000_000_0;
	     LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
	        for(int i=0;i<paidOrderReq.getOrderList().size();i++) {
				 orderDtlsRepo.updtOrdStsBillList(paidOrderReq.getOrderCustOwnerName(), paidOrderReq.getOrderCustCrtdDate(),
						 paidOrderReq.getOrderCustPhoneNo(), paidOrderReq.getOrderCustEmailId(), 
						 paidOrderReq.getOrderList().get(i).getOrderCustProdCmp(), 
						 paidOrderReq.getOrderList().get(i).getOrderCustProdType(),
						 paidOrderReq.getOrderList().get(i).getOrderCustProdName());
			 }
	     billingHistory.setCustDueAmt(remainAmt);
	     if(remainAmt != 0) {
	    	 billingHistory.setCustFullyPaidFlg("N");
	    	 orderRepo.updateordStsBill("N", formattedDate, paidOrderReq.getOrderCustEmailId(), paidOrderReq.getOrderCustPhoneNo(),
	    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderCustOwnerName());
	     }else {
	    	 billingHistory.setCustFullyPaidFlg("Y");
	    	 orderRepo.updateordStsBill("Y", formattedDate, paidOrderReq.getOrderCustEmailId(), paidOrderReq.getOrderCustPhoneNo(),
		    			paidOrderReq.getOrderCustCrtdDate() , paidOrderReq.getOrderCustType(), paidOrderReq.getOrderCustOwnerName());
	     }
	     billingHistory.setCustInvoiceDate(formattedDate);
	     billingHistory.setCustPaidAmt(paidOrderReq.getOrderFinalAmtPaid());
	     billingHistory.setCustPhnNo(paidOrderReq.getOrderCustPhoneNo());
	     billingHistory.setCustShopEmailId(paidOrderReq.getOrderCustOwnerName());
	     billingHistory.setCustTotalAmt(paidOrderReq.getOrderCustTotalPrice());
	     billingHistory.setCutEmailId(paidOrderReq.getOrderCustEmailId());
	     billingHistory.setCustInvoiceId("INV"+String.valueOf(uniqueNumber));
	     billHistRepo.save(billingHistory);
	    
	     
	     pdfService.generatePaidBillPdf(paidOrderReq, billingHistory);
	     
	     response.setResponse("success");
	     response.setErrorMsg("success");
	     response.setErrorCode("0");
	     
		return response;
	}
	
	public BillingEntityRes orderProcessRequest(OrderRequestDto custOrderDtls) {
		BillingEntityRes response = new BillingEntityRes();
		
		
		int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
        System.out.println("The count is : "+orderCount);
		if(orderCount >= 1) {
        	 response.setResponse("success");
			 response.setErrorCode("1");
			 response.setErrorMsg("Order already processed, Please delete or process the payment");
			 
			 return response;
        }else {
        	ShopCustBalanceDetails blnDtls = new ShopCustBalanceDetails();
    		
    		LocalDate currentDate = LocalDate.now();
    	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	     String formattedDate = currentDate.format(formatter);
    		
    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
    			 int actualPrdQty = milkProdRepo.getCurrentMilkProdQty(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderList().get(i).getOrderCustProdType(), custOrderDtls.getOrderList().get(i).getOrderCustProdName(),custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
    			 int recentProdQty = actualPrdQty - custOrderDtls.getOrderList().get(i).getOrderCustProdQty();
    			 System.out.println("the recent product qty :"+recentProdQty);
    			 milkProdRepo.updateMilkProcQty(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderList().get(i).getOrderCustProdName(), custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), custOrderDtls.getOrderList().get(i).getOrderCustProdType(), recentProdQty);
    			 orderDtlsRepo.updateOrderStatusList(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(),
    					 custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId(), 
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), 
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName());
    		 }
    		 orderRepo.updateOrderStatus(custOrderDtls.getOrderCustOwnerName(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId());
    		 blnDtls.setCustBalActAmt(custOrderDtls.getOrderCustTotalPrice());
    		 blnDtls.setCustBalAmt(custOrderDtls.getOrderCustTotalPrice());
    		 blnDtls.setCustBalDate(formattedDate);
    		 blnDtls.setCustBalEmailId(custOrderDtls.getOrderCustEmailId());
    		 blnDtls.setCustBalName(custOrderDtls.getOrderCustName());
    		 blnDtls.setCustBalOwnerName(custOrderDtls.getOrderCustOwnerName());
    		 blnDtls.setCustBalPhoneNo(custOrderDtls.getOrderCustPhoneNo());
    		 blnDtls.setCustBalPaidAmt(0);
    		 blnDtls.setCustBalStatus("BP");
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
		int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
		int PlcdorderCount = custOrdPlDtlRepo.getCustPlcdOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
        System.out.println("The count is : "+orderCount);
		if(PlcdorderCount >= 1 && orderCount >=1) {
        	 response.setResponse("success");
			 response.setErrorCode("1");
			 response.setErrorMsg("Order has been already approved, Please verify processing list..");
			 
			 return response;
        }else {
             
    		List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
    		double finalAmt = 0;
    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
    			 System.out.println("Inside List started...");
    			 
    			 custOrdPlListRepo.updateCustPlcdDtlListR(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(),
    					 custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId(), 
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), 
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName());
    		 }
    		 
    		 custOrdPlDtlRepo.updateCustPlcdOrderStatusR(custOrderDtls.getOrderCustOwnerName(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
    				 , custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId());
    		 
    		 
    		 response.setErrorCode("0");
    		 response.setResponse("success");
    		 response.setErrorMsg("success");
    		 
    		 return response;
        }
	
	 
}
		
		public BillingEntityRes approveCustPlacedOrder(OrderRequestDto custOrderDtls) {
			BillingEntityRes response = new BillingEntityRes();
			OrderRequestDetails orderPrcDetails = new OrderRequestDetails();
			int orderCount = orderRepo.getProcOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
			int PlcdorderCount = custOrdPlDtlRepo.getCustPlcdOrderCount(custOrderDtls.getOrderCustEmailId(), custOrderDtls.getOrderCustPhoneNo(),custOrderDtls.getOrderCustCrtdDate(), custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustOwnerName());
	        System.out.println("The count is : "+orderCount);
			if(PlcdorderCount >= 1 && orderCount >=1) {
	        	 response.setResponse("success");
				 response.setErrorCode("1");
				 response.setErrorMsg("Order has been already approved, Please verify processing list..");
				 
				 return response;
	        }else {
	             
	    		List<ShopCustOrderDetails> custList = new ArrayList<ShopCustOrderDetails>();
	    		double finalAmt = 0;
	    		 for(int i=0;i<custOrderDtls.getOrderList().size();i++) {
	    			 System.out.println("Inside List started...");
	    			 MilkProductEntity milkObj = milkProdRepo.getMilkProdPrice(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(),
	    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName(), custOrderDtls.getOrderList().get(i).getOrderCustProdType()); 
	    			 System.out.println("milkObj List Ended.....");
	    			 if(milkObj == null) {
	    				response.setErrorCode("1");
	    				response.setResponse("success"); 
	    				response.setErrorMsg(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp()+" "+
	    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName()+" "+custOrderDtls.getOrderList().get(i).getOrderCustProdType()+" "+"Not present in DB");
	    				custOrdPlListRepo.updateCustPlcdDtlListBck(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(),
	    					 custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId());
	    				return response;
	    			 }
	    			 ShopCustOrderDetails shopCustList = new ShopCustOrderDetails();
	    			 shopCustList.setOrderCustCrtdDate(custOrderDtls.getOrderCustCrtdDate());
    				 shopCustList.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
    				 shopCustList.setOrderCustOwnerName(custOrderDtls.getOrderCustOwnerName());
    				 shopCustList.setOrderCustPhoneNo(custOrderDtls.getOrderCustPhoneNo());
    				 shopCustList.setOrderCustProdCmp(custOrderDtls.getOrderList().get(i).getOrderCustProdCmp());
    				 shopCustList.setOrderCustProdName(custOrderDtls.getOrderList().get(i).getOrderCustProdName());
    				 shopCustList.setOrderCustProdQty(custOrderDtls.getOrderList().get(i).getOrderCustProdQty());
    				 shopCustList.setOrderCustProdType(custOrderDtls.getOrderList().get(i).getOrderCustProdType());
    				 shopCustList.setOrderCustStatus("RA");
	    			 if(custOrderDtls.getOrderCustType().equalsIgnoreCase("S")) {
	    				 shopCustList.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdQty()*milkObj.getProductShopPrice());
	    				 finalAmt = finalAmt+shopCustList.getOrderCustProdPrice();
	    				 
	    			 }else if(custOrderDtls.getOrderCustType().equalsIgnoreCase("I")) {
	    				 shopCustList.setOrderCustProdPrice(custOrderDtls.getOrderList().get(i).getOrderCustProdQty()*milkObj.getProductCustPrice());
	    				 finalAmt = finalAmt+shopCustList.getOrderCustProdPrice();
	    			 }
	    			 custOrdPlListRepo.updateCustPlcdDtlList(custOrderDtls.getOrderCustOwnerName(), custOrderDtls.getOrderCustCrtdDate(),
	    					 custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId(), 
	    					 custOrderDtls.getOrderList().get(i).getOrderCustProdCmp(), 
	    					 custOrderDtls.getOrderList().get(i).getOrderCustProdType(),
	    					 custOrderDtls.getOrderList().get(i).getOrderCustProdName());
	    			 custList.add(shopCustList);
	    		 }
	    		 
	    		 orderPrcDetails.setOrderBalanceFlg("YES");
	    		 orderPrcDetails.setOrderBillPayFlg("YES");
	    		 orderPrcDetails.setOrderCustEmailId(custOrderDtls.getOrderCustEmailId());
	    		 orderPrcDetails.setOrderCustPhNo(custOrderDtls.getOrderCustPhoneNo());
	    		 orderPrcDetails.setOrderPlacedDate(custOrderDtls.getOrderCustCrtdDate());
	    		 orderPrcDetails.setOrderProdTotalAmt(finalAmt);
	    		 orderPrcDetails.setOrderProductCustName(custOrderDtls.getOrderCustName());
	    		 orderPrcDetails.setOrderProductCustType(custOrderDtls.getOrderCustType());
	    		 orderPrcDetails.setOrderProductOwnerName(custOrderDtls.getOrderCustOwnerName());
	    		 orderPrcDetails.setOrderRequestStatus("RA");
	    		 custOrdPlDtlRepo.updateCustPlcdOrderStatus(custOrderDtls.getOrderCustOwnerName(),custOrderDtls.getOrderCustType(), custOrderDtls.getOrderCustCrtdDate()
	    				 , custOrderDtls.getOrderCustPhoneNo(), custOrderDtls.getOrderCustEmailId());
	    		 
	    		 orderDtlsRepo.saveAll(custList);
	    		 orderRepo.save(orderPrcDetails);
	    		 
	    		 
	    		 response.setErrorCode("0");
	    		 response.setResponse("success");
	    		 response.setErrorMsg("success");
	    		 
	    		 return response;
	        }
		
		 
	}
	
	public List<OrderRequestDto> getOrderDetails(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<OrderRequestDetails> orderDetailList = orderRepo.getOrderRequestCreated(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderCustOwnerName(orderDetailList.get(i).getOrderProductOwnerName());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getOrderList(orderReqDto.getOrderCustEmailId(), orderReqDto.getOrderCustOwnerName(), orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderCustPhoneNo());
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
			orderReqDto.setOrderCustOwnerName(orderDetailList.get(i).getCustOrderOwnerEmail());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
			orderReqDto.setOrderCustTotalPrice(0);
			orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
			List<CustOrderDtlList> custOrderList = custOrdPlListRepo.getCustOrderPlacedList(orderReqDto.getOrderCustEmailId(), orderReqDto.getOrderCustOwnerName(), orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderCustPhoneNo());
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
		List<CustOrderPlacedDtls> orderDetailList = custOrdPlDtlRepo.getCustOrderReqApproved(ownerEmail,email,date);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getCustOrderCrtdDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getCustOrderEmail());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getCustOrderName());
			orderReqDto.setOrderCustOwnerName(orderDetailList.get(i).getCustOrderOwnerEmail());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
			orderReqDto.setOrderCustTotalPrice(0);
			orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
			orderReqDto.setOrderReqStatus(orderDetailList.get(i).getCustOrderReqStatus());
			List<CustOrderDtlList> custOrderList = custOrdPlListRepo.getCustOrderPlacedListAprvd(orderReqDto.getOrderCustEmailId(), orderReqDto.getOrderCustOwnerName(), orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderReqStatus());
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
	
	public List<OrderRequestDto> getProcessedOrders(String email){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		List<OrderRequestDetails> orderDetailList = orderRepo.getProcessedOrders(email);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getOrderPlacedDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getOrderCustEmailId());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getOrderProductCustName());
			orderReqDto.setOrderCustOwnerName(orderDetailList.get(i).getOrderProductOwnerName());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getProcOrderList(orderReqDto.getOrderCustEmailId(), orderReqDto.getOrderCustOwnerName(), orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderCustPhoneNo());
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
			orderReqDto.setOrderCustOwnerName(orderDetailList.get(i).getOrderProductOwnerName());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getOrderCustPhNo());
			orderReqDto.setOrderCustTotalPrice(orderDetailList.get(i).getOrderProdTotalAmt());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getOrderProductCustType());
			List<ShopCustOrderDetails> custOrderList = orderDtlsRepo.getProcOrderList(orderReqDto.getOrderCustEmailId(), orderReqDto.getOrderCustOwnerName(), orderReqDto.getOrderCustCrtdDate(),orderReqDto.getOrderCustPhoneNo());
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
