package com.qaproject.Cookbook.service;

import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.entity.ChefNotFound;
import com.qaproject.Cookbook.repository.ChefRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {
    private ChefRep repo;
    private Object EmployeeNotFound;


    @Autowired
    public ChefService(ChefRep repo){
        super();
        this.repo=repo;
    }

    public Chef addChef(Chef chef){
        return this.repo.save(chef);
    }

    public List<Chef> getAllChefs() {
        return this.repo.findAll();
    }

    public Chef findById(long id) {
            return repo.findById(id).orElseThrow(ChefNotFound::new);
        }
    

    public Chef updateChef(long id, Chef newChef) {
        Optional<Chef> existingOptional = this.repo.findById(id);
        Chef existing = existingOptional.get();

        existing.setSpeciality(newChef.getSpeciality());
        existing.setChefName(newChef.getChefName());

        return this.repo.save(existing);
    }

    public boolean removeChef(long id) {
        // removes the entity
        this.repo.deleteById(id);
        // checks to see if it still exists
        boolean exists = this.repo.existsById(id);
        // returns true if entity no longer exists
        return !exists;
    }

}
