package ru.job4j.bank;

import java.util.*;

/**
 * Класс для описания работы банка.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class Bank {

    /**
     * Поле, для хранения данных по пользователям банка.
     */
    private Map<User, List<Account>> users = new HashMap<User, List<Account>>();

    /**
     * Метод для добавления пользователя в систему банка.
     * @param user пользователь
     */
    public void addUser(User user) {
        this.users.put(user, new LinkedList<>());
    }

    /**
     * Метод для удаления пользователя из системы банка.
     * @param user пользователь
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Метод для добавления пользователю счета.
     * @param user пользователь
     * @param account счет
     */
    public void addAccountToUser(User user, Account account) {
        List userAccounts = this.users.get(user);
        userAccounts.add(account);
        this.users.put(user, userAccounts);
    }

    /**
     * Геттер для получения активных пользователей банка.
     * @return список пользователей и счетов.
     */
    public Map<User, List<Account>> getUsers() {
        return this.users;
    }

    /**
     * Метод для удаления у пользователя счета.
     * @param user пользователь
     * @param account счет
     */
    public void deleteAccountFromUser(User user, Account account) {
        List userAccounts = this.users.get(user);
        userAccounts.remove(account);
        this.users.put(user, userAccounts);
    }

    /**
     * Метод для получения счетов пользователя.
     * @param user пользователь
     * @return списко счетов пользователя
     */
    public List<Account> getUserAccounts(User user) {
        return this.users.get(user);
    }

    /**
     * Метод для перевода средств с одного счета пользователя на другой счет пользователя.
     * @param srcUser пользователь производящий перевод
     * @param srcAccount счет с которого необходимо списать средства
     * @param dstUser пользователь принимающий перевод
     * @param dstAccount счет на который необходимо добавить средства
     * @param amount количество средств
     * @return true в случае успешного проведения операции, false - если операция отменена.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean operationSuccess = true;
        double srcVal = srcAccount.getValue();
        if (srcVal < amount) {
            operationSuccess = false;
        }
        if (operationSuccess) {
            operationSuccess = false;
            for (Account account : users.get(srcUser)) {
                if (account.getRequisites().equals(srcAccount.getRequisites())) {
                    account.setValue(account.getValue() - amount);
                    operationSuccess = true;
                }
            }
            if (operationSuccess) {
                operationSuccess = false;
                for (Account account : users.get(dstUser)) {
                    if (account.getRequisites().equals(dstAccount.getRequisites())) {
                        account.setValue(account.getValue() + amount);
                        operationSuccess = true;
                    }
                }
            } else { //Возврат денежных средств на первый счет, если второй не обнаружен
                for (Account account : users.get(srcUser)) {
                    if (account.getRequisites().equals(srcAccount.getRequisites())) {
                        account.setValue(account.getValue() + amount);
                        operationSuccess = true;
                    }
                }
            }
        }
        return operationSuccess;
    }

}
