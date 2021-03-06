package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import entity.Utente;
import exceptions.ConnessioneException;

public class CategoriaDAOImpl implements CategoriaDAO {

	private Connection conn;

	public CategoriaDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();

	}

	/*
	 * inserimento di una nuova categoria
	 * 
	 */
	@Override
	public void insert(String descrizione) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("INSERT INTO categoria(descrizione) VALUES (?)");

		ps.setString(2, descrizione);
		ps.executeUpdate();
	}

	/*
	 * modifica del nome di una categoria. la categoria viene individuata in base al
	 * idCategoria se la categoria non esiste si solleva una eccezione
	 */
	@Override
	public void update(Categoria c) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("UPDATE categoria SET descrizione=? where id_categoria=?");
		ps.setString(2, c.getDescrizione());
		int n = ps.executeUpdate();

		if (n == 0) {
			throw new SQLException("categoria inesistente");
		}

	}

	/*
	 * cancellazione di una singola categoria. una categoria si pu� cancellare solo
	 * se non ci sono dati correlati; se la categoria non esiste o non � cancellabile
	 * si solleva una eccezione
	 */
	@Override
	public void delete(int idCategoria) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM categoria where id_categoria=?");
		ps.setInt(1, idCategoria);
		
		int n = ps.executeUpdate();

		if (n == 0) {
			throw new SQLException("categoria inesistente");
		}

	}

	/*
	 * lettura di una singola categoria in base al suo id se la categoria non esiste
	 * si solleva una eccezione
	 */
	@Override
	public Categoria select(int idCategoria) throws SQLException {
		
		PreparedStatement ps=conn.prepareStatement("SELECT * FROM categoria where id_categoria =?");

		ps.setInt(1, idCategoria);

		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			 idCategoria = rs.getInt("id_categoria");
			String descrizione = rs.getString ("descrizione");
			

			return new Categoria (idCategoria, descrizione);
		}
		else {
			throw new SQLException("categoria: " + idCategoria + " non presente");
		}
		
	}

	/*
	 * lettura di tutte le categorie se non vi sono categoria il metodo ritorna una
	 * lista vuota
	 */
	@Override
	public ArrayList<Categoria> select() throws SQLException {
		
		ArrayList<Categoria> categorie = new ArrayList<Categoria>(); 

		PreparedStatement ps=conn.prepareStatement("SELECT * FROM categoria");

		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int idCategoria = rs.getInt("id_categoria");
			String descrizione= rs.getString("descrizione");
			

			Categoria lettura = new Categoria(idCategoria, descrizione);
			categorie.add(lettura);
		}

		return categorie;
		
	}

}
