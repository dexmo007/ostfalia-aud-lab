import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Henrik on 12/17/2015.
 * @author Henrik Drefs
 */
public class Maths {

    public static List<Integer> primeSieve(int n) {
        final List<Integer> primes = new ArrayList<>(n);
        IntStream.iterate(2, i -> i + 1).filter(i -> {
            for (int prime : primes) {
                if (i % prime == 0) {
                    return false;
                }
            }
            return true;
        }).limit(n).forEach(primes::add);
        return primes;
    }

    public static Collection hashCodes(Collection c) {
        Collection res = new LinkedList<>();
        c.stream().map(o -> o.hashCode()).reduce((a, b) -> a.equals(b));
        return res;
    }

    public static void main(String[] args) {
        Collection c = new LinkedList<>();
        c.add("String");
        c.add(3);
        c.add(0.45);
        c.add('c');
        c.add(new Programmer(new Programmer.Contract(5)));
        System.out.println((hashCodes(c)));
    }
}
