package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Edizione;
import entity.Utente;
import exceptions.ConnessioneException;

public class IscrizioneUtenteDAOImpl implements IscrizioneUtenteDAO {

	private Connection conn;
	
	public IscrizioneUtenteDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	/*
	 * iscrizione di un certo utente ad una certa edizione di un corso.
	 * sia l'utente che l'edizione devono gi� essere stati registrati in precedenza
	 * se l'utente e/o l'edizione non esistono o l'utente � gi� iscritto a quella edizione si solleva una eccezione
	 */
	@Override
	public void iscriviUtente(int idEdizione, String idUtente) throws SQLException {
		
		
		String query = "select id_utente, id_edizione from iscritti where id_utente = ? and id_edizione = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, idUtente);
		ps.setInt(2, idEdizione);
		ResultSet res = ps.executeQuery();
		
		if(res.next()) {
			throw new SQLException("Errore! Utente gi� iscritto a questa edizione");
		}
			query = "insert into iscritti (id_edizione, id_utente) select ?,? from registrati, calendario where registrati.id_utente= ? and calendario.id_edizione = ?";
			
			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setInt(1, idEdizione);
			ps1.setString(2, idUtente);
			ps1.setInt(4, idEdizione);
			ps1.setString(3, idUtente);
		
			int n = ps1.executeUpdate();
			if(n>0) {
				System.out.println("Iscrizione aggiunta correttamente");
			}else{
			throw new SQLException("Errore! Utente o edizione non esistono");
			}
	}

//	FATTO
	/*
	 * cancellazione di una iscrizione ad una edizione
	 * nota: quando si cancella l'iscrizione, sia l'utente che l'edizione non devono essere cancellati
	 * se l'utente e/o l'edizione non esistono si solleva una eccezione
	 */
	@Override
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("delete from iscritti where id_edizione=? and id_utente=?");
		ps.setInt(1, idEdizione);
		ps.setString(2, idUtente);
		int n = ps.executeUpdate();
		if(n==0) {
			throw new SQLException("iscrizione (id edizione '" + idEdizione + "' e id utente '"+ idUtente+"') non presente");
		}else {
			System.out.println("Iscrizione eliminata correttamente");
		}
	}
//	FATTO
	
		/*
	 * lettura di tutte le edizioni a cui � iscritto un utente
	 * se l'utente non esiste o non � iscritto a nessuna edizione si torna una lista vuota
	 */
	@Override
	public ArrayList<Edizione> selectIscrizioniUtente(String idUtente) throws SQLException {

		ArrayList<Edizione> edizioni = new ArrayList<Edizione>(); 
		
		String query = "select calendario.* from calendario, iscritti where iscritti.id_edizione=calendario.id_edizione and id_utente=?";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setString(1, idUtente);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			int idEdizione=rs.getInt("id_Edizione");
			int idCorso=rs.getInt("id_corso");
			Date dataInizio=rs.getDate("dataInizio");
			int durata=rs.getInt("durata");
			String aula=rs.getString("aula");
			String docente=rs.getString("docente");
			
			Edizione edizione = new Edizione(idEdizione, idCorso, dataInizio, durata, aula, docente);
			edizioni.add(edizione);
		}

		return edizioni;
	}
	
	/*
	 * lettura di tutti gli utenti iscritti ad una certa edizione
	 * se l'edizione non esiste o non vi sono utenti iscritti si torna una lista vuota
	 */
	@Override
	public ArrayList<Utente> selectUtentiPerEdizione(int idEdizione) throws SQLException {

		ArrayList<Utente> utenti = new ArrayList<Utente>(); 

		PreparedStatement ps=conn.prepareStatement("select registrati.* from registrati, iscritti where iscritti.id_utente=registrati.id_utente and id_edizione=?");

		ps.setInt(1, idEdizione);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			String idUtente = rs.getString("id_utente");
			String password= rs.getString("password");
			String nome= rs.getString("nome");
			String cognome= rs.getString("cognome");
			Date dataNascita = rs.getDate("dataNascita");
			String email= rs.getString("email");
			String telefono= rs.getString("telefono");

			Utente utente = new Utente(idUtente,password,nome,cognome,dataNascita,email,telefono, true);
			utenti.add(utente);
		}

		return utenti;
	}

	/*
	 * ritorna il numero di utenti iscritti ad una certa edizione
	 */
	@Override
	public int getNumeroIscritti(int idEdizione) throws SQLException {
		
		PreparedStatement ps=conn.prepareStatement("select count(id_utente) as conta from iscritti where id_edizione=?");

		ps.setInt(1, idEdizione);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt("conta");
	}

}
