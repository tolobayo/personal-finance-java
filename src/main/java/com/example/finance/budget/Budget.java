package com.example.finance.budget;

import java.time.LocalDate;

public class Budget {
    private Long id;
    private Long user_id;
    private Long catagory_id;
    private LocalDate month;
    private Double total;
    public Budget() {
    }
    public Budget(Long id, Long user_id, Long catagory_id, LocalDate month, Double total) {
        this.id = id;
        this.user_id = user_id;
        this.catagory_id = catagory_id;
        this.month = month;
        this.total = total;
    }
    public Budget(Long user_id, Long catagory_id, LocalDate month, Double total) {
        this.user_id = user_id;
        this.catagory_id = catagory_id;
        this.month = month;
        this.total = total;
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
    public LocalDate getMonth() {
        return month;
    }
    public void setMonth(LocalDate month) {
        this.month = month;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "Budget [id=" + id + ", user_id=" + user_id + ", catagory_id=" + catagory_id + ", month=" + month
                + ", total=" + total + "]";
    }

    




}
