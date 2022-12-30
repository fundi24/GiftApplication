package be.giftapplication.javabeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.giftapplication.dao.DAO;
import be.giftapplication.dao.ListGiftDAO;

public class ListGift implements Serializable {

	private static final long serialVersionUID = 99181030694279507L;
	private static final DAO<ListGift> listGiftDAO = new ListGiftDAO();
	private int idListGift;
	private String name;
	private LocalDate deadline;
	private boolean status;
	private String theme;
	private Customer owner;
	private ArrayList<Customer> invitations;
	private ArrayList<Gift> gifts;
	
	public ListGift()
	{
		invitations = new ArrayList<>();
		gifts = new ArrayList<>();
	}
	
	

	public ListGift(int idListGift, String name, LocalDate deadline, boolean status, String theme, Customer owner) {
		this.idListGift = idListGift;
		this.name = name;
		this.deadline = deadline;
		this.status = status;
		this.theme = theme;
		this.owner = owner;
		invitations = new ArrayList<>();
		gifts = new ArrayList<>();
	}


	//Getters and Setters

	public int getIdListGift() {
		return idListGift;
	}

	public void setIdListGift(int idListGift) {
		this.idListGift = idListGift;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public ArrayList<Customer> getInvitations() {
		return invitations;
	}

	public void setInvitations(ArrayList<Customer> invitations) {
		this.invitations = invitations;
	}

	public ArrayList<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(ArrayList<Gift> gifts) {
		this.gifts = gifts;
	}
	
	//Add and remove for lists
    
    public void addInvitation(Customer customer) {
    	invitations.add(customer);
	}
	
    
    public void removeInvitation(Customer customer) {
    	invitations.remove(customer);
	}
    
    public void addGift(Gift gift) {
    	gifts.add(gift);
    }
    
    public void removeGift(Gift gift) {
    	gifts.remove(gift);
    }
	
	//Call to DAO
	public static ArrayList<ListGift> getListGiftsFromCustomer(Customer customer) {
		
		return listGiftDAO.findAll(customer);
	}
	

	
	public boolean getListGiftGifts() {
		gifts = Gift.getGiftsFromListGift(this);
		if(gifts != null) {
			return true;
		}
		return false;
	}
	
	public boolean getListGiftInvitations() {
		ListGiftDAO listgiftDao = new ListGiftDAO();
		invitations =  listgiftDao.getInvitations(this);
		if(invitations != null) {
			return true;
		}
		return false;
	}
	

	public boolean insert() {
		return listGiftDAO.create(this);
	}
	
	public boolean update() {
		if(status == true) {
			status = false;
		}
		else
		{
			status = true;
		}
		return listGiftDAO.update(this);
	}




	@Override
	public String toString() {
		return "ListGift [idListGift=" + idListGift + ", name=" + name + ", deadline=" + deadline + ", status=" + status
				+ ", theme=" + theme + ", owner=" + owner + ", invitations=" + invitations + ", gifts=" + gifts + "]";
	}
	
	
	
}
