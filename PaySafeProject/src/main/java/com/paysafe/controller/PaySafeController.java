package com.paysafe.controller;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paysafe.model.PaymentRequest;
import com.paysafe.model.SingleUserTokenRequest;
import com.paysafe.service.PaySafeService;



@RestController
public class PaySafeController {

	@Autowired
	PaySafeService paySafeService;
	
	@PostMapping("/api/singleusertoken")
	public JSONObject getSingleUserToken(@RequestBody SingleUserTokenRequest userTokenRequest) {
		return paySafeService.getSingleUserToken(userTokenRequest);
		
	}
	
	@PostMapping("/api/payment")
	public JSONObject makePayment(@RequestBody PaymentRequest paymentRequest) {
		return paySafeService.makePayment(paymentRequest);
		
	}
}
