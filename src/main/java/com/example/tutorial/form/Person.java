package com.example.tutorial.form;

import uk.q3c.krail.persist.KrailEntity;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person implements KrailEntity<Long, Integer> {

    @Min(0)
    @Max(150)
    private int age;
    @Size(min = 3)
    private String firstName;
    @Id
    private Long id;

    @Size(min = 3)
    private String lastName;
    @Version
    private Integer version;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}