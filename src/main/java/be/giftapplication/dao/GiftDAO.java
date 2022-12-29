package be.giftapplication.dao;

import java.awt.Image;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

public class GiftDAO extends DAO<Gift> {

	public GiftDAO() {
	}

	@Override
	public boolean create(Gift obj) {
		ClientResponse res;
		try {
			res = this.resource.path("gift").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, mapper.writeValueAsString(obj));
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
	public boolean delete(Gift obj) {
		return false;
	}

	@Override
	public boolean update(Gift obj) {
		return false;
	}

	@Override
	public Gift find(int id) {
		return null;
	}

	@Override
	public ArrayList<Gift> findAll(Object obj) {
		ListGift listGift = null;
		if(obj instanceof ListGift) {
			listGift = (ListGift) obj;
		}
		ArrayList<Gift> gifts = new ArrayList<>();
		String APIResponse = this.resource.path("gift").path("listgift").path(String.valueOf(listGift.getIdListGift())).accept(MediaType.APPLICATION_JSON).get(String.class);
		
		if(APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);
			
			try {
				
				for(int i=0; i < array.length(); i++) {
					
					JSONObject objJson = array.getJSONObject(i);
					int idGift = objJson.getInt("idGift");
					String name = objJson.getString("name");
					String description = objJson.getString("description");
					String picture = objJson.getString("picture");
					double price = objJson.getDouble("price");
					int priority = objJson.getInt("priority");
					boolean booked = objJson.getBoolean("booked");
					boolean multiplePayment = objJson.getBoolean("multiplePayment");
					String linkToWebsite = objJson.getString("linkToWebsite");
					
					Gift gift = new Gift(idGift, name, description, price, priority, picture, booked, multiplePayment, linkToWebsite, listGift);
					gifts.add(gift);
				}
			}
			catch(Exception e)	{
				System.out.println(e.getMessage());
			}	
		}
		return gifts;
	}

}