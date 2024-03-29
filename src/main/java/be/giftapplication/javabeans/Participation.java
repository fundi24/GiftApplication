package be.giftapplication.javabeans;

import java.io.Serializable;
import java.util.ArrayList;

import be.giftapplication.dao.DAO;

import be.giftapplication.dao.ParticipationDAO;



public class Participation implements Serializable {
	private static final long serialVersionUID = -4098840781809412627L;
	private static final DAO<Participation> participationDAO = new ParticipationDAO();
	private int idParticipation;
	private double amountPaid;
	private Customer customer;
	private Gift gift;
	
	public Participation()
	{
		
	}
	
	public Participation(int idParticipation, double amountPaid, Customer customer, Gift gift) {
		this.idParticipation = idParticipation;
		this.amountPaid = amountPaid;
		this.customer = customer;
		this.gift = gift;
	}
	
	//Getters and Setters

	public int getIdParticipation() {
		return idParticipation;
	}

	public void setIdParticipation(int idParticipation) {
		this.idParticipation = idParticipation;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}
	
	//Call to DAO
	
	public boolean create() {
		return participationDAO.create(this);
	}
	
	public static ArrayList<Participation> getParticipationsFromGift(Gift gift){
		return participationDAO.findAll(gift);
		
	}
}
