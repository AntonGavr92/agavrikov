package ru.job4j.condition;

/**
 * Class Класс для определения, принадлежит ли точка функции y(x) = a * x + b.
 * @author agavrikov
 * @since 04.07.2017
 * @version 1
*/
public class Point {
	/**
	 * Поле с координатой по x.
	*/
	private int x;

	/**
	 * Поле с координатой по y.
	*/
	private int y;

	/**
	 * Конструктор. производит инициализацию полей x и y.
	 * @param x - координата по x
	 * @param y - координата по y
	*/
	public Point(int x, int y) {
	  this.x = x;
	  this.y = y;
	}

	/**
	 * Геттер.
	 * @return координату точки по x
	*/
	public int getX() {
	  return this.x;
	}

	/**
	 * Геттер.
	 * @return координату точки по y
	*/
	public int getY() {
	 return this.y;
	}

	/**
	 * Метод проверяет, принадлежит ли точка функции y(x) = a * x + b.
	 * @param a - множитель a в функции
	 * @param b - слагаемое b в функции
	 * @return координату точки по y
	*/
	public boolean is(int a, int b) {
		return this.y == a * this.x + b;
	}
}