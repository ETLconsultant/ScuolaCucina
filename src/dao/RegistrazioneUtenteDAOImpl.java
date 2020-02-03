package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;


public class RegistrazioneUtenteDAOImpl implements RegistrazioneUtenteDAO {

	private Connection conn;
	private Statement statement;
	private PreparedStatement prepared;
	private ResultSet rs;

	public RegistrazioneUtenteDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	/*
	 * registrazione di un nuovo utente alla scuola di formazione 
	 * se l'utente già esiste si solleva una eccezione
	 */
	@Override
	public void insert(Utente u) throws SQLException, DAOException {
		String query= "insert into registrati (id_utente, password, nome, cognome, dataNascita, email, telefono) values(?,?,?,?,?,?,?)";
		
			prepared=conn.prepareStatement(query);
			prepared.setString(1, u.getIdUtente());
			prepared.setString(2, u.getPassword());
			prepared.setString(3, u.getNome());
			prepared.setString(4, u.getCognome());
			prepared.setDate(5, new java.sql.Date(u.getDataNascita().getTime()));
			prepared.setString(6, u.getEmail());
			prepared.setString(7, u.getTelefono());
			prepared.executeUpdate();
			
			
			close();
		
	}

	/*
	 * modifica di tutti i dati di un utente
	 * l'utente viene individuato dal suo idUtente
	 * se l'utente non esiste si solleva una exception
	 */
	@Override
	public void update(Utente u) throws SQLException, DAOException {
		String query = "update registrati set password=?, nome=?, cognome=?, dataNascita=?, email=?, telefono=? where id_utente=?";
		
		
		    prepared=conn.prepareStatement(query);
			prepared.setString(1, u.getPassword());
			prepared.setString(2, u.getNome());
			prepared.setString(3, u.getCognome());
			prepared.setDate(4, new java.sql.Date(u.getDataNascita().getTime()));
			prepared.setString(5, u.getEmail());
			prepared.setString(6, u.getTelefono());
			prepared.setString(7, u.getIdUtente());
			int n =prepared.executeUpdate();
			if(n==0)
			throw new SQLException("utente: " + u.getIdUtente() + " non presente");
			
			close();

	}

	/*
	 * cancellazione di un singolo utente
	 * l'utente si può cancellare solo se non è correlato ad altri dati
	 * se l'utente non esiste o non è cancellabile si solleva una eccezione
	 */
	@Override
	public void delete(String idUtente) throws SQLException, DAOException {
		String query = "delete from registrati where id_utente=?";
		
		    prepared=conn.prepareStatement(query);
			prepared.setString(1, idUtente);
			int n =prepared.executeUpdate();
			if(n==0)
			throw new SQLException("utente: " + idUtente + " non presente");
			
			close();
		


	}
	
	/*
	 * lettura di tutti gli utenti registrati
	 * se non ci sono utenti registrati il metodo ritorna una lista vuota
	 */
	@Override
	public ArrayList<Utente> select() throws SQLException, DAOException {
		ArrayList<Utente> u = new ArrayList<Utente>();
		String query = "select * from registrati";
			prepared=conn.prepareStatement(query);
			rs=prepared.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Utente u1 = new Utente();
					u1.setIdUtente(rs.getString("id_utente"));
					u1.setPassword(rs.getString("password"));
					u1.setNome(rs.getString("nome"));
					u1.setCognome(rs.getString("cognome"));
					u1.setDataNascita(rs.getDate("dataNascita"));
					u1.setEmail(rs.getString("email"));
					u1.setTelefono(rs.getString("telefono"));
					u.add(u1);
					
				}
				close();
				return u;
				
			}else {
				close();
				return u;
			}	
		
	}

	
	/*
	 * lettura dei dati di un singolo utente
	 * se l'utente non esiste si solleva una eccezione
	 */
	@Override
	public Utente select(String idUtente, String password) throws SQLException, DAOException {
		Utente u1 = new Utente();
		String query = "select * from registrati where id_utente=? and password=? ";
		
			prepared=conn.prepareStatement(query);
			prepared.setString(1, idUtente);
			prepared.setString(2, password);
			rs=prepared.executeQuery();
			
			if(rs.next()){
			
			u1.setIdUtente(rs.getString("id_utente"));
			u1.setPassword(rs.getString("password"));
			u1.setNome(rs.getString("nome"));
			u1.setCognome(rs.getString("cognome"));
			u1.setDataNascita(rs.getDate("dataNascita"));
			u1.setEmail(rs.getString("email"));
			u1.setTelefono(rs.getString("telefono"));
			close();
			return u1;
			}
			
			else
				close();
				throw new SQLException("utente: " + idUtente + " non presente");
			
			

			
		
	}

	@Override
	public void close() {
		if (prepared!=null)
			try {
				prepared.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		
	}

}
