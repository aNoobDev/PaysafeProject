package com.paysafe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value =  Include.NON_NULL)
public class SingleUserTokenRequest {

	public String email;
	public String phone;
	public String firstName;
	public String merchantCustomerId;
	public String id;
	public String paymentToken;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMerchantCustomerId() {
		return merchantCustomerId;
	}
	public void setMerchantCustomerId(String merchantCustomerId) {
		this.merchantCustomerId = merchantCustomerId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPaymentToken() {
		return paymentToken;
	}
	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}
	
	
	
}
