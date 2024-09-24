package com.example.finance.spending;

import java.time.LocalDate;

public class Spending {
    private Long id;
    private Long user_id;
    private Long catagory_id;
    private Double amount_spent;
    private LocalDate date;
    public Spending() {
    }
    public Spending(Long id, Long user_id, Long catagory_id, Double amount_spent, LocalDate date) {
        this.id = id;
        this.user_id = user_id;
        this.catagory_id = catagory_id;
        this.amount_spent = amount_spent;
        this.date = date;
    }
    public Spending(Long user_id, Long catagory_id, Double amount_spent, LocalDate date) {
        this.user_id = user_id;
        this.catagory_id = catagory_id;
        this.amount_spent = amount_spent;
        this.date = date;
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
    public Long getCatagory_id() {
        return catagory_id;
    }
    public void setCatagory_id(Long catagory_id) {
        this.catagory_id = catagory_id;
    }
    public Double getAmount_spent() {
        return amount_spent;
    }
    public void setAmount_spent(Double amount_spent) {
        this.amount_spent = amount_spent;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Spending [id=" + id + ", user_id=" + user_id + ", catagory_id=" + catagory_id + ", amount_spent="
                + amount_spent + ", date=" + date + "]";
    }

    
}
