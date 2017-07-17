package ru.job4j.iterator;

import java.util.Iterator;


/**
 * Класс конвертирующий несколько итераторов в один.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class Converter {
    /**
     * Метод для конвертации Итератора итераторов в единый итератор.
     * @param it итератор итераторов
     * @return Один итератор
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator = it.next();

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext() || it.hasNext();
            }

            @Override
            public Integer next() {
                int result = 0;
                if (currentIterator.hasNext()) {
                    result = currentIterator.next();
                } else if (it.hasNext()) {
                    currentIterator = it.next();
                    result = currentIterator.next();
                }
                return result;
            }
        };
    }
}
