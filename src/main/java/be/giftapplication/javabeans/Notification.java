package be.giftapplication.javabeans;

import java.io.Serializable;
import java.util.ArrayList;

import be.giftapplication.dao.DAO;
import be.giftapplication.dao.NotificationDAO;



public class Notification implements Serializable {
	private static final long serialVersionUID = 3306630235932605381L;
	private static final DAO<Notification> notificationDAO = new NotificationDAO();
	private int idNotification;
	private String message;
	private boolean read;
	private Customer customer;
	
	public Notification()
	{
		
	}
	
	public Notification(int idNotification, String message, boolean read, Customer customer) {
		this.idNotification = idNotification;
		this.message = message;
		this.read = read;
		this.customer = customer;
	 }
	    
	 //Getters and Setters

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	//Call to DAO
	public static ArrayList<Notification> getNotificationFromCustomer(Customer customer) {
		
		return notificationDAO.findAll(customer);
	}
}
