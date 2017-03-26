package br.unisinos.sistemapdv.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
@SpringBootApplication
@ComponentScan
@EnableJpaRepositories("br.unisinos.sistemapdv.application.repository")
@EntityScan(basePackages = {"br.unisinos.sistemapdv.domain.model"})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}