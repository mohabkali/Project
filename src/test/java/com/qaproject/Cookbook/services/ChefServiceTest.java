package com.qaproject.Cookbook.services;

import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.repository.ChefRep;
import com.qaproject.Cookbook.service.ChefService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChefServiceTest {

    @MockBean
    private ChefRep  repo;

    @Autowired
    private ChefService service;

    @Test
    void testAddChef(){
        Chef testChef = new Chef(1,"Mohab","Italian");
        when(repo.save(testChef)).thenReturn(testChef);
        assertThat(service.addChef(testChef)).isEqualTo(testChef);
        verify(repo, times(1)).save(testChef);


    }

    @Test
    void testGetAllChefs(){
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertThat(service.getAllChefs()).isEqualTo(new ArrayList<>());
        verify(repo, times(1)).findAll();

    }

    @Test
    void testRemoveChef() {
        when(repo.existsById((long) 1)).thenReturn(false);
        assertThat(service.removeChef((long) 1)).isEqualTo(true);
        verify(repo, times(1)).deleteById((long) 1);
        verify(repo, times(1)).existsById((long) 1);
    }

    @Test
    void testUpdateChef(){
        Chef testChef1 = new Chef(1,"Mohab","Morrocan");
        Chef testChef2 = new Chef(1,"Mohab","Italian");
        when(repo.findById((long)1)).thenReturn(java.util.Optional.of(testChef1));
        when(repo.save(testChef2)).thenReturn(testChef2);
        assertThat(service.updateChef((long) 1,testChef2)).isEqualTo(testChef2);
        verify(repo, times(1)).save(testChef2);

    }



}
