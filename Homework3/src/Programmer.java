import java.util.*;

/**
 * Aufgabe 3-4 b)
 *
 * @author Henrik Drefs
 */
public class Programmer {

    Contract[] contracts;

    static class Contract {

        int time;

        Contract(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "" + this.time;
        }
    }

    public Programmer(Contract... contracts) {
        this.contracts = contracts;
    }

    public int minWaitingTime() {
        quickSort(contracts, (c1, c2) -> c1.time - c2.time);
        int total = 0;
        for (int i = 0; i < contracts.length; i++) {
            total += (contracts.length - i) * contracts[i].time;
        }
        return total;
    }

    public static <T> void quickSort(T[] objects, Comparator<T> comparator) {
        recQuickSort(objects, 0, objects.length - 1, comparator);
    }

    private static <T> void recQuickSort(T[] objects, int leftBound, int rightBound, Comparator<T> comparator) {
        if (rightBound <= leftBound) {
            return;
        }
        T pivot = objects[rightBound];
        int wall = leftBound;
        for (int i = leftBound; i < rightBound; i++) {
            T currentElement = objects[i];
            if (comparator.compare(currentElement, pivot) < 0) {
                // swop current element with the element next to the wall
                objects[i] = objects[wall];
                objects[wall] = currentElement;
                wall++;
            }
        }
        //swop pivot element with the element next to the wall
        objects[rightBound] = objects[wall];
        objects[wall] = pivot;

        recQuickSort(objects, leftBound, wall - 1, comparator);
        recQuickSort(objects, wall + 1, rightBound, comparator);
    }

    public static void main(String[] args) {
        System.out.println(findMin(new Integer[]{5, 3, 10}));
    }

    public static int findMin(Integer[] array) {
        boolean[] marked = new boolean[array.length];
        int gesamt = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int neu = findMin(array, i, marked).total;
            if (neu < gesamt) {
                gesamt = neu;
            }
        }
        return gesamt;
    }

    private static Pair findMin(Integer[] array, int location, boolean[] marked) {
        marked[location] = true;
        int aktuell = 0;
        int gesamt = 0;
        int neu;
        for (int i = 0; i < array.length; i++) {
            if (!marked[i]) {
                Pair tw = findMin(array, i, marked);
                neu = tw.current;
                if (aktuell == 0 || neu < aktuell || tw.total < gesamt) {
                    aktuell = neu;
                    gesamt = tw.total;
                }
            }
        }
        marked[location] = false;
        aktuell += array[location];
        return new Pair(aktuell, gesamt + aktuell);
    }

    private static class Pair {
        int current;
        int total;

        public Pair(int current, int total) {
            this.current = current;
            this.total = total;
        }
    }
}
