package br.unisinos.sistemapdv.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lfaitao on 26/03/2017.
 */
@SpringBootApplication
@ComponentScan
@EnableJpaRepositories("br.unisinos.sistemapdv.application.repository")
@EntityScan(basePackages = {"br.unisinos.sistemapdv.domain.model"})
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