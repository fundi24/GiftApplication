package be.giftapplication.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

public class ListGiftDAO extends DAO<ListGift> {

	public ListGiftDAO() {
	}

	@Override
	public boolean create(ListGift obj) {
		return false;
	}

	@Override
	public boolean delete(ListGift obj) {
		return false;
	}

	@Override
	public boolean update(ListGift obj) {
		return false;
	}

	@Override
	public ListGift find(int id) {
		return null;
	}

	@Override
	public ArrayList<ListGift> findAll(Object obj) {
		Customer customer = null;
		if(obj instanceof Customer) {
			 customer = (Customer) obj;
		}
		ArrayList<ListGift> giftLists = new ArrayList<>();
		
		String APIResponse = this.resource.path("listgift").path("customer").path(String.valueOf(customer.getIdCustomer())).accept(MediaType.APPLICATION_JSON).get(String.class);
		
		if(APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);
			
			
			try {
				
				for(int i=0; i < array.length(); i++) {
					
					JSONObject objJson = array.getJSONObject(i);
					int idListGift = objJson.getInt("idListGift");
					String name = objJson.getString("name");
					JSONObject jsonDob = objJson.getJSONObject("deadline");
					int year = jsonDob.getInt("year");
					int month = jsonDob.getInt("monthValue");
					int day = jsonDob.getInt("dayOfMonth");
					boolean status = objJson.getBoolean("status");
					String theme = objJson.getString("theme");
					ListGift listGift = new ListGift(idListGift, name, LocalDate.of(year, month, day), status, theme, customer);
					giftLists.add(listGift);
				}
			}
			catch(Exception e)	{
				System.out.println(e.getMessage());
			}	
		}
		
		
		return giftLists;
	}

}