package com.microservice.springuserwebservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)                           //specify the exact HTTP error type to return for this Exception
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}


//Exception created when user is not found
//Normally a 200 Ok response status is returned even for users that don't exist
//to change that need to create custom exception to return appropriate error code for user not found

//Postman Response:
//        {
//        "timestamp": "2018-04-14T23:08:39.047+0000",
//        "status": 404,
//        "error": "Not Found",
//        "message": "id-50",
//        "path": "/users/50"
//        }