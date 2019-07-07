package com.miso.dao;


import com.miso.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionDAO {

    /**
     * Add new transaction
     * @param transaction is new transaction
     */
    public void create(Transaction transaction);

    /**
     * Update player
     * @param transaction is transaction to be updated
     */
    public void update(Transaction transaction);

    /**
     * delete method is used for delete transaction according to transaction id in table transactions
     *
     * @param transaction is transactionto be deleted
     * @throws IllegalArgumentException
     *             when there is null.
     */
    public void delete(Transaction transaction) throws IllegalArgumentException;

    /**
     * Returns information about a transaction corresponding to given transaction id.
     *
     * @param id is the transaction id.
     */
    public Transaction findById(Long id);

    /**
     * Returns transactions beetween fromDate and toDate
     *
     * @param fromDate is the date from to start searching (inclusive).
     * @param toDate is the date to end searching (inclusive).
     */
    public List<Transaction> findCreatedBetween(Date fromDate, Date toDate);
}
