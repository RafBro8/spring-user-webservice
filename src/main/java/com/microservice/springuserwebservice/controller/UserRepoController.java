package com.microservice.springuserwebservice.controller;

import com.microservice.springuserwebservice.entity.Post;
import com.microservice.springuserwebservice.entity.User;
import com.microservice.springuserwebservice.exception.UserNotFoundException;
import com.microservice.springuserwebservice.repository.PostRepository;
import com.microservice.springuserwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRepoController {             //this will be calling UserRepository


    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();

    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional <User> user = userRepository.findById(id);        //add local variable UserEntity user
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);


//use HATEOAS here - provide link to all users - "all-users", Server_PATH + "/users" - Hardcoded way (might brake if users change)
        //Resource is Hateoas
        //ControllerLinkBuilder helps specify for which method to implement Hateoas, in this example retrieveAllUsers()
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(controllerLinkBuilder.withRel("all-users"));   //provide link to all users in retrieveUser() method

        return resource;         //return resource instead of previous return user, also change method signature from public UserEntity to public Resource<UserEntity>

        //to test - http://localhost:8080/jpa/users/1
    }

    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {                     //can do public void to return empty 200 response when user deleted or use ResponseEntity.noContent
        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user ) {  //UserEntity user - map Post request to user     @Valid is Java Validation API used to validate content
        //input - details of user
        //output - CREATED HTTP Response & Return the created URI
        //@RequestBody - whatever is created in the Post method gets mapped to UserEntity parameter
        //when using Post to create UserEntity the id is created by the system, so don't include id
        //Use Postman to create new Post request (Body, raw, JSON(application/json) format)

        User savedUser = userRepository.save(user);

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
        return ResponseEntity.created(URI.create("http://localhost:8080/jpa/users/4")).build();

    }


    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("id-" + id);

        return userOptional.get().getPosts();

    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
