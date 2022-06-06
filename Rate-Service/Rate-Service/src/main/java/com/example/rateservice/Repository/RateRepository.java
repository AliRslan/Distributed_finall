package com.example.rateservice.Repository;

import com.example.rateservice.Models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findByCurrencyid(Long id);

    List<Rate> findByModifydate(String date);

    @Query(nativeQuery = true, value = "select * from rate y where y.pricesell= (select max(x.pricesell) from rate x where x.currencyid=:currency_id) and y.currencyid=:currency_id order by y.id desc limit 1")
    Rate findByPricesell(Long currency_id);
}
