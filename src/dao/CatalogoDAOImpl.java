package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Categoria;
import entity.Corso;
import entity.Feedback;
import exceptions.ConnessioneException;

public class CatalogoDAOImpl implements CatalogoDAO {

	private Connection conn;

	public CatalogoDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/*
	 * registrazione di un nuovo corso nel catalogo dei corsi
	 */
	@Override
	public void insert(Corso corso) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO catalogo(titolo, id_categoria,numeroMaxPartecipanti, costo, descrizione) VALUES (?,?,?,?,?,?)");

		ps.setString(2, corso.getTitolo());
		ps.setInt(3, corso.getIdCategoria());
		ps.setInt(4, corso.getMaxPartecipanti());
		ps.setDouble(5, corso.getCosto());
		ps.setString(6, corso.getDescrizione());

		ps.executeUpdate();

	}

	/*
	 * modifica di tutti i dati di un corso nel catalogo dei corsi il corso viene
	 * individuato in base al idCorso se il corso non esiste si solleva una
	 * eccezione
	 */
	@Override
	public void update(Corso corso) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE catalogo SET titolo=?, id_categoria=?, numeroMaxPartecipanti=?, costo=?, descrizione=?) VALUES (?,?,?,?,?,?)");

		ps.setString(2, corso.getTitolo());
		ps.setInt(3, corso.getIdCategoria());
		ps.setInt(4, corso.getMaxPartecipanti());
		ps.setDouble(5, corso.getCosto());
		ps.setString(6, corso.getDescrizione());

		int n = ps.executeUpdate();

		if (n == 0) {
			throw new SQLException("corso inesistente");
		}

	}

	/*
	 * cancellazione di un nuovo corso nel catalogo dei corsi questo potrà essere
	 * cancellato solo se non vi sono edizioni di quel corso o qualsiasi altro
	 * legame con gli altri dati Se il corso non esiste si solleva una eccezione Se
	 * non è cancellabile si solleva una eccezione
	 */
	@Override
	public void delete(int idCorso) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM catalogo where id_corso=?");
		ps.setInt(1, idCorso);

		int n = ps.executeUpdate();

		if (n == 0) {
			throw new SQLException("categoria inesistente");
		}

	}

	/*
	 * lettura di tutti i corsi dal catalogo se non ci sono corsi nel catalogo il
	 * metodo torna una lista vuota
	 */
	@Override
	public ArrayList<Corso> select() throws SQLException {

		ArrayList<Corso> corsi = new ArrayList<Corso>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM catalogo");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			String titolo = rs.getString("titolo");
			int idCategoria = rs.getInt("id_categoria");
			int maxPartecipanti = rs.getInt("numeroMaxPartecipanti");
			double costo = rs.getDouble("costo");
			String descrizione = rs.getString("descrizione");

			Corso lettura = new Corso(titolo, idCategoria, maxPartecipanti, costo, descrizione);
			corsi.add(lettura);
		}

		return corsi;

	}

	/*
	 * lettura di un singolo corso dal catalogo dei corsi se il corso non è presente
	 * si solleva una eccezione
	 */
	@Override
	public Corso select(int idCorso) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM catalogo where id_corso =?");

		ps.setInt(1, idCorso);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			idCorso = rs.getInt("id_corso");
			String titolo = rs.getString("titolo");
			int idCategoria = rs.getInt("id_categoria");
			int maxPartecipanti = rs.getInt("numeroMaxPartecipanti");
			double costo = rs.getDouble("costo");
			String descrizione = rs.getString("descrizione");

			return new Corso(titolo, idCategoria, maxPartecipanti, costo, descrizione);
		} else {
			throw new SQLException("corso: " + idCorso + " non presente");
		}

	}

}
