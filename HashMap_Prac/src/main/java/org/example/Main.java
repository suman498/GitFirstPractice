package org.example;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Hashmap Implementation in Java
        HashMap<String,Integer> empIDs=new HashMap<>();

        // Adding data in the hashmap
        empIDs.put("Ravi",1001);
        empIDs.put("Shyam", 2001);
        empIDs.put("Prasad",3001);
        empIDs.put("Puja", 4001);
        System.out.println(empIDs);

    }

}