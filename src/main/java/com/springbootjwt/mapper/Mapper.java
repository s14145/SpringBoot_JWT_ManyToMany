package com.springbootjwt.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapper {

    public static String mapToJsonString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch(JsonProcessingException ex){
            log.error("Unable to convert java object to JSON.");
            ex.printStackTrace();
        }
        return null;
    }
}
