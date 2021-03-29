package com.paysafe.dao;

import org.springframework.stereotype.Component;

import com.paysafe.model.SingleUserTokenRequest;

@Component
public interface PaySafeDao {

	public SingleUserTokenRequest checkCustomerExists(SingleUserTokenRequest tokenReq);
	
	public boolean createCustomer(SingleUserTokenRequest tokenReq);


}
