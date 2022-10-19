package com.wfis.SimpleBank.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public Account findByOwnerUserId(String userId);

    public List<Account> findAll();
}
