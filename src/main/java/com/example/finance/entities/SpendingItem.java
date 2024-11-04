package com.example.finance.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "Spending")
@Table(name = "spending")
public class SpendingItem {
    @Id
    @SequenceGenerator(name = "spending_item_sequence",
    sequenceName = "spending_item_sequence",
    allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "spending_item_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false )
    private User user;

    //Note: each spending item can only have one catagory
    //TODO: create default value if catagory not set
    @ManyToOne
    @JoinColumn(name = "catagory_id", nullable = false)
    private Catagory catagory;

    @Column(
        name = "label",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String label;

    @Column(
        name = "amount_spent",
        nullable = false,
        columnDefinition = "NUMERIC(10, 2)"
    )
    private Double amountSpent;

    @Column(
        name = "date",
        nullable = false,
        columnDefinition = "DATE"
    )
    private LocalDate date;
    
    public SpendingItem() {
    }

    public SpendingItem(Long id, User user, Catagory catagory, String label, Double amountSpent, LocalDate date) {
        this.id = id;
        this.user = user;
        this.catagory = catagory;
        this.label = label;
        this.amountSpent = amountSpent;
        this.date = date;
    }

    public SpendingItem(User user, Catagory catagory, String label, Double amountSpent, LocalDate date) {
        this.user = user;
        this.catagory = catagory;
        this.label = label;
        this.amountSpent = amountSpent;
        this.date = date;
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

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Spending [id=" + id + ", user=" + user + ", catagory=" + catagory + ", label=" + label
                + ", amountSpent=" + amountSpent + ", date=" + date + "]";
    }

    
    

    
}
