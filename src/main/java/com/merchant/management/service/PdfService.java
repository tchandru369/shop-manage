package com.merchant.management.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.BlobId;
//import com.google.cloud.storage.BlobInfo;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;
import com.merchant.management.dto.PdfDetails;
import com.merchant.management.dto.PdfProductDetails;
import com.merchant.management.entity.BillingEntity;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ProductDetails;
import com.merchant.management.repository.BillingHistoryRepo;
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
	private BillingHistoryRepo billingHistoryRepo;
	
//	@Autowired
//	private Storage storage;
	
	@Autowired
	private EmailService emailService;
	
	@Value("${billreport.path}")
	private String exprotFilePath;
	
	
//	public PdfService(MerchantRepository merchantRepository,Storage storage) {
//		this.merchantRepository = merchantRepository;
//		this.storage = storage;
//	}
	
	public PdfService(MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}
	
	@Async
	public CompletableFuture<Void> generateBillPdf(BillingEntity billingEntity,List<ProductDetails> productDetails) {
		
		
		try {
			//ClassPathResource resource = new ClassPathResource("JasperFile/Invoice_Table_Based.jasper");

	        System.out.println("Inside PDF generation..............");
            BillingHistory billingHistory = new BillingHistory();
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
		    System.out.println("PDf details has been added to list");
		    for(int i=0;i<productDetails.size();i++) {
				PdfProductDetails pdfProduct = new PdfProductDetails();
		    	pdfProduct.setProductName(productDetails.get(i).getProductName());
		    	pdfProduct.setProductActualPrice(productDetails.get(i).getProductCustomerPrice());
		    	pdfProduct.setProductPrice(Integer.parseInt(productDetails.get(i).getProductPrice()));
		    	pdfProduct.setProductQuantity(Integer.parseInt(productDetails.get(i).getProductQuantity()));
		    	pdfProductList.add(pdfProduct);
		    }
		    System.out.println(pdfProductList.get(0).getProductName());		    
		    billingHistory.setCutEmailId(pdfDetails.getCustomerEmail());
		    billingHistory.setCustShopEmailId(pdfDetails.getOwnerEmail());
		    billingHistory.setCustInvoiceId(pdfDetails.getInvoiceNumber());
		    billingHistory.setCustPhnNo(pdfDetails.getCustomerPhNo());
		    billingHistory.setCustTotalAmt(billingEntity.getBillingTotalPrice());
		    billingHistory.setCustInvoiceDate(billingEntity.getBillingDate());
		    billingHistory.setCustPaidAmt(billingEntity.getBillingAmtPaid());
		    billingHistory.setCustDueAmt(billingEntity.getBillingDuePrice());
		    
		    if(billingEntity.getBillingDueFlag().equals("1")) {
		    	billingHistory.setCustFullyPaidFlg("0");
		    }else {
		    	billingHistory.setCustFullyPaidFlg("1");
		    }
		    System.out.println("billing history entity has been added......"); 
		    billingHistoryRepo.save(billingHistory);

			try {
		    //String jasperFilePath = resource.getFile().getAbsolutePath();
//		    System.out.println(jasperFilePath);
		    String jasperFilePaths = "/app/resources/JasperFile/Invoice_Table_Based.jasper";
				//String jasperFilePaths ="src/main/resources/JasperFile/Invoice_Table_Based.jasper";
		    System.out.println("Inside Jasper Loader........."+jasperFilePaths);
		    File jasperFile = new File(jasperFilePaths);
		    System.out.println("Jasper file exists: " + jasperFile.exists() + " | Path: " + jasperFile.getAbsolutePath());
		    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePaths);
		    
		    //byte[] jasperFileBytes = downloadFileFromGCS("crypto-moon-450715-c2.appspot.com", "Invoice_Table_Based.jasper");

	        // Step 2: Load the JasperReport from the byte array
//	        ByteArrayInputStream jasperInputStream = new ByteArrayInputStream(jasperFileBytes);
//	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperInputStream);

			//JasperReport jasperReport = (JasperReport)JRLoader.loadObjectFromFile(jasperFilePath1); 
			
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(pdfDetailsList);
			
			JRBeanCollectionDataSource tableDataSource = new JRBeanCollectionDataSource(pdfProductList);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("TABLE_DATA_SOURCE", tableDataSource);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,datasource);
			
			byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
			
	        //uploadPdfToGCS(pdfBytes, "crypto-moon-450715-c2.appspot.com", pdfDetailsList.get(0).getInvoiceNumber() + ".pdf");

			String pdfName = pdfDetails.getInvoiceNumber()+".pdf";
//			String finalPdfPath = exprotFilePath+pdfName;
//			JasperExportManager.exportReportToPdfFile(jasperPrint,finalPdfPath);
			
			System.out.println("Billing Report Generated Successfully......");
            
			emailService.sendEmail1(billingEntity.getBillingCustomerEmail(), productDetails.get(0).getProductOwner(), pdfName, pdfBytes);
			//emailService.sendEmailDup(billingEntity.getBillingCustomerEmail(), productDetails.get(0).getProductOwner(),pdfName);
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
	
//	private void uploadPdfToGCS(byte[] pdfBytes, String bucketName, String pdfFileName) throws Exception {
//        // Create the Google Cloud Storage client
//        Storage storage = StorageOptions.getDefaultInstance().getService();
//        
//        // Create a BlobId and BlobInfo for the file
//        BlobId blobId = BlobId.of(bucketName, pdfFileName); // The path within your bucket
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//        
//        // Upload the file to Google Cloud Storage
//        try (InputStream inputStream = new ByteArrayInputStream(pdfBytes)) {
//            storage.create(blobInfo, inputStream);
//        }
//
//        System.out.println("File uploaded to GCS: " + bucketName + "/" + pdfFileName);
//    }
	
//	private byte[] downloadFileFromGCS(String bucketName, String fileName) throws IOException {
//        // Initialize the Storage client
//        Storage storage = StorageOptions.getDefaultInstance().getService();
//        
//        // Get the blob (file) from Cloud Storage
//        BlobId blobId = BlobId.of(bucketName, fileName);
//        Blob blob = storage.get(blobId);
//        
//        // Check if the file exists
//        if (blob == null) {
//            throw new IOException("File not found in GCS: " + fileName);
//        }
//
//        // Download the file as a byte array
//        return blob.getContent();
//    }
}
