package ru.job4j.loop;

/**
 * Class Класс для построения пирамиды.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class Paint {
	/**
	 * Метод строит пирамиду псевдографике.
	 * @param height - высота пирамиды
	 * @return строку псевдографика
	*/
	public String piramid(int height) {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= height; i++) {
			for (int j = 0; j < height * 2 - 1; j++) {
				if (height - i <= j && height + i - 1 > j) {
					result.append("^");
				} else {
					result.append(" ");
				}
			}
			if (i != height) {
				result.append(System.getProperty("line.separator"));
			}
		}
		return result.toString();
	}
}