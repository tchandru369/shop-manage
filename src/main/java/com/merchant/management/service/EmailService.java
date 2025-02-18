package com.merchant.management.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

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

}
