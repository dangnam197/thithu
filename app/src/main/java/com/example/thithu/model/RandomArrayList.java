package com.example.thithu.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArrayList<T> {
    private Gson gson;
    public RandomArrayList() {

    }
    public ArrayList<T> random(ArrayList<T> list){
        Random random = new Random();
        ArrayList<T> listRandom = new ArrayList<>();
        int i;
        while(true){
            if(list.size()<=2){
                break;
            }
            System.out.println(list.size());
            i = random.nextInt(list.size());
            listRandom.add(list.get(i));
            list.remove(i);

        }
        return listRandom;
    }
}
