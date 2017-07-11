package ru.job4j;

/**
 * Class отвечающий за валидацию данных, введенных пользователем.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Метод для обратки ввода для массива.
     * @param question - вопрос пользователю
     * @param range - длинна массива
     * @return
     */
    @Override
    public int ask(String question, int[] range) {
        int value = -1;
        boolean invalid = true;
        while (invalid) {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                this.print("Please select key from menu items.");
            } catch (NumberFormatException nfe) {
                this.print("Please enter validate data.");
            }
        }
        return value;
    }
}
