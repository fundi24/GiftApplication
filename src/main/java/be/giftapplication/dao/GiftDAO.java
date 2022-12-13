package be.giftapplication.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.giftapplication.javabeans.Gift;

public class GiftDAO extends DAO<Gift>{
	
	public GiftDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Gift obj) {
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
	public ArrayList<Gift> findAll(int id) {
		return null;
	}

}
