package com.example.finance.catagory;

public class Catagory {
    private Long id;
    private Long user_id;
    private String name;
    public Catagory() {
    }
    public Catagory(Long id, Long user_id, String name) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
    }
    public Catagory(Long user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Catagory [id=" + id + ", user_id=" + user_id + ", name=" + name + "]";
    }
    

}
