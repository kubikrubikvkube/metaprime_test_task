package entity;

import lombok.Data;

/**
 * Должность
 */
@Data
public class Position {

    /**
     * Наименование должности
     */
    private String name;

    /**
     * Краткое наименование должности
     */
    private String shortName;

    /**
     * Код должности согласно ОКПДТР
     */
    private Integer code;
}

