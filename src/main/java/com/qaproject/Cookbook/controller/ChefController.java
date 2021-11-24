package com.qaproject.Cookbook.controller;

import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ChefController {

    private ChefService service;

    @Autowired
    public ChefController(ChefService service) {
        super();
        this.service = service;
    }

    @PostMapping("/newChef")
    public Chef addChef(@RequestBody Chef chef) {
        ;
        return service.addChef(chef);
    }

    @GetMapping("/getChefs")
    public List<Chef> getAllChefs() {
        return this.service.getAllChefs();
    }

    @PutMapping("/updateChef")
    public Chef updateChef(@PathParam("id") long id, @RequestBody Chef chef) {
        return this.service.updateChef(id, chef);
    }

    @DeleteMapping("/deleteChef/{id}")
    public boolean removeAccount(@PathVariable long id) {
        return this.service.removeAccount(id);
    }

}