package org.jamayoral.minsait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MinsaitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinsaitApplication.class, args);
    }

}
