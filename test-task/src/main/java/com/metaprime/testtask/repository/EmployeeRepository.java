package com.metaprime.testtask.repository;

import com.metaprime.testtask.employee.EmployeeEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class EmployeeRepository extends AbstractDummyRepository {
    private final Map<Long, EmployeeEntity> db = Collections.synchronizedMap(new HashMap<>());
    private final AtomicLong latestId = new AtomicLong();

    public Mono<EmployeeEntity> create(EmployeeEntity employee) {
        long latestId = this.latestId.incrementAndGet();

        Mono.just(employee).subscribe(inputEmployee -> {
            inputEmployee.setId(latestId);
            db.putIfAbsent(latestId, inputEmployee);
        });

        return Mono.justOrEmpty(db.get(latestId));
    }

    public Mono<EmployeeEntity> get(Long id) {
        return Mono.justOrEmpty(db.get(id));
    }

    public void delete(Long id) {
        db.remove(id);
    }

    public Flux<EmployeeEntity> getAll() {
        return (Flux.fromArray(db.values().toArray()).cast(EmployeeEntity.class));
    }

    public Mono<EmployeeEntity> update(EmployeeEntity employee) {
        return Mono.justOrEmpty(db.replace(employee.getId(), employee));
    }

    public void deleteAll() {
        Mono.fromFuture(CompletableFuture.runAsync(() -> {
            db.clear();
            latestId.set(0);
        }))
                .subscribe(e -> System.out.println("Deleted all employees"));
    }


}
