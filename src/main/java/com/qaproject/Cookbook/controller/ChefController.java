package com.qaproject.Cookbook.controller;

import com.qaproject.Cookbook.entity.Chef;
import com.qaproject.Cookbook.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Chef> addChef(@RequestBody Chef chef) {
        return new ResponseEntity<>(service.addChef(chef), HttpStatus.CREATED);
    }

    @GetMapping("/getChefs")
    public ResponseEntity<List<Chef>> getAllChefs() {
        return ResponseEntity.ok(this.service.getAllChefs());
    }

    @PutMapping("/updateChef")
    public ResponseEntity<Chef> updateChef(@PathParam("id") long id, @RequestBody Chef newChef) {
            return new ResponseEntity<>(service.updateChef(id,newChef),HttpStatus.ACCEPTED);
        }

    @DeleteMapping("/deleteChef/{id}")
    public ResponseEntity<Chef> removeChef(@PathVariable long id) {

        return this.service.removeChef(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}