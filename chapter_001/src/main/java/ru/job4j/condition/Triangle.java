package ru.job4j.condition;

/**
 * Class  для определения площади треугольника.
 * @author agavrikov
 * @since 04.07.2017
 * @version 1
*/
public class Triangle {
	/**
	 * Поле a в объекте которого хрянятся координаты x и y.
	*/
	private Point a;

	/**
	 * Поле b в объекте которого хрянятся координаты x и y.
	*/
	private Point b;

	/**
	 * Поле c в объекте которого хрянятся координаты x и y.
	*/
	private Point c;

	/**
	 * конструктор.
	 * @param a - точка a
	 * @param b - точка b
	 * @param c - точка c
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Метод для определения площади треугольникс.
	 * @return площадь треугольника
	*/
	public double area() {
		double area;
		double a = getLenghtLine(this.a.getX(), this.a.getY(), this.b.getX(), this.b.getY());
        double b = getLenghtLine(this.b.getX(), this.b.getY(), this.c.getX(), this.c.getY());
        double c = getLenghtLine(this.c.getX(), this.c.getY(), this.a.getX(), this.a.getY());
        if (a + b <= c || a + c <= b || a + c <= b) {
			area = 0;
		} else {
            double p = (a + b + c) / 2;
            area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
		return area;
	}

	/**
	 * Вспомогательный метод, для поределения длинны отрезка по 4 координатам.
	 * @param x1 - Координата по оси x первой точки
	 * @param y1 - Координата по оси y первой точки
	 * @param x2 - Координата по оси ч второй точки
	 * @param y2 - Координата по оси y второй точки
	 * @return длинну отрезка
	*/
	public double getLenghtLine(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
}