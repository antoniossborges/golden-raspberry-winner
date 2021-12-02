package com.golden.goldenraspberrywinner.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Studio {
    private @Id
    @GeneratedValue
    Long id;
    private String name;

    public Studio(String name) {
        this.setName(name);
    }

    public Studio() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
