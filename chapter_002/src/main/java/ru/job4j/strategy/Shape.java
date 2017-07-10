package ru.job4j.strategy;

/** Интерфейс, определяющий геометрическую фигуру.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public interface Shape {

    /**
     * Метод для отрисовки фигуры.
     * @return возвращает строку, которая рисует фигуру
     */
    String pic();
}
