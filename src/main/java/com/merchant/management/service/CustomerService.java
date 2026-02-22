package com.merchant.management.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.management.dto.BillingHistoryRes;
import com.merchant.management.dto.CustDetailSummary;
import com.merchant.management.dto.CustOverAllPymtStatusRes;
import com.merchant.management.dto.CustProdPriceCount;
import com.merchant.management.dto.CustomerGraphEntityRes;
import com.merchant.management.dto.MilkOrderList;
import com.merchant.management.dto.OrderRequestDto;
import com.merchant.management.dto.UserCustBalDto;
import com.merchant.management.dto.UserCustDetailsReq;
import com.merchant.management.dto.UserCustDetailsRes;
import com.merchant.management.dto.UserCustLastTransaction;
import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ComProdDtls;
import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.CustOrderPlacedDtls;
import com.merchant.management.entity.CustOverallPymtStatus;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.entity.ShopCustBalanceDetails;
import com.merchant.management.entity.ShopCustOrderDetails;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.CustOrdPlDtlsListRepo;
import com.merchant.management.repository.CustOrderPlDtlRepo;
import com.merchant.management.repository.CustOverallPymtStatusRepo;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.repository.OrderReqDetailRepo;
import com.merchant.management.repository.OrderReqRepo;
import com.merchant.management.repository.ShopCustBlnRepo;
import com.merchant.management.repository.ShopCustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustOverallPymtStatusRepo custOverallPymtStatusRepo;
	
	@Autowired
	private OrderReqRepo orderReqRepo;
	
	@Autowired
	private OrderReqDetailRepo orderReqDetailepo;
	
	@Autowired
	private ShopCustomerRepo shopCustRepo;
	
	@Autowired
	private ShopCustBlnRepo shopCustBlnRepo;
	
	@Autowired
	private CustOrderPlDtlRepo custPlacedRepo;
	
	@Autowired
	private CustOrdPlDtlsListRepo custPlacdOrderListRepo;
	
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
    private JdbcTemplate jdbcTemplate;
	
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
	
	public List<CustProdPriceCount> getCustProdCount(String custEmail){
		List<CustProdPriceCount> comProdDtls =
		        orderReqDetailepo.getMonthlyPriceProduct(custEmail).stream()
		            .map(row -> new CustProdPriceCount(
		                    ((String) row[0]).toString(),          // date
		                    ((Number) row[2]).doubleValue(),              // amount
		                    ((Number) row[3]).doubleValue(),
		                    ((Number) row[1]).intValue()))
		            .collect(Collectors.toList());
		 
		 return comProdDtls;
	 }
	
	public List<CustomerGraphEntityRes> getCustGraphEntity(String custEmail){
		List<CustomerGraphEntityRes> comProdDtls =
		        custOverallPymtStatusRepo.getCustGraphEntity(custEmail).stream()
		            .map(row -> new CustomerGraphEntityRes(
		                    ((java.sql.Date) row[0]).toString(),          // date
		                    ((Number) row[1]).doubleValue(),              // amount
		                    ((Number) row[2]).doubleValue(),              // balance
		                    ((Number) row[3]).longValue()                 // prod_qty
		            ))
		            .collect(Collectors.toList());
		 
		 return comProdDtls;
	 }
	
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
			        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					Long seq = jdbcTemplate.queryForObject("SELECT nextval('customer_ref_seq')", Long.class);

			        // 3️⃣ Generate Ref ID with leading zeros
			        String customerRefId = "CUST-" + String.format("%012d", seq);
			        merchantDetails.setMerchantSignUpTime(timestamp.toString());
			        customerDetails.setCustLive("1");
			        customerDetails.setCustBalanceFlg("1");
			        customerDetails.setShopCustRefId(customerRefId);
			        String defaultPassword = "customer@123";
			        System.out.println("Customer Country : "+customerDetails.getCustCountry());
			        customerDetails.setCustPassword(passwordEncoder.encode(defaultPassword));
			        merchantDetails.setMerchantAddress(customerDetails.getCustAddress());
			        merchantDetails.setMerchantEmail(customerDetails.getCustEmailId());
			        merchantDetails.setMerchantPassword(passwordEncoder.encode(defaultPassword));
			        merchantDetails.setMerchantPhoneNumber(customerDetails.getCustPhoneNo());
			        merchantDetails.setMerchantSignUpTime(timestamp.toString());
			        merchantDetails.setMerchantUserName(customerDetails.getCustName());
			        merchantDetails.setMerchantRefId(customerRefId);
			        merchantDetails.setMerchantUserType("CUSTOMER");
			        merchantDetails.setRole(Role.Cust);
			        merchantRepository.save(merchantDetails);
			        shopCustRepo.save(customerDetails);
			        emailService.sendCustomerEmail(customerDetails.getCustEmailId(), customerDetails.getCustName(),customerDetails.getCustOwnerRefId(), defaultPassword);
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
			detailRes.setCustRefId(customerDetails.get(i).getShopCustRefId());
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
	
	public List<OrderRequestDto> getLastOrderStatus(String custMail,String ownerEmail){
		List<OrderRequestDto> finalOrderList = new ArrayList<OrderRequestDto>();
		System.out.println("Customer Order Proccess List");
		List<CustOrderPlacedDtls> orderDetailList = custPlacedRepo.getCustOverallOrderStatus(custMail,ownerEmail);
		for(int i=0;i<orderDetailList.size();i++) {
			OrderRequestDto orderReqDto = new OrderRequestDto();
			orderReqDto.setOrderCustCrtdDate(orderDetailList.get(i).getCustOrderCrtdDate());
			orderReqDto.setOrderCustEmailId(orderDetailList.get(i).getCustOrderEmail());
			orderReqDto.setOrderCustName(orderDetailList.get(i).getCustOrderName());
			orderReqDto.setOrderOwnerRefId(orderDetailList.get(i).getCustOrderOwnerRefId());
			orderReqDto.setOrderCustPhoneNo(orderDetailList.get(i).getCustOrderPhNo());
			orderReqDto.setOrderReqStatus(orderDetailList.get(i).getCustOrderReqStatus());
			orderReqDto.setOrderCustType(orderDetailList.get(i).getCustOrderType());
			if(orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OP") || orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OPA") || orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OPR")) {
				orderReqDto.setOrderCustTotalPrice(0);
			}else {
				orderReqDto.setOrderCustTotalPrice(orderReqRepo.getOrderplcdTotalAmt(orderDetailList.get(i).getCustCustRefId(), orderDetailList.get(i).getCustOrderCrtdDate(),
						orderDetailList.get(i).getCustOrderOwnerRefId(), orderDetailList.get(i).getCustOrderRefId()));
			}
			List<CustOrderDtlList> custOrderList = custPlacdOrderListRepo.getCustOverAllOrderList(orderDetailList.get(i).getCustOrderCrtdDate(),orderDetailList.get(i).getCustOrderRefId());
			List<MilkOrderList> milkOrderList = new ArrayList<MilkOrderList>();
			for(int j=0;j<custOrderList.size();j++) {
				MilkOrderList milkList = new MilkOrderList();
				milkList.setOrderCustProdCmp(custOrderList.get(j).getCustOrderProdCmp());
				milkList.setOrderCustProdName(custOrderList.get(j).getCustOrderProdName());
				if(orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OP") || orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OPA") || orderDetailList.get(i).getCustOrderReqStatus().equalsIgnoreCase("OPR")) {
					milkList.setOrderCustProdPrice(0);
				}else {
					milkList.setOrderCustProdPrice(orderReqDetailepo.getOrderRequestAmountList(custOrderList.get(j).getCustOrderRefId(), custOrderList.get(j).getCustOrderCrdDate(),
							  custOrderList.get(j).getCustOrderProdCmp(), 
							 custOrderList.get(j).getCustOrderProdName(),custOrderList.get(j).getCustOrderProdType(), custOrderList.get(j).getCustOrderProdQty()));
				}
				//milkList.setOrderCustProdPrice(custOrderList.get(j));
				milkList.setOrderCustProdQty(custOrderList.get(j).getCustOrderProdQty());
				milkList.setOrderCustProdType(custOrderList.get(j).getCustOrderProdType());
				milkOrderList.add(milkList);
			}
			orderReqDto.setOrderList(milkOrderList);
			finalOrderList.add(orderReqDto);
		}
		return finalOrderList;
	}
	
public List<CustOverAllPymtStatusRes> getCustOverAllStatusList(String custEmail,String ownerEmail){
		
		List<CustOverAllPymtStatusRes> custOverAllStsList = new ArrayList<CustOverAllPymtStatusRes>();
		List<CustOverallPymtStatus> custOverPymtStatusList = custOverallPymtStatusRepo.getCustOverAllStatusList(custEmail, ownerEmail);
		
		for(int i=0;i<custOverPymtStatusList.size();i++) {
			CustOverAllPymtStatusRes custOverAllStatusRes = new CustOverAllPymtStatusRes();
			custOverAllStatusRes.setBillDate(custOverPymtStatusList.get(i).getBillDate());
			custOverAllStatusRes.setCustEmail(custOverPymtStatusList.get(i).getCustRefId());
			custOverAllStatusRes.setCustName(custOverPymtStatusList.get(i).getCustName());
			custOverAllStatusRes.setFullPaymentFlg(custOverPymtStatusList.get(i).getFullPaymentFlg());
			custOverAllStatusRes.setOwnerEmail(custOverPymtStatusList.get(i).getOwnerRefId());
			custOverAllStatusRes.setPaymentDate(custOverPymtStatusList.get(i).getBillDate());
			custOverAllStatusRes.setPymtAmountBal(custOverPymtStatusList.get(i).getPymtAmountBalance());
			custOverAllStatusRes.setTotalPymtAmt(custOverPymtStatusList.get(i).getPymtAmount());
			custOverAllStatusRes.setCustPaidAmt(custOverPymtStatusList.get(i).getCustPaidAmount());
			custOverAllStatusRes.setPymtOverAllStatus(custOverPymtStatusList.get(i).getOverAllOrderStatus());
			
			custOverAllStsList.add(custOverAllStatusRes);
		}
		return custOverAllStsList;
	}
}
