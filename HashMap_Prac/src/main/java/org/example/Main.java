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
        empIDs.put("Priya", 4001);

        System.out.println(empIDs);

        //Search Using key
        System.out.println("Employee ID of Prasad is "+empIDs.get("Prasad"));

        //See if specific key is preset in hashmap
        System.out.println("Is vivek present in this list --> "+empIDs.containsKey("Vivek"));

        //See if specific key present using its value
        System.out.println("Is Employee ID 4001 present in List --> "+empIDs.containsValue(4001));



    }

}