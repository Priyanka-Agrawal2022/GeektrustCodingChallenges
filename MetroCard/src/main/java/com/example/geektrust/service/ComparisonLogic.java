package com.example.geektrust.service;

import java.util.Comparator;
import java.util.Map;

public class ComparisonLogic implements Comparator<String> {
    private Map<String, Integer> map;
    public ComparisonLogic(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(String o1, String o2) {
        int count1 = map.get(o1);
        int count2 = map.get(o2);

        if(count1 > count2)
            return -1;
        else if(count1 < count2)
            return 1;
        else {
            return o1.compareTo(o2);
        }
    }
}