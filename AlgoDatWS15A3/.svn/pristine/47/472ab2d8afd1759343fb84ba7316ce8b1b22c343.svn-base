import de.ostfalia.aud.ws15.MyContactList;

import java.io.IOException;

/**
 * @author Henrik
 * @author Maxi
 */
public class RuntimeMeasurement {

    public static long evaluateLoadingDuration() {
        MyContactList mc = null;
        long durationLoading = 0;
        for (int i = 0; i < 100; i++) {
            long before = System.nanoTime();
            try {
                mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A4\\Materialien\\public\\Contacts25k.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            durationLoading += System.nanoTime() - before;
        }
        return durationLoading / 100;
    }

    public static long evaluateRemovingDuration() {
        MyContactList mc = null;
        long duration = 0;
        int numberOfTests = 100;
        for (int i = 0; i < 25000; i += 25000 / numberOfTests) {
            try {
                mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A4\\Materialien\\public\\Contacts25k.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            long before = System.nanoTime();
            mc.removeEntriesByMail("SophieKrueger@jourrapide.com");
            mc.removeEntriesByMail("MandyFaerber@dayrep.com");
            mc.removeEntriesByMail("StephanMaurer@superrito.com");
            duration += System.nanoTime() - before;
        }
        return duration / numberOfTests / 3;
    }

    public static long evaluateSearchingForNameDuration() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = 0;
        for (int i = 0; i < 25000; i += 250) {
            long before = System.nanoTime();
            mc.getName("SophieKrueger@jourrapide.com");
            mc.getName("MandyFaerber@dayrep.com");
            mc.getName("StephanMaurer@superrito.com");
            duration += System.nanoTime() - before;
        }
        return duration / 300;
    }

    public static long evaluateSearchingForEmailDuration() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = 0;
        for (int i = 0; i < 25000; i += 250) {
            long before = System.nanoTime();
            mc.getEmail("Maurer");
            mc.getEmail("Krueger");
            mc.getEmail("Faerber");
            duration += System.nanoTime() - before;
        }
        return duration / 100;
    }

//    public static int evaluateNumberOfCompRemoving() {
//        MyContactList mc = null;
//        int numberOfComp = 0;
//        for (int i = 0; i < 25000; i += 250) {
//            try {
//                mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mc.removeEntriesByMail(mc.contactsEmailFirst[i].getEmail());
//            numberOfComp += mc.numberOfComparisons;
//        }
//        return numberOfComp / 100;
//    }
//    public static int evaluateNumberOfCompSearchingForName() {
//        MyContactList mc = null;
//        try {
//            mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int numberOfComp = 0;
//        for (int i = 0; i < 25000; i += 1) {
//            mc.getName(mc.contactsEmailFirst[i].getEmail());
//            numberOfComp += mc.numberOfComparisons;
//        }
//        return numberOfComp / 25000;
//    }
//
//    public static int evaluateNumberOfCompSearchingForEmail() {
//        MyContactList mc = null;
//        try {
//            mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int numberOfComp = 0;
//        for (int i = 0; i < 25000; i += 1) {
//            mc.getEmail(mc.contactsEmailFirst[i].getName());
//            numberOfComp += mc.numberOfComparisons;
//        }
//        return numberOfComp / 25000;
//    }
//
//    public  static int evaluateNumberOfCompLoading(){
//        MyContactList mc = null;
//        int numberOfComp = 0;
//        for (int i = 0; i < 100; i++) {
//            long before = System.nanoTime();
//            try {
//                mc = new MyContactList("C:\\Users\\Henrik\\Documents\\S2_AuD_Lab\\AlgoDatWS15A2\\Materialien\\public\\Contacts25k.csv");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            numberOfComp += mc.numberOfComparisons;
//        }
//        return numberOfComp / 100;
//    }

    public static void main(String[] args) {
//        System.out.println(evaluateLoadingDuration());
        System.out.println(evaluateRemovingDuration());
        System.out.println(evaluateSearchingForEmailDuration());
        System.out.println(evaluateSearchingForNameDuration());
    }
}
