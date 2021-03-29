package com.paysafe.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.paysafe.model.PaymentRequest;
import com.paysafe.model.SingleUserTokenRequest;

@Service
public interface PaySafeService {

	public JSONObject getSingleUserToken(SingleUserTokenRequest tokenReq);

	public JSONObject makePayment(PaymentRequest paymentRequest);
}
