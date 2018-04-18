package com.microservice.springuserwebservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@ApiModel(description = "User RAF")                      //SWAGGER
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")                  //part of Java Validation API (@Valid) which was set in UserController
    @ApiModelProperty(notes = "Name should have at least 2 characters")   //SWAGGER
    private String name;

    @Past                          //part of Java Validation API (date should be in the past)
    @ApiModelProperty(notes = "Birth date should be in the past")            //SWAGGER
    private Date birthDate;

    protected User() {       //default Constructor - also can use @NoArgsConstructor annotation

    }

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s, postId=%s]", id, name, birthDate);
    }
}
