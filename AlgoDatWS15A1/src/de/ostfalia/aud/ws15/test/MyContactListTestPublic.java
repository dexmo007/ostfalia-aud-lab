package de.ostfalia.aud.ws15.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import de.ostfalia.aud.ws15.MyContactList;

/**
 * @author M.Gruendel
 *
 */
public class MyContactListTestPublic {

	/**
	 * Test method for MyContactList(java.lang.String).
	 * Loading empty file.....
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testMyContactListEmptyFile() throws IOException {
		new MyContactList("Materialien/ContactEmpty.csv");		
	}
	
	
	/**
	 * Test method for MyContactList(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000, expected = FileNotFoundException.class)
	public void testMyContactListNotExit() throws IOException {
		new MyContactList("Materialien/x.csv");		
	}
	
	
	/**
	 * Test method for MyContactList(java.lang.String).
	 * Loading 25k file.....runtime < 1000ms expected.
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testMyContactListBigFile() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contacts25k.csv");
		assertEquals("25K-Datei. Üngültige Anzahl Einträge.", 25000, mc.size());
	}

	/**
	 * Test method for MyContactList#size().
	 * Loading empty file.....
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testSize() throws IOException {
		MyContactList mc = new MyContactList("Materialien/ContactEmpty.csv");
		assertEquals("Leere Datei. Üngültige Anzahl Einträge.", 0, mc.size());
	}

	/**
	 * Test method for MyContactList#getName(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testGetName() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("DavidLemann@cuvox.de, erwarteter Name: ", "Lemann", mc.getName("DavidLemann@cuvox.de"));
		assertEquals("JorgHirsch@cuvox.de,  erwarteter Name: ", "Hirsch", mc.getName("JorgHirsch@cuvox.de"));
	}
	
		
	/**
	 * Test method for MyContactList#getName(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testGetNameIgnorCase() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("davidlemann@cuvox.de, erwarteter Name: ", "Lemann", mc.getName("davidlemann@cuvox.de"));
		assertEquals("JORGHIRSCH@cuvox.DE,  erwarteter Name: ", "Hirsch", mc.getName("JORGHIRSCH@cuvox.DE"));
	}
	
	
	/**
	 * Test method for MyContactList#getEmail(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testGetEmail() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"Lemann\", erwartete eMail: ", "DavidLemann@cuvox.de", mc.getEmail("Lemann"));
		assertEquals("\"Hirsch\", erwartete eMail: ", "JorgHirsch@cuvox.de", mc.getEmail("Hirsch"));
	}
	
	
	/**
	 * Test method for MyContactList#getEmail(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testGetEmailCase() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"lemann\", erwartete eMail: ", null, mc.getEmail("lemann"));
		assertEquals("\"HIRSCH\", erwartete eMail: ", null, mc.getEmail("HIRSCH"));
	}
	
	
	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testRemoveEntriesByMail() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("Größe ungültig!", 9, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("DavidLemann@cuvox.de");
		assertEquals("\"Lemann\" darf nicht mehr vorhanden sein!", 
				null, mc.getEmail("Lemann"));
		assertEquals("Größe ungültig!", 8, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("JorgHirsch@cuvox.de");
		assertEquals("\"Hirsch\" darf nicht mehr vorhanden sein!", 
				null, mc.getEmail("Hirsch"));
		assertEquals("Größe ungültig!", 7, mc.size());
	}
	
	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testRemoveEntriesByMailMixed() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-3.csv");
		assertEquals("Größe ungültig!", 9, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("AlexanderLuft@gustr.com");
		assertEquals("\"Luft\" darf nicht mehr vorhanden sein!", 
				null, mc.getEmail("Luft"));
		assertEquals("Größe ungültig!", 6, mc.size());		
	}
	
	
	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testRemoveEntriesByMailDouble() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact7-2.csv");
		assertEquals("Größe ungültig!", 7, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("SteffenHartmann@cuvox.de");
		assertEquals("\"Hartmann\" darf nicht mehr vorhanden sein!", 
				null, mc.getEmail("Hartmann"));
		assertEquals("Größe ungültig!", 3, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("AlexanderLuft@gustr.com");
		assertEquals("\"Luft\" darf nicht mehr vorhanden sein!", 
				null, mc.getEmail("Luft"));
		assertEquals("Größe ungültig!", 0, mc.size());
	}

	/**
	 * Test method for MyContactList#countEntries(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testCountEntriesExist() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"Hirsch\": Anzahl Einträge ungültig!", 1, mc.countEntries("Hirsch"));
		assertEquals("\"Lemann\": Anzahl Einträge ungültig!", 1, mc.countEntries("Lemann"));
	}
	
	/**
	 * Test method for MyContactList#countEntries(java.lang.String).
	 * @throws IOException 
	 */
	@Test (timeout = 1000)
	public void testCountEntriesNotExist() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"hirsch\": Anzahl Einträge ungültig!", 0, mc.countEntries("hirsch"));
		assertEquals("\"lemann\": Anzahl Einträge ungültig!", 0, mc.countEntries("lemann"));
		assertEquals("\"Noname\": Anzahl Einträge ungültig!", 0, mc.countEntries("Noname"));
	}

}
