package be.giftapplication.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;
import be.giftapplication.javabeans.Participation;


public class ParticipationDAO extends DAO<Participation> {
	
	public ParticipationDAO () {
    }

    @Override
    public boolean create(Participation obj) {
    	ClientResponse res;
		try {
			res = this.resource.path("participation").header("Content-Type",
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
    public boolean delete(Participation obj) {
        return false;
    }

    @Override
    public boolean update(Participation obj) {
        return false;
    }

    @Override
    public Participation find(int id) {
        return null;
    }

    @Override
    public ArrayList<Participation> findAll(Object obj) {
    	ArrayList<Participation> participations = new ArrayList<>(); 
    	Gift gift = null;
    	
    	if(obj instanceof Gift) {
    		gift = (Gift) obj;
    	}
    	
    	String APIResponse = this.resource.path("participation").path("gift").path(String.valueOf(gift.getIdGift())).accept(MediaType.APPLICATION_JSON).get(String.class);
    	
    	if(APIResponse != null) {
			JSONArray array = new JSONArray(APIResponse);
			try {
				
				for(int i=0; i < array.length(); i++) {
					
					JSONObject json = array.getJSONObject(i);
					int idParticipation = json.getInt("idParticipation");
					double amountPaid = json.getDouble("amountPaid");
					JSONObject jsonCustomer = json.getJSONObject("customer");
					int idCustomer = jsonCustomer.getInt("idCustomer");
					String firstName = jsonCustomer.getString("firstName");
					String lastName = jsonCustomer.getString("lastName");
					JSONObject jsonDob = jsonCustomer.getJSONObject("dateOfBirth");
					int year = jsonDob.getInt("year");
					int month = jsonDob.getInt("monthValue");
					int day = jsonDob.getInt("dayOfMonth");
					LocalDate dateOfBirth = LocalDate.of(year, month, day);
					String username = jsonCustomer.getString("username");
					String password = jsonCustomer.getString("password");
					
					Customer customer = new Customer(idCustomer,firstName, lastName, dateOfBirth, username, password);
					Participation participation = new Participation(idParticipation, amountPaid, customer, gift);
					participations.add(participation);
				}
			}
			catch(Exception e)	{
				System.out.println(e.getMessage());
			}	
			
    	}
    	
    	return participations;
    }

}