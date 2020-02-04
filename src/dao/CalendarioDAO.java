package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entity.Edizione;


public interface CalendarioDAO {

	public void insert(Edizione e) throws SQLException;
	
	public void delete(int idEdizione) throws SQLException;
	
	public void update(Edizione e) throws SQLException;
	
	public Edizione selectEdizione(int idEdizione) throws SQLException;
	
	public ArrayList<Edizione> select(int idCaregotia) throws SQLException;
	
	public ArrayList<Edizione> select(int idCaregotia, boolean future) throws SQLException;
	
	public ArrayList<Edizione> select() throws SQLException;
	
	public ArrayList<Edizione> select(boolean future) throws SQLException;
	
	public ArrayList<Edizione> select(Date da, Date a) throws SQLException;
	
	public ArrayList<Edizione> select(String idUtente) throws SQLException;
	
	public ArrayList<Edizione> select(String idUtente, boolean future) throws SQLException;

}