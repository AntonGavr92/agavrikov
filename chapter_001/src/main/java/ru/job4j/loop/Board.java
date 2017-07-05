package ru.job4j.loop;


/**
 * Class Класс для построения шахматной доски.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class Board {

	/**
	 * Метод строит шахматную доску в псевдографике без вложенного цикла.
	 * @param width - ширина доски
	 * @param height - высота доски
	 * @return строку псевдографика
	*/
	public String paintWithoutInnerLoop(int width, int height) {
		StringBuilder result = new StringBuilder();
		int countFields = width * height;
		for (int i = 0; i < countFields; i++) {
			if (i != 0 && i % width == 0) {
				result.append(System.getProperty("line.separator"));
			}
			if (i % 2 == 0) {
				result.append("x");
			} else {
				result.append(" ");
			}
		}
		result.append(System.getProperty("line.separator"));
		return result.toString();
	}

	/**
	 * Метод строит шахматную доску в псевдографике с вложенным циклом.
	 * @param width - ширина доски
	 * @param height - высота доски
	 * @return строку псевдографика
	*/
	public String paint(int width, int height) {
		String lastField = "";
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (lastField.length() > 0) {
					if (lastField.equals("x")) {
						result.append(" ");
						lastField = " ";
					} else {
						result.append("x");
						lastField = "x";
					}
				} else {
					result.append("x");
					lastField = "x";
				}
			}
			result.append(System.getProperty("line.separator"));
		}
		return result.toString();
	}
}