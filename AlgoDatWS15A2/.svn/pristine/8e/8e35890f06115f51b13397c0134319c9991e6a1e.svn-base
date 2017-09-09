package de.ostfalia.aud.ws15.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.ostfalia.aud.ws15.MyContactList;

public class MyContactListTest2Public {

    @Test(timeout = 1000)
    public void testReadCSV() throws IOException {
        new MyContactList("Materialien/public/test1.csv");
    }

    @Test(timeout = 1000, expected = FileNotFoundException.class)
    public void testFileNotFound() throws IOException {
        new MyContactList("nonexistent.csv");
    }

    @Test(timeout = 1000)
    public void testSize() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/test1.csv");
        assertEquals(2, contactList.size());
    }

    @Test(timeout = 1000)
    public void testGetName() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/test1.csv");
        assertEquals("Klug", contactList.getName("seBasTianklug@web.de"));
        assertEquals("Friedmann",
                contactList.getName("leahfriedmann@gmail.com"));
        assertNull(contactList.getName("nonexistent@nodomain.com"));
        assertNull(contactList.getName(""));
    }

    @Test(timeout = 1000)
    public void testGetEmail() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/test1.csv");
        assertEquals("SebastianKlug@web.de", contactList.getEmail("Klug"));
        assertEquals("LeahFriedmann@gmail.com",
                contactList.getEmail("Friedmann"));
        assertNull(contactList.getEmail("Noname"));
        assertNull(contactList.getEmail("KluG"));
        assertNull(contactList.getEmail(""));
    }

    @Test(timeout = 1000)
    public void testRemoveEntryByMail() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/test1.csv");
        assertEquals(2, contactList.size());
        assertEquals("SebastianKlug@web.de", contactList.getEmail("Klug"));
        assertEquals("Friedmann",
                contactList.getName("leahfriedmann@gmail.com"));
        contactList.removeEntriesByMail("");
        assertEquals(2, contactList.size());
        contactList.removeEntriesByMail("SebastianKlug@web.de");
        assertEquals(1, contactList.size());
        contactList.removeEntriesByMail("SebastianKlug@web.de");
        assertEquals(1, contactList.size());
        contactList.removeEntriesByMail("nonexistent@mymail.com");
        assertEquals(1, contactList.size());
        contactList.removeEntriesByMail("LeahFriedmann@gmail.com.");
        assertEquals(1, contactList.size());
        contactList.removeEntriesByMail("leahFriedmann@gmail.com");
        assertEquals(0, contactList.size());
    }

    @Test(timeout = 1000)
    public void testCountEntries() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/test1.csv");
        assertEquals(1, contactList.countEntries("Klug"));
        assertEquals(0, contactList.countEntries("KluG"));
        assertEquals(0, contactList.countEntries("klug"));
        assertEquals(1, contactList.countEntries("Friedmann"));
    }

    @Test(timeout = 1000)
    public void testEmpty() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/testEmpty.csv");
        assertEquals(0, contactList.size());
        assertNull(contactList.getName("nonexistent@nodomain.com"));
        assertNull(contactList.getEmail("Klug"));
        assertNull(contactList.getEmail(""));
    }

    @Test(timeout = 1000)
    public void testCountNames() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/family-25-50-file0.csv");
        assertEquals(0, contactList.countNames("nomail@nohost.com"));
        assertEquals(2, contactList.countNames("Jorgschroder@dayrep.com"));
        assertEquals(1, contactList.countNames("AntjeFreytag@jourrapide.com"));
    }

    @Test(timeout = 1000)
    public void testGetEmails() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/family-25-50-file0.csv");
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

    @Test
    public void testGetEmailsDuplicate1() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/ContactsDuplicateEmail.csv");
        Set<String> emailsExpected = new HashSet<String>() {
            private static final long serialVersionUID = 5803635058934625369L;

            {
                add("ClaudiaSchmitz@jourrapide.com");
                add("ChristianSchmitz@jourrapide.com");
                add("UtaSchmitz@armyspy.com");
                add("ChristinSchmitz@jourrapide.com");
                add("JulianeSchmitz@gustr.com");
                add("DianaSchmitz@jourrapide.com");
                add("WolfgangSchmitz@rhyta.com");
                add("LenaSchmitz@rhyta.com");
                add("HeikeSchmitz@superrito.comSHADY_ENTRY_17");
                add("HeikeSchmitz@superrito.comSHADY_ENTRY_16");
            }
        };
        Set<String> emailsActual = new HashSet<>();
        String[] emails = contactList.getEmails("Schmitz");
        assertNotNull(emails);
        for (String email : emails) {
            assertTrue(String.format("%s nicht erwartet!", email),
                    emailsExpected.contains(email));
            assertFalse(String.format("%s doppelt!", email),
                    emailsActual.contains(email));
            emailsActual.add(email);
        }
        assertEquals(emailsExpected.size(), emailsActual.size());
        assertEquals(11,
                contactList.getFirstLocationOf("HeikeSchmitz@superrito.com"));
        assertEquals(12,
                contactList.getLastLocationOf("HeikeSchmitz@superrito.com"));
        assertEquals("HeikeSchmitz@superrito.com",
                contactList.emailAtLocation(11));
        assertEquals("HeikeSchmitz@superrito.com",
                contactList.emailAtLocation(12));
    }

    @Test(timeout = 1000)
    public void testStringArrayConstructor() {
        String[] lines = {
                "Probst,Ines,InesProbst@superrito.com,\"Flotowstr. 5\",06551,Artern,\"03466 12 98 60\"",
                "Kohl,Johanna,JohannaKohl@rhyta.com,\"Spresstrasse 65\",24915,Flensburg,\"0461 59 92 11\"",
                "Wolf,Matthias,MatthiasWolf@armyspy.com,\"Jahnstrasse 69\",85615,Assling,\"08065 34 18 16\"",
                "Wolf,Martin,MatthiasWolf@armyspy.com,\"Jahnstrasse 69\",85615,Assling,\"08065 34 18 16\"",
                "Oster,Anke,AnkeOster@rhyta.com,\"Gruenauer Strasse 59\",21493,Basthorst,\"04151 58 92 71\"" };
        MyContactList contactList = new MyContactList(lines);
        assertEquals(5, contactList.size());
        assertEquals("AnkeOster@rhyta.com", contactList.emailAtLocation(0));
        assertEquals("InesProbst@superrito.com", contactList.emailAtLocation(1));
        assertEquals("MatthiasWolf@armyspy.com", contactList.emailAtLocation(3));
        assertEquals("JohannaKohl@rhyta.com", contactList.emailAtLocation(2));
        assertEquals(2, contactList.countEntries("Wolf"));
        contactList = new MyContactList(new String[0]);
        assertEquals(0, contactList.size());
        assertEquals(0, contactList.countEntries("Wolf"));
    }

    @Test(timeout = 2000)
    public void testLargeFileOperations() throws IOException {
        MyContactList contactList = new MyContactList(
                "Materialien/public/Contacts25k.csv");
        assertEquals(25_000, contactList.size());
        assertEquals(20_964,
                contactList.getFirstLocationOf("StefanieVogler@superrito.com"));
        assertEquals(20_965,
                contactList.getLastLocationOf("Stefanievogler@superrito.com"));
        assertEquals(82, contactList.countEntries("Vogler"));
        contactList.removeEntriesByMail("MichelleBEckenbauer@rhyta.com");
        assertEquals(24_999, contactList.size());
        contactList.removeEntriesByMail("MelanieDUerr@einrot.com");
        assertEquals(24_998, contactList.size());
        contactList.removeEntriesByMail("MelanieDUerr@einrot.com");
        assertEquals(24_998, contactList.size());
        assertNull(contactList.getName("nonexistent@nohost.com"));
        assertNull(contactList.getEmail("Noname"));
        assertNotNull(contactList.getName("MikeHofmann@jourrapide.com"));
        assertNotNull(contactList.getEmail("Eichelberger"));
    }
}
