package de.ostfalia.aud.ws15;

import de.ostfalia.aud.ws15.comparators.ContactComparator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * own linked list implementation
 *
 * @author Henrik
 * @author Maxi
 */
public class MyLinkedList implements Iterable<Contact> {

    private Node firstNode;
    private Node lastNode;

    private ContactComparator comparator;

    private int size;

    /**
     * constructor for MyLinkedList based on comparator
     * @param comparator contact comparator
     */
    public MyLinkedList(ContactComparator comparator) {
        this.firstNode = null;
        this.lastNode = null;
        this.comparator = comparator;
        this.size = 0;
    }

    /**
     * adds a contact to MyLinkedList
     *
     * @param contact contact to add
     */
    public void add(Contact contact) {
        Node newNode = new Node(contact);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
            newNode.previous = lastNode;
        }
        lastNode = newNode;
        size++;
    }

    /**
     * removes a contact from MyLinkedList
     *
     * @param contact contact to remove
     * @return true if contact removed, else false
     */
    public boolean remove(Contact contact) {
        for (Node element = firstNode; element != null; element = element.next) {
            if (comparator.compare(contact, element.contact) == 0) {
                unlink(element);
                return true;
            }
        }
        return false;
    }

    /**
     * removes all occurances of a contact on MyLinkedList
     *
     * @param contact contact to remove
     * @return list of removed elements
     */
    public MyLinkedList removeAll(Contact contact) {
        MyLinkedList res = new MyLinkedList(this.comparator);
        for (Node element = firstNode; element != null; element = element.next) {
            if (comparator.compare(contact, element.contact) == 0) {
                res.add(element.contact);
                unlink(element);
            }
        }
        return res;
    }

    /**
     * removes all occurances of a contact on MyLinkedList based on String key
     * @param key
     * @return
     */
    public MyLinkedList removeAll(String key) {
        MyLinkedList res = new MyLinkedList(this.comparator);
        Node element = firstNode;
        while (element != null) {
            if (comparator.compareKeyToContact(key, element.contact) == 0) {
                res.add(element.contact);
                Node remove = element;
                element = element.next;
                unlink(remove);
            } else {
                element = element.next;
            }
        }
        return res;
    }

    /**
     * unlinks a node from the double linked list
     *
     * @param node node to unlink
     */
    private void unlink(Node node) {
        // assert node != null;
        final Node next = node.next;
        final Node previous = node.previous;

        if (previous == null) {
            firstNode = next;
        } else {
            previous.next = next;
            node.previous = null;
        }

        if (next == null) {
            lastNode = previous;
        } else {
            next.previous = previous;
            node.next = null;
        }

        node.contact = null;
        size--;
    }

    /**
     * checks if the linked list is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * returns size of the linked list
     *
     * @return size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * converts linked list into a contact array
     *
     * @return contact array containing all list elements
     */
    public Contact[] toArray() {
        Contact[] arr = new Contact[size];
        int i = 0;
        for (Contact element : this) {
            arr[i++] = element;
        }
        return arr;
    }

    @Override
    public String toString() {
        Iterator<Contact> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            Contact contact = it.next();
            sb.append(comparator.comparingKey(contact));
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }

    @Override
    public Iterator<Contact> iterator() {
        return new MyIterator();
    }

    @Override
    public void forEach(Consumer<? super Contact> action) {
        for (Contact element : this) {
            action.accept(element);
        }
    }

    /**
     * List Element
     */
    private static class Node {
        Contact contact;
        Node previous;
        Node next;

        /**
         * constructor for a Node
         * @param contact data of {@code Node}
         */
        Node(Contact contact) {
            this.previous = null;
            this.next = null;
            this.contact = contact;
        }
    }

    /**
     * Iterator class for MyLinkedList
     */
    private class MyIterator implements Iterator<Contact> {

        private Node next;

        /**
         * constructor for MyIterator
         */
        private MyIterator() {
            next = firstNode;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Contact next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node prev = next;
            next = next.next;
            return prev.contact;
        }
    }
}