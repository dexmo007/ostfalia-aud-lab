package de.ostfalia.aud.ws15.comparators;

import de.ostfalia.aud.ws15.Contact;

import java.util.Comparator;

/**
 * Created by Henrik on 11/26/2015.
 *
 * @author Henrik
 * @author Maxi
 */
public interface ContactComparator extends Comparator<Contact> {
    /**
     * compares two contacts
     *
     * @param o1 first contact
     * @param o2 second contact
     * @return integer compare value
     */
    public int compare(Contact o1, Contact o2);

    /**
     * compares key, considers case-sensitivity
     *
     * @param k1 first key
     * @param k2 second key
     * @return integer compare value
     */
    public int compareKeys(String k1, String k2);

    /**
     * compares a key and a contact
     * @param key     key
     * @param contact contact
     * @return integer compare value
     */
    public int compareKeyToContact(String key, Contact contact);

    /**
     * returns the key that is compared in compare(...)-method
     * @param contact contact
     * @return relevant String key
     */
    public String comparingKey(Contact contact);

    /**
     * converts the string to the case that it is compared in
     *
     * @param key string key
     * @return key in comparing case
     */
    public String toCase(String key);
}
