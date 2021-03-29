package com.paysafe.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysafe.dao.PaySafeDao;
import com.paysafe.model.PaymentRequest;
import com.paysafe.model.SingleUserTokenRequest;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Component
public class PaySafeServiceImpl implements PaySafeService{

	@Autowired
	PaySafeDao paysafeDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getSingleUserToken(SingleUserTokenRequest tokenReq) {
		JSONObject res = new JSONObject();
		tokenReq = paysafeDao.checkCustomerExists(tokenReq);
		if(tokenReq.getMerchantCustomerId()!=null)
		{
			res = generateSingleUserToken(tokenReq.getId());
		}
		else
		{
			tokenReq.setMerchantCustomerId(new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()));
			res = saveCustomerAPI(tokenReq);
		}
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject saveCustomerAPI(SingleUserTokenRequest tokenReq)
	{
		JSONObject result = new JSONObject();
		String url = "https://api.test.paysafe.com/paymenthub/v1/customers";
		RestTemplate rest = new RestTemplate();
		HttpEntity<SingleUserTokenRequest> entity = new HttpEntity<>(tokenReq, getHeaders());
		String response = rest.postForObject(url, entity, String.class); 
		System.out.println(response);
		JSONParser parser = new JSONParser();  
		try {
			JSONObject json = (JSONObject) parser.parse(response.toString());
			tokenReq.setId(json.get("id").toString());
			tokenReq.setPaymentToken(json.get("paymentToken").toString());
			if(paysafeDao.createCustomer(tokenReq))
			{
				result = generateSingleUserToken(tokenReq.getId());	
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}  

		
		return result;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject makePayment(PaymentRequest paymentRequest) {
		JSONObject result = new JSONObject();
		String url = "https://api.test.paysafe.com/paymenthub/v1/payments";
		RestTemplate rest = new RestTemplate();
		
		HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, getHeaders());
		String response = rest.postForObject(url, entity, String.class); 
		
		
		System.out.println(response);
		JSONParser parser = new JSONParser();  
		try {
			JSONObject json = (JSONObject) parser.parse(response.toString());
			result.put("data", json.get("status"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result; 
	}

	public HttpHeaders getHeaders()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4");
		headers.set("Content-Type", "application/json");
		headers.set("Simulator", "External");
		return headers;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject generateSingleUserToken(String cutomerId)
	{
		JSONObject result = new JSONObject();
		JSONObject request = new JSONObject();
		request.put("merchantRefNum", "MERREF"+new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()));
		List<String> pyTyp = new ArrayList<String>();
		pyTyp.add("CARD");
		request.put("paymentTypes",pyTyp);
		System.out.println(request);
		String url = "https://api.test.paysafe.com/paymenthub/v1/customers/"+cutomerId+"/singleusecustomertokens";
		RestTemplate rest = new RestTemplate();
		HttpEntity<JSONObject> entity = new HttpEntity<>(request, getHeaders());
		String response = rest.postForObject(url, entity, String.class); 
		System.out.println(response);
		JSONParser parser = new JSONParser();  
		try {
			JSONObject json = (JSONObject) parser.parse(response.toString());
			result.put("token", json.get("singleUseCustomerToken"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}  

		
		return result;
		
	}
}
