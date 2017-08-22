package ru.job4j.calculator;

import java.util.HashMap;


/**
 * Console calculator class.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public class ModCalculator extends InteractCalc{

    /**
     * HashMap for calculator operations.
     */
    private HashMap<String, CalculateOperation> operations = new HashMap<>();

    /**
     * Constructor for initialization.
     */
    public ModCalculator() {
        this.operations.put("+", new OSum());
        this.operations.put("-", new OSubtract());
        this.operations.put("sin", new OSin());
    }

    /**
     * Method for parse user input. After each number or operation must be white space.
     * @param userInput user input.
     * @return result of calculate or message about Incorrect data.
     */
    public String parserUserInput(String userInput) {
        String result = "";
        String[] arrUserInput = userInput.split(" ");
        for (int i = 0; i < arrUserInput.length; i++) {
            if(this.operations.containsKey(arrUserInput[i])) {
                CalculateOperation operation = operations.get(arrUserInput[i]);
                if (!arrUserInput[i].equals("sin")) {
                    operation.addArg(Double.parseDouble(arrUserInput[i - 1]));
                }
                operation.addArg(Double.parseDouble(arrUserInput[i + 1]));
                result = Double.toString(operation.doCalculate());
            }
        }
        return result;
    }


    /**
     * Entry point
     * @param args params
     */
    public static void main(String[] args) {
        ModCalculator calc = new ModCalculator();
        calc.start();
    }


}
