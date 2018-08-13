package application.services.account;

import application.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.AccountRepository;
import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

        if (money.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Money amount cannot be negative!");
        }

        if (!this.accountRepository.exists(id)){
            throw new IllegalArgumentException("No such ID!");
        }

        Account account = this.accountRepository.findOne(id);

        if (account.getBalance().compareTo(money)<0){
            throw new IllegalArgumentException("Insufficient funds!");
        }

        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

        if (money.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Money amount cannot be negative!");
        }

        if (!this.accountRepository.exists(id)){
            throw new IllegalArgumentException("No such ID!");
        }

        Account account = this.accountRepository.findOne(id);

        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);
    }
}
