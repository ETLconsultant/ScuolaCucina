package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Edizione;
import entity.Utente;

public interface IscrizioneUtenteDAO {

	public void iscriviUtente(int idEdizione, String idUtente) throws SQLException;
	
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws SQLException;
	
	public ArrayList<Edizione> selectIscrizioniUtente(String idUtente) throws SQLException;
	
	public ArrayList<Utente> selectUtentiPerEdizione(int idEdizione) throws SQLException;
	
	public int getNumeroIscritti(int idEdizione) throws SQLException;
	
	public void close();
}