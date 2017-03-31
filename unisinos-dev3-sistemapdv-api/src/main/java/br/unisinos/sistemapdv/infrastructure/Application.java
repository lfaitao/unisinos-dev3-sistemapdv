package br.unisinos.sistemapdv.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lfaitao on 26/03/2017.
 */
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@EntityScan(basePackages = {"br.unisinos.sistemapdv.domain.model"})
@EnableJpaRepositories("br.unisinos.sistemapdv.application.repository")
@RestController
public class Application {

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Este é o backend do SistemaPDV!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}