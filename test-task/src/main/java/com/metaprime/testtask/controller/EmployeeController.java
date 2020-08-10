package com.metaprime.testtask.controller;

import com.metaprime.testtask.employee.EmployeeEntity;
import com.metaprime.testtask.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = {"/employee"}, method = RequestMethod.PUT)
    @ResponseStatus(CREATED)
    public Mono<EmployeeEntity> create(@RequestBody EmployeeEntity e) {
        return employeeService.create(e);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Mono<EmployeeEntity> findById(@PathVariable("id") Long id) {
        return employeeService.get(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Flux<EmployeeEntity> getAll() {
        return employeeService.getAll();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(OK)
    public void deleteById(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseStatus(OK)
    public Mono<EmployeeEntity> update(@RequestBody EmployeeEntity employee) {
        return employeeService.update(employee);
    }


}