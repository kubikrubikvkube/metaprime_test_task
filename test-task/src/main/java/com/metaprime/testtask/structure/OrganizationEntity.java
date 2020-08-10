package com.metaprime.testtask.structure;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class OrganizationEntity {
    /**
     * Идентификационный номер организации
     */
    private Long id;
    /**
     * Наименование организации
     */
    private String name;

    /**
     * Юридическое/физическое лицо
     */
    private OrganizationType organizationType;

    /**
     * Наименование полное
     */
    private String nameFull;

    /**
     * Наименование сокращенное
     */
    private String nameShort;

    /**
     * ИНН
     */
    private Long inn;

    /**
     * КПП
     */
    private Long kpp;

    /**
     * ОГРН
     */
    private Long ogrn;

    /**
     * Список департаментов
     */
    private List<Department> departments;
}
