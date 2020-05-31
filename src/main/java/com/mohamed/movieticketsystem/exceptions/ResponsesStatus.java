package com.mohamed.movieticketsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class ResponsesStatus  {


    public  ResponsesStatus(Status status){
        ResponseEntity responseEntity;
        switch(status){

            case OK :
                responseEntity=new ResponseEntity("Ok", HttpStatus.OK);break;
            case FOUND:
                responseEntity= new ResponseEntity("Resource found",HttpStatus.FOUND);break;
            case CREATE :
                responseEntity=new ResponseEntity("Object created",HttpStatus.CREATED);break;
            case NOT_FOUND :
                responseEntity=new ResponseEntity("Object not Found",HttpStatus.NOT_FOUND);break;
            case NO_CONTENT:
                responseEntity=new ResponseEntity("Object not Content",HttpStatus.NO_CONTENT);break;
            case CONFLICT:
                responseEntity=new ResponseEntity("Conflict",HttpStatus.CONFLICT);break;
                default:
                throw new IllegalStateException("Unexpected value: " + status);
        }

    }



}
