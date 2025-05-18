package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.BillingHistoryRes;
import com.merchant.management.dto.PdfProductDetails;
import com.merchant.management.entity.BillingEntity;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.CountryStateCity;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.repository.BillingHistoryRepo;
import com.merchant.management.repository.BillingRepository;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.ProductRepository;


@Service
public class BillingService {
	
	@Autowired
	private BillingRepository billingRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BillingHistoryRepo billingHistoryRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	public BillingService(BillingRepository billingRepository,JavaMailSender mailSender) {
		this.billingRepository = billingRepository;
		this.mailSender = mailSender;
		
	}
	
	public ResponseEntity billingService(BillingEntity billingEntity) {
		BillingHistory billingHistory = new BillingHistory();
		BillingEntityRes billingResponse = new BillingEntityRes();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        billingEntity.setBillingDateTime(timestamp.toString());
        long nanoTime = System.nanoTime();
        long uniqueNumber = nanoTime % 10_000_000_0;
        billingEntity.setBillingNo(String.valueOf(uniqueNumber));
        LocalTime currentTime = LocalTime.now();
        billingEntity.setBillingTime(currentTime.toString());
        List<ProductDetails> productDetails = billingEntity.getProductDetails();
        if(!billingEntity.getBillingDuePrice().equals("0")) {
        	billingEntity.setBillingDueFlag("1");
        }else {
        	billingEntity.setBillingDueFlag("0");
        }
       
        for(int i=0;i<productDetails.size();i++) {
        	int productQuantity = 0;
        	String productOwner = productDetails.get(i).getProductOwner();
        	String productName =productDetails.get(i).getProductName();
        	int currentProductQty = Integer.parseInt( productDetails.get(i).getProductQuantity());
        	System.out.println("Billing Product Qty : "+ productRepository.getProductQuantity(productOwner, productName));
        	int prodQtyFromDB = Integer.parseInt(productRepository.getProductQuantity(productOwner, productName));
        	productQuantity = prodQtyFromDB - currentProductQty;
        	System.out.println("Product Quantity : "+productQuantity);
        	String strPrdQty = Integer.toString(productQuantity);
        	productRepository.updateProductQty(productOwner, productName, strPrdQty);
        }
        
        pdfService.generateBillPdf(billingEntity, productDetails);
        //emailService.sendEmail(billingEntity.getBillingCustomerEmail(), productDetails.get(0).getProductOwner());
		 billingRepository.save(billingEntity);
		
			 billingResponse.setErrorCode("0");
			 billingResponse.setResponse("success");
		return ResponseEntity.ok(billingResponse);
	}
	
	@Async
	 public void sendEmail(String customerEmail, String ownerMail) {
		 LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
	        System.out.println("Formatted Date: " + formattedDate);
		try { 
			 SimpleMailMessage message = new SimpleMailMessage();
			 message.setFrom("chanper369@gmail.com");
			 message.setTo(customerEmail);
			 message.setSubject("Shopping at Merchant App "+formattedDate);
			 message.setText("Thanks for shopping using Merchant App");
			 mailSender.send(message);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		 
	 }
	
	public List<BillingHistoryRes> getBillHistoryDetails(String email){
		
		List<BillingHistoryRes> billingHistResList = new ArrayList<BillingHistoryRes>();
		List<BillingHistory> billingHistory = billingHistoryRepo.getBillingHistoryDetails(email);
		for(int i=0;i<billingHistory.size();i++) {
			BillingHistoryRes billingHistRes = new BillingHistoryRes();
			billingHistRes.setCustInvoiceDateRes(billingHistory.get(i).getCustInvoiceDate());
			billingHistRes.setCustDueAmtRes(billingHistory.get(i).getCustDueAmt());
			billingHistRes.setCustEmailIdRes(billingHistory.get(i).getCutEmailId());
			billingHistRes.setCustFullPaidFlgRes(billingHistory.get(i).getCustFullyPaidFlg());
			billingHistRes.setCustInvoiceIdRes(billingHistory.get(i).getCustInvoiceId());
			billingHistRes.setCustPaidAmtRes(billingHistory.get(i).getCustPaidAmt());
			billingHistRes.setCustPhnNoRes(billingHistory.get(i).getCustPhnNo());
			billingHistRes.setCustTotalAmtRes(billingHistory.get(i).getCustTotalAmt());
			billingHistResList.add(billingHistRes);
		}
		return billingHistResList;
	}
	
	 public List<CountryStateCity> getConStDtls(){
		 List<CountryStateCity> comProdDtls = billingRepository.getConStDtls().stream()
				    .map(row -> new CountryStateCity((String) row[0], (String) row[1], (String) row[2]))
				    .collect(Collectors.toList());
		 
		 return comProdDtls;
	 }

}
