package de.ostfalia.aud.ws15.comparators;

import de.ostfalia.aud.ws15.Contact;

/**
 * Created by Henrik on 11/26/2015.
 *
 * @author Henrik
 * @author Maxi
 */
public class EmailComparator implements ContactComparator {

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getEmail().compareToIgnoreCase(o2.getEmail());
    }

    @Override
    public int compareKeys(String k1, String k2) {
        return k1.compareToIgnoreCase(k2);
    }

    @Override
    public int compareKeyToContact(String key, Contact contact) {
        return key.compareToIgnoreCase(contact.getEmail());
    }

    @Override
    public String comparingKey(Contact contact) {
        return contact.getEmail();
    }

    @Override
    public String toCase(String key) {
        return key.toLowerCase();
    }
}
