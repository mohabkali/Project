package com.qaproject.Cookbook.controller;

import com.qaproject.Cookbook.entity.Recipe;
import com.qaproject.Cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        ;
        return new ResponseEntity<>(service.addRecipe(recipe), HttpStatus.CREATED);
    }

    @GetMapping("/getRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(this.service.getAllRecipes());
    }

    @PutMapping("/updateRecipe")
    public ResponseEntity<Recipe> updateRecipe(@PathParam("id") long id, @RequestBody Recipe recipe) {
        return new ResponseEntity<>(this.service.updateRecipe(id, recipe),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteRecipe/{id}")
    public ResponseEntity<Recipe> removeRecipe(@PathVariable long id) {
        return this.service.removeRecipe(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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


