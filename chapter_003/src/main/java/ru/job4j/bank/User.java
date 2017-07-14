package ru.job4j.bank;

/**
 * Класс для описания пользователя банка.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class User {

    /**
     * Поле для хранения имени пользователя.
     */
    private String name;

    /**
     * Поле для хранения удостоверения пользователя.
     */
    private String pasport;

    /**
     * Конструктор для инициализации полей пользователя.
     * @param name - имя пользователя.
     * @param pasport - удостоверение пользователя.
     */
    public User(String name, String pasport) {
        this.name = name;
        this.pasport = pasport;
    }

    /**
     * Геттер имени пользователя.
     * @return имя пользователя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер удостоверения пользователя.
     * @return удостоверение пользователя
     */
    public String getPasport() {
        return this.pasport;
    }

    /**
     * Переопределение метода equals.
     * @param o объект для сравнения.
     * @return true в случе если объекты одинаковые, false - если нет
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        return pasport.equals(user.pasport);
    }

    /**
     * Переопределение метода hashCode.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pasport.hashCode();
        return result;
    }

}
