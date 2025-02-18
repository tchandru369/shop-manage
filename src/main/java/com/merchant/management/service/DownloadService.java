package com.merchant.management.service;

import org.springframework.stereotype.Service;

@Service
public class DownloadService {
 
	
	public String getFileName() {

        String fileName ="D:\\POC_API_DEVELOPMENT\\DATAFILE159.zip" ;
   
        return fileName;
    }
}
