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
	 * se l'edizione già è presente nel carrello questa non va aggiunta
	 */
	public void aggiungiEdizione (Edizione e){
		
		if( !(edizioniAcquistate.contains(e))) {	//se non contiene 'edizione fai aggiunta
			edizioniAcquistate.add(e);
			System.out.println("edizione aggiunta al carrello");
		}
		
	}

	/*
	 * elimina una edizione nel carrello
	 * se l'edizione non è presente nel carrello NON si solleva una eccezione
	 */
	public void rimuoviEdizione(Edizione e){
		
		if(edizioniAcquistate.contains(e)) {
			edizioniAcquistate.remove(e);
			System.out.println("edizione rimossa dal carrello");
		}
	}
	
	/*
	 * legge una edizione presente nel carrello in base ad idEdizione
	 * se l'edizione non esiste il metodo torna null
	 */
	public Edizione getEdizione(int idEdizione) throws ConnessioneException, SQLException{
		
		 for (int i = 0; i < edizioniAcquistate.size()	; i++) {
			 if(edizioniAcquistate.get(i).getIdEdizione() == idEdizione) {
				 System.out.println("edizione prelevata dal carrello");
					return edizioniAcquistate.get(i);
			 }
			 else {
				return null;
				}
		 }
		return null;
		 
	}
	

	/*
	 * recupera tutte le edizioni presente nel carrello
	 */
	public ArrayList<Edizione> getEdizioniAcquistate() throws ConnessioneException {

		System.out.println("lista edizioni a carrello");
		return edizioniAcquistate;
	}

	/*
	 * recupera il numero di edizioni presente nel carrello
	 */
	public int getSize(){
		
		System.out.println("numero edizioni a carrello");
		return edizioniAcquistate.size();
	}

	/*
	 * il metodo ritorna il costo totale delle edizioni presenti nel carrello 
	 */
	public double getCostoTotale() throws ConnessioneException{
		
		double somma = 0;
		for(int i = 0 ; i < edizioniAcquistate.size(); i ++ ) {
		if(edizioniAcquistate.get(i).getCorso() != null){
		somma = somma + edizioniAcquistate.get(i).getCorso().getCosto();	//edizioniAcquistate contiene edizioni che contengono corso che contiene costo
		System.out.println("costo totale carrello = " + somma);
		}
		
		}
		
		return somma;
	}
	@Override
	public String toString() {
		return "Carrello [edizioniAcquistate=" + edizioniAcquistate + "]";
	}
	
}
	
	
	

