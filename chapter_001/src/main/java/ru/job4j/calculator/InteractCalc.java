package ru.job4j.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console calculator class.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public class InteractCalc {

    /**
     * Object Calculator for calculate.
     */
    private Calculator calc;

    /**
     * Command for get previous result.
     */
    private static final String PREV_RESULT = "prev";

    /**
     * Command for exit from programm.
     */
    private static final String EXIT_COMMAND = "exit";

    /**
     * Operation sum.
     */
    private static final String OPERATION_SUM = "+";

    /**
     * Operation div.
     */
    private static final String OPERATION_DIV = "/";

    /**
     * Operation multiple.
     */
    private static final String OPERATION_MULTIPLE = "*";

    /**
     * Operation subtract.
     */
    private static final String OPERATION_SUBTRACT = "-";


    /**
     * Constructor for initialization.
     */
    public InteractCalc() {
        this.calc = new Calculator();
    }

    /**
     * Main method whith loop, for interaction with console.
     */
    public void start() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            while (!(userInput = bf.readLine()).equals(EXIT_COMMAND)) {
                System.out.println(this.parserUserInput(userInput));
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Method for parse user input.
     * @param userInput user input.
     * @return result of calculate or message about Incorrect data.
     */
    public String parserUserInput(String userInput) {
        String result;
        if (userInput.contains(PREV_RESULT)) {
            userInput = userInput.replace(PREV_RESULT, Double.toString(this.calc.getResult()));
        }
        String[] arrUserInput = userInput.split(" ");
        if (arrUserInput.length < 3) {
            result = "Incorrect data, try again.";
        } else {
            result = calculateNum(Double.parseDouble(arrUserInput[0]), Double.parseDouble(arrUserInput[2]), arrUserInput[1]);
        }
        return result;
    }

    /**
     * Method for calculate.
     * @param arg1 first argument
     * @param arg2 second argument
     * @param operation operation
     * @return result of calculate
     */
    private String calculateNum(double arg1, double arg2, String operation) {
        if (operation.equals(OPERATION_SUM)) {
            this.calc.add(arg1, arg2);
        } else if (operation.equals(OPERATION_SUBTRACT)) {
            this.calc.substruct(arg1, arg2);
        } else if (operation.equals(OPERATION_DIV)) {
            this.calc.div(arg1, arg2);
        } else if (operation.equals(OPERATION_MULTIPLE)) {
            this.calc.multiple(arg1, arg2);
        }
        return Double.toString(this.calc.getResult());
    }

    /**
     * Entry point
     * @param args params
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.start();
    }


}
