package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CatalogoDAO;
import dao.CatalogoDAOImpl;
import dao.CategoriaDAO;
import dao.FeedbackDAO;
import dto.CorsoDTO;
import dto.EdizioneDTO;
import entity.Categoria;
import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class CorsoServiceImpl implements CorsoService {

	private CatalogoDAO daoCatalogo;
	private CategoriaDAO daoCategoria;
	private FeedbackDAO daoFeedback;

	// costruire qui tutti i dao di cui si ha bisogno
	public CorsoServiceImpl() throws ConnessioneException {
		daoCatalogo = new CatalogoDAOImpl();

	}

	/*
	 * il metodo mostra tutti i corsi offerti dalla scuola se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public ArrayList<Corso> visualizzaCatalogoCorsi() throws DAOException {
		try {
			return daoCatalogo.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);

		}
	}

	/*
	 * il metodo mostra l'elenco dei corsi di una certa categorie se il metodi
	 * del/dei DAO invocati sollevano una eccezione, il metodo deve tornare una
	 * DAOException con all'interno l'exception originale
	 */
	@Override
	public ArrayList<Corso> visualizzaCorsiPerCategoria(int idCategoria) throws DAOException {
		try {
			return daoCatalogo.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);

		}

	}

	/*
	 * lettura di tutte le categorie se il metodi del/dei DAO invocati sollevano una
	 * eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public ArrayList<Categoria> visualizzaCategorie() throws DAOException {
		try {
			return daoCategoria.select();
		} catch (SQLException e) {
			throw new DAOException("errore nel recuperare o leggere i dati", e);

		}
	}

	/*
	 * il metodo (invocabile solo da un amministratore) crea una nuova categoria se
	 * il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve
	 * tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void creaNuovaCategoria(String descrizione) throws DAOException {
		try {
			daoCategoria.insert(descrizione);
		} catch (SQLException e) {
			throw new DAOException("errore nella creazione della categoria", e);

		}
	}

	/*
	 * ritorna un oggetto CorsoDTO con tutti i dati di un singolo corso tutte le
	 * edizioni di quel corso con relativi feedbacks (classe EdizioneDTO) il corso è
	 * individuato tramite idCorso se il metodi del/dei DAO invocati sollevano una
	 * eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public CorsoDTO visualizzaSchedaCorso(int idCorso) throws DAOException {

		try {

			ArrayList<EdizioneDTO> datied = new ArrayList<EdizioneDTO>();
			Edizione edizione = new Edizione();
			List<Utente> utenti = new ArrayList<>();
			List<Feedback> datifeedback = new ArrayList<>();
			datifeedback = daoFeedback.selectFeedbackPerCorso(idCorso);
			EdizioneDTO dati = new EdizioneDTO(edizione, datifeedback, utenti);
			datied.add(dati);

			Corso corso = new Corso();
			corso = daoCatalogo.select(idCorso);
			CorsoDTO corsodati = new CorsoDTO(corso, datied);

			return corsodati;

		} catch (SQLException e) {
			throw new DAOException("errore nella visualizzazione della scheda", e);

		}
	}

	/*
	 * ritorna una lista con tutti i feedbacks relativi ad un corso il corso è
	 * individuato tramite idCorso se il metodi del/dei DAO invocati sollevano una
	 * eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public ArrayList<Feedback> visualizzaFeedbackCorso(int idCorso) throws DAOException {
		try {
			return daoFeedback.selectFeedbackPerCorso(idCorso);
		} catch (SQLException e) {
			throw new DAOException("errore nella visualizzazione del feedback", e);

		}
	}

	/*
	 * modifica tutti i dati di un corso se il metodi del/dei DAO invocati sollevano
	 * una eccezione, il metodo deve tornare una DAOException con all'interno
	 * l'exception originale
	 */
	@Override
	public void modificaDatiCorso(Corso corso) throws DAOException {
		try {
			daoCatalogo.update(corso);
		} catch (SQLException e) {
			throw new DAOException("errore nella modifica dei dati", e);

		}
	}

	/*
	 * inserisce un nuovo corso a catalogo (invocabile solo dall'amministratore) se
	 * il metodi del/dei DAO invocati sollevano una eccezione, il metodo deve
	 * tornare una DAOException con all'interno l'exception originale
	 */
	@Override
	public void inserisciCorso(Corso corso) throws DAOException {
		try {
			daoCatalogo.insert(corso);
		} catch (SQLException e) {
			throw new DAOException("errore nella modifica dei dati", e);

		}
	}

	/*
	 * cancella un singolo corso dal catalogo dei corsi se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public void cancellaCorso(int codiceCorso) throws DAOException {
		try {
			daoCatalogo.delete(codiceCorso);
		} catch (SQLException e) {
			throw new DAOException("errore nella modifica dei dati", e);

		}
	}

	/*
	 * legge i dati di un singolo corso (senza edizioni) se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public Corso visualizzaCorso(int codiceCorso) throws DAOException {
		try {
			return daoCatalogo.select(codiceCorso);
		} catch (SQLException e) {
			throw new DAOException("errore nella modifica dei dati", e);

		}
	}
}
