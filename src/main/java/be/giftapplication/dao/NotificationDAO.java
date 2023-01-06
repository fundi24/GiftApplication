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

public class NotificationDAO extends DAO<Notification> {

	public NotificationDAO() {
	}

	@Override
	public boolean create(Notification obj) {
		ClientResponse res;
		try {
			res = this.resource.path("notification").header("Content-Type",
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
	public boolean delete(Notification obj) {
		return false;
	}

	@Override
	public boolean update(Notification obj) {
		boolean success = false;
		Notification notification = null;
		if(obj instanceof Notification) {
			notification = (Notification) obj;
		}
		
		ClientResponse res;
		try {
			res = this.resource.path("notification").path(String.valueOf(notification.getIdNotification())).header("Content-Type",
		            "application/json;charset=UTF-8").put(ClientResponse.class, mapper.writeValueAsString(notification));
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
	public Notification find(int id) {
		return null;
	}

	@Override
	public ArrayList<Notification> findAll(Object obj) {
		Customer customer = null;
		if(obj instanceof Customer) {
			 customer = (Customer) obj;
		}
		
		ArrayList<Notification> notifications = new ArrayList<>();
		
		String APIResponse = this.resource.path("notification").path("customer").path(String.valueOf(customer.getIdCustomer())).accept(MediaType.APPLICATION_JSON).get(String.class);
		
		if(APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);
			
			try {
				
				for(int i=0; i < array.length(); i++) {
					
					JSONObject objJson = array.getJSONObject(i);
					int idNotification = objJson.getInt("idNotification");
					String message = objJson.getString("message");
					boolean read = objJson.getBoolean("read");
					Notification notification = new Notification(idNotification, message, read, customer);
					notifications.add(notification);
				}
			}
			catch(Exception e)	{
				System.out.println(e.getMessage());
			}	
		}
		
		return notifications;
	}

}
