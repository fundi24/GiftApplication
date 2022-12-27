package be.giftapplication.javabeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


import be.giftapplication.dao.CustomerDAO;
import be.giftapplication.dao.DAO;

public class Customer implements Serializable {

	private static final long serialVersionUID = 2411323456692016479L;
	private static final DAO<Customer> customerDAO = new CustomerDAO();
	private int idCustomer;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String username;
	private String password;
	private ArrayList<Participation> participations;
	private ArrayList<ListGift> myListGifts;
	private ArrayList<ListGift> sharedListGifts;
	private ArrayList<Notification> notifications;
	
	public Customer()
	{
		participations = new ArrayList<>();
		myListGifts = new ArrayList<>();
		sharedListGifts = new ArrayList<>();
		notifications = new ArrayList<>();
		
	}
	public Customer(int idCustomer, String firstName, String lastName, LocalDate dateOfBirth, String username,
			String password) {
		this.idCustomer = idCustomer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.username = username;
		this.password = password;
		participations = new ArrayList<>();
		myListGifts = new ArrayList<>();
		sharedListGifts = new ArrayList<>();
		notifications = new ArrayList<>();
	}

	//Getters and Setters
	
	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(ArrayList<Participation> participations) {
		this.participations = participations;
	}

	public ArrayList<ListGift> getMyListGifts() {
		return myListGifts;
	}

	public void setMyListGifts(ArrayList<ListGift> myListGifts) {
		this.myListGifts = myListGifts;
	}

	public ArrayList<ListGift> getSharedListGifts() {
		return sharedListGifts;
	}

	public void setSharedListGifts(ArrayList<ListGift> sharedListGifts) {
		this.sharedListGifts = sharedListGifts;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
	//Add and remove for lists
	
	public void addParticipation(Participation participation) {
		participations.add(participation);
	}
	public void addMyListGifts(ListGift listGift) {
		myListGifts.add(listGift);
	}
		
	public void addSharedListGift(ListGift sharedlistGift) {
		sharedListGifts.add(sharedlistGift);
	}
		
	public void addNotifications(Notification notification) {
		notifications.add(notification);
	}
		
	public void removeParticipation(Participation participation) {
		participations.remove(participation);
	}
	public void removeMyListGifts(ListGift listGift) {
		myListGifts.remove(listGift);
	}
		
	public void removeSharedListGift(ListGift sharedlistGift) {
		sharedListGifts.remove(sharedlistGift);
	}
		
	public void removeNotifications(Notification notification) {
		notifications.remove(notification);
	}
		
	
	//Call to DAO
	
	public boolean insert() {
		return customerDAO.create(this);
	}
	
	public static Customer login (String username, String password) {
		CustomerDAO customerdao = new CustomerDAO();
		return customerdao.find(username, password);
	}
	
	public boolean getCustomerListGifts(){
		myListGifts = ListGift.getListGiftsFromCustomer(this);
		if(myListGifts.size() > 0) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", username=" + username + ", password=" + password + "]";
	}
	
	
	

}
