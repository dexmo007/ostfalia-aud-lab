package de.ostfalia.aud.ws15;

import de.ostfalia.aud.ws15.comparators.EmailComparator;
import de.ostfalia.aud.ws15.comparators.NameComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

/**
 * Contact List class
 *
 * @author Henrik
 * @author Maxi
 */
public class MyContactList {

    private MyHashTable contactsEmailFirst;
    private MyHashTable contactsNameFirst;

    //    public long numberOfComparisons = 0;

    /**
     * contructor for a contact list
     *
     * @param filename name of the file to be created
     * @throws IOException if file does not exist or is not readable
     */
    public MyContactList(String filename) throws IOException {
//        numberOfComparisons = 0;
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        //determine required capacity
        int capacity = 0;
        while (br.readLine() != null) {
            capacity++;
        }
        contactsEmailFirst = new MyHashTable(new EmailComparator(), capacity);
        contactsNameFirst = new MyHashTable(new NameComparator(), capacity);
        //fill hash table
        br = new BufferedReader(new FileReader(file));
        String[] currentLine;
        String lineStr;
        while ((lineStr = br.readLine()) != null) {
            lineStr = lineStr.replaceAll("\"", "");
            currentLine = lineStr.split(",");
            Contact currentContact = new Contact(currentLine);
            contactsEmailFirst.add(currentContact);
            contactsNameFirst.add(currentContact);
        }
        br.close();
    }

    /**
     * constructor for a MyContactList from a String array containing contact lines
     *
     * @param lines string array containing contact lines
     */
    public MyContactList(String[] lines) {
        int capacity = lines.length;
        contactsEmailFirst = new MyHashTable(new EmailComparator(), capacity);
        contactsNameFirst = new MyHashTable(new NameComparator(), capacity);
        for (int i = 0; i < capacity; i++) {
            String lineStr = lines[i].replaceAll("\"", "");
            Contact currentContact = new Contact(lineStr.split(","));
            contactsEmailFirst.add(currentContact);
            contactsNameFirst.add(currentContact);
        }
    }

    /**
     * getter for the size of the contact list
     *
     * @return size of the contact list
     */
    public int size() {
        return contactsEmailFirst.size();
    }

    /**
     * methods searches the contact list for an email and returns the name of the first result
     *
     * @param email email to search the contact list for
     * @return name of the first search result
     */
    public String getName(String email) {
        if (contactsEmailFirst.contains(email)) {
            return contactsEmailFirst.get(email).getName();
        }
        return null;
    }

    /**
     * method to search the contact list for a name that
     * returns the email of the first search result
     * @param name name to search the contact list for
     * @return email of the first search result
     */
    public String getEmail(String name) {
        if (contactsNameFirst.contains(name)) {
            return contactsNameFirst.get(name).getEmail();
        }
        return null;
    }

    /**
     * Does several search statements to put them together in a string array
     * @param name name that will be searched for
     * @return returns a String array with all the Email address that match the name
     */
    public String[] getEmails(String name) {
        Contact[] allEntries = contactsNameFirst.getAllOnce(name).toArray();
        String[] res = new String[allEntries.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = allEntries[i].getEmail();
        }
        return res;
    }

    /**
     * method removes all contactsEmailFirst with the given email
     * @param mail mail identifier to delete the contactsEmailFirst
     */
    public void removeEntriesByMail(String mail) {
        MyLinkedList removed = contactsEmailFirst.remove(mail);
        for (Contact contact : removed) {
            contactsNameFirst.remove(contact.getName());
        }
    }

    /**
     * counts the number of entries with a given name
     * @param name name to count entries
     * @return the number of entries with the given name
     */
    public int countEntries(String name) {
        if (contactsNameFirst.contains(name)) {
            return contactsNameFirst.getAll(name).size();
        }
        return 0;
    }

    /**
     * counts all entries with an email
     * @param email email to search for
     * @return number of entries with the email
     */
    public int countNames(String email) {
        if (contactsEmailFirst.contains(email)) {
            return contactsEmailFirst.getAll(email).size();
        }
        return 0;
    }

    @Override
    public String toString() {
        return contactsNameFirst.toString();
    }

    public String toString2() {
        return contactsEmailFirst.toString();
    }
}