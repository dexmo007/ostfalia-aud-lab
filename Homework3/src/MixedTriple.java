import java.lang.reflect.Array;

/**
 * Created by Henrik on 12/17/2015.
 */
public class MixedTriple<T, U, V> {

    T content1;
    U content2;
    V content3;

    public MixedTriple(T t, U u, V v) {
        this.content1 = t;
        this.content2 = u;
        this.content3 = v;
    }

    public void setFirst(T t) {
        content1 = t;
    }

    public void setSecond(U u) {
        content2 = u;
    }

    public void setThird(V v) {
        content3 = v;
    }

    public T getFirst() {
        return content1;
    }

    public U getSecond() {
        return content2;
    }

    public V getThird() {
        return content3;
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> boolean isSorted(MixedTriple<T, U, V> mt) {
        Comparable h1 = mt.getFirst();
        Comparable h2 = mt.getSecond();
        Comparable h3 = mt.getThird();
        return h1.compareTo(h2) <= 0 && h2.compareTo(h3) <= 0;
    }

    public static void main(String[] args) {
        MixedTriple<Integer, Integer, Integer> mt = new MixedTriple<>(1, 2, 3);
        System.out.println(isSorted(mt));
    }

}