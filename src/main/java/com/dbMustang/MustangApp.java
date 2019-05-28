package com.dbMustang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.dbMustang"})
@EnableTransactionManagement
@EnableCaching
public class MustangApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(MustangApp.class,args);
    }
}
