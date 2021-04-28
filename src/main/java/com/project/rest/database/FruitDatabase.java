package com.project.rest.database;


import com.project.rest.models.Fruit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FruitDatabase {

    private List<Fruit> myDataList = new ArrayList<>();

    public FruitDatabase() {
    }

    // ADD TO LIST
    public void addToList(Fruit dataObj) {
        myDataList.add(dataObj);
    }

    // RETURN LIST
    public List<Fruit> getList() {
        return myDataList;
    }


    // SEARCH LIST
    public List<Fruit> searchFromList(int id) {
        List<Fruit> myDataList1 = new ArrayList<>();
        Iterator<Fruit> itr = getList().iterator();
        while (itr.hasNext()) {
            Fruit currentFruit = itr.next();
            if (currentFruit.getId() == id) {
                myDataList1.add(currentFruit);
            }
        }
        return myDataList1;
    }


    // DELETE FROM LIST
    public void deleteFromList(int id) {
        Iterator<Fruit> itr = getList().iterator();
        while (itr.hasNext()) {
            Fruit currentFruit = itr.next();
            if (currentFruit.getId() == id) {
                itr.remove();
            }
        }
    }

    // UPDATE FROM LIST
    public void updateFromList(Fruit receivedFruit) {
        Iterator<Fruit> itr = getList().iterator();
        while (itr.hasNext()) {
            Fruit currentFruit = itr.next();
            if (currentFruit.getId() == receivedFruit.getId()) {
                currentFruit.setName(receivedFruit.getName());
                currentFruit.setPrice(receivedFruit.getPrice());
                currentFruit.setQuantity(receivedFruit.getQuantity());
            }
        }
    }
}