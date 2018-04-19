package com.microservice.springuserwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    @GetMapping("v1/person")    //basic versioning example with URIs
    public PersonV1 personV1() {
        return new PersonV1("John Wayne");
    }

    @GetMapping("v2/person")     //basic versioning example with URIs
    public PersonV2 personV2() {
        return new PersonV2(new Name("John", "Wayne"));
    }
//URI Pollution




    @GetMapping(value = "/person/param", params = "version=1")  //versioning using Request Parameters - http://localhost:8080/person/param?version=1
    public PersonV1 paramV1() {
        return new PersonV1("John Wayne");
    }

    @GetMapping(value = "/person/param", params = "version=2")  //versioning using Request Parameters - http://localhost:8080/person/param?version=2
    public PersonV2 paramV2() {
        return new PersonV2(new Name("John", "Wayne"));
    }
//URI Pollution




    //versioning using Header - http://localhost:8080/person/header       --> Headers --> Key - X-API-VERSION   Value - 1
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("John Wayne");
    }

    //versioning using Header - http://localhost:8080/person/header      --> Headers --> Key - X-API-VERSION   Value - 2
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("John", "Wayne"));
    }
//No URI Pollution
    //No Caching available
    //Misuse HTTP Headers
    //Can't execute in browser without special plugins




    //versioning using Produces, also called Mime Type Versioning, Content Negotiation or Accept Versioning       based on kind of output that is produced
    //http://localhost:8080/person/produces         -->  Headers --> Key - Accept   Value - application/vnd.company.app-v1+json
    @GetMapping(value = "/person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("John Wayne");
    }

    //versioning using Produces, also called Mime Type Versioning, Content Negotiation or Accept Versioning       based on kind of output that is produced
    //http://localhost:8080/person/produces         -->  Headers --> Key - Accept   Value - application/vnd.company.app-v2+json
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("John", "Wayne"));
    }
    //No URI Pollution
    //No Caching available
    //Misuse HTTP Headers
    //Can't execute in browser without special plugins
}
