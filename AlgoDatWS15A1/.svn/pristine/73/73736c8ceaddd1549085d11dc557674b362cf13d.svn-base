package de.ostfalia.aud.ws15;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

/**
 * @author Henrik and Maxi
 */
public class MyContactList {

    public Contact[] contacts;
    public int numberOfComparisons = 0;

    /**
     * contructor for a contact list
     *
     * @param filename name of the file to be created
     * @throws IOException if file does not exist or is not readable
     */
    public MyContactList(String filename) throws IOException {
        numberOfComparisons = 0;
        File file = new File(filename);
        int lines = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null) {
            lines++;
        }
        br.close();
        br = new BufferedReader(new FileReader(file));
        contacts = new Contact[lines];
        String[] currentLine;
        String lineStr;
        int line = 0;
        while ((lineStr = br.readLine()) != null) {
            lineStr = lineStr.replaceAll("\"", "");
            currentLine = lineStr.split(",");
            Contact currentContact = new Contact(currentLine);
            contacts[line] = currentContact;
            line++;
        }
    }

    /**
     * getter for the size of the contact list
     *
     * @return size of the contact list
     */
     public int size() {
         return contacts.length;
     }

    /**
     * methods searches the contact list for an email and returns the name of the first result
     *
     * @param email email to search the contact list for
     * @return name of the first search result
     */
    public String getName(String email) {
        numberOfComparisons = 0;
        for (Contact element : contacts) {
            numberOfComparisons++;
            if (element.getEmail().equalsIgnoreCase(email)) {
                return element.getName();
            }
        }
        // email not found
        return null;
    }

    /**
     * method to search the contact list for a name that
     * returns the email of the first search result
     *
     * @param name name to search the contact list for
     * @return email of the first search result
     */
    public String getEmail(String name) {
        numberOfComparisons = 0;
        for (Contact element : contacts) {
            numberOfComparisons++;
            if (element.getName().equals(name)) {
                return element.getEmail();
            }
        }
        // name not found
        return null;
    }

    /**
     * method removes all contacts with the given email
     *
     * @param mail mail identifier to delete the contacts
     */
    public void removeEntriesByMail(String mail) {
        numberOfComparisons = 0;
        int foundCounter = 0;
        for (int i = 0; i < contacts.length; i++) {
            numberOfComparisons++;
            if (contacts[i].getEmail().equalsIgnoreCase(mail)) {
                foundCounter++;
            }
        }
        Contact[] newContacts = new Contact[contacts.length - foundCounter];
        int counter = 0;
        for (int i = 0; i < contacts.length; i++) {
            numberOfComparisons++;
            if (!contacts[i].getEmail().equalsIgnoreCase(mail)) {
                newContacts[counter] = contacts[i];
                counter++;
            }
        }
        contacts = newContacts;
    }

    /**
     * counts the number of entries with a given name
     *
     * @param name name to count entries
     * @return the number of entries with the given name
     */
    public int countEntries(String name) {
        int counter = 0;
        for (Contact element : contacts) {
            if (element.getName().equals(name)) {
                counter++;
            }
        }
        return counter;
    }
}
