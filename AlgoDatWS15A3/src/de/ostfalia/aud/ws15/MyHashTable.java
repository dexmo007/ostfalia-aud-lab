package de.ostfalia.aud.ws15;

import de.ostfalia.aud.ws15.comparators.ContactComparator;
import de.ostfalia.aud.ws15.comparators.EmailComparator;

/**
 * own hash table implementation
 * Created by Henrik on 11/26/2015.
 *
 * @author Henrik
 * @author Maxi
 */
public class MyHashTable {

    private MyLinkedList[] arr;
    private int capacity;
    private int size;
    private ContactComparator c;

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double MAX_LOAD_FACTOR = 0.75;

    /**
     * contructor for hashTable with omparator
     *
     * @param c           contact omparator
     * @param initialSize initial size
     */
    public MyHashTable(ContactComparator c, int initialSize) {
        capacity = 2 * initialSize;
        size = 0;
        this.c = c;
        arr = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = new MyLinkedList(c);
        }
    }

    /**
     * contructor for hash table with comparator only, using default initial size
     *
     * @param c contact comparator
     */
    public MyHashTable(ContactComparator c) {
        this(c, DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * hash value of a key
     * @param key String key
     * @return integer hash value of the key
     */
    private int hashFunction(String key) {
        return Math.abs(c.toCase(key).hashCode()) % capacity;
    }

    /**
     * adds a {@code Contact} to hash table
     * @param contact contact to add
     */
    public void add(Contact contact) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            rehash();
        }
        String key = c.comparingKey(contact);
        int index = hashFunction(key);
        arr[index].add(contact);
        size++;
    }

    /**
     * performs rehash with doubled size
     */
    private void rehash() {
        MyLinkedList[] old = arr;
        capacity = 2 * capacity;
        arr = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = new MyLinkedList(c);
        }
        for (MyLinkedList e : old) {
            for (Contact contact : e) {
                String key = c.comparingKey(contact);
                int index = hashFunction(key);
                arr[index].add(contact);
            }
        }
    }

    /**
     * calculates the load factor for this hash table
     * @return double load factor
     */
    private double loadFactor() {
        return capacity == 0 ? 0 : ((double) size) / capacity;
    }

    /**
     * gets a contact based on string key
     *
     * @param key {@code String} key
     * @return contact in {@code MyHashTable} to key
     */
    public Contact get(String key) {
        if (capacity == 0) {
            return null;
        }
        MyLinkedList myLinkedList = arr[hashFunction(key)];
        for (Contact element : myLinkedList) {
            if (c.compareKeyToContact(key, element) == 0) {
                return element;
            }
        }
        return null;
    }

    /**
     * gets all contacts to a string key in a {@code MyLinkedList}
     * @param key string key
     * @return linked list with all contacts to the key
     */
    public MyLinkedList getAll(String key) {
        if (capacity == 0) {
            return null;
        }
        MyLinkedList found = arr[hashFunction(key)];
        MyLinkedList res = new MyLinkedList(c);
        for (Contact element : found) {
            if (c.compareKeyToContact(key, element) == 0) {
                res.add(element);
            }
        }
        return res;
    }

    /**
     * get all contacts with a specified key once in a hash table
     * @param key key to search for
     * @return hash table with all contacts once matching the key
     */
    public MyHashTable getAllOnce(String key) {
        if (capacity == 0) {
            return null;
        }
        MyLinkedList found = arr[hashFunction(key)];
        MyHashTable res = new MyHashTable(new EmailComparator());
        for (Contact contact : found) {
            if (c.compareKeyToContact(key, contact) == 0 && !res.contains(contact)) {
                res.add(contact);
            }
        }
        return res;
    }

    /**
     * removes entry in hash table based on a String key and returns a linked ist containing all removed contacts
     * @param key String key
     * @return MyLinkedList with all removed contacts
     */
    public MyLinkedList remove(String key) {
        MyLinkedList res = new MyLinkedList(c);
        res = arr[hashFunction(key)].removeAll(key);
        size -= res.size();
        return res;
    }

    /**
     * number of all elements in hashtable
     *
     * @return size of hashtable
     */
    public int size() {
        return size;
    }

    /**
     * if hash table contains a contact
     *
     * @param contact contact to check
     * @return true if contact in hashtable, else false
     */
    public boolean contains(Contact contact) {
        return contains(c.comparingKey(contact));
    }

    /**
     * if hash table contains a contact with certain key
     *
     * @param key String key
     * @return true if containing, else false
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            MyLinkedList l = arr[i];
            if (l.size() == 1) {
                sb.append(c.comparingKey(l.iterator().next()));
            } else if (l.size() > 1) {
                sb.append(l.size());
            }
            sb.append(',').append(' ');
        }
        sb.setLength(sb.length() - 2);
        return sb.append(']').toString();
    }

    public Contact[] toArray() {
        Contact[] res = new Contact[size];
        int i = 0;
        for (MyLinkedList list : arr) {
            for (Contact contact : list) {
                res[i++] = contact;
            }
        }
        return res;
    }
}
