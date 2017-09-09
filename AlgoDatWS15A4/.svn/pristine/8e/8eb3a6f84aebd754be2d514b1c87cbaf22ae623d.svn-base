package de.ostfalia.aud.ws15;
import de.ostfalia.aud.ws15.comparators.EmailFirstComparator;
import de.ostfalia.aud.ws15.comparators.NameFirstComparator;
import de.ostfalia.aud.ws15.comparators.EmailOnlyComparator;
import de.ostfalia.aud.ws15.comparators.NameOnlyComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

/**
 * @author Henrik and Maxi
 */
public class MyContactList {

    public Contact[] contactsEmailFirst;
    public Contact[] contactsNameFirst;
    public long numberOfComparisons = 0;

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
        contactsEmailFirst = new Contact[lines];
        String[] currentLine;
        String lineStr;
        int line = 0;
        while ((lineStr = br.readLine()) != null) {
            lineStr = lineStr.replaceAll("\"", "");
            currentLine = lineStr.split(",");
            Contact currentContact = new Contact(currentLine);
            contactsEmailFirst[line] = currentContact;
            line++;
        }
        contactsNameFirst = Sorter.mergeSort(contactsEmailFirst, new NameFirstComparator());
        numberOfComparisons = Sorter.numberOfComps;
        Sorter.quickSort(contactsEmailFirst, new EmailFirstComparator());
        numberOfComparisons += Sorter.numberOfComps;
    }

    /**
     * constructor for a MyContactList from a String array containing contact lines
     *
     * @param lines string array containing contact lines
     */
    public MyContactList(String[] lines) {
        contactsEmailFirst = new Contact[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String lineStr = lines[i].replaceAll("\"", "");
            Contact currentContact = new Contact(lineStr.split(","));
            contactsEmailFirst[i] = currentContact;
        }
        contactsNameFirst = Sorter.mergeSort(contactsEmailFirst, new NameFirstComparator());
        Sorter.quickSort(contactsEmailFirst, new EmailFirstComparator());
    }

    /**
     * getter for the size of the contact list
     *
     * @return size of the contact list
     */
     public int size() {
         return contactsEmailFirst.length;
     }

    /**
     * methods searches the contact list for an email and returns the name of the first result
     *
     * @param email email to search the contact list for
     * @return name of the first search result
     */
    public String getName(String email) {
        int foundIndex = Sorter.binSearch(contactsEmailFirst,
                Contact.mailOnlyContact(email), new EmailOnlyComparator());
        numberOfComparisons = Sorter.numberOfComps;
        return foundIndex == -1 ? null : contactsEmailFirst[foundIndex].getName();
    }

    /**
     * method to search the contact list for a name that
     * returns the email of the first search result
     *
     * @param name name to search the contact list for
     * @return email of the first search result
     */
    public String getEmail(String name) {
        int index = Sorter.binSearch(contactsNameFirst,
                Contact.nameOnlyContact(name), new NameOnlyComparator());
        numberOfComparisons = Sorter.numberOfComps;
        return index == -1 ? null : contactsNameFirst[index].getEmail();
    }

    /**
     * Does several search statements to put them together in a string array
     *
     * @param name name that will be searched for
     *
     * @return returns a String array with all the Email address that match the name
     */
    public String[] getEmails(String name) {
        int length = Sorter.binCountEntries(contactsNameFirst,
                Contact.nameOnlyContact(name), new NameOnlyComparator());
        numberOfComparisons = Sorter.numberOfComps;
        String result = "";
        int index = Sorter.binFindFirst(contactsNameFirst,
                Contact.nameOnlyContact(name), new NameOnlyComparator());
        numberOfComparisons += Sorter.numberOfComps;

        if (index == -1) {
            return new String[0];
        }

        result = contactsNameFirst[index].getEmail();

        int counter = 1;
        while ((index + counter) < contactsNameFirst.length &&
                contactsNameFirst[index + counter].getName().equals(name)) {
            int compare = 0;
            for (int i = 0; i <= length - 1; i++) {
                if (counter != i) {
                    numberOfComparisons++;
                    if (contactsNameFirst[index + counter].getEmail().
                            equals(contactsNameFirst[index + i].getEmail())
                            && !contactsNameFirst[index + counter].getPhoneNumber().
                            equals(contactsNameFirst[index + i].getPhoneNumber())) {
                        result = result.concat("," +
                                contactsNameFirst[index + counter].getEmail() +
                                "SHADY_ENTRY_" + (index + i));
                        compare--;
                    }
                }
            }
            for (int i = 0; i <= counter - 1; i++) {
                numberOfComparisons++;
                if ((!contactsNameFirst[index + counter].getEmail().
                        equals(contactsNameFirst[index + i].getEmail()))) {
                    compare++;
                }
            }
            if (compare == counter) {
                result = result.concat("," + contactsNameFirst[index + counter].getEmail());
            }
            counter++;
        }
        return result.split(",");
    }

    /**
     * method removes all contactsEmailFirst with the given email
     *
     * @param mail mail identifier to delete the contactsEmailFirst
     */
    public void removeEntriesByMail(String mail) {
        int foundCounter = Sorter.binCountEntries(contactsEmailFirst,
                Contact.mailOnlyContact(mail), new EmailOnlyComparator());
        numberOfComparisons = Sorter.numberOfComps;
        if (foundCounter == 0) {
            return;
        }
        int firstIndex = Sorter.binFindFirst(contactsEmailFirst,
                Contact.mailOnlyContact(mail), new EmailOnlyComparator());
        numberOfComparisons += Sorter.numberOfComps;
        Contact[] newContacts = new Contact[contactsEmailFirst.length - foundCounter];
        int newIndex = 0;
        for (int i = 0; i < contactsEmailFirst.length; i++) {
            if (i < firstIndex || i >= firstIndex + foundCounter) {
                newContacts[newIndex] = contactsEmailFirst[i];
                newIndex++;
            }
        }
        contactsEmailFirst = newContacts;
        contactsNameFirst = Sorter.mergeSort(contactsEmailFirst, new NameFirstComparator());
        numberOfComparisons += Sorter.numberOfComps;
    }

    /**
     * counts the number of entries with a given name
     *
     * @param name name to count entries
     *
     * @return the number of entries with the given name
     */
    public int countEntries(String name) {
        return Sorter.binCountEntries(contactsNameFirst,
                Contact.nameOnlyContact(name), new NameOnlyComparator());
    }

    /**
     * counts all entries with an email
     *
     * @param email email to search for
     *
     * @return number of entries with the email
     */
    public int countNames(String email) {
        return Sorter.binCountEntries(contactsEmailFirst,
                Contact.mailOnlyContact(email), new EmailOnlyComparator());
    }

    /**
     * finds the first position of an email in contact array
     *
     * @param email email search key
     *
     * @return first of email in array
     */
    public int getFirstLocationOf(String email) {
        return Sorter.binFindFirst(contactsEmailFirst,
                Contact.mailOnlyContact(email), new EmailOnlyComparator());
    }

    /**
     * gets the last index of the mail in the list
     * @param email email key
     * @return the index of the last appearance of the email
     */
    public int getLastLocationOf(String email) {
        int index = Sorter.binFindFirst(contactsEmailFirst,
                Contact.mailOnlyContact(email), new EmailOnlyComparator());
        return index + Sorter.binCountEntries(contactsEmailFirst,
                Contact.mailOnlyContact(email), new EmailOnlyComparator()) - 1;
    }

    /**
     * Gets the email at the index
     * @param i int index
     * @return email at the index
     */
    public String emailAtLocation(int i) {
        return contactsEmailFirst[i].getEmail();
    }
}
