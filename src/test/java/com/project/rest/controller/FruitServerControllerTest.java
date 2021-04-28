package com.project.rest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rest.app.MyRestApplication;
import com.project.rest.database.FruitDatabase;
import com.project.rest.models.Fruit;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {MyRestApplication.class})
class FruitServerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FruitDatabase userService;

    // SERVER GET
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/server/fruits")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // GET ID
    @Test
    public void testGetId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/server/fruit/{id}", "1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(jsonPath("name").value("1"));
    }


    // POST
    @Test
    public void testPost() throws Exception {
        Fruit fruit = new Fruit(1, "apple", 33, 4);
        mockMvc.perform(MockMvcRequestBuilders.post("/server/update/fruit/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonFruit(fruit)))
                .andExpect(status().isOk());
    }


    // PUT
    @Test
    public void testPut() throws Exception {
        Fruit fruit = new Fruit(1, "orange", 33, 4);
        mockMvc.perform(MockMvcRequestBuilders.put("/server/create/fruit/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonFruit(fruit)))
                .andExpect(status().isOk());
    }

    private String toJsonFruit(Fruit fruitObj) throws Exception {
        return new ObjectMapper().writeValueAsString(fruitObj);
    }


    // DELETE
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/server/delete/fruit/{id}", "1").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id", "1")).andExpect(status().isOk());
    }


}