package com.merchant.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.merchant.management.repository.OrderReqRepo;
import com.merchant.management.repository.ShopCustBlnRepo;

@Component
public class ScheduledTask {
	
	@Autowired
	private OrderReqRepo orderReqRepo;
	
	@Autowired
	private ShopCustBlnRepo shopBlnRepo;
	
	public ScheduledTask(OrderReqRepo orderReqRepo,ShopCustBlnRepo shopBlnRepo) {
		this.orderReqRepo = orderReqRepo;
		this.shopBlnRepo = shopBlnRepo;
	}
	
	
	 @Scheduled(fixedRate = 21600000) // 3 hours in milliseconds
	    public void performRemUpdateTask() {
	        // Your scheduled task logic goes here
		 shopBlnRepo.updateCustRemSentFlg();
		 orderReqRepo.deleteRemSentTable();
	      
	    }

}
