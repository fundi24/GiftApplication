package be.giftapplication.javabeans;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import be.giftapplication.dao.DAO;
import be.giftapplication.dao.GiftDAO;
import be.giftapplication.dao.ListGiftDAO;


public class Gift implements Serializable {
	private static final long serialVersionUID = -375971532475389184L;
	private static final DAO<Gift> giftDAO = new GiftDAO();
	private int idGift;
	private String name;
	private String description;
	private double price;
	private int priority;
	private String picture;
	private boolean booked;
	private boolean multiplePayment;
	private String linkToWebsite;
	private ListGift listGift;
	private ArrayList<Participation> participations;
	
	public Gift ()
	{
		participations = new ArrayList<>();
	}

	
	public Gift(int idGift, String name, String description,  double price, int priority,String picture, boolean booked,
			boolean multiplePayment, String linkToWebsite, ListGift listGift) {
		
		this.idGift = idGift;
		this.name = name;
		this.description = description;
		this.price = price;
		this.priority = priority;
		this.picture = picture;
		this.booked = booked;
		this.multiplePayment = multiplePayment;
		this.linkToWebsite = linkToWebsite;
		this.listGift = listGift;
		participations = new ArrayList<>();
	}




	//Getters and Setters

	public int getIdGift() {
		return idGift;
	}

	public void setIdGift(int idGift) {
		this.idGift = idGift;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public boolean isMultiplePayment() {
		return multiplePayment;
	}

	public void setMultiplePayment(boolean multiplePayment) {
		this.multiplePayment = multiplePayment;
	}

	public String getLinkToWebsite() {
		return linkToWebsite;
	}

	public void setLinkToWebsite(String linkToWebsite) {
		this.linkToWebsite = linkToWebsite;
	}

	public ListGift getListGift() {
		return listGift;
	}

	public void setListGift(ListGift listGift) {
		this.listGift = listGift;
	}

	public ArrayList<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(ArrayList<Participation> participations) {
		this.participations = participations;
	}
	
	public double calculTotal() {
		double total = 0;
		
		for(Participation p : participations) {
			total += p.getAmountPaid();
		}
		
		return total;
		
	}

	//Add and remove for lists
	
	public void addParticipation(Participation participation) {
		participations.add(participation);
	}
		
	public void removeParticipation(Participation participation) {
		participations.remove(participation);
	}
		
	//Call to DAO
	
	public static ArrayList<Gift> getGiftsFromListGift(ListGift listGift) {
		return giftDAO.findAll(listGift);
	}
	
	public boolean insert() {
		return giftDAO.create(this);
	}
	
	public boolean update(Gift giftWithoutList) {
		//send to dao the owner without his lists
		boolean success = giftDAO.update(giftWithoutList);
		if(success) {
			this.name = giftWithoutList.getName();
			this.description = giftWithoutList.getDescription();
			this.price = giftWithoutList.getPrice();
			this.picture = giftWithoutList.getPicture();
			this.linkToWebsite = giftWithoutList.getLinkToWebsite();
			this.priority = giftWithoutList.getPriority();
			this.booked = giftWithoutList.isBooked();
			this.multiplePayment = giftWithoutList.isMultiplePayment();
		}
		
		return success;
	}
	
	public boolean updatePriority() {
		
		Gift giftWithoutListGift = new Gift(idGift, name, description, price, priority, picture, booked, multiplePayment, linkToWebsite, null);
		
		boolean success = giftDAO.update(giftWithoutListGift);
		
		return success;
	}
	
	public void getGiftParticipations() {
		this.participations = Participation.getParticipationsFromGift(this);
		
	
	}
	


	@Override
	public String toString() {
		return "Gift [idGift=" + idGift + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", priority=" + priority + ", picture=" + picture + ", booked=" + booked + ", multiplePayment="
				+ multiplePayment + ", linkToWebsite=" + linkToWebsite +  "]";
	}
	
	
	
}
