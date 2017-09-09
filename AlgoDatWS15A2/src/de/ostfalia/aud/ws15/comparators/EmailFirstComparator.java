package de.ostfalia.aud.ws15.comparators;

import de.ostfalia.aud.ws15.Contact;

import java.util.Comparator;

/**
 * Created by Henrik on 10/28/2015.
 * @author Henrik and Maxi
 */
public class EmailFirstComparator implements Comparator<Contact> {
    /**
     * compares two contacts through email, then name
     *
     * @param contact1 first contact
     * @param contact2 second contact
     * @return alphabetical diffrence between the first letters of the emails
     * if not zero, else alphabetical diffrence between the first letters of the names
     */
    @Override
    public int compare(Contact contact1, Contact contact2) {
        int compared = contact1.getEmail().compareToIgnoreCase(contact2.getEmail());
        return compared == 0 ? contact1.getName()
                .compareToIgnoreCase(contact2.getName()) : compared;
    }
}
