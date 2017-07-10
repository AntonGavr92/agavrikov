package ru.job4j.strategy;

/** Класс, описывающий треугольник.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class Square implements Shape {
    /**
     *
     * @return возвращает строку, которая рисует треугольник.
     */
    @Override
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append(" xxxx ");
        pic.append(System.lineSeparator());
        pic.append(" xxxx ");
        pic.append(System.lineSeparator());
        pic.append(" xxxx ");
        pic.append(System.lineSeparator());
        pic.append(" xxxx ");
        return pic.toString();
    }
}
