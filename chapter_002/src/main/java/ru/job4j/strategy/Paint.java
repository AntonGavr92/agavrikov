package ru.job4j.strategy;

/** Класс, описывающий рисование фигур.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class Paint {

    /**
     * Метод рисует фигуру.
     * @param shape - фигура
     */
    public void draw(Shape shape) {
        System.out.println(shape.pic());
    }
}
