package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Utente;
import exceptions.DAOException;


public interface RegistrazioneUtenteDAO {

	void insert(Utente u) throws SQLException, DAOException; 
	void update(Utente u) throws SQLException, DAOException;
	void delete(String idUtente) throws SQLException, DAOException;
	ArrayList<Utente> select() throws SQLException, DAOException;
	Utente select(String idUtente, String password) throws SQLException, DAOException;
	void close();

}