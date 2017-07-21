package ru.job4j.task;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс реализующий справочник подразделений с сортировкой.
 * @author agavrikov
 * @since 18.07.2017
 * @version
 */
public class CatalogUnits {

    /**
     * Поле для хранения множества подразделений.
     */
    private Set<String> catalog = new TreeSet<String>();


    /**
     * Метод для добавления подразделений в наш справочник.
     * @param unit - подразделение
     */
    public void addUnit(String unit) {
        String[] arUnits = unit.split("\\\\");
        String unitName = "";
        for (String s : arUnits) {
            if (s.equals(arUnits[0])) {
                unitName = String.format("%s", s);
            } else {
                unitName = String.format("%s\\%s", unitName, s);
            }
            if (!this.catalog.contains(unitName)) {
                catalog.add(unitName);
            }
        }
    }

    /**
     * Метод, возвращающий наш справочник с сортировкой по возрастанию.
     * @return справочник с сортировкой по возрастанию.
     */
    public Set<String> getCatalogAsc() {
        return this.catalog;
    }

    /**
     * Метод, возвращающий наш справочник с сортировкой по убыванию.
     * @return справочник с сортировкой по убыванию.
     */
    public Set<String> getCatalogDesc() {
         Set set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] ar1 = o1.split("\\\\");
                String[] ar2 = o2.split("\\\\");
                int result = -o1.compareTo(o2);

                if (ar1.length > ar2.length) {
                    if (ar1[0].compareTo(ar2[0]) > 0) {
                        result = -1;
                    } else {
                        result = 1;
                    }
                } else if (ar1.length == ar2.length && ar1[0].compareTo(ar2[0]) <= 0) {
                    result = o1.compareTo(o2);
                } else if (ar1.length < ar2.length && ar1[0].compareTo(ar2[0]) <= 0) {
                    result = o1.compareTo(o2);
                }
                return result;
            }
        });
        set.addAll(this.catalog);
        return set;
    }
}
