package ru.job4j.calculator;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for calculator operations.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public abstract class CalculateOperation {

    /**
     * List of arguments.
     */
    private final List<Double> args = new LinkedList<>();

    /**
     * Abstract method for calculate operation on args.
     * @return result of calculate
     */
    public abstract Double calculate();


    /**
     * Method for calculate operation and clear args after that.
     */
    public double doCalculate() {
        double result = this.calculate();
        this.args.clear();
        return result;
    }

    /**
     * Method for adding arguments.
     * @param arg argument
     */
    public void addArg(Double arg) {
        args.add(arg);
    }

    /**
     * Method for get all arguments.
     * @return list of arguments
     */
    public List<Double> getArgs() {
        return this.args;
    }

}
