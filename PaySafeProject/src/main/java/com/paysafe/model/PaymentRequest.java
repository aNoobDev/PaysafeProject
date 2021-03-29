package com.paysafe.model;

public class PaymentRequest {

	String paymentHandleToken;
	String amount;
	String merchantRefNum;
	String currencyCode;
	
	
	public String getPaymentHandleToken() {
		return paymentHandleToken;
	}
	public void setPaymentHandleToken(String paymentHandleToken) {
		this.paymentHandleToken = paymentHandleToken;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMerchantRefNum() {
		return merchantRefNum;
	}
	public void setMerchantRefNum(String merchantRefNum) {
		this.merchantRefNum = merchantRefNum;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

	
}
