package com.example.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.models.Product;
import com.example.repositories.ProductRepository;


@Configuration
public class Database {
    //Logger
    private static final Logger logger= LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception{
                Product productA = new Product("Macbook",2020,2400.0,"");
                Product productB = new Product("Ipad", 2022, 2500.0, "");
                logger.info("insert data:"+productRepository.save(productA));
                logger.info("insert data:"+productRepository.save(productB));  
            }
        };
    }
}
