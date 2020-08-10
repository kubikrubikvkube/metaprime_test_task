package com.metaprime.testtask.structure;

import lombok.Data;

/**
 * Департамент организации
 */
@Data
public class Department {
    /**
     * Организация
     */
    private OrganizationEntity organization;

    /**
     * Головной департамент
     */
    private Department headDepartment;
}
