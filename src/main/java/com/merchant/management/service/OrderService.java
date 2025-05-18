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
import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.ShopCustBalanceDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.repository.BillingHistoryRepo;
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
	
	public OrderService(OrderReqDetailRepo orderDtlsRepo,OrderReqRepo orderRepo,MilkProductRepo milkProdRepo,ShopCustBlnRepo shopBlnRepo,JavaMailSender mailSender,BillingHistoryRepo billHistRepo) {
		this.orderDtlsRepo = orderDtlsRepo;
		this.orderRepo = orderRepo;
		this.milkProdRepo = milkProdRepo;
		this.shopBlnRepo = shopBlnRepo;
		this.mailSender = mailSender;
		this.billHistRepo = billHistRepo;
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
		return finalOrderList;
	}
}
