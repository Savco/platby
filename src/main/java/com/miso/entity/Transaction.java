package com.miso.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

//    @ManyToOne
//    @JoinColumn(name="account_id")
//    private BankAccount account;

    private Double amount;

    private Date date;

    public Transaction() {}

    public Transaction(Double amount, Date date) {
 //       this.account = account;
        this.amount = amount;
        this.date = date;
    }

    public Long getTransactionId() {
        return transactionId;
    }

//    public BankAccount getBankAccount() {
//        return account;
//    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

//    public void setBankAccout(BankAccount accout) {
//        this.account = account;
//    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, date);
    }
}

