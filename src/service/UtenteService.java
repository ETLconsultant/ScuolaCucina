package service;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Feedback;
import entity.Utente;
import exceptions.DAOException;


public interface UtenteService {

	public void registrazioneUtente(Utente u) throws DAOException, SQLException;  
	public Utente checkCredenziali(String idUtente,String psw) throws DAOException, SQLException; 
	public void cancellaRegistrazioneUtente(String idUtente) throws DAOException, SQLException;
	public void modificaDatiUtente(Utente u) throws DAOException, SQLException;
	public ArrayList<Utente> visualizzaUtentiRegistrati() throws DAOException, SQLException;
	public void inserisciFeedback(Feedback f) throws DAOException, SQLException;
	public void modificaFeedback(Feedback feedback) throws DAOException, SQLException;
	public void cancellaFeedback(int idFeedback)throws DAOException, SQLException;
	
}
