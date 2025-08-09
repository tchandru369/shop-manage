package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.CustDetailSummary;
import com.merchant.management.dto.UserCustBalDto;
import com.merchant.management.dto.UserCustDetailsReq;
import com.merchant.management.dto.UserCustDetailsRes;
import com.merchant.management.dto.UserCustLastTransaction;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.entity.ShopCustBalanceDetails;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.repository.OrderReqRepo;
import com.merchant.management.repository.ShopCustBlnRepo;
import com.merchant.management.repository.ShopCustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderReqRepo orderReqRepo;
	
	@Autowired
	private ShopCustomerRepo shopCustRepo;
	
	@Autowired
	private ShopCustBlnRepo shopCustBlnRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OrderService orderService;

	private Object UserCustDetailsReq;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ShopCustomerRepo shopCustRepo,OrderReqRepo orderReqRepo,ShopCustBlnRepo shopCustBlnRepo) {
		this.customerRepository = customerRepository;
		this.shopCustRepo = shopCustRepo;
		this.orderReqRepo = orderReqRepo;
		this.shopCustBlnRepo = shopCustBlnRepo;
	}
 
//	public ResponseEntity saveCustomerDetails( CustomerDetails customerDetails) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        MerchantDetails merchantDetails = new MerchantDetails();
//        
//        customerDetails.setCustomerCreationTime(timestamp.toString());
//        long nanoTime = System.nanoTime();
//        long uniqueNumber = nanoTime % 10_000_000;
//        customerDetails.setCustomerUniqueNo(String.valueOf(uniqueNumber));
//        customerDetails.setCustomerType("N");
//        String defaultPassword = "customer@123";
//        customerDetails.setCustomerPassword(passwordEncoder.encode(defaultPassword));
//        merchantDetails.setMerchantAddress(customerDetails.getCustomerAddress());
//        merchantDetails.setMerchantEmail(customerDetails.getCustomerEmail());
//        merchantDetails.setMerchantPassword(passwordEncoder.encode(defaultPassword));
//        merchantDetails.setmerchantPhoneNumber(customerDetails.getCustomerPhoneNo());
//        merchantDetails.setMerchantSignUpTime(timestamp.toString());
//        merchantDetails.setmerchantUserName(customerDetails.getCustomerName());
//        merchantDetails.setMerchantUserType("CUST");
//        merchantDetails.setRole(Role.Cust);
//        merchantRepository.save(merchantDetails);
//        
//		return customerRepository.save(customerDetails);
//	}
	
	public ResponseEntity<CustomerDetailsRes> saveShopCustomer( ShopCustomerDetails customerDetails) {
		 CustomerDetailsRes custRes = new CustomerDetailsRes();
		int merchantCount = merchantRepository.getMerchantCount(customerDetails.getCustEmailId());
		if(merchantCount!=0) {
			custRes.setResponse("success");
			custRes.setErrorMsg("customer already present as Owner");
			custRes.setErrorCode("1");
		}else {
			int custEmailCount = shopCustRepo.getCustMailCount(customerDetails.getCustEmailId());
			if(custEmailCount!=0) {
				custRes.setResponse("success");
				custRes.setErrorMsg("Email already registered as Customer");
				custRes.setErrorCode("1");
			}else {
				String custPhEmail = shopCustRepo.getCustPhoneCount(customerDetails.getCustPhoneNo());
				if(custPhEmail!=null) {
					custRes.setResponse("success");
					custRes.setErrorMsg("Phone already registered with "+custPhEmail);
					custRes.setErrorCode("1");
				}else {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			        MerchantDetails merchantDetails = new MerchantDetails();
					LocalDate currentDate = LocalDate.now();
				     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				     String formattedDate = currentDate.format(formatter);
			        customerDetails.setCustCreatedDate(formattedDate);
			        long nanoTime = System.nanoTime();
			        long uniqueNumber = nanoTime % 10_000_000;
			        customerDetails.setCustLive("1");
			        customerDetails.setCustBalanceFlg("1");
			        String defaultPassword = "customer@123";
			        System.out.println("Customer Country : "+customerDetails.getCustCountry());
			        customerDetails.setCustPassword(passwordEncoder.encode(defaultPassword));
			        merchantDetails.setMerchantAddress(customerDetails.getCustAddress());
			        merchantDetails.setMerchantEmail(customerDetails.getCustEmailId());
			        merchantDetails.setMerchantPassword(passwordEncoder.encode(defaultPassword));
			        merchantDetails.setmerchantPhoneNumber(customerDetails.getCustPhoneNo());
			        merchantDetails.setMerchantSignUpTime(timestamp.toString());
			        merchantDetails.setmerchantUserName(customerDetails.getCustName());
			        merchantDetails.setMerchantUserType("CUSTOMER");
			        merchantDetails.setRole(Role.Cust);
			        merchantRepository.save(merchantDetails);
			        shopCustRepo.save(customerDetails);
			        emailService.sendCustomerEmail(customerDetails.getCustEmailId(), customerDetails.getCustName(),customerDetails.getCustOwmerDetails(), defaultPassword);
			        custRes.setErrorCode("0");
			        custRes.setResponse("success");
			        custRes.setErrorMsg("success");
				}
				
			}
			
		}
		 
		return ResponseEntity.ok(custRes);
	}
	
	public ShopCustomerDetails getCustomerDetailsByPhNo(String customerPhNo) {
		ShopCustomerDetails customerDetails = shopCustRepo.getShopCustDetailsByPhNo(customerPhNo);
		return customerDetails;
	}
	
	public List<UserCustDetailsRes> getCustomerDetailsByOwnerE(String ownerEmail) {
		
		List<UserCustDetailsRes> responseList = new ArrayList<UserCustDetailsRes>();
		List<ShopCustomerDetails> customerDetails = shopCustRepo.getShopCustDtlsByOwnerE(ownerEmail);
		
		for(int i=0;i<customerDetails.size();i++) {
			UserCustDetailsRes detailRes = new UserCustDetailsRes();
			detailRes.setCustBalanceFlg(customerDetails.get(i).getCustBalanceFlg());
			detailRes.setCustEmail(customerDetails.get(i).getCustEmailId());
			detailRes.setCustName(customerDetails.get(i).getCustName());
			detailRes.setCustPhoneNo(customerDetails.get(i).getCustPhoneNo());
			detailRes.setCustType(customerDetails.get(i).getCustType());
			responseList.add(detailRes);
		}
		
		return responseList;
	}
	
public CustDetailSummary getCustDetailSummary(String ownerEmail,String custEmail) {
		
        CustDetailSummary custSummary = new CustDetailSummary();
        List<UserCustBalDto> balanceList = new ArrayList<UserCustBalDto>();
        List<UserCustLastTransaction> tranList = new ArrayList<UserCustLastTransaction>();
        List<OrderRequestDetails> orderReqList = orderReqRepo.getCustOrderTranDetails(ownerEmail, custEmail);
        ShopCustomerDetails shopCustomer = shopCustRepo.getShopCustDtlsEmailPh(custEmail);
		List<ShopCustBalanceDetails> customerBlDetails = shopCustBlnRepo.getCustBalList(ownerEmail, custEmail);
		for(int i=0;i<orderReqList.size();i++) {
			UserCustLastTransaction orderRequest = new UserCustLastTransaction();
			orderRequest.setOrderBillPayDate(orderReqList.get(i).getOrderBillPayDate());
			orderRequest.setOrderPlacedDate(orderReqList.get(i).getOrderPlacedDate());
			orderRequest.setOrderProdTotalAmt(orderReqList.get(i).getOrderProdTotalAmt());
			orderRequest.setOrderReqStatus(orderReqList.get(i).getOrderRequestStatus());
			tranList.add(orderRequest);
		}
		for(int i=0;i<customerBlDetails.size();i++) {
			UserCustBalDto custBalance = new UserCustBalDto();
			custBalance.setCustBalActAmt(customerBlDetails.get(i).getCustBalActAmt());
			custBalance.setCustBalAmt(customerBlDetails.get(i).getCustBalAmt());
			custBalance.setCustBalDate(customerBlDetails.get(i).getCustBalDate());
			custBalance.setCustBalOrderStatus(customerBlDetails.get(i).getCustBalStatus());
			custBalance.setCustBalPaidAmt(customerBlDetails.get(i).getCustBalPaidAmt());
			balanceList.add(custBalance);
		}
		custSummary.setCustAddress(shopCustomer.getCustAddress());
		custSummary.setCustCity(shopCustomer.getCustCity());
		custSummary.setCustCountry(shopCustomer.getCustCountry());
		custSummary.setCustCreatedDate(shopCustomer.getCustCreatedDate());
		custSummary.setCustDob(shopCustomer.getCustDob());
		custSummary.setCustGender(shopCustomer.getCustGender());
		custSummary.setCustModifiedDate(shopCustomer.getCustModifiedDate());
		custSummary.setCustPincode(shopCustomer.getCustPinCode());
		custSummary.setCustState(shopCustomer.getCustState());
		custSummary.setBalanceList(balanceList);
		custSummary.setTransactionList(tranList);

		return custSummary;
	}
	
	public ShopCustomerDetails getCustDtlsPhEmail(String custEmail) {
		ShopCustomerDetails customerDetails = shopCustRepo.getShopCustDtlsEmailPh(custEmail);
		return customerDetails;
	}
	
	
	public String getFileName() {

        String fileName ="D:\\POC_API_DEVELOPMENT\\DATAFILE152.txt";
   
        return fileName;
    }
}
