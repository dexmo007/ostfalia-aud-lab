import com.oracle.xmlns.internal.webservices.jaxws_databinding.XmlOneway;
import de.ostfalia.aud.ws15.Contact;
import de.ostfalia.aud.ws15.MyContactList;
import de.ostfalia.aud.ws15.MyLinkedList;
import de.ostfalia.aud.ws15.comparators.ContactComparator;
import de.ostfalia.aud.ws15.comparators.NameComparator;

import java.io.IOException;
import java.util.*;

/**
 * Created by Henrik on 10/29/2015.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String[] lines = {
                "Probst,Ines,InesProbst@superrito.com,\"Str. 5\",06551,Artern,\"03466 12 98 60\"",
                "Kohl,Johanna,JohannaKohl@rhyta.com,\"Strasse 65\",24915,Flensburg,\"0461 59 92 11\"",
                "Oster,Anke,AnkeOster@rhyta.com,\"Strasse 59\",21493,Basthorst,\"04151 58 92 71\""};
        MyLinkedList list = new MyLinkedList(new NameComparator());
        for (int i = 0; i < 3; i++) {
            Contact contact = new Contact(lines[i].replaceAll("\"", "").split(","));
            list.add(contact);
        }
        Iterator<Contact> it = list.iterator();
        while (it.hasNext()) {
            Contact c = it.next();

        }
    }
}
