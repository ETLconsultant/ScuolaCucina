package entity;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CalendarioDAO;
import dao.CalendarioDAOImpl;
import dao.CatalogoDAO;
import dao.CatalogoDAOImpl;
import exceptions.ConnessioneException;

/*
 * rappresenta il carrello di acquisto da parte di un utente 
 * L'utente può acquistare una o più partecipazioni ad una edizione di un corso
 */
public class Carrello {

	
	private ArrayList<Edizione> edizioniAcquistate=new ArrayList<Edizione>();	//CARRELLO

	/*
	 * aggiunge una edizione nel carrello
	 * se l'edizione già è presente nel carrello questa non va aggiuta
	 */
	public void aggiungiEdizione (Edizione e){
		
		if( !(edizioniAcquistate.contains(e))) {	//se non contiene 'edizione fai aggiunta
			edizioniAcquistate.add(e);
		}
		
	}

	/*
	 * elimina una edizione nel carrello
	 * se l'edizione non è presente nel carrello NON si solleva una eccezione
	 */
	public void rimuoviEdizione(Edizione e){
		
		if(edizioniAcquistate.contains(e)) {
			edizioniAcquistate.remove(e);
		}
	}
	
	/*
	 * legge una edizione presente nel carrello in base ad idEdizione
	 * se l'edizione non esiste il metodo torna null
	 */
	public Edizione getEdizione(int idEdizione) throws ConnessioneException, SQLException{
		
		 CalendarioDAO calendar = new CalendarioDAOImpl() ;	//legge da table calendario
		 Edizione ediz = new Edizione();
		 ediz = calendar.selectEdizione(idEdizione);
		 
		if( edizioniAcquistate.contains(ediz)) {
			return ediz;
		}
		else {
			return null;
		}
		 
	}
	

	/*
	 * recupera tutte le edizioni presente nel carrello
	 */
	public ArrayList<Edizione> getEdizioniAcquistate() throws ConnessioneException {

		return edizioniAcquistate;
	}

	/*
	 * recupera il numero di edizioni presente nel carrello
	 */
	public int getSize(){
		
		return edizioniAcquistate.size();
	}

	/*
	 * il metodo ritorna il costo totale delle edizioni presenti nel carrello 
	 */
	public double getCostoTotale() throws ConnessioneException{
		
		double somma = 0;
		for(int i = 0 ; i < edizioniAcquistate.size(); i ++ ) {
		somma += edizioniAcquistate.get(i).getCorso().getCosto();	//edizioniAcquistate contiene edizioni che contengono corso che contiene costo
		}
		
		return somma;
	}
	@Override
	public String toString() {
		return "Carrello [edizioniAcquistate=" + edizioniAcquistate + "]";
	}
	
}
	
	
	

