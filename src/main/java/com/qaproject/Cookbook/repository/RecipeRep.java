package com.qaproject.Cookbook.repository;

import com.qaproject.Cookbook.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRep extends JpaRepository<Recipe,Long> {

    public List<Recipe> findRecipeByChefName(String chefName);

    public List<Recipe> findRecipeByCuisine(String cuisine);
}
