package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Feedback;
import exceptions.ConnessioneException;
import exceptions.DAOException;


public class FeedBackDAOImpl implements FeedbackDAO {

	private Connection conn;
	private PreparedStatement prepared;
	private ResultSet resultset; 

	public FeedBackDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	/*
	 * inserimento di un singolo feedbak relativo ad una edizione di un corso da aprte di un utente
	 * se un utente ha già inserito un feedback per una certa edizione si solleva una eccezione
	 */
	@Override
	public void insert(Feedback feedback) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		String query = "insert into feedback (id_edizione, id_utente, descrizione, voto) values (?, ?, ?, ?)";
		
		prepared = conn.prepareStatement(query, prepared.RETURN_GENERATED_KEYS);
		prepared.setInt(1, feedback.getIdEdizione());
		prepared.setString(2, feedback.getIdUtente());
		prepared.setString(3, feedback.getDescrizione());
		prepared.setInt(3, feedback.getVoto());
		
		if(selectSingoloFeedback(feedback.getIdUtente(), feedback.getIdEdizione()) == null) {
			int numero = prepared.executeUpdate();
			resultset = prepared.getGeneratedKeys();
			if (resultset.next()) {
				System.out.println("Auto Generated Primary Key " + resultset.getInt(1));
				feedback.setIdFeedback(resultset.getInt(1));
			}
			if(numero>0) {
				System.out.println("Feedback inserito correttamente");
			}
		}else {
			throw new SQLException("feedback: " + feedback.getIdFeedback() + " già inserito");
		}
		
		close();
	}

	/*
	 * modifica di tutti i dati di un singolo feedback
	 * un feedback viene individuato attraverso l'idFeedback
	 * se il feedback non esiste si solleva una eccezione
	 */
	@Override
	public void update(Feedback feedback) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		String query = "update feedback set id_edizione=?, id_utente=?, descrizione=?, voto=? where id_feedback = ?";
		
		prepared = conn.prepareStatement(query);
		prepared.setInt(1, feedback.getIdEdizione());
		prepared.setString(2, feedback.getIdUtente());
		prepared.setString(3, feedback.getDescrizione());
		prepared.setInt(4, feedback.getVoto());
		prepared.setInt(5, feedback.getIdFeedback());
				
		int numeroRighe = prepared.executeUpdate();
				
		if(numeroRighe>0) {
			System.out.println("Feddback aggiornato");
		}else if(numeroRighe == 0) {
			throw new SQLException("Il feedback non esiste");
		}
		close();
	}

	/*
	 * cancellazione di un feedback
	 * se il feedback non esiste si solleva una eccezione
	 */
	@Override
	public void delete(int idFeedback) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		String query = "delete from feedback where id_feedback = ?";
		
		prepared = conn.prepareStatement(query);
		prepared.setInt(1, idFeedback);
		int numeroRighe = prepared.executeUpdate();
		if(numeroRighe>0) {
			System.out.println("Feedback cancellato");
		}else if(numeroRighe == 0) {
			throw new SQLException("Il feedback non esiste");
		}
		close();
	}
	
	/*
	 * lettura di un singolo feedback scritto da un utente per una certa edizione 
	 * se il feedback non esiste si solleva una eccezione
	 */
	@Override
	public Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		Feedback feedback = new Feedback();
		String query = "select * from feedback where id_utente = ? and id_edizione = ?";
		
			prepared = conn.prepareStatement(query);
			prepared.setString(1, idUtente);
			prepared.setInt(2, idEdizione);
		
			resultset = prepared.executeQuery();
			
			if(resultset != null) {
				while(resultset.next()) {
					feedback.setIdEdizione(idEdizione);
					feedback.setIdUtente(idUtente);
					feedback.setIdFeedback(resultset.getInt("id_feedback"));
					feedback.setDescrizione(resultset.getString("descrizione"));
					feedback.setVoto(resultset.getInt("voto"));
				}
				close();
				return feedback;
			
			}else {
				close();
				throw new SQLException("Il feedback non esiste");
			}
			
	}

	/*
	 * lettura di tutti i feedback di una certa edizione
	 * se non ci sono feedback o l'edizione non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Feedback> listaFeedback = null;
		
		String query = "select * from feedback where id_edizione = ?";
		
		prepared = conn.prepareStatement(query);
		prepared.setInt(1, idEdizione);
		
		resultset = prepared.executeQuery();
		
		if(resultset != null) {
			while(resultset.next()) {
				Feedback feedback = new Feedback();
				feedback.setIdFeedback(resultset.getInt("id_feedback"));
				feedback.setIdEdizione(idEdizione);
				feedback.setIdUtente(resultset.getString("id_utente"));
				feedback.setDescrizione(resultset.getString("descrizione"));
				feedback.setVoto(resultset.getInt("voto"));
			
				listaFeedback.add(feedback);
			}
			close();
			return listaFeedback;
		}else {
			close();
			return listaFeedback;
		}
	}

	/*
	 * lettura di tutti i feedback scritti da un certo utente
	 * se non ci sono feedback o l'utente non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Feedback> listaFeedback = null;
		String query = "select * from feedback where id_utente = ?";
		
		prepared = conn.prepareStatement(query);
		prepared.setString(1, idUtente);
		
		resultset = prepared.executeQuery();
		
		if(resultset != null) {
			while(resultset.next()) {
				Feedback feedback = new Feedback();
				feedback.setIdFeedback(resultset.getInt("id_feedback"));
				feedback.setIdEdizione(resultset.getInt("id_edizione"));
				feedback.setIdUtente(idUtente);
				feedback.setDescrizione(resultset.getString("descrizione"));
				feedback.setVoto(resultset.getInt("voto"));
			
				listaFeedback.add(feedback);
			}
			close();
			return listaFeedback;
		}else {
			close();
			return listaFeedback;
		}
	}


	/*
	 * lettura di tutti i feedback scritti per un certo corso (nota: non edizione ma corso)
	 * se non ci sono feedback o il corso non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Feedback> listaFeedback = null;
		String query = "select feedback.id_feedback, feedback.id_utente, feedback.id_edizione, feedback.descrizione, feedback.voto from feedback, calendario where"
						+ "feedback.id_edizione = calendario.id_edizione"
						+ "and calendario.id_corso = ?";
		
		prepared = conn.prepareStatement(query);
		prepared.setInt(1, idCorso);
		
		resultset = prepared.executeQuery();
		
		if(resultset != null) {
			while(resultset.next()) {
				Feedback feedback = new Feedback();
				feedback.setIdFeedback(resultset.getInt("id_feedback"));
				feedback.setIdEdizione(resultset.getInt("id_edizione"));
				feedback.setIdUtente(resultset.getString("id_utente"));
				feedback.setDescrizione(resultset.getString("descrizione"));
				feedback.setVoto(resultset.getInt("voto"));
			
				listaFeedback.add(feedback);
			}
			close();
			return listaFeedback;
		}else {
			close();
			return listaFeedback;
		}
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		if(prepared != null)
			try{
				prepared.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		if(resultset != null)
			try{
				resultset.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
	}

}
