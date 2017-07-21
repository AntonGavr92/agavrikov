package ru.job4j.task;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gavrikov.a on 20/07/2017.
 */
public class CatalogUnits {

    public static void main(String[] srgs) {
        Set<String> catalog = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] ar1 = o1.split("\\\\");
                String[] ar2 = o2.split("\\\\");


                if (o1.split("\\\\").length > o2.split("\\\\").length){
                    if (ar1[0].compareTo(ar2[0]) > 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                    //System.out.println(ar1[0]);
                    //return 1;
                }
                return -o1.compareTo(o2);
            }
        });
        catalog.add("K1\\SK1");
        catalog.add("K1\\SK2");
        catalog.add("K1\\SK1\\SSK1");
        catalog.add("K1\\SK1\\SSK2");
        catalog.add("K2");
        catalog.add("K2\\SK1\\SSK1");
        catalog.add("K2\\SK1\\SSK2");

        for (String s : catalog) {
            System.out.println(s);
        }
    }
}
