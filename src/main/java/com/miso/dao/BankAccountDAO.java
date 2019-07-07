package com.miso.dao;

import com.miso.entity.BankAccount;

public interface BankAccountDAO {

    /**
     * Add new bank account
     * @param account is new bank account
     */
    public void create(BankAccount account);

    /**
     * Update bank account
     * @param account is bank account to be updated
     */
    public void update(BankAccount account);

    /**
     * delete method is used for delete account according to account id in table bank_accounts
     *
     * @param account is bank account to be deleted
     * @throws IllegalArgumentException
     *             when there is null.
     */
    public void delete(BankAccount account) throws IllegalArgumentException;

    /**
     * Returns information about a account corresponding to given account id.
     *
     * @param id is the account id.
     */
    public BankAccount findById(Long id);
}
