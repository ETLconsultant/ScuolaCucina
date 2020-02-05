package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.CalendarioDAO;
import dao.CalendarioDAOImpl;
import dao.IscrizioneUtenteDAO;
import dao.IscrizioneUtenteDAOImpl;
import dto.CorsoDTO;
import dto.EdizioneDTO;
import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class EdizioneServiceImpl implements EdizioneService{

	//dichiarare qui tutti i dao di cui si ha bisogno
	private CalendarioDAO daoC;
	private IscrizioneUtenteDAO daoI;
	
	//... dichiarazione di altri DAO
	
	//costruire qui tutti i dao di cui si ha bisogno
	public  EdizioneServiceImpl() throws ConnessioneException{
		daoC = new CalendarioDAOImpl();
		daoI = new IscrizioneUtenteDAOImpl();
		
		//... costruzione di altri DAO
	}
	
	/*
	 * inserisce una nuova edizione 
	 */
	@Override
	public void inserisciEdizione(Edizione e) throws DAOException {
		try {
			daoC.insert(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	
	/*
	 * modifica tutti i dati di una edizione esistente 
	 */
	@Override
	public void modificaEdizione(Edizione e) throws DAOException {
		try {
			daoC.update(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/*
	 * cancella una edizione esistente.
	 * E' possibile cancellare una edizione soltanto se la data di inizio è antecedente a quella odierna
	 * Nel caso l'edizione sia cancellabile, è necessario cancellare l'iscrizione a tutti gli utenti iscritti
	 */
	@Override
	public void cancellaEdizione(int idEdizione) throws DAOException {
		try {
			daoC.delete(idEdizione);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * iscrive un utente ad una edizione 
	 * un utente si può iscrivere solo se ci sono ancora posti disponibili considerato che ogni corso a un numero massimo di partecipanti
	 */
	@Override
	public void iscriviUtente(int idEdizione, String idUtente) throws DAOException {
		try {
			daoI.iscriviUtente(idEdizione, idUtente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * cancella l'iscrizione ad un utente
	 */
	@Override
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws DAOException {
		try {
			daoI.cancellaIscrizioneUtente(idEdizione, idUtente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * il metodo ritorna tutte le edizioni con relativi utenti e feedback dei corsi in calendario nel mese indicato dell'anno corrente
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<EdizioneDTO> visualizzaEdizioniPerMese(int mese) throws DAOException {
		ArrayList<Edizione> lista = new ArrayList<Edizione>();
		EdizioneDTO edizDTO = new EdizioneDTO();
		ArrayList<EdizioneDTO> listaDTO = new ArrayList<EdizioneDTO>();
		listaDTO.add(edizDTO);
		mese = mese-1;
		Calendar cal = Calendar.getInstance(); 
		
		cal.set(Calendar.MONTH, mese); 
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date da = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		Date a = cal.getTime();
		
		try {
			lista = daoC.select(da, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaDTO;

	}

	/*
	 * il metodo ritorna tutte le edizioni con relativi utenti e feedback dei corsi in calendario nel mese indicato dell'anno corrente
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<EdizioneDTO> visualizzaEdizioniPerAnno(int anno) throws DAOException {
		anno = anno-1;
		Calendar cal = Calendar.getInstance(); 
		
		cal.set(Calendar.YEAR, anno); 
		cal.set(Calendar.MONTH, 0);
		Date da = cal.getTime();
		cal.set(Calendar.MONTH, 12);
		Date a = cal.getTime();
		try {
			daoC.select(da, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EdizioneDTO edizDTO = new EdizioneDTO();
		ArrayList<EdizioneDTO> listaDTO = new ArrayList<EdizioneDTO>();
		listaDTO.add(edizDTO);
		
		return listaDTO;
	}
	
	/*
	 * il metodo ritorna tutte le edizioni con relativi utenti e feedback del corso specificato presenti in calendario nell'anno corrente a partire dalla data odierna
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<EdizioneDTO> visualizzaEdizioniPerCorso(int idCorso) throws DAOException {
		
		return null;
		
		
	
	}

	/*
	 * il metodo ritorna tutte le edizioni dei corsi e relativi utenti e feedbacks in calendario dell'anno corrente a partire dalla data odierna
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public EdizioneDTO visualizzaEdizione(int idEdizione) throws DAOException {
		try {
			daoC.selectEdizione(idEdizione);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Edizione edizione = new Edizione();
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>() ;
		ArrayList<Utente> utentiIscritti  = new ArrayList<Utente>();
		EdizioneDTO edizDTO = new EdizioneDTO(edizione, feedbacks, utentiIscritti);
		
		
    	return edizDTO;
	}


}
