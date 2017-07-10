package ru.job4j.strategy;

/** Класс, описывающий треугольник.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class Triangle implements Shape {
    /**
     *
     * @return возвращает строку, которая рисует треугольник
     */
    @Override
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("   x   ");
        pic.append(System.lineSeparator());
        pic.append("  xxx  ");
        pic.append(System.lineSeparator());
        pic.append(" xxxxx ");
        pic.append(System.lineSeparator());
        pic.append("xxxxxxx");
        return pic.toString();
    }
}
