package com.qaproject.Cookbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String recipeName;

    @Column
    private String cuisine;

    @Column
    private String categoryType;
    // Starter, Main or Dessert

    @Column
    private String ingredients;

    @Column
    private String method;

    @Column
    private String chefName;

}



