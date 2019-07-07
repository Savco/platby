package com.miso.entity;

import com.miso.enums.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="Players")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long playerId;

    @NotNull
    private String surname;

    private String name;

    @NotNull
    private Double debt;

    @Enumerated
    private Category category;

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name="account_id")
//    private BankAccount account;

    public Player(String surname, String name, Double debt, Category category) {
        this.surname = surname;
        this.name = name;
        this.debt = debt;
        this.category = category;
    }

    public Player(){

    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Double getDebt() { return  debt;}

    public Category getCategory() { return category;}

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDebt(Double debt) {this.debt = debt;}

    public void setCategory(Category category) { this.category = category;}

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", debt=" + debt +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId) &&
                surname.equals(player.surname) &&
                name.equals(player.name);
//                        Objects.equals(account, player.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, surname, name, category);
    }
}
