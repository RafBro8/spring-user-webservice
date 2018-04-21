package com.microservice.springuserwebservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

    @Id               //Primary Key
    @GeneratedValue
    private Integer id;


    private String description;


    // fetch = FetchType.LAZY - means unless you call it(Post.getUser), it will not try to fetch data
    @ManyToOne(fetch = FetchType.LAZY)      //Multiple Posts can belong to Single user
    @JsonIgnore  //only want details of Post not User, both Post User could create Recursive Loop, so ignore User
    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

//primary key (id)
//foreign key (user_id)

//SELECT * FROM POST;
//ID  	DESCRIPTION  	USER_ID
