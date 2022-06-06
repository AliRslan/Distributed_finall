package com.example.rateservice.Controllers;

import com.example.rateservice.Models.Currency;
import com.example.rateservice.Models.Rate;
import com.example.rateservice.Repository.RateRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rate")
public class RateController {

@Autowired
    RestTemplate restTemplate;

    @Autowired
    RateRepository rateRepository;
    @PostMapping("/add")
    public Rate add(@RequestBody Rate c) {
        Rate r = new Rate(null,c.getPrice_sell(),c.getPrice_buy(),c.getModify_date(),c.getCurrency_id());
        try {
            r = rateRepository.save(r);
        } catch (Exception e) {

        }
        return r;
    }
    ///////////////////////////////////////////////
    @GetMapping("/delete/{id}")
    public Boolean remove(@PathVariable Long id) {
        try {
            rateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //////////////////////////////////////////////
    @GetMapping("/details/{id}")
    public Rate listById(@PathVariable Long id) {


        try {

            return rateRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
    //////////////////////////////////////////////
    @GetMapping("/list")
    public Iterable<Rate> listAll() {
        try {
           // Iterable<Rate> c = rateRepository.findAll();
            return  rateRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }


//    @GetMapping("/list1")
//    public Iterable<Rate> listAll1() {
//        try {
//            // Iterable<Rate> c = rateRepository.findAll();
//            return  rateRepository.findAll();
//        } catch (Exception e) {
//            return null;
//        }
//    }


    //////////////////////////////////////////////
    @GetMapping("/rate-max-sell-for-currency/{id}")
    public Rate max(@PathVariable   Integer id) {


        try {

            return rateRepository.findByPricesell(Long.valueOf(id));
        } catch (Exception e) {
            return null;
        }
    }
//find_currency
    @GetMapping("/all-rate-for-currency/{id}")
    public Rate[] byCurrency(@PathVariable Long id) {


        try {
            List<Rate> res = rateRepository.findByCurrencyid(id);
            return res.toArray(new Rate[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @HystrixCommand(fallbackMethod = "not_find")
//    find
    @GetMapping("/find-currency/{id}")
    public Currency find(@PathVariable Long id) {

        return    restTemplate.getForObject("http://currenciesa/currency/details/"+id, Currency.class);

    }
         public  Currency not_find(Long id){
        return null;
}



    @GetMapping("/delete-all-rate-for-currency/{id}")
    public String delete_by_currency(@PathVariable Long id) {


        try {
            List<Rate> res = rateRepository.findByCurrencyid(id);
            for (Rate rate : res) {
                rateRepository.deleteById(rate.getId());
            }
            return "All Rates Deleted";
        } catch (Exception e) {
            return "Exception in Deleting Rates";
        }
    }



}
