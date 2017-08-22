package ru.job4j.calculator;

/**
 * Class for calculate operation sin.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public class OSin extends CalculateOperation {

    /**
     * Abstract method for calculate operation on args.
     * @return result of calculate
     */
    @Override
    public Double calculate() {
        Double pi = 3.14159265358979;
        return this.getArgs().get(0) * pi/ 180;
    }
}