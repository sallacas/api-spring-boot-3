package com.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {
    @GetMapping("/GetList")
    public bodyResponse getList(){
        return new bodyResponse(
                "1", List.of("SNK","Naruto"),
                new Person("Johnatan","Casallas",20)
        );
    }

    record Person(String name, String lastname, int age){}
    record bodyResponse(
            String id,
            List<String> animes,
            Person person
    ){}
}
