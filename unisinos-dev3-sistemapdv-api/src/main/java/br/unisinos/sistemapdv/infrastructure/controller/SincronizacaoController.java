package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CredencialRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class SincronizacaoController {

    @Autowired
    private CredencialRepository credencialRepository;

    @CrossOrigin("*")
    @GetMapping("/sinc")
    @ResponseBody
    public int get() {
        int r = 0;
        /*SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("show slave status");
        ArrayList<Object[]> results = (ArrayList<Object[]>)query.list();
        Object [] result =  results.get(0);
        //Seconds Behind Master should always be the last value in the row.
        String secondsBehindMaster = ""+result[result.length-1];
        */
        return r;

    }

}
