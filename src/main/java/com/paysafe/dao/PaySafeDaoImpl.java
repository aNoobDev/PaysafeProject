package com.paysafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.paysafe.constants.QueryConstants;
import com.paysafe.model.SingleUserTokenRequest;

@Component
public class PaySafeDaoImpl implements PaySafeDao {

	@Autowired
	JdbcTemplate jdbctemp;
	
	@Override
	public SingleUserTokenRequest checkCustomerExists(SingleUserTokenRequest tokenReq) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet res = null;
		try {
			connection = jdbctemp.getDataSource().getConnection();
			preStmt = connection.prepareStatement(QueryConstants.CUSTOMER_CHECK);
			preStmt.setString(1, tokenReq.getEmail());
			preStmt.setString(2, tokenReq.getPhone());
			res = preStmt.executeQuery();
			if (res.next()) {
				tokenReq.setMerchantCustomerId(res.getString(1));
				tokenReq.setId(res.getString(2));
				tokenReq.setPaymentToken(res.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				res.close();
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return tokenReq;
	}

	@Override
	public boolean createCustomer(SingleUserTokenRequest tokenReq) {
		PreparedStatement preStmt = null;
		Connection connection = null;
		boolean stat = false;
		try {
			connection = jdbctemp.getDataSource().getConnection();
			preStmt = connection.prepareStatement(QueryConstants.CREATE_CUSTOMER);
			preStmt.setString(1,tokenReq.getId());
			preStmt.setString(2,tokenReq.getPaymentToken());
			preStmt.setString(3, tokenReq.getFirstName());
			preStmt.setString(4,tokenReq.getEmail());
			preStmt.setString(5,tokenReq.getPhone());
			preStmt.setString(6, tokenReq.getMerchantCustomerId());
			preStmt.executeUpdate();
			stat = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
				preStmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return stat;
	}
	
	
}
