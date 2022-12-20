package be.giftapplication.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.giftapplication.javabeans.Participation;


public class ParticipationDAO extends DAO<Participation> {
	
	public ParticipationDAO () {
    }

    @Override
    public boolean create(Participation obj) {
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
    public ArrayList<Participation> findAll(int id) {
        return null;
    }
}