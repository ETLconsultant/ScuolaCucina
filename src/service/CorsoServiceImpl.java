package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CalendarioDAO;
import dao.CalendarioDAOImpl;
import dao.CatalogoDAO;
import dao.CatalogoDAOImpl;
import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.FeedBackDAOImpl;
import dao.FeedbackDAO;
import dto.CorsoDTO;
import dto.EdizioneDTO;
import entity.Categoria;
import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class CorsoServiceImpl implements CorsoService {

	//dichiarare qui tutti i dao di cui si ha bisogno
	private CatalogoDAO daoC;
	private CategoriaDAO daoCat;
	private CalendarioDAO daoCal;
	
	private FeedbackDAO daoF;
	private EdizioneDTO dtoE;
	//... dichiarazione di altri DAO
	
	//costruire qui tutti i dao di cui si ha bisogno
	public  CorsoServiceImpl() throws ConnessioneException{
		daoC = new CatalogoDAOImpl();
		daoCat = new CategoriaDAOImpl();
		daoCal = new CalendarioDAOImpl();
		daoF=new FeedBackDAOImpl();
		dtoE = new EdizioneDTO();
		//... costruzione dei altri dao
	}
	
	/*
	 * il metodo mostra tutti i corsi offerti dalla scuola 
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<Corso> visualizzaCatalogoCorsi() throws DAOException {
		try {
			return daoC.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);
			
		}
	}

	/*
	 * il metodo mostra l'elenco dei corsi di una certa categorie
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale 
	 */
	@Override
	public ArrayList<Corso> visualizzaCorsiPerCategoria(int idCategoria) throws DAOException {
		try {
			return daoC.selectByIdCategoria(idCategoria);
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);
			
		}
		
		 
	}
	
	/*
	 * lettura di tutte le categorie
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale 
	 */
	@Override
	public ArrayList<Categoria> visualizzaCategorie() throws DAOException {
		
		try {
			return daoCat.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);
		}
		
	
		
	}
	
	/*
	 * il metodo (invocabile solo da un amministratore) crea una nuova categoria
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void creaNuovaCategoria(String descrizione) {
		Utente u = new Utente();
		if(u.isAdmin()) {
			try {
				daoCat.insert(descrizione);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

	/*
	 * ritorna un oggetto CorsoDTO con tutti i dati di un singolo corso 
	 * tutte le edizioni di quel corso con relativi feedbacks (classe EdizioneDTO)
	 * il corso è individuato tramite idCorso
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public CorsoDTO visualizzaSchedaCorso(int idCorso) throws DAOException, SQLException { 
		ArrayList<Edizione> lista = new ArrayList<Edizione>();
		lista= daoCal.select();
		for(Edizione ediz : lista ) {
			if(ediz.getIdCorso()==idCorso) {
				Corso corso = new Corso();
				corso = daoC.select(idCorso);
				
			}
		}
	
		
		CorsoDTO dtoC = new CorsoDTO();
		
		
		return dtoC;
	}

	/*
	 * ritorna una lista con tutti i feedbacks relativi ad un corso 
	 * il corso è individuato tramite idCorso
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<Feedback> visualizzaFeedbackCorso(int idCorso) throws DAOException {
		try {
			return daoF.selectFeedbackPerCorso(idCorso);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("errore nel recuperare o leggere i dati", e);
		}
	}

	/*
	 * modifica tutti i dati di un corso
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void modificaDatiCorso(Corso corso) throws DAOException {
		try {
			daoC.update(corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * inserisce un nuovo corso a catalogo (invocabile solo dall'amministratore)
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void inserisciCorso(Corso corso) throws DAOException {
		try {
			daoC.insert(corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * cancella un singolo corso dal catalogo dei corsi
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale 
	 */ 
	@Override
	public void cancellaCorso(int codiceCorso) throws DAOException {
		try {
			daoC.delete(codiceCorso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * legge i dati di un singolo corso (senza edizioni)
	 * se il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public Corso visualizzaCorso(int codiceCorso) throws DAOException {
		
		try {
			return daoC.select(codiceCorso);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("errore nel recuperare o leggere i dati", e);
		}
	}

}
