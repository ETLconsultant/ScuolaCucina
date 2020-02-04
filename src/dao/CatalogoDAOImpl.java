package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Categoria;
import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;

public class CatalogoDAOImpl implements CatalogoDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet resultset; 

	public CatalogoDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	/*
	 * registrazione di un nuovo corso nel catalogo dei corsi
	 */
	@Override
	public void insert(Corso corso) throws SQLException {
		
		String query = "INSERT INTO catalogo(titolo, id_categoria, numeromaxpartecipanti, costo, descrizione) VALUES (?,?,?,?,?)";
		
		ps = conn.prepareStatement(query, ps.RETURN_GENERATED_KEYS);
		
		ps.setString(1, corso.getTitolo());
		ps.setInt(2, corso.getIdCategoria() );
		ps.setInt(3, corso.getMaxPartecipanti());
		ps.setDouble(4, corso.getCosto());
		ps.setString(5, corso.getDescrizione());
		
		int count = ps.executeUpdate();
		
		resultset = ps.getGeneratedKeys();
		if (resultset.next()) {
			System.out.println("Auto Generated Primary Key " + resultset.getInt(1));
			corso.setCodice(resultset.getInt(1));
		}
		if(count>0) {
			System.out.println("Feedback inserito correttamente");
		}
	}

	/*
	 * modifica di tutti i dati di un corso nel catalogo dei corsi
	 * il corso viene individuato in base al idCorso
	 * se il corso non esiste si solleva una eccezione
	 */
	@Override
	public void update(Corso corso) throws SQLException {
		
		Corso c1 = new Corso();
		c1 = select(corso.getCodice());
	
		ps=conn.prepareStatement("UPDATE catalogo SET titolo=?, id_categoria=?, numeromaxpartecipanti=?, costo=?, descrizione=? where id_corso=?");
		
		ps.setString(1, corso.getTitolo());
		ps.setInt(2, corso.getIdCategoria() );
		ps.setInt(3, corso.getMaxPartecipanti());
		ps.setDouble(4, corso.getCosto());
		ps.setString(5, corso.getDescrizione());
		ps.setInt(6, corso.getCodice());
		
		int n = ps.executeUpdate();
		if(n==0)
			throw new SQLException("corso: " + corso.getCodice() + " non presente");

	}

	/*
	 * cancellazione di un nuovo corso nel catalogo dei corsi
	 * questo potrà essere cancellato solo se non vi sono edizioni di quel corso o qualsiasi altro legame con gli altri dati A CALENDARIO
	 * Se il corso non esiste si solleva una eccezione
	 * Se non è cancellabile si solleva una eccezione
	 */
	@Override
	public void delete(int idCorso) throws SQLException {
		
		
		PreparedStatement ps = conn.prepareStatement("DELETE FROM catalogo WHERE id_corso=? and id_corso NOT IN calendario ");
		ps.setInt(1, idCorso);
		int n = ps.executeUpdate();
		if(n==0)
			throw new SQLException("corso con id " + idCorso + " non presente");
	}

	/*
	 * lettura di tutti i corsi dal catalogo
	 * se non ci sono corsi nel catalogo il metodo torna una lista vuota
	 */
	@Override
	public ArrayList<Corso> select() throws SQLException {
		
		ArrayList<Corso> corsi = new ArrayList<Corso>(); 

		PreparedStatement ps=conn.prepareStatement("SELECT * FROM catalogo");

		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			
			String titolo= rs.getString("titolo");
			int id_categoria= rs.getInt("id_categoria");
			int numeromaxpartecipanti= rs.getInt("numeromaxpartecipanti");
			double costo =  rs.getDouble("costo");
			String descrizione= rs.getString("descrizione");
			

			Corso corso = new Corso( titolo, id_categoria, numeromaxpartecipanti, costo, descrizione);
			corsi.add(corso);
		}

		return corsi;
	}
	
public ArrayList<Corso> selectByIdCategoria(int idCategoria) throws SQLException {
		
		ArrayList<Corso> corsi = new ArrayList<Corso>(); 

		PreparedStatement ps=conn.prepareStatement("SELECT * FROM catalogo where id_categoria = ? ");

		ps.setInt(1, idCategoria );
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			
			
			String titolo= rs.getString("titolo");
			int id_categoria= rs.getInt("id_categoria");
			int numeromaxpartecipanti= rs.getInt("numeromaxpartecipanti");
			double costo =  rs.getDouble("costo");
			String descrizione= rs.getString("descrizione");
			

			Corso corso = new Corso( titolo, id_categoria, numeromaxpartecipanti, costo, descrizione);
			corsi.add(corso);
		}

		return corsi;
	}

	/*
	 * lettura di un singolo corso dal catalogo dei corsi
	 * se il corso non è presente si solleva una eccezione
	 */
	@Override
	public Corso select(int idCorso) throws SQLException {
		
		PreparedStatement ps=conn.prepareStatement("SELECT * FROM catalogo where id_corso =?");

		ps.setInt(1, idCorso);

		ResultSet rs = ps.executeQuery();
		Corso corso =null;
		
		if(rs.next()){
			String titolo= rs.getString("titolo");
			int id_categoria= rs.getInt("id_categoria");
			int numeromaxpartecipanti= rs.getInt("numeromaxpartecipanti");
			double costo =  rs.getDouble("costo");
			String descrizione= rs.getString("descrizione");

			corso = new Corso(titolo, id_categoria, numeromaxpartecipanti, costo, descrizione);
			return corso;
		}
		else
			throw new SQLException("corso: " + idCorso + " non presente");
	}
	
	
	public static void main(String[] args) throws Exception{
		CatalogoDAO catdao= new CatalogoDAOImpl();
		Corso c2 = new Corso("miocorso 33", 48, 33, 250, "nuovo corso inutile");
		
//		catdao.insert(c);
		
//		catdao.delete(99);
		catdao.update(c2);
		
//		u.setCognome("Doria");
//		catdao.delete("aa");
	
//		System.out.println(catdao.select("marco81"));
	}


}
