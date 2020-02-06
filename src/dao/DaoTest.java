package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Carrello;
import entity.Edizione;
import entity.Utente;
import exceptions.ConnessioneException;

public class DaoTest {

	public static void main(String[] args) throws ConnessioneException, SQLException {
		// TODO Auto-generated method stub
		CalendarioDAOImpl cal = new CalendarioDAOImpl();
		
		
		
		Date data = new Date();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("2020/03/04");
		
		dateformat.format(data);
		
		Edizione ed = new Edizione();
//		ed.setIdEdizione(95);
//		ed.setDurata(15);
//		ed.setDataInizio(data);
//		ed.setAula("lagrange");
//		ed.setDocente("SARA");
		
		Edizione ed2 = new Edizione();
		ed2.setIdEdizione(99);
		ed2.setDurata(7);
		ed2.setDataInizio(data);
		ed2.setAula("magna cum laude");
		ed2.setDocente("giogio");
		
		Edizione ed3 = new Edizione();
		ed3.setIdEdizione(12);
		ed3.setDurata(3);
		ed3.setDataInizio(data);
		ed3.setAula("lucifer");
		ed3.setDocente("luciana");
//		Utente ut = new  Utente();
		
//		cal.delete(93);
//		cal.update(ed);
		
		ArrayList<Edizione> list = new ArrayList<Edizione>();
//		
//		list = cal.select(23);
//		
//		System.out.println(list);
		
//		ed = cal.selectEdizione(92);
		
//		list = cal.select("veronica");
//
//		System.out.println(list);
	
		
//		IscrizioneUtenteDAOImpl ut = new IscrizioneUtenteDAOImpl();
		
//		ut.iscriviUtente(96, "francesco");
//		ut.cancellaIscrizioneUtente(96, "francesco");
//		ut.iscriviUtente(96, "francesco");
//		ut.iscriviUtente(97, "fausto");
//		System.out.println(ut.selectIscrizioniUtente("francesco"));
//		System.out.println(ut.selectUtentiPerEdizione(90));
//		System.out.println(ut.getNumeroIscritti(97));
		
		ed = cal.selectEdizione(95);
		
		Carrello bag = new Carrello();
		
		bag.aggiungiEdizione(ed);
		bag.aggiungiEdizione(ed2);
		bag.aggiungiEdizione(ed3);
		
		Edizione c = new Edizione();
		

		
		 list = bag.getEdizioniAcquistate();
		 
		 System.out.println(list);
		 
		 
			c = bag.getEdizione(95);
			System.out.println(c);
		
	}
	
	

}
