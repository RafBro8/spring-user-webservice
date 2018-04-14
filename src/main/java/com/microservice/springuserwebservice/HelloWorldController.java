package com.microservice.springuserwebservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloWorldController {

    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")        //in this case no need to specify GET method like above
    public String helloWorld() {
        return "Hello World";

    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")                   //to test go to http://localhost:8080/hello-world/path-variable/Rafal   Rafal is name
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}


//Controller - handling HTTP Requests
//GET - HTTP method -
//URI - /hello-world
//Map GET Request to URI - @RequestMapping(method = RequestMethod.GET, path = "/hello-world") or use @GetMapping(path = "/hello-world")
//Method return - "Hello World

//@PathVariable String name maps to /{name}
