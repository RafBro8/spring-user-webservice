package com.microservice.springuserwebservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}


//Controller - handling HTTP Requests
//GET - HTTP method -
//URI - /hello-world
//Map GET Request to URI - @RequestMapping(method = RequestMethod.GET, path = "/hello-world") or use @GetMapping(path = "/hello-world")
//Method return - "Hello World

//@PathVariable String name maps to /{name}
