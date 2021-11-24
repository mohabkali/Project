package com.qaproject.Cookbook.service;

import com.qaproject.Cookbook.entity.Recipe;
import com.qaproject.Cookbook.repository.RecipeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private  RecipeRep repo;

    @Autowired
    public RecipeService(RecipeRep repo){
        super();
        this.repo=repo;
    }

    public Recipe addRecipe(Recipe recipe){
        return this.repo.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return this.repo.findAll();
    }

    public List<Recipe> findByChefName(String chefName) {
        return repo.findRecipeByChefName(chefName);
    }

    public List<Recipe> findByCuisine(String cuisine) {
        return repo.findRecipeByCuisine(cuisine);
    }

    public Recipe updateRecipe(long id, Recipe newRecipe) {
        Optional<Recipe> existingOptional = this.repo.findById(id);
        Recipe existing = existingOptional.get();

        existing.setCuisine(newRecipe.getCuisine());
        existing.setRecipeName(newRecipe.getRecipeName());
        existing.setCategoryType(newRecipe.getCategoryType());
        existing.setIngredients(newRecipe.getIngredients());
        existing.setMethod(newRecipe.getMethod());
        existing.setChefName(newRecipe.getChefName());

        return this.repo.save(existing);
    }

    public boolean removeRecipe(long id) {
        // removes the entity
        this.repo.deleteById(id);
        // checks to see if it still exists
        boolean exists = this.repo.existsById(id);
        // returns true if entity no longer exists
        return !exists;
    }
}