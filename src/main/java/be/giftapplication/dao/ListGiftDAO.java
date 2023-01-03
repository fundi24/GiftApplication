package be.giftapplication.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;
import be.giftapplication.javabeans.Notification;

public class ListGiftDAO extends DAO<ListGift> {

	public ListGiftDAO() {
	}

	@Override
	public boolean create(ListGift obj) {
		ClientResponse res;
		try {
			res = this.resource.path("listgift").type(MediaType.APPLICATION_JSON).post(ClientResponse.class,
					mapper.writeValueAsString(obj));
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
	public boolean delete(ListGift obj) {
		return false;
	}

	@Override
	public boolean update(ListGift obj) {
		boolean success = false;
		ListGift listGift = null;
		if (obj instanceof ListGift) {
			listGift = (ListGift) obj;
		}

		ClientResponse res;
		try {
			res = this.resource.path("listgift").path(String.valueOf(listGift.getIdListGift()))
					.header("Content-Type", "application/json;charset=UTF-8")
					.put(ClientResponse.class, mapper.writeValueAsString(listGift));
			int httpResponseCode = res.getStatus();
			if (httpResponseCode == 204) {
				success = true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return success;
	}

	@Override
	public ListGift find(int id) {
		ListGift listgift = null;

		String APIResponse = this.resource.path("listgift").path(String.valueOf(id)).accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		if (APIResponse != null) {
			try {
				JSONObject objJson = new JSONObject(APIResponse);
				int idListGift = objJson.getInt("idListGift");
				String name = objJson.getString("name");
				JSONObject jsonDob = objJson.getJSONObject("deadline");
				int year = jsonDob.getInt("year");
				int month = jsonDob.getInt("monthValue");
				int day = jsonDob.getInt("dayOfMonth");
				boolean status = objJson.getBoolean("status");
				String theme = objJson.getString("theme");
				listgift = new ListGift(idListGift, name, LocalDate.of(year, month, day), status, theme, null);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return listgift;

	}

	@Override
	public ArrayList<ListGift> findAll(Object obj) {
		Customer customer = null;
		if (obj instanceof Customer) {
			customer = (Customer) obj;
		}
		ArrayList<ListGift> giftLists = new ArrayList<>();

		String APIResponse = this.resource.path("listgift").path("customer")
				.path(String.valueOf(customer.getIdCustomer())).accept(MediaType.APPLICATION_JSON).get(String.class);

		if (APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);

			try {

				for (int i = 0; i < array.length(); i++) {

					JSONObject objJson = array.getJSONObject(i);
					int idListGift = objJson.getInt("idListGift");
					String name = objJson.getString("name");
					JSONObject jsonDob = objJson.getJSONObject("deadline");
					int year = jsonDob.getInt("year");
					int month = jsonDob.getInt("monthValue");
					int day = jsonDob.getInt("dayOfMonth");
					boolean status = objJson.getBoolean("status");
					String theme = objJson.getString("theme");
					ListGift listGift = new ListGift(idListGift, name, LocalDate.of(year, month, day), status, theme,
							customer);
					giftLists.add(listGift);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return giftLists;
	}

	public ArrayList<Customer> getInvitations(ListGift obj) {
		ArrayList<Customer> customers = new ArrayList<>();

		String APIResponse = this.resource.path("listgift").path("invitation").path(String.valueOf(obj.getIdListGift()))
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		if (APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);

			try {

				for (int i = 0; i < array.length(); i++) {

					JSONObject json = array.getJSONObject(i);
					int idCustomer = json.getInt("idCustomer");
					String firstName = json.getString("firstName");
					String lastName = json.getString("lastName");
					JSONObject jsonDob = json.getJSONObject("dateOfBirth");
					int year = jsonDob.getInt("year");
					int month = jsonDob.getInt("monthValue");
					int day = jsonDob.getInt("dayOfMonth");
					LocalDate dateOfBirth = LocalDate.of(year, month, day);
					String username = json.getString("username");
					String password = json.getString("password");

					Customer customer = new Customer(idCustomer, firstName, lastName, dateOfBirth, username, password);

					customers.add(customer);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return customers;

	}
}