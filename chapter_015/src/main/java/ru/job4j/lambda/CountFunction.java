package ru.job4j.lambda;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by gavrikov.a on 08/09/2017.
 */
public class CountFunction {

    List<Double> diapason(int start, int end, Function<Integer, Double> func){
        List<Double> result = new LinkedList<Double>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply(i));
        }
        return result;
    }

    public static void main(String[] args) {
        CountFunction cf = new CountFunction();
        cf.diapason(1, 10,
                (Integer x)-> new Integer(x * 2 + 1).doubleValue()
        );
        cf.diapason(1, 10,
                (Integer x)-> new Integer(3 * x * x * 2 * x + 1).doubleValue()
        );
        cf.diapason(1, 10,
                (Integer x)-> Math.log(x)
        );
    }
}
