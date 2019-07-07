package com.miso.dao;

import com.miso.entity.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Transaction transaction) {
        em.persist(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        em.merge(transaction);
    }

    @Override
    public void delete(Transaction transaction) throws IllegalArgumentException {
        em.remove(transaction);
    }

    @Override
    public Transaction findById(Long id) {
        return em.find(Transaction.class, id);
    }

    @Override
    public List<Transaction> findCreatedBetween(Date fromDate, Date toDate) {
        TypedQuery<Transaction> query = em
                .createQuery(
                        "SELECT t " +
                                "FROM Transactions t " +
                                "WHERE p.creationDate BETWEEN :startDate AND :endDate",
                        Transaction.class);
        query.setParameter("startDate", fromDate);
        query.setParameter("endDate", toDate);
        return query.getResultList();
    }
}
