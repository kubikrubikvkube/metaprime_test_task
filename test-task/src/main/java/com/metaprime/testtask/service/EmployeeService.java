package com.metaprime.testtask.service;

import com.metaprime.testtask.employee.EmployeeEntity;
import com.metaprime.testtask.exception.ValidationException;
import com.metaprime.testtask.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<EmployeeEntity> create(EmployeeEntity employee) {
        assertEmployeeHasNoId(employee);
        return employeeRepository.create(employee);
    }

    public Mono<EmployeeEntity> get(Long id) {
        return employeeRepository.get(id);
    }

    public Flux<EmployeeEntity> getAll() {
        return employeeRepository.getAll();
    }

    public Mono<EmployeeEntity> update(EmployeeEntity employee) {
        return employeeRepository.update(employee);
    }

    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    private void assertEmployeeHasNoId(EmployeeEntity employee) {
        if (employee.getId() != null && employee.getId() != 0) {
            throw new ValidationException("Employee '%s' has id '%d', no id expected", employee, employee.getId());
        }
    }


}
