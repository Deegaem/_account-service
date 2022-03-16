package org.deegaem.accountservice.boundary;

import org.deegaem.accountservice.domain.Account;
import org.deegaem.accountservice.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bf/accounts")
public class AccountsResource {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public Flux<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping({"/{accountId}"})
    public Mono<Account> getAccount(@PathVariable Long accountId) {
        return accountRepository.findById(accountId);
    }

    @PostMapping
    public Mono<Account> saveAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping({"/{accountId}"})
    public Mono<Account> putAccount(@PathVariable("accountId") Long accountId, @RequestBody Account account) {
        return accountRepository.findById(accountId).map((c) -> {
            c.setName(account.getName());
            c.setEmail(account.getEmail());
            c.setUsername(account.getUsername());
            c.setPassword(account.getPassword());
            return c;
        }).flatMap(c -> accountRepository.save(c));
    }

    @DeleteMapping({"/{accountId}"})
    public Mono<Void> deleteAccount(@PathVariable("accountId") Long accountId) {
        return accountRepository.deleteById(accountId);
    }
}
