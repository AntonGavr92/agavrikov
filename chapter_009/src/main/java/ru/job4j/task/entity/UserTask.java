package ru.job4j.task.entity;

import java.util.List;

/**
 * Class описывающий пользователя.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class UserTask {
    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Идентификатор пользователя.
     */
    private int id;

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Login пользователя.
     */
    private String login;

    /**
     * Password пользователя.
     */
    private String password;

    /**
     * Дата создания пользователя.
     */
    private long createDate;

    /**
     * Идентификатор роли пользователя.
     */
    private Role role;

    /**
     * Поле для хранения значения роли администратора.
     */
    private final static String ADMIN_ROLE_STR = "ADMIN";

    /**
     * Поле для хранения музыкальных типов.
     */
    private List<MusicType> musicTypes;

    /**
     * Поле для хранения адреса.
     */
    private Address adress;

    /**
     * Конструктор для инициализации.
     * @param name имя
     * @param id идентификатор
     * @param email почта
     * @param login логин
     * @param password пароль
     * @param createDate дата создания пользователя
     * @param role роль
     * @param musicTypes музыкальные типы
     * @param adress адрес
     */
    public UserTask(String name, int id, String email, String login, String password, long createDate, Role role, List<MusicType> musicTypes, Address adress) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.role = role;
        this.musicTypes = musicTypes;
        this.adress = adress;
    }

    /**
     * Конструктор для инициализации.
     * @param name имя
     * @param id идентификатор
     * @param email почта
     * @param login логин
     * @param password пароль
     * @param createDate дата создания пользователя
     * @param role роль
     * @param adress адрес
     */
    public UserTask(String name, int id, String email, String login, String password, long createDate, Role role, Address adress) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.role = role;
        this.adress = adress;
    }

    /**
     * Конструктор для инициализации.
     * @param name имя
     * @param email почта
     * @param login логин
     * @param password пароль
     * @param role роль
     * @param musicTypes музыкальные типы
     * @param adress адрес
     */
    public UserTask(String name, String email, String login, String password, Role role, Address adress, List<MusicType> musicTypes) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.adress = adress;
        this.musicTypes = musicTypes;
    }

    /**
     * Сеттер музыкальных типов.
     * @param musicTypes музыкальные типы
     */
    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    /**
     * Геттер музыкальных типов.
     * @return музыкальные типы
     */
    public List<MusicType> getMusicTypes() {
        return this.musicTypes;
    }

    /**
     * Геттер роли.
     * @return роль
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Геттер адреса
     * @return адрес
     */
    public Address getAdress() {
        return this.adress;
    }

    /**
     * Геттер пароля.
     * @return пароля пользователя.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Геттер имени.
     * @return имя пользователя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер почты.
     * @return почта пользователя.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Геттер логина.
     * @return логин пользователя.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Геттер даты создания пользователя.
     * @return дата создания пользователя.
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Геттер идентификатора пользователя.
     * @return идентификатор пользователя.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Сеттер идентификатора пользователя.
     * @param id идентификатор пользователя.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод для проверки пользователя на наличие прав администратора.
     * @return true - если пользователь администтратор, иначе - false
     */
    public boolean isAdmin() {
        boolean result = false;
        if (ADMIN_ROLE_STR.equals(this.getRole().getName())) {
            result = true;
        }
        return result;
    }
}
