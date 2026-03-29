package com.merchant.management.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merchant.management.dto.BrevoEmailReq;
import com.merchant.management.dto.EmailAttachement;
import com.merchant.management.dto.EmailReceipent;
import com.merchant.management.dto.EmailSender;
import com.merchant.management.dto.OrderRequestDto;

//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.BlobId;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	 @Value("${brevo.api.key}")
	 private String brevoApiKey;
	 
	 @Value("${brevo.api.url}")
	 private String brevoApiUrl;
	 
	 
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
 					+ "Please find the attached invoice for your recent purchase with Merchant App. The details of your bill are included in the attached PDF document.\r\n"
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
	
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendEmail1(String customerEmail, String productOwner,String fileName,byte[] pdfBytes) {
        // Simulate email sending process (e.g., calling an email service)
        try {
        	System.out.println("Inside Email service...................");
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
 			
 			
 			helper.addAttachment(fileName, new ByteArrayDataSource(pdfBytes, "application/pdf"));
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
	

	@Async("taskExecutor")
    public CompletableFuture<Void> sendBrevoEmail(String customerEmail, String productOwner,String fileName,String encodedString) {
        // Simulate email sending process (e.g., calling an email service)
		 LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String formattedDate = currentDate.format(formatter);
	        System.out.println("Formatted Date: " + formattedDate);
		System.out.println("Inside Brevo Email Service.........");
		BrevoEmailReq request = new BrevoEmailReq();
		EmailSender sender = new EmailSender();
		sender.setName("Merchant App");
		sender.setEmail("tchandru369@gmail.com");

		EmailReceipent recipient = new EmailReceipent();
		recipient.setEmail(customerEmail);
		recipient.setName("Customer");
		
		EmailAttachement attachement = new EmailAttachement();
		attachement.setName(fileName);
		attachement.setContent(encodedString);

		request.setSender(sender);
		request.setTo(List.of(recipient));
		request.setSubject("Shopping at Merchant App "+formattedDate);
		String htmlContent = "<html><body>"
		        + "<p>Dear Customer,</p>"
		        + "<p>I hope this email finds you well.</p>"
		        + "<p>Please find the attached invoice for your recent purchase with Merchant App. "
		        + "The details of your bill are included in the attached PDF document.</p>"
		        + "<p>If you have any questions or concerns regarding the invoice, "
		        + "please do not hesitate to contact us.</p>"
		        + "<p>Thank you for your business, and we look forward to serving you again.</p>"
		        + "<p>Best regards,<br>Merchant App Team</p>"
		        + "</body></html>";
		request.setHtmlContent(htmlContent);
		request.setAttachment(List.of(attachement));
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000); // 10 seconds
		factory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("api-key", brevoApiKey);
        
        System.out.println("Before Http Call!!!!!!!!!!!");
        System.out.println(brevoApiUrl +" "+ brevoApiKey);
        System.out.println("Encoded size: " + encodedString.length());
        HttpEntity<BrevoEmailReq> emailRequest = new HttpEntity<>(request, headers);
        System.out.println("After Http Entity!!!!!!!!!!!");
        ResponseEntity<String> response = restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                emailRequest,
                String.class
        );
        System.out.println("After Http Call!!!!!!!!!!!");
        
        System.out.println(response.getBody());
        return CompletableFuture.completedFuture(null);
    }
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendCustomerEmail(String customerEmail,String custName, String productOwner,String customerPass) {
        // Simulate email sending process (e.g., calling an email service)
        try {
        	System.out.println("Inside Email service...................");
        	 LocalDate currentDate = LocalDate.now();
 	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 	        String formattedDate = currentDate.format(formatter);
 	        System.out.println("Formatted Date: " + formattedDate);
 		try { 
 			MimeMessage mimeMessage = mailSender.createMimeMessage();
 			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
 			 SimpleMailMessage message = new SimpleMailMessage();
 			String emailBody = "Dear " + custName + ",\r\n\r\n"
 			        + "Thank you for registering with Merchant Corporation.\r\n\r\n"
 			        + "Please install Merchant App from Play Store and use the below credentials:\r\n\r\n"
 			        + "Username: " + customerEmail + "\r\n"
 			        + "Temporary Password: " + customerPass + "\r\n\r\n"
 			        + "For your security, please log in as soon as possible and change your password.\r\n\r\n"
 			        + "If you have any questions or need assistance, feel free to contact us.\r\n\r\n"
 			        + "Best regards,\r\n"
 			        + "Merchant Corporation";
 			helper.setFrom("chanper369@gmail.com");
 			helper.setTo(customerEmail);
 			helper.setSubject("Your Account Password Information");
 			helper.setText(emailBody);
 			
 			
 			//helper.addAttachment(fileName, new ByteArrayDataSource(pdfBytes, "application/pdf"));
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
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendCustomerEmailBrevo(String customerEmail,String custName, String productOwner,String customerPass) {
        // Simulate email sending process (e.g., calling an email service)
        
        BrevoEmailReq request = new BrevoEmailReq();
		EmailSender sender = new EmailSender();
		sender.setName("Merchant App");
		sender.setEmail("tchandru369@gmail.com");

		EmailReceipent recipient = new EmailReceipent();
		recipient.setEmail(customerEmail);
		recipient.setName("Customer");
		
//		EmailAttachement attachement = new EmailAttachement();
//		attachement.setName(fileName);
//		attachement.setContent(encodedString);

		request.setSender(sender);
		request.setTo(List.of(recipient));
		request.setSubject("Your Account Password Information");
		String htmlContent = "<html><body>"
		        + "<p>Dear " + custName + ",</p>"
		        + "<p>Thank you for registering with Merchant Corporation.</p>"
		        + "<p>Please install Merchant App from Play Store and use the below credentials:</p>"
		        + "<p>"
		        + "<b>Username:</b> " + customerEmail + "<br>"
		        + "<b>Temporary Password:</b> " + customerPass
		        + "</p>"
		        + "<p>For your security, please log in as soon as possible and change your password.</p>"
		        + "<p>If you have any questions or need assistance, feel free to contact us.</p>"
		        + "<p>Best regards,<br>"
		        + "Merchant Corporation</p>"
		        + "</body></html>";
		request.setHtmlContent(htmlContent);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000); // 10 seconds
		factory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("api-key", brevoApiKey);
        
        System.out.println("Before Http Call!!!!!!!!!!!");
        HttpEntity<BrevoEmailReq> emailRequest = new HttpEntity<>(request, headers);
        System.out.println("After Http Entity!!!!!!!!!!!");
        ResponseEntity<String> response = restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                emailRequest,
                String.class
        );
        System.out.println("After Http Call!!!!!!!!!!!");
        
        System.out.println(response.getBody());
        return CompletableFuture.completedFuture(null);
    }
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendOtpEmail(String userEmail,String otpUser) {
        // Simulate email sending process (e.g., calling an email service)
        
        BrevoEmailReq request = new BrevoEmailReq();
		EmailSender sender = new EmailSender();
		sender.setName("Merchant App");
		sender.setEmail("tchandru369@gmail.com");

		EmailReceipent recipient = new EmailReceipent();
		recipient.setEmail(userEmail);
		recipient.setName("User");
		
//		EmailAttachement attachement = new EmailAttachement();
//		attachement.setName(fileName);
//		attachement.setContent(encodedString);

		request.setSender(sender);
		request.setTo(List.of(recipient));
		request.setSubject("OTP for Password Reset");
		String htmlContent = "<html><body>"
	            + "<h3>Your OTP for password reset</h3>"
	            + "<p><b>" + otpUser + "</b></p>"
	            + "<p>This OTP is valid for 5 minutes.</p>"
	            + "</body></html>";
		request.setHtmlContent(htmlContent);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000); // 10 seconds
		factory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("api-key", brevoApiKey);
        
        System.out.println("Before Http Call!!!!!!!!!!!");
        HttpEntity<BrevoEmailReq> emailRequest = new HttpEntity<>(request, headers);
        System.out.println("After Http Entity!!!!!!!!!!!");
        ResponseEntity<String> response = restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                emailRequest,
                String.class
        );
        System.out.println("After Http Call!!!!!!!!!!!");
        
        System.out.println(response.getBody());
        return CompletableFuture.completedFuture(null);
    }
	
	@Async("taskExecutor")
    public CompletableFuture<Void> balancePaidAcknowledge(OrderRequestDto orderRequest) {
        // Simulate email sending process (e.g., calling an email service)
        
        BrevoEmailReq request = new BrevoEmailReq();
		EmailSender sender = new EmailSender();
		sender.setName("Merchant App");
		sender.setEmail("tchandru369@gmail.com");

		EmailReceipent recipient = new EmailReceipent();
		recipient.setEmail(orderRequest.getOrderCustEmailId());
		recipient.setName("User");
	
//		EmailAttachement attachement = new EmailAttachement();
//		attachement.setName(fileName);
//		attachement.setContent(encodedString);

		request.setSender(sender);
		request.setTo(List.of(recipient));
		request.setSubject("Order Balance Notification");
		String htmlContent = "<html>\r\n"
		        + "<body>\r\n"
		        + "    <p>Dear <b>"+orderRequest.getOrderCustName()+"</b>,</p>\r\n"
		        + "    <p>Thank you for settling the balance amount ₹<b>"+orderRequest.getOrderFinalAmtPaid()+"</b> for order <b>"+orderRequest.getOrderRefId()+"</b>, which was placed on <b>"+orderRequest.getOrderCustCrtdDate()+"</b>.</p>\r\n"
		        + "    <p>We kindly request you to clear any pending balance from your end to the Dealer as soon as possible. This will help avoid any inconvenience when placing future orders.</p>\r\n"
		        + "    <p>Note: If the balance amount is greater than ₹1,000, it should be settled within 3 days. For other amounts, the settlement period is up to 7 days. This is to ensure smooth order processing and avoid any potential order blocking.</p>\r\n"
		        + "    <p>We appreciate your cooperation and look forward to your continued business.</p>\r\n"
		        + "    <p>Best regards,<br>Merchant Corporation</p>\r\n"
		        + "</body>\r\n"
		        + "</html>";
		
		request.setHtmlContent(htmlContent);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000); // 10 seconds
		factory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("api-key", brevoApiKey);
        
        System.out.println("Before Http Call!!!!!!!!!!!");
        HttpEntity<BrevoEmailReq> emailRequest = new HttpEntity<>(request, headers);
        System.out.println("After Http Entity!!!!!!!!!!!");
        ResponseEntity<String> response = restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                emailRequest,
                String.class
        );
        System.out.println("After Http Call!!!!!!!!!!!");
        
        System.out.println(response.getBody());
        return CompletableFuture.completedFuture(null);
    }
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendConfmCustPymtEmail(String customerEmail,String custName, String productOwner) {
        // Simulate email sending process (e.g., calling an email service)
        try {
        	System.out.println("Inside Email service...................");
        	 LocalDate currentDate = LocalDate.now();
        	 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        	 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12-hour format with AM/PM
        	 LocalDateTime currentDateTime = LocalDateTime.now(); // Get current date and time

        	 String formattedDate = currentDateTime.format(dateFormatter); // Format the date
        	 String formattedTime = currentDateTime.format(timeFormatter); // Format the time	
 		try { 
 			MimeMessage mimeMessage = mailSender.createMimeMessage();
 			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
 			 SimpleMailMessage message = new SimpleMailMessage();
 			 String emailBody = "Dear " + custName + ",\r\n\r\n"
	                   + "Date: " + formattedDate + "\r\n"
	                   + "Time: " + formattedTime + "\r\n\r\n" // Add the time here
	                   + "Thanks for making payment to the dealer. You will receive the acknowledgement once your payment has been verified by your respective dealer.\r\n\r\n"
	                   + "If you have any questions or need assistance, feel free to contact us.\r\n\r\n"
	                   + "Best regards,\r\n"
	                   + "Merchant Corporation";
 			helper.setFrom("chanper369@gmail.com");
 			helper.setTo(customerEmail);
 			helper.setSubject("Acknowledgement of Payment to Dealer");
 			helper.setText(emailBody);
 			
 			
 			//helper.addAttachment(fileName, new ByteArrayDataSource(pdfBytes, "application/pdf"));
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
	
	@Async("taskExecutor")
    public CompletableFuture<Void> sendConfmCustPymtEmailBrevo(String customerEmail,String custName, String productOwner) {
        // Simulate email sending process (e.g., calling an email service)
		LocalDate currentDate = LocalDate.now();
   	 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
   	 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12-hour format with AM/PM
   	 LocalDateTime currentDateTime = LocalDateTime.now(); // Get current date and time

   	 String formattedDate = currentDateTime.format(dateFormatter); // Format the date
   	 String formattedTime = currentDateTime.format(timeFormatter); // Format the time	
        BrevoEmailReq request = new BrevoEmailReq();
		EmailSender sender = new EmailSender();
		sender.setName("Merchant App");
		sender.setEmail("tchandru369@gmail.com");

		EmailReceipent recipient = new EmailReceipent();
		recipient.setEmail(customerEmail);
		recipient.setName("Customer");
		
//		EmailAttachement attachement = new EmailAttachement();
//		attachement.setName(fileName);
//		attachement.setContent(encodedString);

		request.setSender(sender);
		request.setTo(List.of(recipient));
		request.setSubject("Acknowledgement of Payment to Dealer");
		String htmlContent = "<html><body>"
		        + "<p>Dear " + custName + ",</p>"
		        + "<p><b>Date:</b> " + formattedDate + "<br>"
		        + "<b>Time:</b> " + formattedTime + "</p>"
		        + "<p>Thanks for making payment to the dealer. You will receive the acknowledgement once your payment has been verified by your respective dealer.</p>"
		        + "<p>If you have any questions or need assistance, feel free to contact us.</p>"
		        + "<p>Best regards,<br>"
		        + "Merchant Corporation</p>"
		        + "</body></html>";
		request.setHtmlContent(htmlContent);
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000); // 10 seconds
		factory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("api-key", brevoApiKey);
        
        System.out.println("Before Http Call!!!!!!!!!!!");
        HttpEntity<BrevoEmailReq> emailRequest = new HttpEntity<>(request, headers);
        System.out.println("After Http Entity!!!!!!!!!!!");
        ResponseEntity<String> response = restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                emailRequest,
                String.class
        );
        System.out.println("After Http Call!!!!!!!!!!!");
        
        System.out.println(response.getBody());
        return CompletableFuture.completedFuture(null);
    }
	
//	@Async
//    public CompletableFuture<Void> sendEmailDup(String customerEmail, String productOwner,String fileName) {
//        // Simulate email sending process (e.g., calling an email service)
//        try {
//        	 LocalDate currentDate = LocalDate.now();
// 	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
// 	        String formattedDate = currentDate.format(formatter);
// 	        System.out.println("Formatted Date: " + formattedDate);
// 		try { 
// 			MimeMessage mimeMessage = mailSender.createMimeMessage();
// 			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
// 			 SimpleMailMessage message = new SimpleMailMessage();
// 			helper.setFrom("chanper369@gmail.com");
// 			helper.setTo(customerEmail);
// 			helper.setSubject("Shopping at Merchant App "+formattedDate);
// 			helper.setText("Dear Customer,\r\n"
// 					+ "\r\n"
// 					+ "I hope this email finds you well.\r\n"
// 					+ "\r\n"
// 					+ "Please find the attached invoice for your recent purchase with Merchant App. The details of your bill are included in the attached PDF document.\r\n"
// 					+ "\r\n"
// 					+ "If you have any questions or concerns regarding the invoice, please do not hesitate to contact us.\r\n"
// 					+ "\r\n"
// 					+ "Thank you for your business, and we look forward to serving you again.\r\n"
// 					+ "\r\n"
// 					+ "Best regards,");
// 			//byte[] fileBytes = downloadFileFromGCS(fileName);
//
// 	        // Attach the PDF file
// 	        InputStream inputStream = new ByteArrayInputStream(fileBytes);
// 	        
// 	       DataSource dataSource = new ByteArrayDataSource(inputStream, "application/pdf");
// 			
// 			helper.addAttachment(fileName,dataSource );
// 			
// 			
// 			 mailSender.send(mimeMessage);
// 			//deleteFileFromGCS("crypto-moon-450715-c2.appspot.com",fileName);
// 		}catch(Exception e){
// 			System.out.println(e.getMessage());
// 		}
//            Thread.sleep(5000); // Simulating a delay for the email sending
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return CompletableFuture.completedFuture(null);
//    }
	
//	 private byte[] downloadFileFromGCS(String fileName) throws IOException {
//	        // Initialize the Storage client
//	        Storage storage = StorageOptions.getDefaultInstance().getService();
//	        
//	        // Replace with your bucket name
//	        String bucketName = "crypto-moon-450715-c2.appspot.com";
//	        
//	        // Get the blob (file) from Cloud Storage
//	        BlobId blobId = BlobId.of(bucketName, fileName);
//	        Blob blob = storage.get(blobId);
//	        
//	        // Check if the file exists
//	        if (blob == null) {
//	            throw new IOException("File not found in GCS: " + fileName);
//	        }
//
//	        // Download the file as a byte array
//	        return blob.getContent();
//	    }
//	 
//	 public void deleteFileFromGCS(String bucketName, String fileName) {
//	        // Initialize the Storage client
//	        Storage storage = StorageOptions.getDefaultInstance().getService();
//
//	        // Get the BlobId for the file
//	        BlobId blobId = BlobId.of(bucketName, fileName);
//
//	        // Delete the file from Cloud Storage
//	        boolean deleted = storage.delete(blobId);
//
//	        if (deleted) {
//	            System.out.println("File " + fileName + " deleted successfully.");
//	        } else {
//	            System.out.println("Failed to delete file " + fileName);
//	        }
//	    }

}
