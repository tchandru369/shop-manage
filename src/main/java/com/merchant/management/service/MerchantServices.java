package com.merchant.management.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.dto.MerchantReg;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.OwnerPaymtDetails;
import com.merchant.management.entity.Role;
import com.merchant.management.entity.ShopCustomerDetails;
import com.merchant.management.repository.MerchantRepository;
import com.merchant.management.repository.OwnerPaymtDetailRepo;
import com.merchant.management.repository.ShopCustomerRepo;
import com.merchant.management.security.JwtService;

@Service
public class MerchantServices {

	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authentication;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ShopCustomerRepo shopCustRepo;
	@Autowired
	private OwnerPaymtDetailRepo pymntDetailRepo;
	

	
	public ResponseEntity<MerchantDetailRes> saveMerchantDetails( MerchantReg merchant) {
		
		MerchantDetailRes merchantRes = new MerchantDetailRes();
		MerchantDetails merchantDetailMain = new MerchantDetails();
		ShopCustomerDetails newCustDetails = new ShopCustomerDetails();
		int merchantCount = merchantRepository.getMerchantCount(merchant.getRegMerchantEmail());
		if(merchantCount!=0) {
			merchantRes.setResponse("success");
			merchantRes.setErrorMsg("customer already present as Owner");
			merchantRes.setErrorCode("1");
		}else {
			int custEmailCount = shopCustRepo.getCustMailCount(merchant.getRegMerchantEmail());
			if(custEmailCount != 0) {
				merchantRes.setResponse("success");
				merchantRes.setErrorMsg("Email already registered as Customer");
				merchantRes.setErrorCode("1");
			}else {
				String customerEmail = shopCustRepo.getCustPhoneCount(merchant.getRegMerchantPhNo());
				if(customerEmail!=null) {
					merchantRes.setResponse("success");
					merchantRes.setErrorMsg("Phone number already registered as Customer with "+customerEmail);
					merchantRes.setErrorCode("1");
				}else {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					Long seq = jdbcTemplate.queryForObject("SELECT nextval('customer_ref_seq')", Long.class);

			        // 3️⃣ Generate Ref ID with leading zeros
			        String customerRefId = "CUST-" + String.format("%012d", seq);
					merchantDetailMain.setMerchantSignUpTime(timestamp.toString());
					merchantDetailMain.setMerchantPassword(passwordEncoder.encode(merchant.getRegMerchantPass()));
					
					merchantDetailMain.setMerchantAddress(merchant.getRegMerchantAddrs());
					merchantDetailMain.setMerchantEmail(merchant.getRegMerchantEmail());
					merchantDetailMain.setMerchantRefId(customerRefId);
					merchantDetailMain.setMerchantUserName(merchant.getRegMerchantName());
					merchantDetailMain.setMerchantPhoneNumber(merchant.getRegMerchantPhNo());
					if(merchant.getRegMerchantNature().equals("D")) {
						merchantDetailMain.setRole(Role.User);
						newCustDetails.setCustType("D");
						merchantDetailMain.setMerchantUserType("USER");
						newCustDetails.setCustOwnerRefId("SELF");
					}else if(merchant.getRegMerchantNature().equals("S")) {
					merchantDetailMain.setRole(Role.Cust);
					newCustDetails.setCustType("S");
					newCustDetails.setCustOwnerRefId("CHOOSE");
					merchantDetailMain.setMerchantUserType("CUSTOMER");
					}else if(merchant.getRegMerchantNature().equals("I")) {
						merchantDetailMain.setRole(Role.Cust);
						newCustDetails.setCustType("I");
						newCustDetails.setCustOwnerRefId("CHOOSE");
						merchantDetailMain.setMerchantUserType("CUSTOMER");
					}
					
					newCustDetails.setCustCreatedDate(timestamp.toString());
					newCustDetails.setCustAddress(merchant.getRegMerchantAddrs());
					newCustDetails.setCustEmailId(merchant.getRegMerchantEmail());
					newCustDetails.setCustName(merchant.getRegMerchantName());
					newCustDetails.setCustPassword(passwordEncoder.encode(merchant.getRegMerchantPass()));
					newCustDetails.setCustPhoneNo(merchant.getRegMerchantPhNo());
					newCustDetails.setCustPanNo(merchant.getRegMerchantPan());
					newCustDetails.setCustBalanceFlg("N");
					newCustDetails.setCustCity(merchant.getRegMerchantCty());
					newCustDetails.setCustCountry(merchant.getRegMerchantCtry());
					newCustDetails.setShopCustRefId(customerRefId);
					newCustDetails.setCustDob(merchant.getRegMerchantDob());
					newCustDetails.setCustGender(merchant.getRegMerchantGen());
					newCustDetails.setCustLive("1");
					newCustDetails.setCustPinCode(merchant.getRegMerchantPin());
					newCustDetails.setCustState(merchant.getRegMerchantState());
					merchantRepository.save(merchantDetailMain);
					shopCustRepo.save(newCustDetails);
					merchantRes.setResponse("success");
					merchantRes.setErrorCode("0");
					merchantRes.setErrorMsg("Registered successfully, Please Login");
					
					
				}
				
			}
			
			
		}
		
		return ResponseEntity.ok(merchantRes);
	}
	
public BillingEntityRes updatePaymentDetails(String dealersUpi, String ownerRefId,String ownerName, String ownerPh) {
	
	 OwnerPaymtDetails ownerPymtDetails =  new OwnerPaymtDetails();
	 BillingEntityRes billingEntityRes = new BillingEntityRes();
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	 int count = pymntDetailRepo.getPaymtDtlByEmail(ownerRefId,ownerPh);
	 if(count == 0) {
		 ownerPymtDetails.setPymtUpiId(dealersUpi);
		 ownerPymtDetails.setPymtOwnerRefId(ownerRefId);
		 ownerPymtDetails.setPymtLive("1");
		 ownerPymtDetails.setPymtAddedOn(timestamp.toString());
		 ownerPymtDetails.setPymtOwnerName(ownerName);
		 ownerPymtDetails.setPymtPhNumber(ownerPh);
		 pymntDetailRepo.save(ownerPymtDetails);
		 billingEntityRes.setErrorCode("0");
		 billingEntityRes.setErrorMsg("success");
		 billingEntityRes.setResponse("success");
	 }else {
		 
		 pymntDetailRepo.updateUPIPymtDetails(ownerRefId, dealersUpi, ownerPh,timestamp.toString());
		 billingEntityRes.setErrorCode("0");
		 billingEntityRes.setErrorMsg("success");
		 billingEntityRes.setResponse("UPI ID has been updated");
	 }
	 
	 return billingEntityRes;
		
	}

public OwnerPaymtDetails getDealerPymtDetails(String ownerEmail) {
	
	 OwnerPaymtDetails ownerPymtDetail = pymntDetailRepo.getDealerPymtDetails(ownerEmail);
	 return ownerPymtDetail;
		
	}
	
	public String getMerchantPswdByMail(String merchantMail) {
		String password = merchantRepository.findPasswordByEmail(merchantMail);
		return password;
	}

	public MerchantDetailRes authenticate(MerchantDetails merchant) {
		// TODO Auto-generated method stub
		MerchantDetailRes response = new MerchantDetailRes();
		String merchantDetails = merchantRepository.getMerchantDetails(merchant.getMerchantEmail());
		authentication.authenticate(
				new UsernamePasswordAuthenticationToken(merchant.getMerchantEmail(), merchant.getMerchantPassword())
				);
		var user = merchantRepository.findBymerchantEmail(merchant.getMerchantEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		response.setResponse("success");
		response.setToken(jwtToken);
		response.setUserName(merchantDetails);
		return response;
	}
	
	
public MerchantDetails getMerchantService(String email) {
		return merchantRepository.getMerchantProfileDetails(email);
	}

public MerchantDetails getMerchantServiceDetails(String email) {
	return merchantRepository.getMerchantDetailsByRefId(email);
}

//	public MerchantDetails authMerchantPswdByMail(MerchantDetails merchant) {
//		
//		var user = merchantRepository.findBymerchantEmail(merchant.getMerchantEmail());
//		
//	
//		// TODO Auto-generated method stub
//		
//	}
}
