package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Categoria;
import entity.Utente;
import exceptions.ConnessioneException;

public class CategoriaDAOImpl implements CategoriaDAO {

	private Connection conn;
	private PreparedStatement prepared;
	private ResultSet resultset; 

	public CategoriaDAOImpl() throws ConnessioneException{
		conn = SingletonConnection.getInstance();
	}
	
	/*
	 * inserimento di una nuova categoria
	 * 
	 */
	@Override
	public void insert(Categoria c) throws SQLException {
		
		
		String query = "insert into categoria(descrizione) values (?)";

		prepared= conn.prepareStatement(query, prepared.RETURN_GENERATED_KEYS);
		prepared.setString(1, c.getDescrizione());
		
		int numero = prepared.executeUpdate();
		resultset = prepared.getGeneratedKeys();
		if(numero>0) {
			resultset.next();
			System.out.println("Auto Generated Primary Key " + resultset.getInt(1));
			c.setIdCategoria(resultset.getInt(1));
			System.out.println("Categoria inserita correttamente");
		}
		
	}
	/*
	 * modifica del nome di una categoria.
	 * la categoria viene individuata in base al idCategoria
	 * se la categoria non esiste si solleva una eccezione
	 */
	@Override
	public void update(Categoria c) throws SQLException {
		
		PreparedStatement ps=conn.prepareStatement("UPDATE categoria SET descrizione=? where id_categoria=?");
		ps.setString(1, c.getDescrizione());
		ps.setInt(2, c.getIdCategoria());
		
		int n = ps.executeUpdate();
		if(n==0)
			throw new SQLException("Categoria: " + c.getIdCategoria() + " non presente");
		
	}

	/*
	 * cancellazione di una singola categoria
	 * una categoria si può cancellare solo se non ci sono dati correlati
	 * se la categoria non esiste o non è cancellabile si solleva una eccezione
	 */
	@Override
	public void delete(int idCategoria) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("DELETE from categoria WHERE id_categoria=?");
		ps.setInt(1, idCategoria);
		int n = ps.executeUpdate();
		if(n==0)
			throw new SQLException("Categoria: " + idCategoria + " non presente");
		
	}

	/*
	 * lettura di una singola categoria in base al suo id
	 * se la categoria non esiste si solleva una eccezione
	 */
	@Override
	public Categoria select(int idCategoria) throws SQLException {

		PreparedStatement ps=conn.prepareStatement("SELECT * FROM categoria where id_categoria =?");

		ps.setInt(1, idCategoria);

		ResultSet rs = ps.executeQuery();
		Categoria cat = null;
		
		if(rs.next()){
			int id_cat = rs.getInt("id_categoria");
			String descrizione = rs.getString("descrizione");
			

			cat = new Categoria(id_cat, descrizione);
			return cat;
		}
		else
			throw new SQLException("categoria: " + idCategoria + " non presente");
	}
	
	/*
	 * lettura di tutte le categorie
	 * se non vi sono categoria il metodo ritorna una lista vuota
	 */
	@Override
	public ArrayList<Categoria> select() throws SQLException {

		ArrayList<Categoria> lista_categorie = new ArrayList<Categoria>(); 

		PreparedStatement ps=conn.prepareStatement("SELECT * FROM categoria");

		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int id_cat = rs.getInt("id_categoria");
			String descrizione = rs.getString("descrizione");

			Categoria cat = new Categoria(id_cat, descrizione);
			lista_categorie.add(cat);
			
		}

		return lista_categorie;
	}

}
