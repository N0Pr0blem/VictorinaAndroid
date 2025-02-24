package com.laba.viktorina.utils;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {
    public static List<Integer> generate(int count,int size){
        if(count>size) throw new IllegalArgumentException("Size should be bigger or equals than count");
        List<Integer> result = new ArrayList<>();
        int randomIndex;

        while(result.size()!=count){
            randomIndex = (int)(Math.random()*size);
            if(!result.contains(randomIndex)){
                result.add(randomIndex);
            }
        }

        return result;
    }
}
