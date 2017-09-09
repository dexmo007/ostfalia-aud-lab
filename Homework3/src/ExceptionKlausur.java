/**
 * Created by Henrik on 12/13/2015.
 */
public class ExceptionKlausur {
    static class E1 extends Exception {
        public E1(String message) {
            super(message);
        }
    }

    static class E2 extends Exception {
        public E2(String message) {
            super(message);
        }
    }

    static class E3 extends Exception {
        public E3(String message) {
            super(message);
        }
    }

    static void g(int x) throws E1,E2,E3 {
        System.out.print("A");
        switch (x) {
            case 1:
                throw new E1("B");
            case 2:
                throw new E2("C");
            case 3:
                throw new E3("D");
            default:
                //do nothing
        }
        System.out.print("E");
    }
    static void f(int x) throws E1,E3 {
        System.out.print("F");
        try {
            g(x);
            System.out.print("G");
        } catch (E2 e) {
            System.err.print(e.getMessage());
        } finally {
            System.out.println("H");
        }
        System.out.print("I");
    }

    public static void main(String[] args) throws E3{
        System.out.print("J");
        for (int i = 0; i < 4; i++) {
            try {
                f(i);
            } catch (E1 e) {
                System.out.print(e.getMessage());
            }
        }
        System.out.print("K");
    }
}
