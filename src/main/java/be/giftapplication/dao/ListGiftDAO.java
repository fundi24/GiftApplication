package be.giftapplication.dao;

import java.sql.Connection;
import java.util.ArrayList;

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
	public ArrayList<ListGift> findAll(int id) {
		return null;
	}

}