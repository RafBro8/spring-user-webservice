package com.microservice.springuserwebservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "UserEntity RAF")                      //SWAGGER
@Entity
public class User {

   @Id
   @GeneratedValue
   private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")                  //part of Java Validation API (@Valid) which was set in UserController
    @ApiModelProperty(notes = "Name should have at least 2 characters")   //SWAGGER
    private String name;

    @Past                          //part of Java Validation API (date should be in the past)
    @ApiModelProperty(notes = "Birth date should be in the past")            //SWAGGER
   private Date birthDate;


    @OneToMany(mappedBy = "user")   //Single User can have multiple Posts,    (mappedBy = "user") - which Entity owns the Relationship - User Entity
    private List<Post> posts;



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




    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }



    @Override
   public String toString() {
       return String.format("UserEntity [id=%s, name=%s, birthDate=%s, postId=%s]", id, name, birthDate);
   }
}
//primary key (id)
//foreign key (user_id)

//SELECT * FROM POST;
//ID  	DESCRIPTION  	USER_ID