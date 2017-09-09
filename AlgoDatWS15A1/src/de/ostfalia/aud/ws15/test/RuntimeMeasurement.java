package de.ostfalia.aud.ws15.test;

import de.ostfalia.aud.ws15.MyContactList;

import java.io.IOException;

/**
 * @author Henrik and Maxi
 */
public class RuntimeMeasurement {

    public  static long evaluateLoadingDuration(){
        MyContactList mc = null;
        long durationLoading = 0;
        for (int i = 0; i < 100; i++) {
            long before = System.nanoTime();
            try {
                mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
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
        int numberOfTests = 25000;
        for (int i = 0; i < 25000; i += 25000 / numberOfTests) {
            try {
                mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            long before = System.nanoTime();
            mc.removeEntriesByMail(mc.contacts[i].getEmail());
            duration += System.nanoTime() - before;
            if (i % 20 == 0) {
                System.out.println(i);
            }
        }
        return duration / numberOfTests;
    }

    public static long evaluateSearchingForNameDuration() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = 0;
        for (int i = 0; i < 25000; i += 250) {
            long before = System.nanoTime();
            mc.getName(mc.contacts[i].getEmail());
            duration += System.nanoTime() - before;
        }
        return duration / 100;
    }

    public static long evaluateSearchingForEmailDuration() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = 0;
        for (int i = 0; i < 25000; i += 250) {
            long before = System.nanoTime();
            mc.getEmail(mc.contacts[i].getName());
            duration += System.nanoTime() - before;
        }
        return duration / 100;
    }

    public static int evaluateNumberOfCompRemoving() {
        MyContactList mc = null;
        int numberOfComp = 0;
        for (int i = 0; i < 25000; i += 250) {
            try {
                mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            mc.removeEntriesByMail(mc.contacts[i].getEmail());
            numberOfComp += mc.numberOfComparisons;
        }
        return numberOfComp / 100;
    }
    public static int evaluateNumberOfCompSearchingForName() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numberOfComp = 0;
        for (int i = 0; i < 25000; i += 250) {
            mc.getName(mc.contacts[i].getEmail());
            numberOfComp += mc.numberOfComparisons;
        }
        return numberOfComp / 100;
    }

    public static int evaluateNumberOfCompSearchingForEmail() {
        MyContactList mc = null;
        try {
            mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numberOfComp = 0;
        for (int i = 0; i < 25000; i += 250) {
            mc.getEmail(mc.contacts[i].getName());
            numberOfComp += mc.numberOfComparisons;
        }
        return numberOfComp / 100;
    }

    public  static int evaluateNumberOfCompLoading(){
        MyContactList mc = null;
        int numberOfComp = 0;
        for (int i = 0; i < 100; i++) {
            long before = System.nanoTime();
            try {
                mc = new MyContactList("C:/Users/Henrik/Documents/S2_AuD_Lab/AlgoDatWS15A1/Materialien/Contacts25k.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            numberOfComp += mc.numberOfComparisons;
        }
        return numberOfComp / 100;
    }



    public static void main(String[] args) {
        System.out.println(evaluateRemovingDuration());
    }
}