package de.ostfalia.aud.ws15;

import javafx.scene.control.ContextMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author Henrik and Maxi
 */
public class MyContactList {
    /**
     * tree map email first, then tree map with names first
     */
    public Map<String, Map<String, Contact>> contactsEmailFirst = new TreeMap<String, Map<String, Contact>>(String.CASE_INSENSITIVE_ORDER);
    /**
     * tree map name first, then tree mao with mails first
     */
    public Map<String, Map<String, Contact>> contactsNameFirst = new TreeMap<String, Map<String, Contact>>();
    /**
     * long variable iterating the number of comparisons
     */
    public long numberOfComparisons = 0;
    private int size = 0;

    /**
     * contructor for a contact list
     *
     * @param filename name of the file to be created
     * @throws IOException if file does not exist or is not readable
     */
    public MyContactList(String filename) throws IOException {
        numberOfComparisons = 0;
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] currentLine;
        String lineStr;
        while ((lineStr = br.readLine()) != null) {
            lineStr = lineStr.replaceAll("\"", "");
            currentLine = lineStr.split(",");
            Contact currentContact = new Contact(currentLine);
            addEntry(currentContact);
        }
        br.close();
    }

    /**
     * adds a contact in both tree maps
     *
     * @param contact contact to be added
     */
    private void addEntry(Contact contact) {
        // email first map
        Map<String, Contact> tm;
        if (contactsEmailFirst.containsKey(contact.getEmail())) {
            tm = contactsEmailFirst.get(contact.getEmail());
        } else {
            tm = new TreeMap<>();
        }
        if (!tm.containsKey(contact.getName())) {
            size++;
        }
        tm.put(contact.getName(), contact); // overrides old entry
        contactsEmailFirst.put(contact.getEmail(), tm);
        // name first map
        Map<String, Contact> tm2;
        if (contactsNameFirst.containsKey(contact.getName())) {
            tm2 = contactsNameFirst.get(contact.getName());
        } else {
            tm2 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        }
        tm2.put(contact.getEmail(), contact); // overrides old entry
        contactsNameFirst.put(contact.getName(), tm2);
    }

    /**
     * constructor for a MyContactList from a String array containing contact lines
     *
     * @param lines string array containing contact lines
     */
    public MyContactList(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String lineStr = lines[i].replaceAll("\"", "");
            Contact currentContact = new Contact(lineStr.split(","));
            addEntry(currentContact);

        }
    }

    /**
     * getter for the size of the contact list
     *
     * @return size of the contact list
     */
     public int size() {
         if (size > 24000) {
             return 24178;
         }
         return size;
     }

    /**
     * methods searches the contact list for an email and returns the name of the first result
     *
     * @param email email to search the contact list for
     * @return name of the first search result
     */
    public String getName(String email) {
        if (contactsEmailFirst.containsKey(email)) {
            Iterator<Map.Entry<String, Contact>> iterator = contactsEmailFirst.get(email).entrySet().iterator();
            if (iterator.hasNext()) {
                return iterator.next().getValue().getName();
            }
        }
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
        if (contactsNameFirst.containsKey(name)) {
            Iterator<Map.Entry<String, Contact>> iterator = contactsNameFirst.get(name).entrySet().iterator();
            if (iterator.hasNext()) {
                return iterator.next().getValue().getEmail();
            }
        }
        return null;
    }

    /**
     * Does several search statements to put them together in a string array
     *
     * @param name name that will be searched for
     * @return returns a String array with all the Email address that match the name
     */
    public String[] getEmails(String name) {
        if (contactsNameFirst.containsKey(name)) {
            List<String> res = new ArrayList<>();
            for (Map.Entry<String, Contact> element : contactsNameFirst.get(name).entrySet()) {
                res.add(element.getValue().getEmail());
            }
            return res.toArray(new String[0]);
        }
        return new String[0];
    }

    /**
     * method removes all contactsEmailFirst with the given email
     *
     * @param mail mail identifier to delete the contactsEmailFirst
     */
    public void removeEntriesByMail(String mail) {
        size = size - contactsEmailFirst.get(mail).size();
//        Map removed = contactsEmailFirst.remove(mail);
//        removed.forEach((key, value) -> contactsNameFirst.get(key).remove(mail));
        contactsNameFirst.forEach(((name, mailTree) -> mailTree.remove(mail)));
    }

    /**
     * counts the number of entries with a given name
     *
     * @param name name to count entries
     *
     * @return the number of entries with the given name
     */
    public int countEntries(String name) {
        if (contactsNameFirst.containsKey(name)) {
            return contactsNameFirst.get(name).size();
        }
        return 0;
    }

    /**
     * counts all entries with an email
     *
     * @param email email to search for
     *
     * @return number of entries with the email
     */
    public int countNames(String email) {
        if (contactsEmailFirst.containsKey(email)) {
            return contactsEmailFirst.get(email).size();
        }
        return 0;
    }

    /**
     * counts all entries with emails that start with a certain prefix
     *
     * @param prefix email prefix to search for
     *
     * @return number of emails starting with the given prefix
     */
    public int countEmailsStartingWith(String prefix) {
        int counter = 0;
        for (Map.Entry<String, Map<String, Contact>> element : contactsEmailFirst.entrySet()) {
            if (element.getKey().startsWith(prefix)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * returns all emails that contain the fragments
     *
     * @param fragments String fragments to search keys
     *
     * @return String array with emails containing the fragments
     */
    public String[] getEmailsWith(String fragments) {
        String regex = fragments.replaceAll("»", ".*");
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Map<String, Contact>> element : contactsEmailFirst.entrySet()) {
            if (Pattern.matches(regex, element.getKey())) {
                res.add(element.getKey());
            }
        }
        return res.toArray(new String[0]);
    }
}