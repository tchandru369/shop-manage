package com.merchant.management.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
	
	@Async
    public CompletableFuture<Void> sendEmail(String customerEmail, String productOwner,String fileName,String FilePath) {
        // Simulate email sending process (e.g., calling an email service)
        try {
        	 LocalDate currentDate = LocalDate.now();
 	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 	        String formattedDate = currentDate.format(formatter);
 	        System.out.println("Formatted Date: " + formattedDate);
 		try { 
 			MimeMessage mimeMessage = mailSender.createMimeMessage();
 			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
 			 SimpleMailMessage message = new SimpleMailMessage();
 			helper.setFrom("chanper369@gmail.com");
 			helper.setTo(customerEmail);
 			helper.setSubject("Shopping at Merchant App "+formattedDate);
 			helper.setText("Dear Customer,\r\n"
 					+ "\r\n"
 					+ "I hope this email finds you well.\r\n"
 					+ "\r\n"
 					+ "Please find attached the invoice for your recent purchase with Merchant App. The details of your bill are included in the attached PDF document.\r\n"
 					+ "\r\n"
 					+ "If you have any questions or concerns regarding the invoice, please do not hesitate to contact us.\r\n"
 					+ "\r\n"
 					+ "Thank you for your business, and we look forward to serving you again.\r\n"
 					+ "\r\n"
 					+ "Best regards,");
 			
 			
 			helper.addAttachment(fileName, new File(FilePath));
 			 mailSender.send(mimeMessage);
 		}catch(Exception e){
 			System.out.println(e.getMessage());
 		}
            Thread.sleep(5000); // Simulating a delay for the email sending
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
	
	
	@Async
    public CompletableFuture<Void> sendEmailDup(String customerEmail, String productOwner,String fileName) {
        // Simulate email sending process (e.g., calling an email service)
        try {
        	 LocalDate currentDate = LocalDate.now();
 	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 	        String formattedDate = currentDate.format(formatter);
 	        System.out.println("Formatted Date: " + formattedDate);
 		try { 
 			MimeMessage mimeMessage = mailSender.createMimeMessage();
 			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
 			 SimpleMailMessage message = new SimpleMailMessage();
 			helper.setFrom("chanper369@gmail.com");
 			helper.setTo(customerEmail);
 			helper.setSubject("Shopping at Merchant App "+formattedDate);
 			helper.setText("Dear Customer,\r\n"
 					+ "\r\n"
 					+ "I hope this email finds you well.\r\n"
 					+ "\r\n"
 					+ "Please find the attached invoice for your recent purchase with Merchant App. The details of your bill are included in the attached PDF document.\r\n"
 					+ "\r\n"
 					+ "If you have any questions or concerns regarding the invoice, please do not hesitate to contact us.\r\n"
 					+ "\r\n"
 					+ "Thank you for your business, and we look forward to serving you again.\r\n"
 					+ "\r\n"
 					+ "Best regards,");
 			byte[] fileBytes = downloadFileFromGCS(fileName);

 	        // Attach the PDF file
 	        InputStream inputStream = new ByteArrayInputStream(fileBytes);
 	        
 	       DataSource dataSource = new ByteArrayDataSource(inputStream, "application/pdf");
 			
 			helper.addAttachment(fileName,dataSource );
 			
 			
 			 mailSender.send(mimeMessage);
 			deleteFileFromGCS("crypto-moon-450715-c2.appspot.com",fileName);
 		}catch(Exception e){
 			System.out.println(e.getMessage());
 		}
            Thread.sleep(5000); // Simulating a delay for the email sending
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }
	
	 private byte[] downloadFileFromGCS(String fileName) throws IOException {
	        // Initialize the Storage client
	        Storage storage = StorageOptions.getDefaultInstance().getService();
	        
	        // Replace with your bucket name
	        String bucketName = "crypto-moon-450715-c2.appspot.com";
	        
	        // Get the blob (file) from Cloud Storage
	        BlobId blobId = BlobId.of(bucketName, fileName);
	        Blob blob = storage.get(blobId);
	        
	        // Check if the file exists
	        if (blob == null) {
	            throw new IOException("File not found in GCS: " + fileName);
	        }

	        // Download the file as a byte array
	        return blob.getContent();
	    }
	 
	 public void deleteFileFromGCS(String bucketName, String fileName) {
	        // Initialize the Storage client
	        Storage storage = StorageOptions.getDefaultInstance().getService();

	        // Get the BlobId for the file
	        BlobId blobId = BlobId.of(bucketName, fileName);

	        // Delete the file from Cloud Storage
	        boolean deleted = storage.delete(blobId);

	        if (deleted) {
	            System.out.println("File " + fileName + " deleted successfully.");
	        } else {
	            System.out.println("Failed to delete file " + fileName);
	        }
	    }

}
