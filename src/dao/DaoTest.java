package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Edizione;
import entity.Utente;
import exceptions.ConnessioneException;

public class DaoTest {

	public static void main(String[] args) throws ConnessioneException, SQLException {
		// TODO Auto-generated method stub
		CalendarioDAOImpl cal = new CalendarioDAOImpl();
		
		Edizione ed = new Edizione();
		
		Date data = new Date();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("2020/03/04");
		
		dateformat.format(data);
		
		ed.setIdEdizione(101);
		ed.setDurata(15);
		ed.setDataInizio(data);
		ed.setAula("lagrange");
		ed.setDocente("SARA");
		
//		Utente ut = new  Utente();
		
//		cal.delete(93);
//		cal.update(ed);
		
		ArrayList<Edizione> list = new ArrayList<Edizione>();
//		
//		list = cal.select(23);
//		
//		System.out.println(list);
		
//		ed = cal.selectEdizione(92);
		
		list = cal.select("veronica");

		System.out.println(list);
		
		
		
	}

}
