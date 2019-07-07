package com.miso.DTO;

import com.miso.entity.Player;
import com.miso.enums.Category;

import java.util.Objects;

public class PlayerDTO {

    private String surname;

    private String name;

    private Double debt;

    private Category category;

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getName(){
        return  this.name;
    }

    public Double getDebt() {
        return debt;
    }

    public Category getCategory() {
        return category;
    }

    public PlayerDTO(){

    }

    public PlayerDTO(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return Objects.equals(surname, playerDTO.surname) &&
                Objects.equals(name, playerDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name);
    }
}
