package com.example.greetingapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    /*
     Create a Java web rest controller for a web application containing a hello endpoint,
     which takes a String parameter called name and append this parameter
     to the String "Hello", using &ldquo;World&rdquo; as the name default value
    */
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name;
    }



}
