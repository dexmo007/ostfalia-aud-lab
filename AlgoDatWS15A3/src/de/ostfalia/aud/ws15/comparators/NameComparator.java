package de.ostfalia.aud.ws15.comparators;

import de.ostfalia.aud.ws15.Contact;

/**
 * Created by Henrik on 11/26/2015.
 *
 * @author Henrik
 * @author Maxi
 */
public class NameComparator implements ContactComparator {
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public int compareKeys(String k1, String k2) {
        return k1.compareTo(k2);
    }

    @Override
    public int compareKeyToContact(String key, Contact contact) {
        return key.compareTo(contact.getName());
    }

    @Override
    public String comparingKey(Contact contact) {
        return contact.getName();
    }

    @Override
    public String toCase(String key) {
        return key;
    }
}