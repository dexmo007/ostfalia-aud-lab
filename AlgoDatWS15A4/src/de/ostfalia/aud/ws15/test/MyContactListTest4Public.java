package de.ostfalia.aud.ws15.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.ostfalia.aud.ws15.MyContactList;

/**
 * @author M.Gruendel, A.Gabel
 *
 * Zeichencodierung: UTF-8
 *
 */
public class MyContactListTest4Public {

	private String[] contact1 = {
			"Lemann,David,DavidLemann@cuvox.de,\"Stresemannstr. 77\",66781,Wadgassen,\"06834 78 34 28\"",
			"Luft,Alexander,AlexanderLuft@gustr.com,\"Konstanzer Strasse 93\",63872,Heimbuchenthal,\"06092 42 64 45\"",
			"Eggers,Peter,PeterEggers@jourrapide.com,\"Paul-Nevermann-Platz 10\",97618,Heustreu,\"09773 78 85 73\"",
			"Becker,Leon,LeonBecker@cuvox.de,\"Am Borsigturm 10\",46238,\"Bottrop Welheim\",\"02045 55 58 74\"",
			"Lust,Alexander,AlexanderLust@gustr.com,\"Konstanzer Strasse 93\",63872,Heimbuchenthal,\"06092 42 64 45\""
	};

	private String[] contact2 = {
			"Lemann,David,DavidLemann@cuvox.de,\"Stresemannstr. 77\",66781,Wadgassen,\"06834 78 34 28\"",
			"Lemann,Igor,IgorLemann@cuvox.de,\"Stresemannstr. 77\",66781,Wadgassen,\"06834 78 34 28\"",
			"Kamann,Igor,IgorKamann@web.de,\"Stresemannstr. 77\",66781,Wadgassen,\"06834 78 34 28\"",
			"Altmann,David,DavidAltmann@cuvox.de,\"Stresemannstr. 78\",66781,Wadgassen,\"06834 78 34 27\"",

	};


	/**
	 * Test method for MyContactList(java.lang.String).
	 * Loading empty file.....
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testMyContactListEmptyFile() throws IOException {
		new MyContactList("Materialien/ContactEmpty.csv");
	}


	/**
	 * Test method for MyContactList(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000, expected = IOException.class)
	public void testMyContactListNotExit() throws IOException {
		new MyContactList("Materialien/x.csv");
	}


	/**
	 * Test method for MyContactList(java.lang.String).
	 * Loading 25k file.....runtime < 1000ms expected.
	 * @throws IOException
	 */
	@Test(timeout = 2000)
	public void testMyContactListBigFile() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contacts25k.csv");
		assertEquals("25K-Datei. Ungueltige Anzahl Eintraege.", 24178, mc.size());
	}

	/**
	 * Test method for MyContactList#size().
	 * Loading empty file.....
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testSize() throws IOException {
		MyContactList mc = new MyContactList("Materialien/ContactEmpty.csv");
		assertEquals("Leere Datei. Ungueltige Anzahl Eintraege.", 0, mc.size());
	}

	/**
	 * Test method for MyContactList#getName(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testGetName() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("DavidLemann@cuvox.de, erwarteter Name: ", "Lemann", mc.getName("DavidLemann@cuvox.de"));
		assertEquals("JorgHirsch@cuvox.de,  erwarteter Name: ", "Hirsch", mc.getName("JorgHirsch@cuvox.de"));
		assertNull("nonexistent@nodomain.com", mc.getName("nonexistent@nodomain.com"));
		assertNull(mc.getName(""));
	}


	/**
	 * Test method for MyContactList#getName(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testGetNameIgnorCase() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("davidlemann@cuvox.de, erwarteter Name: ", "Lemann", mc.getName("davidlemann@cuvox.de"));
		assertEquals("JORGHIRSCH@cuvox.DE,  erwarteter Name: ", "Hirsch", mc.getName("JORGHIRSCH@cuvox.DE"));
	}


	/**
	 * Test method for MyContactList#getEmail(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testGetEmail() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"Lemann\", erwartete eMail: ", "DavidLemann@cuvox.de", mc.getEmail("Lemann"));
		assertEquals("\"Hirsch\", erwartete eMail: ", "JorgHirsch@cuvox.de", mc.getEmail("Hirsch"));
		assertNull(mc.getEmail("Noname"));
		assertNull(mc.getEmail(""));
	}


	@Test(timeout = 1000)
	public void testGetEmails() throws IOException {
		MyContactList contactList = new MyContactList(
				"Materialien/family-25-50-file0.csv");
		Set<String> emailsExpected = new HashSet<String>() {
			private static final long serialVersionUID = -4554647838509696020L;
			{
				add("InesAckerman@teleworm.us");
				add("JessikaAckerman@cuvox.de");
				add("UlrichAckerman@cuvox.de");
				add("JessikaAckerman@superrito.com");
				add("SwenAckerman@armyspy.com");
				add("DominikAckerman@armyspy.com");
				add("MatthiasAckerman@armyspy.com");
				add("MichaelAckerman@gustr.com");
			}
		};
		Set<String> emailsActual = new HashSet<>();
		String[] emails = contactList.getEmails("Ackerman");
		assertNotNull(emails);
		for (String email : emails) {
			assertTrue(String.format("%s nicht erwartet!", email),
					emailsExpected.contains(email));
			assertFalse(String.format("%s doppelt!", email),
					emailsActual.contains(email));
			emailsActual.add(email);
		}
		assertEquals(emailsExpected.size(), emailsActual.size());
		assertEquals(0, contactList.getEmails("").length);
	}

	@Test(timeout = 1000)
	public void testStringArrayConstructor() {
		String[] lines = {
				"Probst,Ines,InesProbst@superrito.com,\"Flotowstr. 5\",06551,Artern,\"03466 12 98 60\"",
				"Kohl,Johanna,JohannaKohl@rhyta.com,\"Spresstrasse 65\",24915,Flensburg,\"0461 59 92 11\"",
				"Wolf,Matthias,MatthiasWolf@armyspy.com,\"Jahnstrasse 69\",85615,Assling,\"08065 34 18 16\"",
				"Wolfs,Martin,MartinWolfs@armyspy.com,\"Jahnstrasse 69\",85615,Assling,\"08065 34 18 16\"",
				"Oster,Anke,AnkeOster@rhyta.com,\"Gruenauer Strasse 59\",21493,Basthorst,\"04151 58 92 71\"" };
		MyContactList mc = new MyContactList(lines);
		assertEquals(5, mc.size());
		assertEquals("AnkeOster@rhyta.com", mc.getEmail("Oster"));
		assertEquals("InesProbst@superrito.com", mc.getEmail("Probst"));
		assertEquals("MatthiasWolf@armyspy.com", mc.getEmail("Wolf"));
		assertEquals("JohannaKohl@rhyta.com", mc.getEmail("Kohl"));
		assertEquals(1, mc.countEntries("Wolf"));
		mc = new MyContactList(new String[0]);
		assertEquals(0, mc.size());
		assertEquals(0, mc.countEntries("Wolf"));
	}


	/**
	 * Test method for MyContactList#getEmail(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testGetEmailCase() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"lemann\", erwartete eMail: ", null, mc.getEmail("lemann"));
		assertEquals("\"HIRSCH\", erwartete eMail: ", null, mc.getEmail("HIRSCH"));
	}


	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testRemoveEntriesByMail() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("Groesse ungueltig!", 9, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("DavidLemann@cuvox.de");
		assertEquals("\"Lemann\" darf nicht mehr vorhanden sein!",
				null, mc.getEmail("Lemann"));
		assertEquals("Groesse ungueltig!", 8, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("JorgHirsch@cuvox.de");
		assertEquals("\"Hirsch\" darf nicht mehr vorhanden sein!",
				null, mc.getEmail("Hirsch"));
		assertEquals("Groesse ungueltig!", 7, mc.size());
	}

	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testRemoveEntriesByMailMixed() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-3.csv");
		assertEquals("Groesse ungueltig!", 7, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("AlexanderLuft@gustr.com");
		assertEquals("\"Luft\" darf nicht mehr vorhanden sein!",
				null, mc.getEmail("Luft"));
		assertEquals("Groesse ungueltig!", 6, mc.size());
	}


	/**
	 * Test method for MyContactList#removeEntriesByMail(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testRemoveEntriesByMailDouble() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact7-2.csv");
		assertEquals("Groesse ungueltig!", 2, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("SteffenHartmann@cuvox.de");
		assertEquals("\"Hartmann\" darf nicht mehr vorhanden sein!",
				null, mc.getEmail("Hartmann"));
		assertEquals("Groesse ungueltig!", 1, mc.size());
		//-----------------------------------------------
		mc.removeEntriesByMail("AlexanderLuft@gustr.com");
		assertEquals("\"Luft\" darf nicht mehr vorhanden sein!",
				null, mc.getEmail("Luft"));
		assertEquals("Groesse ungueltig!", 0, mc.size());
	}

	/**
	 * Test method for MyContactList#countEntries(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testCountEntriesExist() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"Hirsch\": Anzahl Eintraege ungueltig!", 1, mc.countEntries("Hirsch"));
		assertEquals("\"Lemann\": Anzahl Eintraege ungueltig!", 1, mc.countEntries("Lemann"));
	}

	/**
	 * Test method for MyContactList#countEntries(java.lang.String).
	 * @throws IOException
	 */
	@Test(timeout = 1000)
	public void testCountEntriesNotExist() throws IOException {
		MyContactList mc = new MyContactList("Materialien/Contact9-0.csv");
		assertEquals("\"hirsch\": Anzahl Eintraege ungueltig!", 0, mc.countEntries("hirsch"));
		assertEquals("\"lemann\": Anzahl Eintraege ungueltig!", 0, mc.countEntries("lemann"));
		assertEquals("\"Noname\": Anzahl Eintraege ungueltig!", 0, mc.countEntries("Noname"));
	}


	@Test (timeout = 1000)
	public void testCountEmailsStartingWith() throws IOException {
		MyContactList mc = new MyContactList(contact1);
		assertEquals("Anzahl Eintraege ungueltig!", 5, mc.size());
		assertEquals("Email beginnt mit XYZ: ", 0,
				mc.countEmailsStartingWith("XYZ"));
		assertEquals("Email beginnt mit L: ", 1,
				mc.countEmailsStartingWith("L"));
		assertEquals("Email beginnt mit Leon: ", 1,
				mc.countEmailsStartingWith("Leon"));
		assertEquals("Email beginnt mit Luft: ", 0,
				mc.countEmailsStartingWith("Luft"));
		assertEquals("Email beginnt mit Alexander: ", 2,
				mc.countEmailsStartingWith("Alexander"));

	}

	@Test (timeout = 1000)
	public void testGetEmailsWith() {
		MyContactList mc = new MyContactList(contact2);
		assertEquals("Anzahl Eintraege ungueltig!", 4, mc.size());

		assertEquals("»: ", 4, mc.getEmailsWith("»").length);
		assertEquals("»@»: ", 4, mc.getEmailsWith("»@»").length);
		assertEquals("Igor»: ", 2, mc.getEmailsWith("Igor»").length);
		assertEquals("David»: ", 2, mc.getEmailsWith("David»").length);
		assertEquals("»@cuvox.de: ", 3, mc.getEmailsWith("»@cuvox.de").length);
		assertEquals("»Lemann»: ", 2, mc.getEmailsWith("»Lemann»").length);
		assertEquals("»Lemann»cuvox»: ", 2, mc.getEmailsWith("»Lemann»cuvox»").length);
		assertEquals("»Lemann»web»: ", 0, mc.getEmailsWith("»Lemann»web»").length);
		assertEquals("»Kamann»web»: ", 1, mc.getEmailsWith("»Kamann»web»").length);

	}
}
