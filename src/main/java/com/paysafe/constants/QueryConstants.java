package com.paysafe.constants;

public interface QueryConstants {

	String CUSTOMER_CHECK = "SELECT merchantCustId,id, paymentToken  FROM paysafe_customer WHERE emailId = ? and phone = ?";
	String CREATE_CUSTOMER = "INSERT INTO paysafe_customer (id, paymentToken, firstName, emailId, phone, merchantCustId) VALUES(?,?,?,?,?,?)";

}
