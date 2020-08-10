package com.metaprime.testtask.service;

import com.metaprime.testtask.exception.ValidationException;
import com.metaprime.testtask.repository.OrganizationRepository;
import com.metaprime.testtask.structure.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public OrganizationService(@Autowired OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Mono<OrganizationEntity> create(OrganizationEntity organization) {
        return organizationRepository.create(organization);
    }

    public Mono<OrganizationEntity> get(Long id) {
        return organizationRepository.get(id);
    }

    public Flux<OrganizationEntity> getAll() {
        return organizationRepository.getAll();
    }

    public Mono<OrganizationEntity> update(OrganizationEntity employee) {
        return organizationRepository.update(employee);
    }

    public void delete(Long id) {
        organizationRepository.delete(id);
    }

    private void assertOrganizationHasNoId(OrganizationEntity organization) {
        if (organization.getId() != null && organization.getId() != 0) {
            throw new ValidationException("Organization '%s' has id '%d', no id expected", organization, organization.getId());
        }
    }
}
