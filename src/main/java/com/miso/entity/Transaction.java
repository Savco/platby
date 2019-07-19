package com.miso.entity;

import com.miso.enums.TransactionState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Transactions")
public class Transaction implements Serializable {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Double amountToPay;

    private Double paid;

    @Enumerated
    private TransactionState status;

    @ElementCollection
    private Set<Date> dates;

    public Transaction() {}

    public Transaction(Double amountToPay) {
        this.amountToPay = amountToPay;
        this.paid = 0.0d;
        this.status = TransactionState.INACTIVE;
        this.dates = new HashSet<>();
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public TransactionState getStatus() {
        return status;
    }

    public void setStatus(TransactionState status) {
        this.status = status;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(amountToPay, that.amountToPay) &&
                Objects.equals(paid, that.paid) &&
                status == that.status &&
                Objects.equals(dates, that.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amountToPay, paid, status, dates);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amountToPay=" + amountToPay +
                ", paid=" + paid +
                ", status=" + status +
                ", dates=" + dates +
                '}';
    }
}

