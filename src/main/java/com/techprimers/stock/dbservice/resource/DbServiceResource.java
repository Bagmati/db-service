package com.techprimers.stock.dbservice.resource;


import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
//This returns all the quotes available with given username

    //@Autowired
    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @RequestMapping(value ="/getall" )
    public List<Quote> getQuotes(){
        return  quotesRepository.findAll();

    }


    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public List<String> getQuotes(@PathVariable("user") String userName) {


        return quotesRepository.findByUserName(userName)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
        //return null;

    }

    @PostMapping("/add")

    public void addQuotes(@RequestBody final Quote quotes) {

        quotesRepository.save(quotes);

    }
}
