package com.project.rest.controller;

import com.project.rest.database.FruitDatabase;
import com.project.rest.database.ReceiveFruitList;
import com.project.rest.models.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FruitServerController {


    @Autowired
    FruitDatabase fruitDatabase;


    @GetMapping(value = "/test")
    public String home() {
        return "test";
    }

    // SERVER GET ALL LIST<FRUIT>
    @GetMapping(value = "/server/fruits", produces = "application/json")
    public ReceiveFruitList sendFruitList() {
        ReceiveFruitList receiveFruitList = new ReceiveFruitList();
        receiveFruitList.setMyDataList(fruitDatabase.getList());
        log.info("[SERVER] [@GET] - Sending All ArrayList [CLIENT]: " + receiveFruitList.getMyDataList());
        return receiveFruitList;
    }

    // SERVER GET BY ID LIST<FRUIT>
    @GetMapping(value = "/server/fruit/{id}")
    public ReceiveFruitList sendFruitListByID(@PathVariable int id) {
        ReceiveFruitList receiveFruitList = new ReceiveFruitList();
        receiveFruitList.setMyDataList(fruitDatabase.searchFromList(id));
        log.info("[SERVER] [@GET] - Sending Searched ArrayList [CLIENT]: " + receiveFruitList.getMyDataList());
        return receiveFruitList;
    }

    // SERVER POST LIST<FRUIT>
    @PostMapping(value = "/server/create/fruit/{id}")
    public void putReceivedFruit(@RequestBody Fruit fruit) {
        log.info("[SERVER] [@PUT] - Received from [CLIENT] add new: " + fruit);
        fruitDatabase.addToList(fruit);
    }

    // SERVER PUT LIST<FRUIT>
    @PutMapping(value = "/server/update/fruit/{id}")
    public void postReceivedFruit(@RequestBody Fruit fruit) {
        log.info("[SERVER] [@POST] - Received from [CLIENT] update: " + fruit);
        fruitDatabase.updateFromList(fruit);
    }

    // SERVER DELETE LIST<FRUIT>
    @DeleteMapping(value = "/server/delete/fruit/{id}")
    public void deleteReceivedFruit(@PathVariable int id) {
        fruitDatabase.deleteFromList(id);
        log.info("[SERVER] [@DELETE] - Received from [CLIENT] delete: " + id);
    }

}
