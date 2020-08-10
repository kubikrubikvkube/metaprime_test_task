package com.metaprime.testtask.controller;

import com.metaprime.testtask.service.OrganizationService;
import com.metaprime.testtask.structure.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrganizationController {
    private final OrganizationService service;

    public OrganizationController(@Autowired OrganizationService service) {
        this.service = service;
    }

    @RequestMapping(value = {"/organization"}, method = RequestMethod.PUT)
    @ResponseStatus(CREATED)
    public Mono<OrganizationEntity> create(@RequestBody OrganizationEntity e) {
        return service.create(e);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public Mono<OrganizationEntity> findById(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public Flux<OrganizationEntity> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(OK)
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseStatus(OK)
    public Mono<OrganizationEntity> update(@RequestBody OrganizationEntity organization) {
        return service.update(organization);
    }
}
