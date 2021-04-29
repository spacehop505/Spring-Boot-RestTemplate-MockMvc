package com.project.rest.controller;

import com.project.rest.database.ReceiveFruitList;
import com.project.rest.models.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@RestController
public class FruitClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    // CLIENT GET ALL DATA LIST<FRUIT>
    @GetMapping(value = "/client/fruits")
    public List<Fruit> getAllFruitObject() {
        ReceiveFruitList receiveFruitList = restTemplate.getForObject("http://localhost:8080/server/fruits", ReceiveFruitList.class);
        log.info("[CLIENT] [@GET] - Received from [SERVER] ArrayList: " + receiveFruitList.getMyDataList());
        return receiveFruitList.getMyDataList();
    }

    // CLIENT GET DATA BY ID LIST<FRUIT>
    @GetMapping(value = "/client/fruit/{id}")
    public List<Fruit> getByIdFruitObject(@PathVariable int id) {
        ReceiveFruitList receiveFruitList = restTemplate.getForObject("http://localhost:8080/server/fruit/" + id + " ", ReceiveFruitList.class);
        log.info("[CLIENT] [@GET] - Received from [SERVER] Search ArrayList: " + receiveFruitList.getMyDataList());
        return receiveFruitList.getMyDataList();
    }

    // CLIENT PUT LIST<FRUIT>
    @PostMapping(value = "/client/create/fruit/{id}")
    public void putFruit(@PathVariable int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") double price, @RequestParam(value = "quantity") int quantity) {
        Fruit fruit = new Fruit(id, name, price, quantity);
        restTemplate.postForLocation("http://localhost:8080/server/create/fruit/" + id + " ", fruit);
        log.info("[CLIENT] [@PUT] - Sent [SERVER] to add: " + fruit);
    }

    // CLIENT POST LIST<FRUIT>
    @PutMapping(value = "/client/update/fruit/{id}")
    public void postFruit(@PathVariable int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") double price, @RequestParam(value = "quantity") int quantity) {
        Fruit fruit = new Fruit(id, name, price, quantity);
        restTemplate.put("http://localhost:8080/server/update/fruit/" + id + " ", fruit);
        log.info("[CLIENT] [@POST] - Sent [SERVER] to update: " + fruit);
    }

    // CLIENT DELETE DATA BY ID LIST<FRUIT>
    @DeleteMapping(value = "/client/delete/fruit/{id}")
    public void deleteFruit(@PathVariable int id) {
        restTemplate.delete("http://localhost:8080/server/delete/fruit/" + id + " ");
        log.info("[CLIENT] [@DELETE] - Sent [SERVER] id to delete: " + id);
    }

}
