package com.qaproject.Cookbook.services;

import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.entity.Recipe;
import com.qaproject.Cookbook.repository.RecipeRep;
import com.qaproject.Cookbook.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class RecipeServiceTest {

    @MockBean
    private RecipeRep repo;

    @Autowired
    private RecipeService service;

    @Test
    void testAddRecipe() {
        Recipe testRecipe = new Recipe(1, "Samosa", "Indian", "Starter", "Filling and Dough", "frying", "Mohab");
        when(repo.save(testRecipe)).thenReturn(testRecipe);
        assertThat(service.addRecipe(testRecipe)).isEqualTo(testRecipe);
        verify(repo, times(1)).save(testRecipe);
    }

    @Test
    void testGetAllRecipes() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThat(service.getAllRecipes()).isEqualTo(new ArrayList<>());
        verify(repo, times(1)).findAll();

    }

    @Test
    void testRemoveRecipe() {
        when(repo.existsById((long) 1)).thenReturn(false);
        assertThat(service.removeRecipe((long) 1)).isEqualTo(true);
        verify(repo, times(1)).deleteById((long) 1);
        verify(repo, times(1)).existsById((long) 1);
    }

    @Test
    void testUpdateRecipe() {
        Recipe testRecipe1 = new Recipe(1, "Samosa", "Indian", "Starter", "Filling and Dough", "frying", "Mohab");
        Recipe testRecipe2 = new Recipe(1, "Samosa", "Indian", "Starter", "Filling and Dough", "bake", "Mohab");
        when(repo.findById((long) 1)).thenReturn(java.util.Optional.of(testRecipe1));
        when(repo.save(testRecipe2)).thenReturn(testRecipe2);
        assertThat(service.updateRecipe((long) 1, testRecipe2)).isEqualTo(testRecipe2);
        verify(repo, times(1)).save(testRecipe2);
    }

}