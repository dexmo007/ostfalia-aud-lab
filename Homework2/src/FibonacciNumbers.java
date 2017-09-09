/**
 * Created by Henrik on 12/2/2015.
 */
public class FibonacciNumbers {
    // a)
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    // b)
    public static int fibDivConq(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int k;
        if (n % 2 == 0) {
            k = n / 2;
            int f = fibDivConq(k);
            return f * f + 2 * f * fibDivConq(k - 1);
        } else {
            k = (n + 1) / 2;
            int f1 = fibDivConq(k);
            int f2 = fibDivConq(k - 1);
            return f1 * f1 + f2 * f2;
        }
    }

    // c)
    /*

    a -> O(2^n)
    b -> O(3^((n+1)/2))

     */

}
