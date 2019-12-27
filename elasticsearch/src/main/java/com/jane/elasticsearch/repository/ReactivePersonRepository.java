package com.jane.elasticsearch.repository;

import com.jane.elasticsearch.model.Person;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jianggc at 2019/12/27.
 */
public interface ReactivePersonRepository extends ReactiveSortingRepository<Person, String> {

    Flux<Person> findByFirstname(String firstname);

    Flux<Person> findByFirstname(Publisher<String> firstname);

    Flux<Person> findByFirstnameOrderByLastname(String firstname);

    Flux<Person> findByFirstname(String firstname, Sort sort);

    Flux<Person> findByFirstname(String firstname, Pageable page);

    Mono<Person> findByFirstnameAndLastname(String firstname, String lastname);

    Mono<Person> findFirstByLastname(String lastname);

    @Query("{ \"bool\" : { \"must\" : { \"term\" : { \"lastname\" : \"?0\" } } } }")
    Flux<Person> findByLastname(String lastname);

    Mono<Long> countByFirstname(String firstname);

    Mono<Boolean> existsByFirstname(String firstname);

    Mono<Long> deleteByFirstname(String firstname);
}
