package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CalendarioDAO;
import dao.CalendarioDAOImpl;
import dao.FeedbackDAO;
import dao.IscrizioneUtenteDAO;
import dto.CorsoDTO;
import dto.EdizioneDTO;
import entity.Corso;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class EdizioneServiceImpl implements EdizioneService {

	// dichiarare qui tutti i dao di cui si ha bisogno
	private CalendarioDAO daoC;
	private IscrizioneUtenteDAO daoIscrizione;
	private FeedbackDAO daoFeedback;

	// costruire qui tutti i dao di cui si ha bisogno
	public EdizioneServiceImpl() throws ConnessioneException {
		daoC = new CalendarioDAOImpl();
		// ... costruzione di altri DAO
	}

	/*
	 * inserisce una nuova edizione
	 */
	@Override
	public void inserisciEdizione(Edizione e) throws DAOException {
		try {
			daoC.insert(e);
		} catch (SQLException x) {
			throw new DAOException("impossibile inserire nuova edizione", x);

		}
	}

	/*
	 * modifica tutti i dati di una edizione esistente
	 */
	@Override
	public void modificaEdizione(Edizione e) throws DAOException {
		try {
			daoC.update(e);
		} catch (SQLException x) {
			throw new DAOException("impossibile modificare l'edizione", x);

		}
	}

	/*
	 * cancella una edizione esistente. E' possibile cancellare una edizione
	 * soltanto se la data di inizio è antecedente a quella odierna Nel caso
	 * l'edizione sia cancellabile, è necessario cancellare l'iscrizione a tutti gli
	 * utenti iscritti
	 */
	@Override
	public void cancellaEdizione(int idEdizione) throws DAOException {
		try {
			daoC.delete(idEdizione);
		} catch (SQLException x) {
			throw new DAOException("impossibile cancellare l'edizione", x);

		}
	}

	/*
	 * iscrive un utente ad una edizione un utente si può iscrivere solo se ci sono
	 * ancora posti disponibili considerato che ogni corso a un numero massimo di
	 * partecipanti
	 */
	@Override
	public void iscriviUtente(int idEdizione, String idUtente) throws DAOException {
		try {
			daoIscrizione.iscriviUtente(idEdizione, idUtente);
		} catch (SQLException x) {
			throw new DAOException("impossibile inserire nuovo utente", x);

		}
	}

	/*
	 * cancella l'iscrizione ad un utente
	 */
	@Override
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws DAOException {
		try {
			daoIscrizione.cancellaIscrizioneUtente(idEdizione, idUtente);
		} catch (SQLException x) {
			throw new DAOException("impossibile cancellare iscrizione", x);

		}
	}

	/*
	 * il metodo ritorna tutte le edizioni con relativi utenti e feedback dei corsi
	 * in calendario nel mese indicato dell'anno corrente se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public ArrayList<EdizioneDTO> visualizzaEdizioniPerMese(int mese) throws DAOException {
		try {
			ArrayList<EdizioneDTO> datiEdMese = new ArrayList<EdizioneDTO>();
			Edizione edizione = new Edizione();
			List<Utente> utenti = new ArrayList<>();
			List<Feedback> datifeedback = daoFeedback.selectPerMese(mese);
			EdizioneDTO dati = new EdizioneDTO(edizione, datifeedback, utenti);
			datiEdMese.add(dati);

			return datiEdMese;

		} catch (SQLException e) {
			throw new DAOException("ops! mese non presente", e);
		}
	}

	/*
	 * il metodo ritorna tutte le edizioni con relativi utenti e feedback dei corsi
	 * in calendario nel mese indicato dell'anno corrente se il metodi del/dei DAO
	 * invocati sollevano una eccezione, il metodo deve tornare una DAOException con
	 * all'interno l'exception originale
	 */
	@Override
	public ArrayList<EdizioneDTO> visualizzaEdizioniPerAnno(int anno) throws DAOException {
		try {
			ArrayList<EdizioneDTO> datiEdMese = new ArrayList<EdizioneDTO>();
			Edizione edizione = new Edizione();
			List<Utente> utenti = new ArrayList<>();
			List<Feedback> datifeedback = daoFeedback.selectPerAnno(anno);
			EdizioneDTO dati = new EdizioneDTO(edizione, datifeedback, utenti);
			datiEdMese.add(dati);

			return datiEdMese;

		} catch (SQLException e) {
			throw new DAOException("ops! anno non presente", e);
		}
	}
}


	