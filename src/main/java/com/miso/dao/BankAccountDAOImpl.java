package com.miso.dao;

import com.miso.entity.BankAccount;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class BankAccountDAOImpl implements BankAccountDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public void create(BankAccount account) {
        em.persist(account);
    }

    @Override
    public void update(BankAccount account) {
        em.merge(account);
    }

    @Override
    public void delete(BankAccount account) throws IllegalArgumentException {
        em.remove(account);
    }

    @Override
    public BankAccount findById(Long id) {
        return em.find(BankAccount.class, id);
    }
}
