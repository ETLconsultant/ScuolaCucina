package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Feedback;
import exceptions.DAOException;



public interface FeedbackDAO {
	
	void insert(Feedback feedback) throws SQLException, DAOException;
	
	void update(Feedback feedback) throws SQLException, DAOException;
	
	void delete(int idFeedback) throws SQLException, DAOException;
	
	public Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException, DAOException;
	
	public ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException;
	
	public ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException;
	
	public ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException;
	
	public void close();

}