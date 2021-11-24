package com.qaproject.Cookbook.controller;

import com.qaproject.Cookbook.entity.Recipe;
import com.qaproject.Cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class RecipeController {

    private RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        super();
        this.service = service;
    }

    @PostMapping("/newRecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        ;
        return service.addRecipe(recipe);
    }

    @GetMapping("/getRecipes")
    public List<Recipe> getAllRecipes() {
        return this.service.getAllRecipes();
    }

    @PutMapping("/updateRecipe")
    public Recipe updateRecipe(@PathParam("id") long id, @RequestBody Recipe recipe) {
        return this.service.updateRecipe(id, recipe);
    }

    @DeleteMapping("/deleteRecipe/{id}")
    public boolean removeRecipe(@PathVariable long id) {
        return this.service.removeRecipe(id);
    }


    @GetMapping("/readByChefName/{chefName}")
    public List<Recipe> readChefName(@PathVariable String chefName) {
        return service.findByChefName(chefName);
    }

    @GetMapping("/readByCuisine/{cuisine}")
    public List<Recipe> readCuisine(@PathVariable String cuisine) {
        return service.findByCuisine(cuisine);
    }
}


