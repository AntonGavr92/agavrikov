package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test bank class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */

public class BankTest {
    /**
     * Тестирование метода addUser.
     */
    @Test
    public void whenBankAddUserAndBankGetUsersThenBankHasSameUser() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        int sizeMap = bank.getUsers().size();
        boolean expected = true;
        boolean result = false;

        for (User userList : bank.getUsers().keySet()) {
            if (userList.equals(user) && sizeMap == 1) {
                result = true;
            }
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода deleteUser.
     */
    @Test
    public void whenBankHas2UsersAndRemove1ThenBankHave1User() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        User user2 = new User("Ivan", "345");
        bank.addUser(user2);
        bank.deleteUser(user);

        int sizeMap = bank.getUsers().size();
        boolean expected = true;
        boolean result = true;

        for (User userList : bank.getUsers().keySet()) {
            if (userList.equals(user)) {
                result = false;
            }
        }

        if (sizeMap != 1) {
            result = false;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода addAccountToUser.
     */
    @Test
    public void whenBankHasUserAndAddUserAccountThenUserHave1Account() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        bank.addAccountToUser(user, new Account(0, "21321312321"));


        int sizeList = bank.getUserAccounts(user).size();
        boolean expected = true;
        boolean result = true;

        if (sizeList != 1) {
            result = false;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода deleteAccountFromUser.
     */
    @Test
    public void whenBankHasUserAndUserHaveAccountAndRemoveUserAccountThenUserHaveNotAccounts() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        Account account = new Account(0, "21321312321");
        bank.addAccountToUser(user, account);
        bank.deleteAccountFromUser(user, account);

        int sizeList = bank.getUserAccounts(user).size();
        boolean expected = true;
        boolean result = true;

        if (sizeList > 0) {
            result = false;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода getUserAccounts.
     */
    @Test
    public void whenBankHasUserAndUserHaveAccountThenUserHave1Account() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        Account account = new Account(0, "21321312321");
        bank.addAccountToUser(user, account);

        int sizeList = bank.getUserAccounts(user).size();
        boolean expected = true;
        boolean result = true;

        if (sizeList != 1) {
            result = false;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода transferMoney.
     */
    @Test
    public void whenUser1Transfer100ToAcountAnotherUserThenUser1AcountValueMin100AndAnotherUserAccountValuePlus100() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        Account account = new Account(200, "21321312321");
        bank.addAccountToUser(user, account);

        User user2 = new User("Ivan", "345");
        bank.addUser(user2);
        Account account2 = new Account(100, "546547567234345");
        bank.addAccountToUser(user2, account2);

        bank.transferMoney(user, account, user2, account2, 100);
        boolean expected = true;
        boolean result = true;
        if (bank.getUserAccounts(user).get(0).getValue() != 100) {
            expected = false;
        }
        if (bank.getUserAccounts(user2).get(0).getValue() != 200) {
            expected = false;
        }
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода transferMoney.
     */
    @Test
    public void whenUserDonHaveMoneyForTransactThenFalse() {
        Bank bank = new Bank();
        User user = new User("Anton", "123");
        bank.addUser(user);
        Account account = new Account(0, "21321312321");
        bank.addAccountToUser(user, account);

        User user2 = new User("Ivan", "345");
        bank.addUser(user2);
        Account account2 = new Account(100, "546547567234345");
        bank.addAccountToUser(user2, account2);

        boolean result = bank.transferMoney(user, account, user2, account2, 100);
        boolean expected = false;
        if (bank.getUserAccounts(user2).get(0).getValue() != 100) {
            expected = true;
        }
        assertThat(result, is(expected));
    }

}