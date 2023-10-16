package com.example.demo;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DataApp {

    Logger logger = LoggerFactory.getLogger(DataApp.class);

     @GetMapping("/locations")
    public List<House> getAllData() {
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("db.json")){
        //pass InputStream to JSON-Library, e.g. using Jackson
            ObjectMapper mapper = new ObjectMapper();
            House[] houses = mapper.readValue(in, House[].class);
            List<House> lstHouses = Arrays.asList(houses);
            logger.debug("Houses size "+ lstHouses.size());
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
                         
                         logger.debug("selected house- " + houses[i].getId() + ", "+ houses[i].getCity());
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
