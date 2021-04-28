package com.project.rest.controller;

import com.project.rest.database.FruitDatabase;
import com.project.rest.database.RecieveFruitList;
import com.project.rest.models.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


//@Slf4j
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
    public void getAllFruitObject() {
        RecieveFruitList recieveFruitList = restTemplate.getForObject("http://localhost:8080/server/fruits", RecieveFruitList.class);
        System.out.println(recieveFruitList.getMyDataList());
        // log.info("[Student: name: {}, surname: {}, age: {}]", student.getName(), student.getSurname(), student.getAge());
    }

    // CLIENT GET DATA BY ID LIST<FRUIT>
    @GetMapping(value = "/client/fruits/{id}")
    public void getByIdFruitObject(@PathVariable int id) {
        RecieveFruitList recieveFruitList = restTemplate.getForObject("http://localhost:8080/server/fruit/" + id + " ", RecieveFruitList.class);
        System.out.println(recieveFruitList.getMyDataList());
        // log.info("[Student: name: {}, surname: {}, age: {}]", student.getName(), student.getSurname(), student.getAge());
    }

    // CLIENT PUT LIST<FRUIT>
    @PutMapping(value = "/client/create/fruit/{id}")
    public void putFruit(@PathVariable int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") double price, @RequestParam(value = "quantity") int quantity) {
        Fruit fruit = new Fruit(id, name, price, quantity);
        restTemplate.put("http://localhost:8080/server/create/fruit/" + id + " ", fruit);
    }

    // CLIENT POST LIST<FRUIT>
    @PutMapping(value = "/client/update/fruit/{id}")
    public void postFruit(@PathVariable int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") double price, @RequestParam(value = "quantity") int quantity) {
        Fruit fruit = new Fruit(id, name, price, quantity);
        restTemplate.postForLocation("http://localhost:8080/server/update/fruit/" + id + " ", fruit);
    }

    // CLIENT DELETE DATA BY ID LIST<FRUIT>
    @GetMapping(value = "/client/delete/fruit/{id}")
    public void deleteFruit(@PathVariable int id) {
       restTemplate.delete("http://localhost:8080/server/delete/fruit/" + id + " ");
    }

}
