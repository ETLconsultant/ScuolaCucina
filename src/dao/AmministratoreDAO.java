package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Utente;

public interface AmministratoreDAO {
	void insert(Utente amministratore) throws SQLException;
	
	void update(Utente amministratore) throws SQLException;
	
	void delete(String idAmministratore) throws SQLException;
	
	public ArrayList<Utente> select() throws SQLException;
	
	public Utente select(String idAmministratore) throws SQLException;
}