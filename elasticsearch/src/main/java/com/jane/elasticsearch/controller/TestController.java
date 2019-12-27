package com.jane.elasticsearch.controller;

import com.jane.elasticsearch.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jianggc at 2019/12/25.
 */
//@RestController
public class TestController {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

//    public TestController(ElasticsearchOperations elasticsearchOperations) {
//        this.elasticsearchOperations = elasticsearchOperations;
//    }

    @PostMapping("/person")
    public String save(@RequestBody Person person) {
        System.out.println("elasticsearchOperations:"+elasticsearchOperations);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery);
        return documentId;
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  String id) {
        Person person = elasticsearchOperations
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }
}
