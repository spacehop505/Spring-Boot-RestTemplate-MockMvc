package com.project.rest.database;

import com.project.rest.models.Fruit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiveFruitList {


    private List<Fruit> myDataList = new ArrayList<>();


    public void addToList(Fruit dataObj) {
        myDataList.add(dataObj);
    }

    public List<Fruit> getMyDataList() {
        return myDataList;
    }

    public void setMyDataList(List<Fruit> myDataList) {
        this.myDataList = myDataList;
    }



}
