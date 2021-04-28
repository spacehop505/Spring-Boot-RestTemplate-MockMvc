package com.project.rest.controller;

import com.project.rest.database.FruitDatabase;
import com.project.rest.database.RecieveFruitList;
import com.project.rest.models.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitServerController {


    @Autowired
    FruitDatabase fruitDatabase = new FruitDatabase();


    @GetMapping(value = "/test")
    public String home() {
        return "test";
    }

    // SERVER GET ALL LIST<FRUIT>
    @GetMapping(value = "/server/fruits")
    public RecieveFruitList sendFruitList() {
        RecieveFruitList recieveFruitList = new RecieveFruitList();
        recieveFruitList.setMyDataList(fruitDatabase.getList());
        System.out.println("sendFruitList()");
        return recieveFruitList;
    }

    // SERVER GET BY ID LIST<FRUIT>
    @GetMapping(value = "/server/fruit/{id}")
    public RecieveFruitList sendFruitListByID(@PathVariable int id) {
        RecieveFruitList recieveFruitList = new RecieveFruitList();
        recieveFruitList.setMyDataList(fruitDatabase.searchFromList(id));
        return recieveFruitList;
    }

    // SERVER PUT LIST<FRUIT>
    @PutMapping(value = "/server/create/fruit/{id}")
    public void putReceivedFruit(@RequestBody Fruit fruit) {
        System.out.println(fruit);
        fruitDatabase.addToList(fruit);
    }

    // SERVER POST LIST<FRUIT>
    @PostMapping(value = "/server/update/fruit/{id}")
    public void postReceivedFruit(@RequestBody Fruit fruit) {
        System.out.println(fruit);
        fruitDatabase.updateFromList(fruit);
    }

    // SERVER DELETE LIST<FRUIT>
    @DeleteMapping(value = "/server/delete/fruit/{id}")
    public void deleteReceivedFruit(@PathVariable int id) {
        fruitDatabase.deleteFromList(id);
    }

}
