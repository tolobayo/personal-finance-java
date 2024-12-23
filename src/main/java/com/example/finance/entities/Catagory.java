package com.example.finance.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity( name = "catagory")
@Table( name = "catagory")
public class Catagory {

    @Id
    @SequenceGenerator(name = "catagory_sequence",
    sequenceName = "catagory_sequence",
    allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "catagory_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    public Catagory() {
    }


    public Catagory(User user, String name) {
        this.user = user;
        this.name = name;
    }


    public Catagory(Long id, User user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catagory [id=" + id + ", user=" + user + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catagory catagory = (Catagory) o;
        return Objects.equals(name, catagory.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    
    

}
