package ru.job4j.task;


/**
 * Class Класс для определения, есть ли в строке подстрока.
 * @author agavrikov
 * @since 04.07.2017
 * @version 1
*/
public class SearchSubWord {

	/**
	 * Метод проверяет, есть ли в строке подстрока.
	 * @param origin - строка
	 * @param sub - Подстрока
	 * @return true, если подстрока есть в строке или false - если нет
	*/
	public boolean contains(String origin, String sub) {
		char[] orignCharArray = origin.toCharArray();
		char[] subCharArray = sub.toCharArray();
		boolean subExist = false;
		for (int i = 0; i < orignCharArray.length; i++) {
			if (orignCharArray[i] == subCharArray[0] && subCharArray.length <= orignCharArray.length - i) {
				subExist = true;
				for (int j = 1; j < subCharArray.length; j++) {
					if (orignCharArray[i + j] != subCharArray[j]) {
						subExist = false;
						break;
					}
				}
				if (subExist) {
					return subExist;
				}
			}
		}
		return subExist;
	}
}