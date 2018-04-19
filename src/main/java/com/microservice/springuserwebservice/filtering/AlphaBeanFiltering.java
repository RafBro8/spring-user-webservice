package com.microservice.springuserwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//Static filtering - @JsonIgnoreProperties, @JsonIgnore

//@JsonIgnoreProperties(value = {"field1", "field3"})           //hide/filter multiple fields at once
@JsonFilter("AlphaBeanFiltering")    //Dynamic Filtering
public class AlphaBeanFiltering {

    private String field1;   //does not show in Postman output now
    private String field2;
    private String field3;   //does not show in Postman output now

    //@JsonIgnore                 //hide/filter single fields
    private String field4;        //does not show in Postman output now


    public AlphaBeanFiltering(String field1, String field2, String field3, String field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }
}
