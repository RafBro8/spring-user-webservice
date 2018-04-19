package com.microservice.springuserwebservice.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")         //DYNAMIC FILTERING with MappingJacksonValue, FilterProvider
    public MappingJacksonValue retrieveAlphaBeanFiltering() {
        AlphaBeanFiltering alphaBeanFiltering = new AlphaBeanFiltering("value1", "value2", "value3", "value4");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("AlphaBeanFiltering", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(alphaBeanFiltering);
        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public List<AlphaBeanFiltering> retrieveListOfAlphaBeanFiltering() {
         List<AlphaBeanFiltering> list = Arrays.asList( new AlphaBeanFiltering("value1", "value2", "value3", "value4"),
                new AlphaBeanFiltering("value12", "value22", "value34", "value45"));


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("AlphaBeanFiltering", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(filters);


        return list;
    }
    }

