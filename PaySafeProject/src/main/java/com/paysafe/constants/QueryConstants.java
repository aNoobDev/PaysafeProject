package com.paysafe.constants;

public interface QueryConstants {

	String CUSTOMER_CHECK = "SELECT merchantCustId,id, paymentToken  FROM customer_detail WHERE emailId = ? and phone = ?";
	String CREATE_CUSTOMER = "INSERT INTO customer_detail (id, paymentToken, firstName, emailId, phone, merchantCustId) VALUES(?,?,?,?,?,?)";

}
