package service;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Feedback;
import entity.Utente;
import exceptions.DAOException;


public interface UtenteService {

	void registrazioneUtente(Utente u) throws DAOException, SQLException; 
	Utente checkCredenziali(String idUtente,String psw) throws DAOException, SQLException; 
	void cancellaRegistrazioneUtente(String idUtente) throws DAOException, SQLException;
	void modificaDatiUtente(Utente u) throws DAOException, SQLException;
	ArrayList<Utente> visualizzaUtentiRegistrati() throws DAOException, SQLException;
	void inserisciFeedback(Feedback f) throws DAOException, SQLException;
	void modificaFeedback(Feedback feedback) throws DAOException, SQLException;
	void cancellaFeedback(int idFeedback)throws DAOException, SQLException;
	
}
