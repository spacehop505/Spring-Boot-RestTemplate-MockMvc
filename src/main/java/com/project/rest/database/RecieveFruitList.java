package com.project.rest.database;

import com.project.rest.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecieveFruitList {


    private List<Fruit> myDataList = new ArrayList<>();

    // CREATE
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
