package com.miso.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="BankAccounts")
public class BankAccount {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long accountId;

    @NotNull
    private String number;

    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    private Set<Player> playerSet;

    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    private Set<Transaction> transactionSet;

    public BankAccount(){}

    public BankAccount(@NotNull String number, @NotNull Set<Player> playerSet, @NotNull Set<Transaction> transactionSet) {
        this.number = number;
        this.playerSet = playerSet;
        this.transactionSet = transactionSet;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getNumber() {
        return number;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(playerSet, that.playerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, number, playerSet);
    }
}
