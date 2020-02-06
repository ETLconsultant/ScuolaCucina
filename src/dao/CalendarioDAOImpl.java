package dao;

import java.sql.Connection;

import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;



public class CalendarioDAOImpl implements CalendarioDAO {

	private Connection conn;
	private PreparedStatement ps;

	public CalendarioDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	CatalogoDAOImpl catalogoDAOImpl = new CatalogoDAOImpl();
	

	/*
	 * registrazione di una nuova edizione nel calendario dei corsi
	 */
	@Override
	public void insert(Edizione ed) throws SQLException{
		
		String query = "insert into calendario (id_corso, dataInizio, durata, aula, docente) values (?,?,?,?,?)";
	
		ps = conn.prepareStatement(query, ps.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, ed.getIdCorso());
		ps.setDate(2, new java.sql.Date(ed.getDataInizio().getTime()));
		ps.setInt(3, ed.getDurata());
		ps.setString(4, ed.getAula());
		ps.setString(5, ed.getDocente());
		
		int numero = ps.executeUpdate(); 
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			System.out.println("Auto Generated Primary Key " + rs.getInt(1));
			ed.setIdEdizione(rs.getInt(1));
		}
		if(numero>0) {
			System.out.println("Edizione aggiunta correttamente");
		}else {
			System.out.println("Errore! Edizione non aggiunta");
		}
	}


	/*
	 * cancellazione di una edizione presente nel calendario dei corsi
	 * per cancellare una edizione è necessario prima cancellare le eventuali iscrizioni degli utenti e i feedbacks  
	 * l'edizione viene individuata in base a idEdizione
	 * se l'edizione non è presente si solleva una eccezione
	 */
	@Override
	public void delete(int idEdizione) throws SQLException {
		// TODO Auto-generated method stub

		//Eliminazione feedback
		String query = "delete from feedback where id_edizione = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, idEdizione);
		int numeroRighe = ps.executeUpdate();
		if(numeroRighe>0) {
			System.out.println("feedback cancellati");
		}
		//Eliminazione iscrizioni
				query = "delete from iscritti where id_edizione = ?";
				
				 ps = conn.prepareStatement(query);
				ps.setInt(1, idEdizione);
				numeroRighe = ps.executeUpdate();
				if(numeroRighe>0) {
					System.out.println("iscrizione cancellata");
				}
		//Eliminazione edizione
		query = "delete from calendario where id_edizione = ?";

		ps = conn.prepareStatement(query);
		ps.setInt(1, idEdizione);
		numeroRighe = ps.executeUpdate();
		if(numeroRighe>0) {
			System.out.println("Edizione cancellata");
		}else if(numeroRighe == 0) {
			try {
				throw new SQLException("L'edizione non esiste");
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	}


	/*
	 * modifica di tutti i dati di una edizione presente nel calendario dei corsi
	 * l'edizione viene individuata in base al idEdizione
	 * se l'edizione non è presente si solleva una eccezione
	 */
	@Override
	public void update(Edizione ed) throws SQLException{
			PreparedStatement ps=conn.prepareStatement("update calendario set  dataInizio=?, durata=?, aula=?, docente=? where id_edizione= ?");

			
			ps.setDate(1,new java.sql.Date(ed.getDataInizio().getTime()));
			ps.setInt(2,ed.getDurata());
			ps.setString(3,ed.getAula());
			ps.setString(4,ed.getDocente());
			ps.setInt(5, ed.getIdEdizione());

			int n = ps.executeUpdate();
			if(n==0) throw new SQLException("edizione " + ed.getIdEdizione() + " non presente");

	}


	/*
	 * lettura di tutte le edizioni di una certa categoria, presenti nel calendario dei corsi
	 * le edizioni vengono individuate in base al idCategoria
	 * se non vi sono edizioni per quella categoria o la categoria non esiste viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(int idCategoria) throws SQLException{
			ArrayList<Edizione> edizioni=new ArrayList<Edizione>();
			PreparedStatement ps=conn.prepareStatement("select * from calendario, catalogo where calendario.id_corso = catalogo.id_corso and id_categoria=?");
			
			
			ps.setInt(1, idCategoria);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				int idEdizione=rs.getInt("id_Edizione");
				int idCorso=rs.getInt("id_corso");
				Date dataInizio=rs.getDate("dataInizio");
				int durata=rs.getInt("durata");
				String aula=rs.getString("aula");
				String docente=rs.getString("docente");

				Corso corso = catalogoDAOImpl.select(idCorso);
				
				Edizione ediz =new Edizione(idCorso,dataInizio,durata,aula,docente);
				ediz.setIdEdizione(idEdizione);
				ediz.setCorso(corso);

				long dataM = dataInizio.getTime();
				long durataM= durata*86400000L;
				Date dataFine = new Date(dataM+durataM);
			

				if (dataFine.before(new java.util.Date()))
					ediz.setTerminata(true);	

				edizioni.add(ediz);

			}

			return edizioni;

	}


	/*
	 * lettura dei dati di una edizione presente nel calendario dei corsi
	 * l'edizione viene individuata in base al idEdizione
	 * se l'edizione non è presente si solleva una eccezione
	 */
	@Override
	public Edizione selectEdizione(int idEdizione) throws SQLException{
	  
		PreparedStatement ps=conn.prepareStatement("select * from calendario where id_edizione = ?");
		ps.setInt(1, idEdizione);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()){
			int idCorso=rs.getInt("id_corso");
			Date dataInizio=rs.getDate("dataInizio");
			int durata=rs.getInt("durata");
			String aula=rs.getString("aula");
			String docente=rs.getString("docente");
			
			Corso corso = catalogoDAOImpl.select(idCorso);

			Edizione ed = new Edizione(idCorso,dataInizio,durata,aula,docente);
			ed.setIdEdizione(idEdizione);
			ed.setCorso(corso);
			

			long dataM = dataInizio.getTime();
			long durataM= durata*86400000L;
			Date dataFine = new Date(dataM+durataM);

			if (dataFine.before(new java.util.Date()))
				ed.setTerminata(true);
			return ed;
		} else {
			try {
				
				throw new SQLException("edizione " + idEdizione + " non presente");
			
			}catch (SQLException e) {
					e.printStackTrace();
					return null;
			}
			
		}
	}

	/*
	 * lettura di tutte le edizioni presenti nel calendario delle edizioni
	 * se non vi sono edizioni registrate viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select()throws SQLException{
	  
		ArrayList<Edizione> edizioni=new ArrayList<Edizione>();
		PreparedStatement ps=conn.prepareStatement("select * from calendario");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			int idEdizione=rs.getInt("id_Edizione");
			int idCorso=rs.getInt("id_corso");
			Date dataInizio=rs.getDate("dataInizio");
			int durata=rs.getInt("durata");
			String aula=rs.getString("aula");
			String docente=rs.getString("docente");

			Corso corso = catalogoDAOImpl.select(idCorso);
			
			Edizione ed = new Edizione(idCorso,dataInizio,durata,aula,docente);
			ed.setIdEdizione(idEdizione);
			ed.setCorso(corso);

			long dataM = dataInizio.getTime();
			long durataM= durata*86400000L;
			Date dataFine = new Date(dataM+durataM);

			if (dataFine.before(new java.util.Date()))
				ed.setTerminata(true);
			
			edizioni.add(ed);
		}
		return edizioni;
	  

		
	}

	
	/*
	 * lettura di tutte le edizioni a cui un certo utente è iscritto o è stato iscritto in passato , presenti nel calendario delle edizioni
	 * le edizioni vengono individuate in base al idUtente 
	 * se non vi sono edizioni per quell'utente o l'utente non esiste viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(String idUtente) throws SQLException{

			ArrayList<Edizione> edizioni=new ArrayList<Edizione>();
			PreparedStatement ps=conn.prepareStatement("select calendario.* from calendario,iscritti where calendario.id_edizione=iscritti.id_edizione and iscritti.id_utente=?");

			ps.setString(1, idUtente);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				int idEdizione=rs.getInt("id_Edizione");
				int idCorso=rs.getInt("id_corso");
				Date dataInizio=rs.getDate("dataInizio");
				int durata=rs.getInt("durata");
				String aula=rs.getString("aula");
				String docente=rs.getString("docente");

				Corso corso = catalogoDAOImpl.select(idCorso);
				Edizione e=new Edizione(idCorso,dataInizio,durata,aula,docente);
				e.setIdEdizione(idEdizione);
				e.setCorso(corso);

				long dataM = dataInizio.getTime();
				long durataM= durata*86400000L;
				Date dataFine = new Date(dataM+durataM);
				java.util.Date d =  new java.util.Date();

				if (dataFine.before(d) )
					e.setTerminata(true);	
				
				edizioni.add(e);
			}

			return edizioni;
	}

	/*
	 * leggere tutte le edizioni presenti nel calendario nel range delle date da, a (inclusi)
	 * se non vi sono edizioni in quel range di date viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(java.util.Date da, java.util.Date a) throws SQLException {
		//		long diffInMillies = Math.abs(a.getTime() - da.getTime());
		//		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		String query = "select * from calendario where (dataInizio between ? and ?)";

		PreparedStatement statement = conn.prepareStatement(query);
		//		new java.sql.Date sqlDa = java.sql.Date(da);
		statement.setDate(1, new java.sql.Date(da.getTime())); 
		statement.setDate(2, new java.sql.Date(a.getTime()));
		System.out.println("statement: " + statement);

		ResultSet resultset  = statement.executeQuery();

		ArrayList<Edizione> arrayList = new ArrayList<Edizione>();

		while(resultset.next()) {
			System.out.println("nel while");
			int id_edizione = resultset.getInt("id_edizione");
			int id_corso = resultset.getInt("id_corso");
			Date dataInizio = resultset.getDate("dataInizio");
			long durata = resultset.getLong("durata");
			String aula = resultset.getString("aula");
			String docente = resultset.getString("docente");

			Corso corso = catalogoDAOImpl.select(id_corso);
			
			Calendar todayCalendar = Calendar.getInstance();
			todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
			todayCalendar.set(Calendar.MINUTE, 0);
			todayCalendar.set(Calendar.SECOND, 0);

			Date today = todayCalendar.getTime();

			boolean terminata = (today.getTime() > (dataInizio.getTime() + TimeUnit.MILLISECONDS.convert(durata, TimeUnit.MILLISECONDS)));
			Edizione e = new Edizione();

			e.setAula(aula);
			e.setIdEdizione(id_edizione);
			e.setCorso(corso);
			e.setDataInizio(dataInizio);
			e.setDocente(docente);
			e.setDurata((int)durata);
			e.setIdCorso(id_corso);
			e.setTerminata(terminata);

			arrayList.add(e);


		}

		return arrayList;
	}

	/*
	 * lettura di tutte le edizioni di una certa categoria, presenti nel calendario dei corsi
	 * se future = true, le edizioni lette devono essere solo quelle a partire dalla data in odierna e dell'anno corrente 
	 * se future = false devono essere lette tutte le edizioni
	 * le edizioni vengono individuate in base al idCategoria
	 * se non vi sono edizioni per quella categoria o la categoria non esiste viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(int idCaregotia, boolean future) throws SQLException {
		ArrayList<Edizione> arrayList = new ArrayList<Edizione>();

		for(Edizione edizione : select(future)) {
			if(edizione.getCorso().getIdCategoria() == idCaregotia) {
				int idCorso = edizione.getIdCorso();
				Corso corso = catalogoDAOImpl.select(idCorso);
				
				edizione.setCorso(corso);
				
				arrayList.add(edizione);
			}
		}

		return arrayList;

	}

	/*
	 * lettura di tutte le edizioni presenti nel calendario dei corsi
	 * se future = true, le edizioni lette devono essere solo quelle a partire dalla data in odierna e dell'anno corrente 
	 * se future = false devono essere lette tutte le edizioni
	 * se non vi sono edizioni viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(boolean future) throws SQLException {

		ArrayList<Edizione> arrayList = new ArrayList<Edizione>();

		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		todayCalendar.set(Calendar.MINUTE, 0);
		todayCalendar.set(Calendar.SECOND, 0);

		Date today = todayCalendar.getTime();



		String minString = "01/01/1000";
		String maxString = "31/12/9999";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date minDate = format.parse(minString);
			Date maxDate = format.parse(maxString);
			System.out.println("da " + minDate);
			System.out.println("a " + maxDate);

			if(future == true) {
				arrayList = select(today, maxDate);
			} else {
				arrayList = select(minDate, maxDate);
			}


		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;




	}

	/*
	 * lettura di tutte le edizioni a cui è iscritto una certo utente, presenti nel calendario dei corsi
	 * se future = true, le edizioni lette devono essere solo quelle a partire dalla data in odierna e dell'anno corrente 
	 * se future = false devono essere lette tutte le edizioni
	 * le edizioni vengono individuate in base al idUtente
	 * se non vi sono edizioni per quella categoria o la categoria non esiste viene ritornata una lista vuota
	 */
	@Override
	public ArrayList<Edizione> select(String idUtente, boolean future) throws SQLException {
		String query;
		PreparedStatement statement;
		ArrayList<Edizione> arrayList = new ArrayList<Edizione>();
		
		
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		todayCalendar.set(Calendar.MINUTE, 0);
		todayCalendar.set(Calendar.SECOND, 0);

		Date today = todayCalendar.getTime();
		

		if(future == true) {
			
			
			query = "select calendario.*, catalogo.* from calendario, catalogo,"
					+ " iscritti where iscritti.id_utente = ? and "
					+ "iscritti.id_edizione = calendario.id_edizione and "
					+ "calendario.id_corso = catalogo.id_corso and "
					+ "calendario.dataInizio > ?";
			
			statement = conn.prepareStatement(query);

			statement.setDate(2, new java.sql.Date(today.getTime()));
			
		} else {
			
			query = "select calendario.*, catalogo.* from calendario, catalogo,"
					+ " iscritti where iscritti.id_utente = ? and "
					+ "iscritti.id_edizione = calendario.id_edizione and "
					+ "calendario.id_corso = catalogo.id_corso";
			
			statement = conn.prepareStatement(query);
			
		}
		
		
		
		statement.setString(1, idUtente);
		System.out.println("statement: " + statement);

		ResultSet resultset  = statement.executeQuery();

//		statement.setInt(parameterIndex, x);
		System.out.println("statement: " + statement);
		
		while(resultset.next()) {
			int id_edizione = resultset.getInt("id_edizione");
			int id_corso = resultset.getInt("id_corso");
			Date dataInizio = resultset.getDate("dataInizio");
			long durata = resultset.getLong("durata");
			String aula = resultset.getString("aula");
			String docente = resultset.getString("docente");
			
			String titolo = resultset.getString("titolo");
			int id_categoria = resultset.getInt("id_categoria");
			int numeroMaxPartecipanti = resultset.getInt("numeroMaxPartecipanti");
			double costo = resultset.getDouble("costo");
			String descrizione = resultset.getString("descrizione");
			
			Corso corso = new Corso();
			corso.setCodice(id_corso);
			corso.setCosto(costo);
			corso.setDescrizione(descrizione);
			corso.setIdCategoria(id_categoria);
			corso.setMaxPartecipanti(numeroMaxPartecipanti);
			corso.setTitolo(titolo);
			
			Edizione edizione = new Edizione();
			edizione.setAula(aula);
			edizione.setIdEdizione(id_edizione);
			edizione.setCorso(corso);
			edizione.setDataInizio(dataInizio);
			edizione.setDocente(docente);
			edizione.setDurata((int)durata);
			edizione.setIdCorso(id_corso);
			edizione.setIdEdizione(id_edizione);
			
			
			boolean terminata = (today.getTime() > (dataInizio.getTime() + TimeUnit.MILLISECONDS.convert(durata, TimeUnit.MILLISECONDS)));
			edizione.setTerminata(terminata);
			
			arrayList.add(edizione);

		}
		
		return arrayList;
		

	}
}
