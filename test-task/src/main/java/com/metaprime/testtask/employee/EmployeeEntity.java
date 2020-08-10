package com.metaprime.testtask.employee;

import com.metaprime.testtask.structure.Department;
import com.metaprime.testtask.structure.OrganizationEntity;
import lombok.Data;

/**
 * Сотрудник
 */
@Data
public class EmployeeEntity {

    /**
     * Идентификационный номер сотрудника
     */
    private Long id;

    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Тип сотрудника
     */
    private EmployeeType employeeType;

    /**
     * Департамент
     */
    private Department department;

    /**
     * Организация
     */
    private OrganizationEntity organization;
}
