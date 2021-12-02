package com.golden.goldenraspberrywinner.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Movie {

    private @Id @GeneratedValue Long id;

    private String name;

    private Integer year;

    private boolean winner;

    @OneToOne
    private Producer producer;

    @OneToOne
    private Studio studio;

    public Movie(String name, Integer year, boolean winner, Producer producer, Studio studio){
        this.setName(name);
        this.setYear(year);
        this.setWinner(winner);
        this.setProducer(producer);
        this.setStudio(studio);
    }

    public Movie() {}

    public Integer getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
