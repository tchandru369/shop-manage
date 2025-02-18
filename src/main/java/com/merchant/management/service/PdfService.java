package com.merchant.management.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.PdfDetails;
import com.merchant.management.dto.PdfProductDetails;
import com.merchant.management.entity.BillingEntity;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.repository.MerchantRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class PdfService {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Value("${billreport.path}")
	private String exprotFilePath;
	
	
	public PdfService(MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}
	
	@Async
	public CompletableFuture<Void> generateBillPdf(BillingEntity billingEntity,List<ProductDetails> productDetails) {
		
		
		try {
			ClassPathResource resource = new ClassPathResource("JasperFile/Invoice_Table_Based.jasper");

	        

			MerchantDetails ownerDetails = new MerchantDetails();
			List<PdfProductDetails> pdfProductList = new ArrayList<PdfProductDetails>();
			List<PdfDetails> pdfDetailsList = new ArrayList<PdfDetails>();
			PdfDetails pdfDetails =  new PdfDetails();
		    String ownerName = productDetails.get(0).getProductOwner();
		    ownerDetails = merchantRepository.getMerchantProfileDetails(ownerName);
		    pdfDetails.setCustomerName(billingEntity.getBillingCustomerName());
		    pdfDetails.setCustomerEmail(billingEntity.getBillingCustomerEmail());
		    pdfDetails.setCustomerAddrs(billingEntity.getBillingCustomerAddress());
		    pdfDetails.setCustomerPhNo(billingEntity.getBillingCustomerPhNo());
		    pdfDetails.setOwnerEmail(ownerDetails.getMerchantEmail());
		    pdfDetails.setOwnerAddress(ownerDetails.getMerchantAddress());
		    pdfDetails.setOwnerName(ownerDetails.getmerchantUserName());
		    pdfDetails.setOwnerPhNo(ownerDetails.getmerchantPhoneNumber());
		    pdfDetails.setInvoiceNumber("INV"+billingEntity.getBillingNo());
		    pdfDetails.setDiscountAmount(billingEntity.getBillingTotalPriceTax());
		    pdfDetailsList.add(pdfDetails);
		    for(int i=0;i<productDetails.size();i++) {
				PdfProductDetails pdfProduct = new PdfProductDetails();
		    	pdfProduct.setProductName(productDetails.get(i).getProductName());
		    	pdfProduct.setProductActualPrice(productDetails.get(i).getProductCustomerPrice());
		    	pdfProduct.setProductPrice(Integer.parseInt(productDetails.get(i).getProductPrice()));
		    	pdfProduct.setProductQuantity(Integer.parseInt(productDetails.get(i).getProductQuantity()));
		    	pdfProductList.add(pdfProduct);
		    }
		    System.out.println(pdfProductList.get(0).getProductName());
		    System.out.println(pdfProductList.get(1).getProductName());

			try {
		    String jasperFilePath = resource.getFile().getAbsolutePath();

			JasperReport jasperReport = (JasperReport)JRLoader.loadObjectFromFile(jasperFilePath); 
			
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(pdfDetailsList);
			
			JRBeanCollectionDataSource tableDataSource = new JRBeanCollectionDataSource(pdfProductList);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("TABLE_DATA_SOURCE", tableDataSource);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,datasource);
			
			String pdfName = pdfDetails.getInvoiceNumber()+".pdf";
			String finalPdfPath = exprotFilePath+pdfName;
			JasperExportManager.exportReportToPdfFile(jasperPrint,finalPdfPath);
			
			System.out.println("Billing Report Generated Successfully......");
            
			emailService.sendEmail(billingEntity.getBillingCustomerEmail(), productDetails.get(0).getProductOwner(),pdfName,finalPdfPath);
			} 
			catch(Exception e) {
		    	 System.out.println(e.getMessage());
		     }
       	 
           Thread.sleep(5000); // Simulating a delay for the email sending
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       return CompletableFuture.completedFuture(null);
	    
	}
}
