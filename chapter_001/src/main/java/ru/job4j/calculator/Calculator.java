package ru.job4j.calculator;

/**
 * Class Класс реализующий работу элементарного калькулятора.
 * @author agavrikov
 * @since 04.07.2017
 * @version 1
*/
public class Calculator {
	/**
	 * Поле для хранения результата.
	*/
    private double result;

	/**
	 * Метод для сложения.
	 * @param first - первое слагаемое
	 * @param second - второе слагаемое
	*/
    public void add(double first, double second) {
        this.result = first + second;
    }

	/**
	 * Метод для вычитания.
	 * @param first - уменьшаемое
	 * @param second - вычитаемое
	*/
	public void substruct(double first, double second) {
        this.result = first - second;
    }

	/**
	 * Метод для деления.
	 * @param first - делимое
	 * @param second - делитель
	*/
	public void div(double first, double second) {
        this.result = first / second;
    }

	/**
	 * Метод для сложения.
	 * @param first - умножаемое
	 * @param second - множитель
	*/
	public void multiple(double first, double second) {
        this.result = first * second;
    }

	/**
	 * Метод для получения результата из поля result.
	 * @return результат
	*/
    public double getResult() {
        return this.result;
    }
}