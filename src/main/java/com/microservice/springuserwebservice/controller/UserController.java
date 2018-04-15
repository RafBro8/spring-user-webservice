package com.microservice.springuserwebservice.controller;

import com.microservice.springuserwebservice.exception.UserNotFoundException;
import com.microservice.springuserwebservice.model.User;
import com.microservice.springuserwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired     //this is dependency
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {

        return userService.findAll();

    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = userService.findOne(id);        //add local variable User user
        if (user == null)
            throw new UserNotFoundException("id-" + id);


//use HATEOAS here - provide link to all users - "all-users", Server_PATH + "/users" - Hardcoded way (might brake if users change)
        //Resource is Hateoas
        //ControllerLinkBuilder helps specify for which method to implement Hateoas, in this example retrieveAllUsers()
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(controllerLinkBuilder.withRel("all-users"));   //provide link to all users in retrieveUser() method


        return resource;         //return resource instead of previous return user, also change method signature from public User to public Resource<User>


        //to test - http://localhost:8080/users/1






    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {                     //can do public void to return empty 200 response when user deleted or use ResponseEntity.noContent
        User user = userService.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user ) {  //User user - map Post request to user     @Valid is Java Validation API used to validate content
        //input - details of user
        //output - CREATED HTTP Response & Return the created URI
        //@RequestBody - whatever is created in the Post method gets mapped to User parameter
        //when using Post to create User the id is created by the system, so don't include id
        //Use Postman to create new Post request (Body, raw, JSON(application/json) format)

        User savedUser = userService.save(user);

        //Postman 200 OK Output
//
//        {
//            "id": 4,
//            "name": "Raf",
//            "birthDate": "2005-04-14T22:11:48.948+0000"
//        }

        //append {id} to URI
        //current Request Uri
        ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        //use ResponseEntity<T>.created(location) to return HTTP Response code Status: 201 Created
        return ResponseEntity.created(URI.create("http://localhost:8080/users/4")).build();

    }
}


//HATEOAS - Hypermedia As The Engine Of Application State - provides additional things such as links, information, etc