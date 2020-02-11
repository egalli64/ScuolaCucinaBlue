package dao;


import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import entity.Utente;

class AmministratoreDAOImplTest {

	@Test
	void insertUpdateDelete() {

		AmministratoreDAO dao = null;

		try {			
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			Date date = df.parse("1992-10-10");
			Utente u = new Utente("1", "aa", "Chiara", "Arci", date, "pp@gmail.com", "pp", true);

			 dao = new AmministratoreDAOImpl();
			dao.insert(u);

			Utente result = dao.select("1");
			assertEquals(u.getIdUtente(), result.getIdUtente());
			assertEquals(u.getPassword(), result.getPassword());
			assertEquals(u.getNome(), result.getNome());
			assertEquals(u.getCognome(), result.getCognome());
//			assertEquals(u.getDataNascita(), result.getDataNascita());
			assertEquals(u.getEmail(), result.getEmail());
			assertEquals(u.getTelefono(), result.getTelefono());
			
			
			u.setCognome("Rovai");
			dao.update(u);
			
			result = dao.select("1");
			assertEquals(u.getCognome(), result.getCognome());
			
			dao.delete("1");

		} catch (Exception e) {
			fail("Unexpected Exception: " + e.getMessage());
			return;
		}

		try {
			dao.select("1");
			fail("Select failure should throw an exception");
		} catch(Exception ex) {
			// as expected
		}

	}
}
