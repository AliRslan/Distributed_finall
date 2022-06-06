package com.example.rateservice.Models;



import javax.persistence.*;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double pricesell;
    private Double pricebuy;
    private String modifydate;
    private Long currencyid;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice_sell() {
        return pricesell;
    }

    public void setPrice_sell(Double pricesell) {
        this.pricesell = pricesell;
    }

    public Double getPrice_buy() {
        return pricebuy;
    }

    public void setPrice_buy(Double pricebuy) {
        this.pricebuy = pricebuy;
    }

    public String getModify_date() {
        return modifydate;
    }

    public void setModify_date(String modifydate) {
        this.modifydate = modifydate;
    }

    public Long getCurrency_id() {
        return currencyid;
    }

    public void setCurrency_id(Long currencyid) {
        this.currencyid = currencyid;
    }

    public Rate(Long id, Double pricesell, Double pricebuy, String modifydate, Long currencyid) {
        this.id = id;
        this.pricesell = pricesell;
        this.pricebuy = pricebuy;
        this.modifydate = modifydate;
        this.currencyid = currencyid;
    }

    public Rate(Double pricesell, Double pricebuy, String modifydate, Long currencyid) {
        this.pricesell = pricesell;
        this.pricebuy = pricebuy;
        this.modifydate = modifydate;
        this.currencyid = currencyid;
    }

    public Rate() {
    }
}
