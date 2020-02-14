package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;

public class FeedBackDAOImpl implements FeedbackDAO {

	private Connection conn;

	public FeedBackDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/*
	 * inserimento di un singolo feedbak relativo ad una edizione di un corso da
	 * aprte di un utente se un utente ha già inserito un feedback per una certa
	 * edizione si solleva una eccezione
	 */
	@Override
	public void insert(Feedback feedback) throws SQLException {

		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO feedback(id_edizione,id_utente,descrizione,voto) VALUES (?,?,?,?)");

		ps.setInt(2, feedback.getIdEdizione());
		ps.setString(3, feedback.getIdUtente());
		ps.setString(4, feedback.getDescrizione());
		ps.setInt(5, feedback.getVoto());
		ps.executeUpdate();
	}

	/*
	 * modifica di tutti i dati di un singolo feedback. Un feedback viene
	 * individuato attraverso l'idFeedback se il feedback non esiste si solleva una
	 * eccezione
	 */
	@Override
	public void update(Feedback feedback) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(
				"UPDATE feedback SET id_edizione=?, id_utente=?, descrizione=?, voto=? where id_feedback=?");
		ps.setInt(2, feedback.getIdEdizione());
		ps.setString(3, feedback.getIdUtente());
		ps.setString(4, feedback.getDescrizione());
		ps.setInt(5, feedback.getVoto());
		int n = ps.executeUpdate();

		if (n == 0) {

			throw new SQLException("feedback " + feedback.getIdFeedback() + " non presente");
		}

	}

	/*
	 * cancellazione di un feedback se il feedback non esiste si solleva una
	 * eccezione
	 */
	@Override
	public void delete(int idFeedback) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("DELETE FROM SET feedback where id_feedback=?");

		ps.setInt(1, idFeedback);

		int n = ps.executeUpdate();

		if (n == 0)

			throw new SQLException("Feedback non presente");

	}

	/*
	 * lettura di un singolo feedback scritto da un utente per una certa edizione se
	 * il feedback non esiste si solleva una eccezione
	 */
	@Override
	public Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback where id_utente=? and id_edzione=?");

		ps.setInt(2, idEdizione);
		ps.setString(3, idUtente);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			idEdizione = rs.getInt("id_edizione");
			idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			return new Feedback(idEdizione, idUtente, descrizione, voto);
		}else {
			throw new SQLException("Feedback non presente");
		}

		
	}

	/*
	 * lettura di tutti i feedback di una certa edizione se non ci sono feedback o
	 * l'edizione non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException {
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>(); 
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback where id_edzione=?");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			idEdizione = rs.getInt("id_edizione");
			String idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");
			
			Feedback feedback = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedbacks.add(feedback); 
			
		} return feedbacks; 
	}
		
		
		
		

	/*
	 * lettura di tutti i feedback scritti da un certo utente se non ci sono
	 * feedback o l'utente non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException {
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>(); 
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback where id_utente=?");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int idEdizione = rs.getInt("id_edizione");
			idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");
			
			Feedback feedback = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedbacks.add(feedback); 
			
		} return feedbacks; 
	}

	/*
	 * lettura di tutti i feedback scritti per un certo corso (nota: non edizione ma
	 * corso) se non ci sono feedback o il corso non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException {
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>(); 
		PreparedStatement ps = conn.prepareStatement("SELECT f.descrizione from catalogo a join calendario c on (a.id_corso=? = c.id_corso) join feedback f on (f.id_edizione = c.id_edizione");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int idEdizione = rs.getInt("id_edizione");
			String idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");
			
			Feedback feedback = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedbacks.add(feedback); 
			
		} return feedbacks; 
	}
	
	@Override
	public ArrayList<Feedback> selectPerMese(int mese) throws SQLException {
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>(); 
		PreparedStatement ps = conn.prepareStatement("SELECT c.id_edizione, i.id_utente, f.descrizione from calendario c join iscritti i on (c.id_edizione = i.id_edizione) join feedback f on (i.id_utente = f.id_utente) where month(dataInizio) = ? and year(curdate()) = year(dataInizio)");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int idEdizione = rs.getInt("id_edizione");
			String idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");
			
			Feedback feedback = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedbacks.add(feedback); 
			
		} return feedbacks; 
	}
	
	@Override
	public ArrayList<Feedback> selectPerAnno(int anno) throws SQLException {
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>(); 
		PreparedStatement ps = conn.prepareStatement("SELECT c.id_edizione, i.id_utente, f.descrizione from calendario c join iscritti i on (c.id_edizione = i.id_edizione) join feedback f on (i.id_utente = f.id_utente) where year(dataInizio) = ? and month(curdate()) = month(dataInizio)");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			int idEdizione = rs.getInt("id_edizione");
			String idUtente = rs.getString("id_utente");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");
			
			Feedback feedback = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedbacks.add(feedback); 
			
		} return feedbacks; 
	}


}
