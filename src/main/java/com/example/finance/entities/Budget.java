package com.example.finance.entities;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity( name = "Budget")
@Table( name = "budget")
public class Budget {

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //TODO: Intialize catagoryLimits to new Hashmap
    @ElementCollection
    @CollectionTable(name = "budget_catagory_limits", joinColumns = @JoinColumn(name = "budget_id"))
    @MapKeyJoinColumn(name = "catagory_id") // Assuming Catagory is an entity
    @Column(name = "spending_limit_amount", columnDefinition = "NUMERIC(10,2)")
    private Map<Catagory,Double> catagoryLimits;


    //TODO: Users can only create one budget per month
    @Column(
        name = "date",
        nullable = false,
        columnDefinition = "DATE"
    )
    private LocalDate date;

    //Note: constraint for this total is that is has to be greater than or equal to the catagories total
    //TODO: Debating on whether I want this to be a calculated value based on the defined catagories
    @Column(
        name = "total",
        nullable = false,
        columnDefinition = "NUMERIC(10,2)"
    )
    private Double total;

    public Budget() {
    }

    public Budget(Long id, User user, Map<Catagory, Double> catagoryLimits, LocalDate date, Double total) {
        this.id = id;
        this.user = user;
        this.catagoryLimits = catagoryLimits;
        this.date = date;
        this.total = total;
    }

    public Budget(User user, Map<Catagory, Double> catagoryLimits, LocalDate date, Double total) {
        this.user = user;
        this.catagoryLimits = catagoryLimits;
        this.date = date;
        this.total = total;
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

    public Map<Catagory, Double> getCatagoryLimits() {
        return catagoryLimits;
    }

    public void setCatagoryLimits(Map<Catagory, Double> catagoryLimits) {
        this.catagoryLimits = catagoryLimits;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Budget [id=" + id + ", user=" + user + ", catagoryLimits=" + catagoryLimits + ", date=" + date
                + ", total=" + total + "]";
    }

    
    
    

}
