package com.techbank.account.query.api.queries;

import com.techbank.account.query.api.dto.EqualityType;
import com.techbank.account.query.domain.AccountRepository;
import com.techbank.account.query.domain.BankAccount;
import com.techbank.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryHandler implements QueryHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BankAccount> bankAccounts = accountRepository.findAll();
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccounts.forEach(bankAccountsList::add);

        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountsByIdQuery query) {
        Optional<BankAccount> bankAccount = accountRepository.findById(query.getId());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount.get());

        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        Optional<BankAccount> bankAccount = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount.get());

        return bankAccountsList;
    }


    @Override
    public List<BaseEntity> handle(FindAccountsWithBalanceQuery query) {
        return query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());
    }
}
