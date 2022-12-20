package be.giftapplication.dao;

import java.util.ArrayList;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;


import be.giftapplication.javabeans.Customer;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO() {
	}

	@Override
	public boolean create(Customer obj) {		
		ClientResponse res;
		try {
			res = this.resource.path("customer").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, mapper.writeValueAsString(obj));
			int httpResponseCode = res.getStatus();
			if (httpResponseCode == 201) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return false;


	}

	@Override
	public boolean delete(Customer obj) {
		return false;
	}

	@Override
	public boolean update(Customer obj) {
		return false;
	}

	@Override
	public Customer find(int id) {
		return null;
	}

	@Override
	public ArrayList<Customer> findAll(int id) {
		return null;
	}
}
