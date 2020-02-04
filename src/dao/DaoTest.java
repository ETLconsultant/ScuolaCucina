package dao;

import java.sql.SQLException;

import exceptions.ConnessioneException;

public class DaoTest {

	public static void main(String[] args) throws ConnessioneException, SQLException {
		// TODO Auto-generated method stub
		CalendarioDAOImpl cal = new CalendarioDAOImpl();
		
		
		cal.delete(93);
	}

}
