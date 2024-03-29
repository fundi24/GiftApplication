package be.giftapplication.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO() {
	}

	@Override
	public boolean create(Customer obj) {
		ClientResponse res;
		try {
			res = this.resource.path("customer").header("Content-Type",
		            "application/json;charset=UTF-8").post(ClientResponse.class, mapper.writeValueAsString(obj));
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
		
		Customer customer = null;
		
		String APIResponse = this.resource.path("customer").path(String.valueOf(id)).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			JSONObject json = new JSONObject(APIResponse);
			String firstName = json.getString("firstName");
			String lastName = json.getString("lastName");
			JSONObject jsonDob = json.getJSONObject("dateOfBirth");
			int year = jsonDob.getInt("year");
			int month = jsonDob.getInt("monthValue");
			int day = jsonDob.getInt("dayOfMonth");
			LocalDate dateOfBirth = LocalDate.of(year, month, day);
			String username = json.getString("username");
			String password = json.getString("password");
			customer = new Customer(id, firstName, lastName, dateOfBirth, username, password);
			return customer;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return customer;
		}
	}

	@Override
	public ArrayList<Customer> findAll(Object obj) {
		ArrayList<Customer> customers = new ArrayList<>();
		
		String APIResponse = this.resource.path("customer").accept(MediaType.APPLICATION_JSON).get(String.class);
		if (APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);
			try {

				for (int i = 0; i < array.length(); i++) {

					JSONObject json = array.getJSONObject(i);
					int id = json.getInt("idCustomer");
					String firstName = json.getString("firstName");
					String lastName = json.getString("lastName");
					JSONObject jsonDob = json.getJSONObject("dateOfBirth");
					int year = jsonDob.getInt("year");
					int month = jsonDob.getInt("monthValue");
					int day = jsonDob.getInt("dayOfMonth");
					LocalDate dateOfBirth = LocalDate.of(year, month, day);
					String username = json.getString("username");
					String password = json.getString("password");
					Customer customer = new Customer(id, firstName, lastName, dateOfBirth, username, password);
					customers.add(customer);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return customers;

	}

	public Customer login(String username, String password) {
		ClientResponse res;
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		
		try {
			res = this.resource.path("customer").path("login").header("Content-Type",
		            "application/json;charset=UTF-8")
					.post(ClientResponse.class, mapper.writeValueAsString(customer));
			
			int httpResponseCode = res.getStatus();
			
			if (httpResponseCode == 200) {
				
				String response = res.getEntity(String.class);
				JSONObject json = new JSONObject(response);
				int id = json.getInt("idCustomer");
				String firstName = json.getString("firstName");
				String lastName = json.getString("lastName");
				JSONObject jsonDob = json.getJSONObject("dateOfBirth");
				int year = jsonDob.getInt("year");
				int month = jsonDob.getInt("monthValue");
				int day = jsonDob.getInt("dayOfMonth");
				LocalDate dateOfBirth = LocalDate.of(year, month, day);
				customer = new Customer(id, firstName, lastName, dateOfBirth, username, password);
				return customer;
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	

}
