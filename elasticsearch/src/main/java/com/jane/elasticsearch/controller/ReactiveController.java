package com.jane.elasticsearch.controller;

import com.jane.elasticsearch.model.Person;
import com.jane.elasticsearch.repository.ReactivePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Created by jianggc at 2019/12/27.
 */
@RestController
public class ReactiveController {

    @Autowired
    ReactivePersonRepository repository;

    @RequestMapping("/testReactive")
    public String testReactive() {
        Flux<Person> persons = repository.findAll();
        System.out.println(persons.blockFirst());
        Mono<List<Person>> listMono = persons.collectList();
        System.out.println("listMono:"+ listMono);
        List<Person> personList = listMono.block();
        System.out.println("personList:"+personList);
        return persons.toString();
    }
}
