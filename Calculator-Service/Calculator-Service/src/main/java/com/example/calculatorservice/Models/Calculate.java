package com.example.calculatorservice.Models;

import javax.persistence.*;

@Entity
public class Calculate {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double amount;
    public Calculate() {

    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calculate(Double amount, Long id) {
        this.amount = amount;
        this.id = id;
    }
}
