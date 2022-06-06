package com.example.calculatorservice.Controllers;

import com.example.calculatorservice.Models.Calculate;
import com.example.calculatorservice.Models.Currency;

import com.example.calculatorservice.Models.Rate;
import com.example.calculatorservice.Repositorys.CurrencyRepository;
import com.example.calculatorservice.Repositorys.RateRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {


    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    RateRepository rateRepository;
@Autowired
    RestTemplate restTemplate;
    @PostMapping("/buy")
    public Double buy(@RequestBody Calculate model) {
        if (model.getAmount() != null && rateRepository.findByPricby(model.getId()) != null) {
            String  s=    restTemplate.getForObject("http://active/active/check", String.class);
            assert s != null;
            if (s.equals("active"))
            return rateRepository.findByPricby(model.getId()).getPrice_sell() * model.getAmount();

            else return 0.0;
        }
        else return 0.0;
    }

    @PostMapping("/sell")
    public Object sell(@RequestBody Calculate model) {
        if (model.getAmount() != null && rateRepository.findByPricesell(model.getId()) != null) {
            //Rate rate=rateRepository.findByPricesell(model.getId());
            //System.out.println(model.getId());
            //System.out.println(rate);
            //System.out.println(rate.getPrice_sell());
            //double get= rate.getPrice_sell();
            return rateRepository.findByPricesell(model.getId()).getPrice_buy() * model.getAmount();
        }
        else return null;
    }
    @GetMapping("/a")
    public void get(){

    }



    //////////////////////////////////////////////////////////////////A->B
@HystrixCommand(fallbackMethod = "currency_all_fallback")
    @GetMapping("/currency/all")
    public Currency[] currency_all() {
     return    restTemplate.getForObject("http://currenciesa/currency/list", Currency[].class);
    }

    public Currency[] currency_all_fallback() {


        return null;
    }
    //////////////////////////////////////////////////////////////A->B->C
    @HystrixCommand(fallbackMethod = "t1")
    @GetMapping("/calculate-max-sell-for-currency/{id}")
    public Rate max_sell(@PathVariable Integer id) {
//        http://localhost:6000/currency/currensy-max-sell/
        return restTemplate.getForObject("http://currenciesa/currency/currensy-max-sell/"+id, Rate.class);
    }




    public Rate t1(@PathVariable Integer id) {
       return null;
    }

    //////////////////////////////////////////////////////////A->B then A->C
   @HystrixCommand(fallbackMethod = "notfound")
    @GetMapping("/check_and_list/{id}")
    public Rate[] currency_rate(@PathVariable Integer id) {

        Currency c = restTemplate.getForObject("http://currenciesa/currency/details/"+id, Currency.class);

       if(c.getId()==null){
    System.out.println("not found currency");
          return null;
      }
      else if(c.getId()!=null) {
           return restTemplate.getForObject("http://rate/rate/all-rate-for-currency/" + id, Rate[].class);
       }
      return null;

   }
        public Rate[] notfound(@PathVariable Integer id){
            System.out.println("not found");
    return null;
  }


    @HystrixCommand(fallbackMethod = "nooo")
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {

        Currency c = restTemplate.getForObject("http://currenciesa/currency/details/"+id, Currency.class);

        if(c.getId() == null){
            System.out.println("no thing to do");
           return "no thing to do";
        }

        else if(c.getId()!=null) {
        String q = restTemplate.getForObject("http://rate/rate/delete-all-rate-for-currency/"+ id, String.class);
            String c1 = restTemplate.getForObject("http://currenciesa/currency/delete/"+ id, String.class);
              return "the delete is done";
        }

        return null;
    }
    public String nooo(@PathVariable Long id){
        return "nooo";
    }




}

