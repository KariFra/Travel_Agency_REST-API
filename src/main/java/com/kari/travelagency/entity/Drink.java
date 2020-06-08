package com.kari.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String recipe;

    @Override
    public String toString() {
        return "DrinkDto{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }

    public Drink(String name, String recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}
