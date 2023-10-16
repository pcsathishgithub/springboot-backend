package com.example.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DataApp {

     @GetMapping("/location")
    public List<House> getAllData() {
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("db.json")){
        //pass InputStream to JSON-Library, e.g. using Jackson
            ObjectMapper mapper = new ObjectMapper();
            House[] houses = mapper.readValue(in, House[].class);
            List<House> lstHouses = Arrays.asList(houses);
            System.out.println(lstHouses);
            return lstHouses;
           
        }
        catch(Exception e){
        throw new RuntimeException(e);
        }        
    }

    @GetMapping("/location/{id}")
    public House getData(@PathVariable(required = false) String id) {
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("db.json")){
        //pass InputStream to JSON-Library, e.g. using Jackson
            ObjectMapper mapper = new ObjectMapper();
            House[] houses = mapper.readValue(in, House[].class);
            if (id !=null ) {
                for (int i=0; i<houses.length;i++) {
                    if (houses[i].getId().equals(id)) {
                         
                         System.out.println(houses[i].getId() + ", "+ houses[i].getCity());
                        return houses[i];
                    }
                }
            }
            return null;
           
        }
        catch(Exception e){
        throw new RuntimeException(e);
        }        
    }
}
