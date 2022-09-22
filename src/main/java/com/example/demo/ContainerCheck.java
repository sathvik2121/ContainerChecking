package com.example.demo;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import java.security.InvalidKeyException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import com.itextpdf.text.DocumentException;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

@RestController
@SpringBootApplication
public class ContainerCheck {

	public static void main(String[] args) throws InvalidKeyException, DocumentException, URISyntaxException, StorageException, IOException, ParseException   {
		ContainerCheck ob1=new ContainerCheck();
		String message2=ob1.run();
		System.out.println(message2);
		SpringApplication.run(ContainerCheck.class, args);
	}

	@GetMapping("/")
	
	public String method2()
	{
		
		return "welcome to ContainerCheck";
	}
	
	@GetMapping("/testing")
	
	public String run() throws InvalidKeyException, URISyntaxException, StorageException, MalformedURLException, UnsupportedEncodingException {
		
	
		 final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=pocfiledemoaccess;AccountKey=GY0kWJV+ik5mNByEavFYamMDMu5LecAHNK0VmiD6VPQka7s/4OrrATcBol4jOIxq33ID3yChL0W8+AStDb8VCA==;EndpointSuffix=core.windows.net";
		 CloudStorageAccount storageAccount;
		 CloudBlobClient blobClient = null;
		 storageAccount = CloudStorageAccount.parse(storageConnectionString);
		 blobClient = storageAccount.createCloudBlobClient();
		 CloudBlobContainer container=blobClient.getContainerReference("xmlcontainer");
		 List<String> list=new ArrayList<String>();
		 for (ListBlobItem blobItem : container.listBlobs()) {
					URL url=blobItem.getUri().toURL();
					String s=FilenameUtils.getName(url.getPath());
	    		    String result = URLDecoder.decode(s,"utf-8");
	    			list.add(result);
	    				}
				 if(list.size()==0)
				   return "empty";
				 else 
				   return "not empty";
				
	}
}