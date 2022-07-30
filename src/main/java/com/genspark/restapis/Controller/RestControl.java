package com.genspark.restapis.Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestControl {

    @RequestMapping("/hello-world")
    public String helloWorld(){
        return "hello world";
    }


    @RequestMapping("/hello-world-bean")
    public helloWorldBean helloWorldBean(){
        return new helloWorldBean("Hello World");
    }

    @RequestMapping("/hello-world-path-param/{name}/message/{message}")
    public helloWorldBean helloWorldPathParam
            (@PathVariable String name, @PathVariable String message){
        return new helloWorldBean("Hello, " + name + " , " + message);
    }
}
