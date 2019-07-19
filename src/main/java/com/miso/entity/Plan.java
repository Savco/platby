package com.miso.entity;

import com.miso.enums.PlanType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="Plans")
public class Plan implements Serializable {

    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private PlanType planType;

    @OneToMany(cascade=CascadeType.ALL)
    //@JoinColumn(name = "transaction_id")
    private List<Transaction> transactions;

    public Plan(){ }

    public  Plan(PlanType planType){
        List<Transaction> transactions = new ArrayList<>();
        Transaction january = new Transaction();
        Transaction others = new Transaction();

        this.planType = planType;
        switch (planType){
            case STANDARD:
                transactions.add(new Transaction(30.0d));
                for (int i=2; i<=12; i++) {
                    transactions.add(new Transaction(40.0d));
                }
                break;
            case REDUCED:
                transactions.add(new Transaction(20.0d));
                for (int i=2; i<=12; i++) {
                    transactions.add(new Transaction(30.0d));
                }
                break;
        }
        this.transactions = transactions;

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(planId, plan.planId) &&
                planType == plan.planType &&
                Objects.equals(transactions, plan.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, planType, transactions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String plan = "Plan{\" +\n" +
                "                \"planId=\" + planId +\n" +
                "                \", planType=\" + planType +\n" +
                "                '}' + \" with transaction:\\n\")";
        sb.append(plan);
        for(Transaction t : transactions){
            sb.append(t);
        }
        return  sb.toString();
    }

    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction jan;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction feb;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction mar;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction apr;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction may;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction jun;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction jul;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction aug;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction sep;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction oct;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction now;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    private Transaction dec;
//
//    public Plan(){
//    }
//
//    public Transaction getJan() {
//        return jan;
//    }
//
//    public void setJan(Transaction jan) {
//        this.jan = jan;
//    }
//
//    public Transaction getFeb() {
//        return feb;
//    }
//
//    public void setFeb(Transaction feb) {
//        this.feb = feb;
//    }
//
//    public Transaction getMar() {
//        return mar;
//    }
//
//    public void setMar(Transaction mar) {
//        this.mar = mar;
//    }
//
//    public Transaction getApr() {
//        return apr;
//    }
//
//    public void setApr(Transaction apr) {
//        this.apr = apr;
//    }
//
//    public Transaction getMay() {
//        return may;
//    }
//
//    public void setMay(Transaction may) {
//        this.may = may;
//    }
//
//    public Transaction getJun() {
//        return jun;
//    }
//
//    public void setJun(Transaction jun) {
//        this.jun = jun;
//    }
//
//    public Transaction getJul() {
//        return jul;
//    }
//
//    public void setJul(Transaction jul) {
//        this.jul = jul;
//    }
//
//    public Transaction getAug() {
//        return aug;
//    }
//
//    public void setAug(Transaction aug) {
//        this.aug = aug;
//    }
//
//    public Transaction getSep() {
//        return sep;
//    }
//
//    public void setSep(Transaction sep) {
//        this.sep = sep;
//    }
//
//    public Transaction getOct() {
//        return oct;
//    }
//
//    public void setOct(Transaction oct) {
//        this.oct = oct;
//    }
//
//    public Transaction getNow() {
//        return now;
//    }
//
//    public void setNow(Transaction now) {
//        this.now = now;
//    }
//
//    public Transaction getDec() {
//        return dec;
//    }
//
//    public void setDec(Transaction dec) {
//        this.dec = dec;
//    }
}
