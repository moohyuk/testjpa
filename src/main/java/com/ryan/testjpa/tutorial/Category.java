package com.ryan.testjpa.tutorial;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;

    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDel;

}
