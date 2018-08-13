package application;


import application.models.Account;
import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import application.services.account.AccountServiceImpl;
import application.services.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner( UserServiceImpl userService, AccountServiceImpl accountService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception{
        User example = new User();
        example.setUserName("Vlado");
        example.setAge(20);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(example);

        example.setAccounts(Collections.singletonList(account));
        System.out.println();
        userService.registerUser(example);

        accountService.withdrawMoney(new BigDecimal(20000),account.getId());
        accountService.transferMoney(new BigDecimal(50000),1L);


    }
}
