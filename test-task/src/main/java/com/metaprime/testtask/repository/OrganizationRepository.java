package com.metaprime.testtask.repository;

import com.metaprime.testtask.structure.OrganizationEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrganizationRepository extends AbstractDummyRepository {
    private final Map<Long, OrganizationEntity> db = Collections.synchronizedMap(new HashMap<>());
    private final AtomicLong latestId = new AtomicLong();

    public Mono<OrganizationEntity> create(OrganizationEntity organization) {
        long latestId = this.latestId.incrementAndGet();

        Mono.just(organization).subscribe(inputOrganization -> {
            inputOrganization.setId(latestId);
            db.putIfAbsent(latestId, inputOrganization);
        });

        return Mono.justOrEmpty(db.get(latestId));
    }

    public Mono<OrganizationEntity> get(Long id) {
        return Mono.justOrEmpty(db.get(id));
    }

    public void delete(Long id) {
        db.remove(id);
    }

    public Flux<OrganizationEntity> getAll() {
        return (Flux.fromArray(db.values().toArray()).cast(OrganizationEntity.class));
    }

    public Mono<OrganizationEntity> update(OrganizationEntity organization) {
        return Mono.justOrEmpty(db.replace(organization.getId(), organization));
    }
}
