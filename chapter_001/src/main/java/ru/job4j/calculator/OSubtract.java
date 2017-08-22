package ru.job4j.calculator;

/**
 * Class for calculate operation subtract.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public class OSubtract extends CalculateOperation {

    /**
     * Abstract method for calculate operation on args.
     * @return result of calculate
     */
    @Override
    public Double calculate() {
        Double result = this.getArgs().get(0);
        this.getArgs().remove(0);
        for (Double n : this.getArgs()) {
            result = result - n;
        }
        return result;
    }
}